package com.mods.kina.KinaCore.movelib.modelloader.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public interface IUseKinaItemModel{
    List<ResourceLocation> getTextures(ItemStack stack);

    ItemStack getDefaultStack();
}
