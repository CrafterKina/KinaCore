package com.mods.kina.KinaCore.movelib.getInfo;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;

import java.util.Set;

@SuppressWarnings("unchecked")
@Deprecated
public class GetInfoCore{
    private static RegistryNamespacedDefaultedByKey allBlock = Block.blockRegistry;
    private static RegistryNamespaced allItem = Item.itemRegistry;

    public static Set<Block> getBlocks(){
        return Sets.newHashSet(allBlock);
    }

    public static Set<Item> getItems(){
        return Sets.newHashSet(allItem);
    }

    public static Set<Block> getBlocksOnlyHandy(){
        /*Set<Block> list = Sets.newHashSet();
        for(Block block : getBlocks()){
            for(Item item : getItems()){
                if(!item.getUnlocalizedName().equals("item.itemFastHand")){
                    for(int meta = 0;meta < 16; meta++){
                        if(!(ForgeHooks.isToolEffective(new ItemStack(item),block,meta)||!block.getMaterial().isToolNotRequired())){
                            list.add(block);
                        }
                    }
                }
            }
        }
        return list;*/
        return null;
    }

    private static boolean getIsBlack(Block block){
        /*for(Block black : getNeedToolBlocks()){
            if(black.equals(block)){
                return true;
            }
        }*/
        return false;
    }

    private static Set<Block> getNeedToolBlocks(){
        /*Set<Block> blocks = Sets.newHashSet();
        for(Object block:Block.blockRegistry){
            for(Object item:Item.itemRegistry){
                if(item instanceof ItemTool&&!((Item)item).getUnlocalizedName().equals("item.itemFastHand")){
                    if(ForgeHooks.isToolEffective(new ItemStack((Item)item),(Block)block,0)){
                        blocks.add((Block)block);
                    }
                }
            }
        }*/
        /*blocks.addAll((Set<Block>)ReflectionHelper.getPrivateValue(ItemPickaxe.class, null, 0));
        blocks.addAll((Set<Block>)ReflectionHelper.getPrivateValue(ItemSpade.class, null, 0));
        blocks.addAll((Set<Block>)ReflectionHelper.getPrivateValue(ItemAxe.class, null, 0));*/
        //return blocks;
        return null;
    }
}
