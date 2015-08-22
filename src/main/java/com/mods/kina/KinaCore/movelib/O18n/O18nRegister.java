package com.mods.kina.KinaCore.movelib.O18n;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import static com.mods.kina.KinaCore.movelib.O18n.O18nField.*;

public class O18nRegister {
    public static void registerThing(FMLInitializationEvent event){
        O18nMisc misc = new O18nMisc();
        if(!GENERAL.isEnable()) return;
        //Block
        for(String a : BLOCK.getLightOpacity())
            Block.getBlockFromName(misc.getSet2(a)[0]).setLightOpacity(Integer.valueOf(misc.getSet2(a)[1]));
        for(String a : BLOCK.getHardness())
            Block.getBlockFromName(misc.getSet2(a)[0]).setHardness(Float.valueOf(misc.getSet2(a)[1] + "f"));
        for(String a : BLOCK.getResistance())
            Block.getBlockFromName(misc.getSet2(a)[0]).setResistance(Float.valueOf(misc.getSet2(a)[1] + "f"));
        for(String a : BLOCK.getLightLevel())
            Block.getBlockFromName(misc.getSet2(a)[0]).setLightLevel(Float.valueOf(misc.getSet2(a)[1] + "f"));
        for(String a : BLOCK.getCreativeTab())
            Block.getBlockFromName(misc.getSet2(a)[0]).setCreativeTab(misc.getTab(misc.getSet2(a)[1]));
        for(String a : BLOCK.getStepSound())
            Block.getBlockFromName(misc.getSet2(a)[0]).setStepSound(misc.getSound(misc.getSet2(a)[1]));
        for(String a : BLOCK.getHarvestLevel())
            Block.getBlockFromName(misc.getSet2(a)[0]).setHarvestLevel(misc.getSet2(a)[1], Integer.valueOf(misc.getSet2(a)[2]));
        //Item
        for(String a : ITEM.getCreativeTab())
            Item.getByNameOrId(misc.getSet2(a)[0]).setCreativeTab(misc.getTab(misc.getSet2(a)[1]));
        for(String a : ITEM.getMaxDamage())
            Item.getByNameOrId(misc.getSet2(a)[0]).setMaxDamage(Integer.valueOf(misc.getSet2(a)[1]));
        for(String a : ITEM.getMaxStackSize())
            Item.getByNameOrId(misc.getSet2(a)[0]).setMaxStackSize(Integer.valueOf(misc.getSet2(a)[1]));
        for(String a : ITEM.getPotionEffect())
            Item.getByNameOrId(misc.getSet2(a)[0]).setPotionEffect(misc.getPotionEffect(misc.getSet2(a)[1]));
        for(String a : ITEM.getHarvestLevel())
            Item.getByNameOrId(misc.getSet2(a)[0]).setHarvestLevel(misc.getSet2(a)[1], Integer.valueOf(misc.getSet2(a)[2]));
        for(String a : ITEM.getFull3D())
            Item.getByNameOrId(a).setFull3D();
        //Food
        for(String a : FOOD.getAlwaysEdible())
            ((ItemFood) Item.getByNameOrId(a)).setAlwaysEdible();
        for(String a : FOOD.getPotionEffect())
            ((ItemFood) Item.getByNameOrId(misc.getSet2(a)[0])).setPotionEffect(misc.getPotionIdFromName(misc.getSet2(a)[1]), Integer.valueOf(misc.getSet2(a)[2]), Integer.valueOf(misc.getSet2(a)[3]), Float.valueOf(misc.getSet2(a)[4] + "f"));
        //Fluid
        for(String a : FLUID.getLuminosity())
            misc.getFluidFromBlockName(misc.getSet2(a)[0]).setLuminosity(Integer.valueOf(misc.getSet2(a)[1]));
        for(String a : FLUID.getDensity())
            misc.getFluidFromBlockName(misc.getSet2(a)[0]).setDensity(Integer.valueOf(misc.getSet2(a)[1]));
        for(String a : FLUID.getTemperature())
            misc.getFluidFromBlockName(misc.getSet2(a)[0]).setTemperature(Integer.valueOf(misc.getSet2(a)[1]));
        for(String a : FLUID.getViscosity())
            misc.getFluidFromBlockName(misc.getSet2(a)[0]).setViscosity(Integer.valueOf(misc.getSet2(a)[1]));
        //OreDictionary
        for(String a : ORE.getRegister())
            OreDictionary.registerOre(misc.getSet2(a)[1], Item.getByNameOrId(misc.getSet2(a)[0]));
    }
}
