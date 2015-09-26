/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *  Neither the name of the CrafterKina nor the　names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CrafterKina BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package jp.crafterkina.KinaCore.movelib.O18n;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class O18nMisc{
    public CreativeTabs getTab(String s){
        if(s.equalsIgnoreCase("Block")) return CreativeTabs.tabBlock;
        if(s.equalsIgnoreCase("Brewing")) return CreativeTabs.tabBrewing;
        if(s.equalsIgnoreCase("Combat")) return CreativeTabs.tabCombat;
        if(s.equalsIgnoreCase("Decorations")) return CreativeTabs.tabDecorations;
        if(s.equalsIgnoreCase("Food")) return CreativeTabs.tabFood;
        if(s.equalsIgnoreCase("Materials")) return CreativeTabs.tabMaterials;
        if(s.equalsIgnoreCase("Misc")) return CreativeTabs.tabMisc;
        if(s.equalsIgnoreCase("Redstone")) return CreativeTabs.tabRedstone;
        if(s.equalsIgnoreCase("Tools")) return CreativeTabs.tabTools;
        if(s.equalsIgnoreCase("Transport")) return CreativeTabs.tabTransport;
        return CreativeTabs.tabAllSearch;
    }

    public Block.SoundType getSound(String s){
        if(s.equalsIgnoreCase("Anvil")) return Block.soundTypeAnvil;
        if(s.equalsIgnoreCase("Cloth")) return Block.soundTypeCloth;
        if(s.equalsIgnoreCase("Glass")) return Block.soundTypeGlass;
        if(s.equalsIgnoreCase("Grass")) return Block.soundTypeGrass;
        if(s.equalsIgnoreCase("Gravel")) return Block.soundTypeGravel;
        if(s.equalsIgnoreCase("Ladder")) return Block.soundTypeLadder;
        if(s.equalsIgnoreCase("Metal")) return Block.soundTypeMetal;
        if(s.equalsIgnoreCase("Piston")) return Block.soundTypePiston;
        if(s.equalsIgnoreCase("Sand")) return Block.soundTypeSand;
        if(s.equalsIgnoreCase("Snow")) return Block.soundTypeSnow;
        if(s.equalsIgnoreCase("Stone")) return Block.soundTypeStone;
        if(s.equalsIgnoreCase("Wood")) return Block.soundTypeWood;
        return null;
    }

    public String getPotionEffect(String s){
        if(s.equalsIgnoreCase("Sugar")) return PotionHelper.sugarEffect;
        if(s.equalsIgnoreCase("MagmaCream")) return PotionHelper.magmaCreamEffect;
        if(s.equalsIgnoreCase("GoldenMelon")) return PotionHelper.speckledMelonEffect;
        if(s.equalsIgnoreCase("SpiderEye")) return PotionHelper.spiderEyeEffect;
        if(s.equalsIgnoreCase("FermentedSpiderEye")) return PotionHelper.fermentedSpiderEyeEffect;
        if(s.equalsIgnoreCase("BlazePowder")) return PotionHelper.blazePowderEffect;
        if(s.equalsIgnoreCase("GoldenCarrot")) return PotionHelper.goldenCarrotEffect;
        if(s.equalsIgnoreCase("Glowstone")) return PotionHelper.glowstoneEffect;
        if(s.equalsIgnoreCase("Redstone")) return PotionHelper.redstoneEffect;
        if(s.equalsIgnoreCase("Gunpowder")) return PotionHelper.gunpowderEffect;
        if(s.equalsIgnoreCase("NetherWart")) return "+4";
        if(s.equalsIgnoreCase("Pufferfish")) return PotionHelper.pufferfishEffect;
        if(s.equalsIgnoreCase("RabbitFoot")) return PotionHelper.rabbitFootEffect;
        return null;
    }

    public int getPotionIdFromName(String s){
        if(isInteger(s)) return Integer.valueOf(s);
        if(s.equalsIgnoreCase("Speed")) return Potion.moveSpeed.id;
        if(s.equalsIgnoreCase("Slowness")) return Potion.moveSlowdown.id;
        if(s.equalsIgnoreCase("Haste")) return Potion.digSpeed.id;
        if(s.equalsIgnoreCase("MiningFatigue")) return Potion.digSlowdown.id;
        if(s.equalsIgnoreCase("Strength")) return Potion.damageBoost.id;
        if(s.equalsIgnoreCase("InstantHealth")) return Potion.heal.id;
        if(s.equalsIgnoreCase("InstantDamage")) return Potion.harm.id;
        if(s.equalsIgnoreCase("JumpBoost")) return Potion.jump.id;
        if(s.equalsIgnoreCase("Nausea")) return Potion.confusion.id;
        if(s.equalsIgnoreCase("Regeneration")) return Potion.regeneration.id;
        if(s.equalsIgnoreCase("Resistance")) return Potion.resistance.id;
        if(s.equalsIgnoreCase("FireResistance")) return Potion.fireResistance.id;
        if(s.equalsIgnoreCase("WaterBreathing")) return Potion.waterBreathing.id;
        if(s.equalsIgnoreCase("Invisibility")) return Potion.invisibility.id;
        if(s.equalsIgnoreCase("Blindness")) return Potion.blindness.id;
        if(s.equalsIgnoreCase("NightVision")) return Potion.nightVision.id;
        if(s.equalsIgnoreCase("Hunger")) return Potion.hunger.id;
        if(s.equalsIgnoreCase("Weakness")) return Potion.weakness.id;
        if(s.equalsIgnoreCase("Poison")) return Potion.poison.id;
        if(s.equalsIgnoreCase("Wither")) return Potion.wither.id;
        if(s.equalsIgnoreCase("HealthBoost")) return Potion.healthBoost.id;
        if(s.equalsIgnoreCase("Absorption")) return Potion.absorption.id;
        if(s.equalsIgnoreCase("Saturation")) return Potion.saturation.id;
        return 0;
    }

    public boolean isInteger(String s){
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    public Fluid getFluidFromBlockName(String s){
        return FluidRegistry.lookupFluidForBlock(Block.getBlockFromName(s));
    }

    public String[] getSet2(String s){
        return s.split(",");
    }
}
