package com.mods.kina.KinaCore.movelib.O18n;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class O18nMisc{
    public CreativeTabs getTab(String s){
        if(s.equalsIgnoreCase("Block")) return CreativeTabs.tabBlock;
        else if(s.equalsIgnoreCase("Brewing")) return CreativeTabs.tabBrewing;
        else if(s.equalsIgnoreCase("Combat")) return CreativeTabs.tabCombat;
        else if(s.equalsIgnoreCase("Decorations")) return CreativeTabs.tabDecorations;
        else if(s.equalsIgnoreCase("Food")) return CreativeTabs.tabFood;
        else if(s.equalsIgnoreCase("Materials")) return CreativeTabs.tabMaterials;
        else if(s.equalsIgnoreCase("Misc")) return CreativeTabs.tabMisc;
        else if(s.equalsIgnoreCase("Redstone")) return CreativeTabs.tabRedstone;
        else if(s.equalsIgnoreCase("Tools")) return CreativeTabs.tabTools;
        else if(s.equalsIgnoreCase("Transport")) return CreativeTabs.tabTransport;
        else return (CreativeTabs) getClassFromName(s);
    }

    public Block.SoundType getSound(String s){
        if(s.equalsIgnoreCase("Anvil")) return Block.soundTypeAnvil;
        else if(s.equalsIgnoreCase("Cloth")) return Block.soundTypeCloth;
        else if(s.equalsIgnoreCase("Glass")) return Block.soundTypeGlass;
        else if(s.equalsIgnoreCase("Grass")) return Block.soundTypeGrass;
        else if(s.equalsIgnoreCase("Gravel")) return Block.soundTypeGravel;
        else if(s.equalsIgnoreCase("Ladder")) return Block.soundTypeLadder;
        else if(s.equalsIgnoreCase("Metal")) return Block.soundTypeMetal;
        else if(s.equalsIgnoreCase("Piston")) return Block.soundTypePiston;
        else if(s.equalsIgnoreCase("Sand")) return Block.soundTypeSand;
        else if(s.equalsIgnoreCase("Snow")) return Block.soundTypeSnow;
        else if(s.equalsIgnoreCase("Stone")) return Block.soundTypeStone;
        else if(s.equalsIgnoreCase("Wood")) return Block.soundTypeWood;
        else return null;
    }

    public String getPotionEffect(String s){
        if(s.equalsIgnoreCase("Sugar")) return "-0+1-2-3&4-4+13";
        else if(s.equalsIgnoreCase("MagmaCream")) return "+0+1-2-3&4-4+13";
        else if(s.equalsIgnoreCase("GoldenMelon")) return "+0-1+2-3&4-4+13";
        else if(s.equalsIgnoreCase("SpiderEye")) return "-0-1+2-3&4-4+13";
        else if(s.equalsIgnoreCase("FermentedSpiderEye")) return "-0+3-4+13";
        else if(s.equalsIgnoreCase("BlazePowder")) return "+0-1-2+3&4-4+13";
        else if(s.equalsIgnoreCase("GoldenCarrot")) return "-0+1+2-3+13&4-4";
        else if(s.equalsIgnoreCase("Glowstone")) return "+5-6-7";
        else if(s.equalsIgnoreCase("Redstone")) return "-5+6-7";
        else if(s.equalsIgnoreCase("Gunpowder")) return "+14&13-13";
        else if(s.equalsIgnoreCase("NetherWart")) return "+4";
        else if(s.equalsIgnoreCase("Unimplemented")) return "+0-1+2+3+13&4-4";
        else return null;
    }

    public int getPotionIdFromName(String s){
        if(isInteger(s)) return Integer.valueOf(s);
        else{
            if(s.equalsIgnoreCase("Speed")) return Potion.moveSpeed.id;
            else if(s.equalsIgnoreCase("Slowness")) return Potion.moveSlowdown.id;
            else if(s.equalsIgnoreCase("Haste")) return Potion.digSpeed.id;
            else if(s.equalsIgnoreCase("MiningFatigue")) return Potion.digSlowdown.id;
            else if(s.equalsIgnoreCase("Strength")) return Potion.damageBoost.id;
            else if(s.equalsIgnoreCase("InstantHealth")) return Potion.heal.id;
            else if(s.equalsIgnoreCase("InstantDamage")) return Potion.harm.id;
            else if(s.equalsIgnoreCase("JumpBoost")) return Potion.jump.id;
            else if(s.equalsIgnoreCase("Nausea")) return Potion.confusion.id;
            else if(s.equalsIgnoreCase("Regeneration")) return Potion.regeneration.id;
            else if(s.equalsIgnoreCase("Resistance")) return Potion.resistance.id;
            else if(s.equalsIgnoreCase("FireResistance")) return Potion.fireResistance.id;
            else if(s.equalsIgnoreCase("WaterBreathing")) return Potion.waterBreathing.id;
            else if(s.equalsIgnoreCase("Invisibility")) return Potion.invisibility.id;
            else if(s.equalsIgnoreCase("Blindness")) return Potion.blindness.id;
            else if(s.equalsIgnoreCase("NightVision")) return Potion.nightVision.id;
            else if(s.equalsIgnoreCase("Hunger")) return Potion.hunger.id;
            else if(s.equalsIgnoreCase("Weakness")) return Potion.weakness.id;
            else if(s.equalsIgnoreCase("Poison")) return Potion.poison.id;
            else if(s.equalsIgnoreCase("Wither")) return Potion.wither.id;
            else if(s.equalsIgnoreCase("HealthBoost")) return Potion.healthBoost.id;
            else if(s.equalsIgnoreCase("Absorption")) return Potion.absorption.id;
            else if(s.equalsIgnoreCase("Saturation")) return Potion.saturation.id;
            else return 0;
        }
    }

    public boolean isInteger(String s){
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    public Item getItemFromName(String s){
        return (Item) Item.itemRegistry.getObject(s);
    }

    public ItemFood getItemFoodFromName(String s){
        return (ItemFood) getItemFromName(s);
    }

    public ItemBlock getItemBlockFromName(String s){
        return (ItemBlock) Item.getItemFromBlock(Block.getBlockFromName(s));
    }

    public Fluid getFluidFromBlockName(String s){
        return FluidRegistry.lookupFluidForBlock(Block.getBlockFromName(s));
    }

    public Object getClassFromName(String s){
        try{
            return Class.forName(s);
        } catch(ClassNotFoundException e){
            return null;
        }
    }

    public String[] getSet1(String s){
        return s.split(";");
    }

    public String[] getSet2(String s){
        return s.split(",");
    }
}
