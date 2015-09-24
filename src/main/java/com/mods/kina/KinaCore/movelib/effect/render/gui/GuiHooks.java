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
 *  Neither the name of the CrafterKina nor theÅ@names of its contributors
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

package com.mods.kina.KinaCore.movelib.effect.render.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;
import java.util.List;

public class GuiHooks{

    private static final Field hasActivePotionEffects = InventoryEffectRenderer.class.getDeclaredFields()[0];

    static{
        hasActivePotionEffects.setAccessible(true);
    }

    @SubscribeEvent
    public void init(InitGuiEvent.Post event){
        if(!(event.gui instanceof InventoryEffectRenderer)) return;
        InventoryEffectRenderer gui = (InventoryEffectRenderer) event.gui;
        @SuppressWarnings("unchecked") List<GuiButton> list = event.buttonList;
        int guiLeft = ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, gui, "guiLeft");
        list.add(new GuiButton(201, guiLeft - 124, 30, 20, 20, "<"){
            @Override
            public boolean mousePressed(Minecraft mc, int mouseX, int mouseY){
                if(super.mousePressed(mc, mouseX, mouseY)){

                }
                return false;
            }
        });
        list.add(new GuiButton(202, guiLeft - 24, 30, 20, 20, ">"));
    }

    @SubscribeEvent
    public void draw(DrawScreenEvent.Pre event){
        if(!(event.gui instanceof InventoryEffectRenderer)) return;
        InventoryEffectRenderer gui = (InventoryEffectRenderer) event.gui;
        //setHasActivePotionEffects(gui,false);
    }

    private void setHasActivePotionEffects(InventoryEffectRenderer gui, boolean z){
        try{
            hasActivePotionEffects.setBoolean(gui, z);
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }
    }
}
