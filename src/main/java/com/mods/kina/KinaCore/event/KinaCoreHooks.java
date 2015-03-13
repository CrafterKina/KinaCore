package com.mods.kina.KinaCore.event;

import com.google.common.collect.Sets;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Set;

public class KinaCoreHooks{
    static Set<Item> containers = Sets.newHashSet(Items.bucket);

    public static boolean isBucket(ItemStack stack){
        return containers.contains(stack.getItem());
    }
}
