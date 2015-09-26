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

package jp.crafterkina.KinaCore.event.hooks;

import jp.crafterkina.KinaCore.event.hooks.furnace.ItemSmeltEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class KinaCoreEventFactory{
    public static void onItemAttemptSmelt(TileEntityFurnace furnace){
        ItemStack[] stacks = ObfuscationReflectionHelper.getPrivateValue(TileEntityFurnace.class, furnace, "furnaceItemStacks");
        int cookTime = ObfuscationReflectionHelper.getPrivateValue(TileEntityFurnace.class, furnace, "cookTime");
        int totalCookTime = ObfuscationReflectionHelper.getPrivateValue(TileEntityFurnace.class, furnace, "totalCookTime");
        ItemSmeltEvent.Pre event = new ItemSmeltEvent.Pre(furnace.getWorld(), furnace.getPos(), stacks, cookTime, totalCookTime);
        MinecraftForge.EVENT_BUS.post(event);
        ObfuscationReflectionHelper.setPrivateValue(TileEntityFurnace.class, furnace, event.getStacks(), "furnaceItemStacks");
        ObfuscationReflectionHelper.setPrivateValue(TileEntityFurnace.class, furnace, event.getCookTime(), "cookTime");
        ObfuscationReflectionHelper.setPrivateValue(TileEntityFurnace.class, furnace, event.getTotalCookTime(), "totalCookTime");
    }

    public static void onItemSmelted(TileEntityFurnace furnace){
        ItemStack[] stacks = ObfuscationReflectionHelper.getPrivateValue(TileEntityFurnace.class, furnace, "furnaceItemStacks");
        ItemSmeltEvent.Post event = new ItemSmeltEvent.Post(furnace.getWorld(), furnace.getPos(), stacks);
        MinecraftForge.EVENT_BUS.post(event);
        ObfuscationReflectionHelper.setPrivateValue(TileEntityFurnace.class, furnace, event.getStacks(), "furnaceItemStacks");
    }
}
