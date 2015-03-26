package com.mods.kina.KinaCore.event;

import com.google.common.collect.Sets;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Set;

public class KinaCoreHooks{
    static Set<ItemStack> containers = Sets.newHashSet(new ItemStack(Items.bucket));

    public static Set<ItemStack> getContainerSet(){
        return containers;
    }
    public static boolean isBucket(ItemStack stack){
        for(ItemStack container : containers){
            if(container.getIsItemStackEqual(stack)) return true;
        }
        return false;
    }
}
