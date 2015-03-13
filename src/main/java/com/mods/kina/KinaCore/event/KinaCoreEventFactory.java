package com.mods.kina.KinaCore.event;

import com.mods.kina.KinaCore.event.furnace.ItemSmeltEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.Arrays;

public class KinaCoreEventFactory{
    public static void onItemAttemptSmelt(TileEntityFurnace furnace){
        ItemStack[] stacks = ReflectionHelper.getPrivateValue(TileEntityFurnace.class, furnace, "furnaceItemStacks");
        int cookTime = ReflectionHelper.getPrivateValue(TileEntityFurnace.class, furnace, "cookTime");
        int totalCookTime = ReflectionHelper.getPrivateValue(TileEntityFurnace.class, furnace, "totalCookTime");
        ItemSmeltEvent.Pre event = new ItemSmeltEvent.Pre(furnace.getWorld(), furnace.getPos(), stacks, cookTime, totalCookTime);
        MinecraftForge.EVENT_BUS.post(event);
        ReflectionHelper.setPrivateValue(TileEntityFurnace.class, furnace, event.getStacks(), "furnaceItemStacks");
        ReflectionHelper.setPrivateValue(TileEntityFurnace.class, furnace, event.getCookTime(), "cookTime");
        ReflectionHelper.setPrivateValue(TileEntityFurnace.class, furnace, event.getTotalCookTime(), "totalCookTime");
    }

    public static void onItemSmelted(TileEntityFurnace furnace){
        System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
        ItemStack[] stacks = ReflectionHelper.getPrivateValue(TileEntityFurnace.class, furnace, "furnaceItemStacks");
        ItemSmeltEvent.Post event = new ItemSmeltEvent.Post(furnace.getWorld(), furnace.getPos(), stacks);
        MinecraftForge.EVENT_BUS.post(event);
        ReflectionHelper.setPrivateValue(TileEntityFurnace.class, furnace, event.getStacks(), "furnaceItemStacks");
    }
}
