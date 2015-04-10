package com.mods.kina.KinaCore.misclib;

import net.minecraft.item.ItemStack;

public class KinaLib{
    public static final KinaLib lib = new KinaLib();

    public boolean areItemStackEqual(ItemStack a, ItemStack b){
        return ItemStack.areItemsEqual(a, b) && ItemStack.areItemStackTagsEqual(a, b);
    }
}
