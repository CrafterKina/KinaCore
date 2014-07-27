package com.mods.kina.KinaCore.movelib.O18n;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import static com.mods.kina.KinaCore.movelib.O18n.O18nField.*;

import java.io.File;

public class O18nConfig{
    public static void makeConfig(FMLPreInitializationEvent event){
        Configuration config = new Configuration(new File(event.getModConfigurationDirectory(), "OverwriteInformationMod.cfg"));
        try{
            config.load();
            isOK = config.get("Overwrite", "isEnable", true).getBoolean(true);
            lightOpacity = config.get(BLOCK, "LightOpacity_Block", "", "set light opacity \"Block,Level;\"").getString();
            resistance = config.get(BLOCK, "Resistance_Block", "", "set resistance(Explosion) \"Block,Level;\"").getString();
            hardness = config.get(BLOCK, "Hardness_Block", "", "set hardness(Mining) \"Block,Level;\"").getString();
            lightLevel = config.get(BLOCK, "LightLevel_Block", "", "set light level \"Block,Level;\"").getString();
            creativeTab = config.get(BLOCK, "CreativeTab_Block", "", "set creative tab \"Block,Tab;\"Tabs=(Block,Brewing,Combat,Decorations,Food,Materials,Misc,Redstone,Tools,Transport)").getString();
            stepSound = config.get(BLOCK, "StepSound_Block", "", "set step sound \"Block,Sound;\"Sounds=(Anvil,Cloth,Grass,Glass,Gravel,Ladder,Metal,Piston,Sand,Stone,Wood)").getString();
            harvestLevelB = config.get(BLOCK, "HarvestLevel_Block", "", "set harvest level \"Block,Tool,Level;\"Tools=(axe,pickaxe,shovel),Levels=(Wood:0,Stone:1,Iron:2,Diamond:3,Gold:0)").getString();
            creativeTabI = config.get(ITEM, "CreativeTab_Item", "", "set creative tab \"Item,Tab;\"Tabs=(Block,Brewing,Combat,Decorations,Food,Materials,Misc,Redstone,Tools,Transport)").getString();
            maxDamage = config.get(ITEM, "MaxDamage_Item", "", "set durability \"Item,MaxDamage;\"").getString();
            maxStackSize = config.get(ITEM, "MaxStackSize_Item", "", "set max stack size \"Item,MaxStackSize;\"").getString();
            potionEffect = config.get(ITEM, "PotionRecipe_Item", "", "set potion recipe \"Item,VanillaPosition;\"Positions=(BlazePowder,FermentedSpiderEye,Glowstone,GoldenCarrot,GoldenMelon,Gunpowder,MagmaCream,NetherWart,Redstone,SpiderEye,Sugar,Unimplemented)").getString();
            harvestLevel = config.get(ITEM, "HarvestLevel_Item", "", "set harvest level \"Item,Tool,Level;\"Tools=(axe,pickaxe,shovel),Levels=(Wood:0,Stone:1,Iron:2,Diamond:3,Gold:0)").getString();
            full3D = config.get(ITEM, "HoldToolLike_Item", "", "set hold tool like \"Item;\"").getString();
            alwaysEdible = config.get(FOOD, "AlwaysEdible_Food", "", "set always edible \"Food;\"").getString();
            potionEffectF = config.get(FOOD, "PotionEffect_Food", "", "set potion effect \"Food,Effect(name or id),Duration,AmplifierLevel,Probability;\"Effects=(Speed,Slowness,Haste,MiningFatigue,Strength,InstantHealth,InstantDamage,JumpBoost,Nausea,Regeneration,Resistance,FireResistance,WaterBreathing,Invisibility,Blindness,NightVision,Hunger,Weakness,Poison,Wither,HealthBoost,Absorption,Saturation)").getString();
            fluidLuminosity = config.get(FLUID, "LightLevel_Fluid", "", "set light level \"Fluid,Level\"").getString();
            fluidDensity = config.get(FLUID, "Density_Fluid", "", "set density \"Fluid,Level\"").getString();
            fluidTemperature = config.get(FLUID, "Temperature_Fluid", "", "set temperature \"Fluid,Degree\"").getString();
            fluidViscosity = config.get(FLUID, "Viscosity_Fluid", "", "set viscosity \"Fluid,Level\"").getString();
            oreBlock = config.get(ORE, "Registry_Block", "", "registry OreDictionary \"Block,Name\"").getString();
            oreItem = config.get(ORE, "Registry_Item", "", "registry OreDictionary \"Item,Name\"").getString();
        } catch(Exception e){
            FMLLog.severe("Error Message");
        }finally{
            config.save();
        }
    }
}
