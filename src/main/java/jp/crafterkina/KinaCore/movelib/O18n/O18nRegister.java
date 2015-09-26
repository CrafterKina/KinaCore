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
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

public class O18nRegister {
    public static void registerThing(FMLInitializationEvent event){
        O18nMisc misc = new O18nMisc();
        if(!O18nField.GENERAL.isEnable()) return;
        //Block
        for(String a : O18nField.BLOCK.getLightOpacity())
            Block.getBlockFromName(misc.getSet2(a)[0]).setLightOpacity(Integer.valueOf(misc.getSet2(a)[1]));
        for(String a : O18nField.BLOCK.getHardness())
            Block.getBlockFromName(misc.getSet2(a)[0]).setHardness(Float.valueOf(misc.getSet2(a)[1] + "f"));
        for(String a : O18nField.BLOCK.getResistance())
            Block.getBlockFromName(misc.getSet2(a)[0]).setResistance(Float.valueOf(misc.getSet2(a)[1] + "f"));
        for(String a : O18nField.BLOCK.getLightLevel())
            Block.getBlockFromName(misc.getSet2(a)[0]).setLightLevel(Float.valueOf(misc.getSet2(a)[1] + "f"));
        for(String a : O18nField.BLOCK.getCreativeTab())
            Block.getBlockFromName(misc.getSet2(a)[0]).setCreativeTab(misc.getTab(misc.getSet2(a)[1]));
        for(String a : O18nField.BLOCK.getStepSound())
            Block.getBlockFromName(misc.getSet2(a)[0]).setStepSound(misc.getSound(misc.getSet2(a)[1]));
        for(String a : O18nField.BLOCK.getHarvestLevel())
            Block.getBlockFromName(misc.getSet2(a)[0]).setHarvestLevel(misc.getSet2(a)[1], Integer.valueOf(misc.getSet2(a)[2]));
        //Item
        for(String a : O18nField.ITEM.getCreativeTab())
            Item.getByNameOrId(misc.getSet2(a)[0]).setCreativeTab(misc.getTab(misc.getSet2(a)[1]));
        for(String a : O18nField.ITEM.getMaxDamage())
            Item.getByNameOrId(misc.getSet2(a)[0]).setMaxDamage(Integer.valueOf(misc.getSet2(a)[1]));
        for(String a : O18nField.ITEM.getMaxStackSize())
            Item.getByNameOrId(misc.getSet2(a)[0]).setMaxStackSize(Integer.valueOf(misc.getSet2(a)[1]));
        for(String a : O18nField.ITEM.getPotionEffect())
            Item.getByNameOrId(misc.getSet2(a)[0]).setPotionEffect(misc.getPotionEffect(misc.getSet2(a)[1]));
        for(String a : O18nField.ITEM.getHarvestLevel())
            Item.getByNameOrId(misc.getSet2(a)[0]).setHarvestLevel(misc.getSet2(a)[1], Integer.valueOf(misc.getSet2(a)[2]));
        for(String a : O18nField.ITEM.getFull3D())
            Item.getByNameOrId(a).setFull3D();
        //Food
        for(String a : O18nField.FOOD.getAlwaysEdible())
            ((ItemFood) Item.getByNameOrId(a)).setAlwaysEdible();
        for(String a : O18nField.FOOD.getPotionEffect())
            ((ItemFood) Item.getByNameOrId(misc.getSet2(a)[0])).setPotionEffect(misc.getPotionIdFromName(misc.getSet2(a)[1]), Integer.valueOf(misc.getSet2(a)[2]), Integer.valueOf(misc.getSet2(a)[3]), Float.valueOf(misc.getSet2(a)[4] + "f"));
        //Fluid
        for(String a : O18nField.FLUID.getLuminosity())
            misc.getFluidFromBlockName(misc.getSet2(a)[0]).setLuminosity(Integer.valueOf(misc.getSet2(a)[1]));
        for(String a : O18nField.FLUID.getDensity())
            misc.getFluidFromBlockName(misc.getSet2(a)[0]).setDensity(Integer.valueOf(misc.getSet2(a)[1]));
        for(String a : O18nField.FLUID.getTemperature())
            misc.getFluidFromBlockName(misc.getSet2(a)[0]).setTemperature(Integer.valueOf(misc.getSet2(a)[1]));
        for(String a : O18nField.FLUID.getViscosity())
            misc.getFluidFromBlockName(misc.getSet2(a)[0]).setViscosity(Integer.valueOf(misc.getSet2(a)[1]));
        //OreDictionary
        for(String a : O18nField.ORE.getRegister())
            OreDictionary.registerOre(misc.getSet2(a)[1], Item.getByNameOrId(misc.getSet2(a)[0]));
    }
}
