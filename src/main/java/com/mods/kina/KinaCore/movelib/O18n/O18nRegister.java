package com.mods.kina.KinaCore.movelib.O18n;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

import static com.mods.kina.KinaCore.movelib.O18n.O18nField.*;

public class O18nRegister {
    public static void registerThing(FMLInitializationEvent event){
        O18nMisc misc = new O18nMisc();
        if(isOK){
            //Block
            if(!lightOpacity.isEmpty()) for(String a : misc.getSet1(lightOpacity))
                Block.getBlockFromName(misc.getSet2(a)[0]).setLightOpacity(Integer.valueOf(misc.getSet2(a)[1]));
            if(!hardness.isEmpty()) for(String a : misc.getSet1(hardness))
                Block.getBlockFromName(misc.getSet2(a)[0]).setHardness(Float.valueOf(misc.getSet2(a)[1] + "f"));
            if(!resistance.isEmpty()) for(String a : misc.getSet1(resistance))
                Block.getBlockFromName(misc.getSet2(a)[0]).setResistance(Float.valueOf(misc.getSet2(a)[1] + "f"));
            if(!lightLevel.isEmpty()) for(String a : misc.getSet1(lightLevel))
                Block.getBlockFromName(misc.getSet2(a)[0]).setLightLevel(Float.valueOf(misc.getSet2(a)[1] + "f"));
            if(!creativeTab.isEmpty()) for(String a : misc.getSet1(creativeTab))
                Block.getBlockFromName(misc.getSet2(a)[0]).setCreativeTab(misc.getTab(misc.getSet2(a)[1]));
            if(!stepSound.isEmpty()) for(String a : misc.getSet1(stepSound))
                Block.getBlockFromName(misc.getSet2(a)[0]).setStepSound(misc.getSound(misc.getSet2(a)[1]));
            if(!harvestLevelB.isEmpty()) for(String a : misc.getSet1(harvestLevelB))
                Block.getBlockFromName(misc.getSet2(a)[0]).setHarvestLevel(misc.getSet2(a)[1], Integer.valueOf(misc.getSet2(a)[2]));
            //Item
            if(!creativeTabI.isEmpty()) for(String a : misc.getSet1(creativeTabI))
                misc.getItemFromName(misc.getSet2(a)[0]).setCreativeTab(misc.getTab(misc.getSet2(a)[1]));
            if(!maxDamage.isEmpty()) for(String a : misc.getSet1(maxDamage))
                misc.getItemFromName(misc.getSet2(a)[0]).setMaxDamage(Integer.valueOf(misc.getSet2(a)[1]));
            if(!maxStackSize.isEmpty()) for(String a : misc.getSet1(maxStackSize))
                misc.getItemFromName(misc.getSet2(a)[0]).setMaxStackSize(Integer.valueOf(misc.getSet2(a)[1]));
            if(!potionEffect.isEmpty()) for(String a : misc.getSet1(potionEffect))
                misc.getItemFromName(misc.getSet2(a)[0]).setPotionEffect(misc.getPotionEffect(misc.getSet2(a)[1]));
            if(!harvestLevel.isEmpty()) for(String a : misc.getSet1(harvestLevel))
                misc.getItemFromName(misc.getSet2(a)[0]).setHarvestLevel(misc.getSet2(a)[1], Integer.valueOf(misc.getSet2(a)[2]));
            if(!full3D.isEmpty()) for(String a : misc.getSet1(full3D))
                misc.getItemFromName(a).setFull3D();
            //Food
            if(!alwaysEdible.isEmpty()) for(String a : misc.getSet1(alwaysEdible))
                misc.getItemFoodFromName(a).setAlwaysEdible();
            if(!potionEffectF.isEmpty()) for(String a : misc.getSet1(potionEffectF))
                misc.getItemFoodFromName(misc.getSet2(a)[0]).setPotionEffect(misc.getPotionIdFromName(misc.getSet2(a)[1]), Integer.valueOf(misc.getSet2(a)[2]), Integer.valueOf(misc.getSet2(a)[3]), Float.valueOf(misc.getSet2(a)[4] + "f"));
            //Fluid
            if(!fluidLuminosity.isEmpty()) for(String a : misc.getSet1(fluidLuminosity))
                misc.getFluidFromBlockName(misc.getSet2(a)[0]).setLuminosity(Integer.valueOf(misc.getSet2(a)[1]));
            if(!fluidDensity.isEmpty()) for(String a : misc.getSet1(fluidDensity))
                misc.getFluidFromBlockName(misc.getSet2(a)[0]).setDensity(Integer.valueOf(misc.getSet2(a)[1]));
            if(!fluidTemperature.isEmpty()) for(String a : misc.getSet1(fluidTemperature))
                misc.getFluidFromBlockName(misc.getSet2(a)[0]).setTemperature(Integer.valueOf(misc.getSet2(a)[1]));
            if(!fluidViscosity.isEmpty()) for(String a : misc.getSet1(fluidViscosity))
                misc.getFluidFromBlockName(misc.getSet2(a)[0]).setViscosity(Integer.valueOf(misc.getSet2(a)[1]));
            //OreDictionary
            if(!oreBlock.isEmpty()) for(String a : misc.getSet1(oreBlock))
                OreDictionary.registerOre(misc.getSet2(a)[1], Block.getBlockFromName(misc.getSet2(a)[0]));
            if(!oreItem.isEmpty()) for(String a : misc.getSet1(oreItem))
                OreDictionary.registerOre(misc.getSet2(a)[1], misc.getItemFromName(misc.getSet2(a)[0]));
        }
    }
}
