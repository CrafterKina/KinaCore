package com.mods.kina.KinaCore.movelib.autoswitch;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Mouse;

public class AutoSwitch{
    private boolean isClicked = false;
    private int beforeSpot = 0;

    public AutoSwitch(){
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onClicked(TickEvent.ClientTickEvent e){
        World world = Minecraft.getMinecraft().theWorld;
        if(world != null){
            if(!isClicked && Mouse.isButtonDown(0) && Minecraft.getMinecraft().inGameHasFocus){
                isClicked = true;
                beforeSpot = Minecraft.getMinecraft().thePlayer.inventory.currentItem;
                MovingObjectPosition touchObject = Minecraft.getMinecraft().objectMouseOver;
                if(touchObject != null){
                    switch(touchObject.typeOfHit){
                        case BLOCK:
                            block(world.getBlockState(touchObject.func_178782_a()), Minecraft.getMinecraft().thePlayer);
                            break;
                        case ENTITY:
                            entity(Minecraft.getMinecraft().thePlayer);
                            break;
                        default:
                            break;
                    }
                }
            }else{
                if(isClicked) Minecraft.getMinecraft().thePlayer.inventory.currentItem = beforeSpot;
                isClicked = false;
            }
        }
    }

    private void block(IBlockState state, EntityPlayer player){
        int harvestLevel = state.getBlock().getHarvestLevel(state);
        int ecoItem = -1;
        int i = 0;
        for(; i < 9; i++){
            if(player.inventory.getStackInSlot(i) == null) continue;
            int currentLevel = player.inventory.getStackInSlot(i).getItem().getHarvestLevel(player.inventory.getStackInSlot(i), state.getBlock().getHarvestTool(state));
            int ecoLevel = ecoItem == -1 ? -1 : player.inventory.getStackInSlot(ecoItem).getItem().getHarvestLevel(player.inventory.getStackInSlot(ecoItem), state.getBlock().getHarvestTool(state));
            if(currentLevel >= harvestLevel && (ecoItem == -1 || ecoLevel > currentLevel)){
                ecoItem = i;
                ecoLevel = ecoItem == -1 ? -1 : player.inventory.getStackInSlot(ecoItem).getItem().getHarvestLevel(player.inventory.getStackInSlot(ecoItem), state.getBlock().getHarvestTool(state));
                if(ecoLevel == harvestLevel) break;
            }
        }
        player.inventory.currentItem = ecoItem;
    }

    private void entity(EntityPlayer player){
        float damageLevel = 0;
        float currentLevel;
        int ecoItem = -1;
        int i = 0;
        for(; i < 9; i++){
            if(player.inventory.getStackInSlot(i) == null || !(player.inventory.getStackInSlot(i).getItem() instanceof ItemTool) && !(player.inventory.getStackInSlot(i).getItem() instanceof ItemSword))
                continue;
            if(player.inventory.getStackInSlot(i).getItem() instanceof ItemTool){
                ItemTool tool = (ItemTool) player.inventory.getStackInSlot(i).getItem();
                currentLevel = tool.getToolMaterial().getDamageVsEntity();
            }else if(player.inventory.getStackInSlot(i).getItem() instanceof ItemSword){
                ItemSword sword = (ItemSword) player.inventory.getStackInSlot(i).getItem();
                currentLevel = sword.func_150931_i();
            }else{
                currentLevel = 0;
            }
            if(currentLevel > damageLevel){
                ecoItem = i;
            }
        }
        player.inventory.currentItem = ecoItem;
    }
}
