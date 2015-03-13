package com.mods.kina.KinaCore.movelib.getInfo;

@SuppressWarnings("unchecked")
public class GetInfoCore{/*UNUSED*/
    /*private static FMLControlledNamespacedRegistry<Block> allBlock = (FMLControlledNamespacedRegistry) Block.blockRegistry;
    private static FMLControlledNamespacedRegistry<Item> allItem = (FMLControlledNamespacedRegistry) Item.itemRegistry;

    public static Set<Block> getBlocks(){
        return Sets.newHashSet(allBlock);
    }

    public static Set<Item> getItems(){
        return Sets.newHashSet(allItem);
    }

    public static Set<Block> getBlocksOnlyHandy(){
        Set<Block> list = Sets.newHashSet();
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
        return list;
    }

    private static boolean getIsBlack(Block block){
        for(Block black : getNeedToolBlocks()){
            if(black.equals(block)){
                return true;
            }
        }
        return false;
    }

    private static Set<Block> getNeedToolBlocks(){
        Set<Block> blocks = Sets.newHashSet();
        for(Object block:Block.blockRegistry){
            for(Object item:Item.itemRegistry){
                if(item instanceof ItemTool&&!((Item)item).getUnlocalizedName().equals("item.itemFastHand")){
                    if(ForgeHooks.isToolEffective(new ItemStack((Item)item),(Block)block,0)){
                        blocks.add((Block)block);
                    }
                }
            }
        }
        *//*blocks.addAll((Set<Block>)ReflectionHelper.getPrivateValue(ItemPickaxe.class, null, 0));
        blocks.addAll((Set<Block>)ReflectionHelper.getPrivateValue(ItemSpade.class, null, 0));
        blocks.addAll((Set<Block>)ReflectionHelper.getPrivateValue(ItemAxe.class, null, 0));*//*
        return blocks;
    }*/
}
