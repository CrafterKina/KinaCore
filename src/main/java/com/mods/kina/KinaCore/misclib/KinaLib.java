package com.mods.kina.KinaCore.misclib;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import java.util.Random;

public class KinaLib{
    public World world;
    public static KinaLib lib;
    public static int h;

    /**
     Sets the block ID and metadata at a given location. Args: X, Y, Z, new block ID, new metadata, flags. Flag 1 will
     cause a block update. Flag 2 will send the change to clients (you almost always want pblockItem). Flag 4 prevents the
     block from being re-rendered, if pblockItem is a client world. Flags can be added together.
     */
    public static boolean setOnBlock(int x, int y, int z, Block block, World world, EntityPlayer player, ItemStack itemStack, int side, int metadata, int flags){
        if(side == 0){
            --y;
        }

        if(side == 1){
            ++y;
        }

        if(side == 2){
            --z;
        }

        if(side == 3){
            ++z;
        }

        if(side == 4){
            --x;
        }

        if(side == 5){
            ++x;
        }

        if(!player.canPlayerEdit(x, y, z, side, itemStack)){
            return false;
        }else{
            world.setBlock(x, y, z, block, metadata, flags);
            return true;
        }
    }

    public static boolean setOnBlock(int x, int y, int z, Block block, World world, EntityPlayer player, ItemStack itemStack, int side){
        setOnBlock(x, y, z, block, world, player, itemStack, side, 0, 3);
        return true;
    }

    public static boolean setOnBlockVanilla(int x, int y, int z, Block pblock, World world, EntityPlayer player, ItemStack itemStack, int side,float hitX,float hitY,float hitZ){
        Block block = world.getBlock(x, y, z);
        ItemBlock pblockItem=(ItemBlock)Item.getItemFromBlock(pblock);

        if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1)
        {
            side = 1;
        }
        else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(world, x, y, z))
        {
            if (side == 0)
            {
                --y;
            }

            if (side == 1)
            {
                ++y;
            }

            if (side == 2)
            {
                --z;
            }

            if (side == 3)
            {
                ++z;
            }

            if (side == 4)
            {
                --x;
            }

            if (side == 5)
            {
                ++x;
            }
        }

        if (itemStack.stackSize == 0)
        {
            return false;
        }
        else if (!player.canPlayerEdit(x, y, z, side, itemStack))
        {
            return false;
        }
        else if (y == 255 && pblock.getMaterial().isSolid())
        {
            return false;
        }
        else if (world.canPlaceEntityOnSide(pblock, x, y, z, false, side, player, itemStack))
        {
            int i1 = pblockItem.getMetadata(itemStack.getItemDamage());
            int j1 = pblock.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, i1);

            if (pblockItem.placeBlockAt(itemStack, player, world, x, y, z, side,hitX , hitY, hitZ, j1))
            {
                world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), pblock.stepSound.func_150496_b(), (pblock.stepSound.getVolume() + 1.0F) / 2.0F, pblock.stepSound.getPitch() * 0.8F);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean spawnThunder(World world, int x, int y, int z){
        Random rand = new Random();
        EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, x, y, z);
        world.spawnEntityInWorld(entitylightningbolt);
        if(!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doFireTick") && (world.difficultySetting == EnumDifficulty.PEACEFUL || world.difficultySetting == EnumDifficulty.EASY) && world.doChunksNearChunkExist(MathHelper.floor_double((double) x), MathHelper.floor_double((double) y), MathHelper.floor_double((double) z), 10)){
            int i = MathHelper.floor_double((double) x);
            int j = MathHelper.floor_double((double) y);
            int k = MathHelper.floor_double((double) z);

            if(world.getBlock(i, j, k).getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(world, i, j, k)){
                world.setBlock(i, j, k, Blocks.fire);
            }

            for(i = 0; i < 4; ++i){
                j = MathHelper.floor_double((double) x) + rand.nextInt(3) - 1;
                k = MathHelper.floor_double((double) y) + rand.nextInt(3) - 1;
                int l = MathHelper.floor_double((double) z) + rand.nextInt(3) - 1;

                if(world.getBlock(j, k, l).getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(world, j, k, l)){
                    world.setBlock(j, k, l, Blocks.fire);
                }
            }
        }
        return true;
    }
    public int getH(){
        return h;
    }
    public static boolean isBlock(Item item){
       return item instanceof ItemBlock;
    }
    public boolean isBlock(int id){
        return isBlock(Item.getItemById(id));
    }
    public boolean isBlock(String name){
        return isBlock((Item)Item.itemRegistry.getObject(name));
    }
    public boolean isBlock(ItemStack stack){
        return isBlock(stack.getItem());
    }
    public boolean isBlock(EntityItem entityItem){
        return isBlock(entityItem.getEntityItem());
    }
}
