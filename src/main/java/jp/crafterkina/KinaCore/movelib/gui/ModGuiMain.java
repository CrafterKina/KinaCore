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

package jp.crafterkina.KinaCore.movelib.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.client.config.GuiMessageDialog;

import java.io.IOException;

public class ModGuiMain extends GuiScreen{
    private GuiScreen parentScreen;
    private GuiButtonsList optionsRowList;

    public ModGuiMain(GuiScreen parent){
        parentScreen = parent;
    }

    @SuppressWarnings("unchecked")
    public void initGui(){
        this.buttonList.clear();
        this.buttonList.add(new GuiOptionButton(0, this.width / 2 - 155, this.height - 27, "Game Reset"));
        this.buttonList.add(new GuiOptionButton(1, this.width / 2 - 155 + 160, this.height - 27, I18n.format("menu.quit")));
        optionsRowList = new GuiButtonsList(mc, width, height, 32, height - 32, 25);
        optionsRowList.addButtons(makeButton(2, "TEST1"), makeButton(3, "TEST2"));
    }

    private GuiButton makeButton(int id, String label){
        return new GuiButton(id, width / 2 - 100, 0, label){
            @Override
            public boolean mousePressed(Minecraft mc, int mouseX, int mouseY){
                playPressSound(mc.getSoundHandler());
                try{
                    actionPerformed(this);
                } catch(IOException e){
                    e.printStackTrace();
                }
                return super.mousePressed(mc, mouseX, mouseY);
            }
        };
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException{
        if(mouseButton != 0 || !optionsRowList.mouseClicked(mouseX, mouseY, mouseButton))
            super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException{
        switch(button.id){
            case 0:
                mc.displayGuiScreen(new GuiMessageDialog(parentScreen, "fml.configgui.gameRestartTitle", new ChatComponentText(I18n.format("fml.configgui.gameRestartRequired")), "fml.configgui.confirmRestartMessage"));
                break;
            case 1:
                mc.displayGuiScreen(parentScreen);
                break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        drawDefaultBackground();
        optionsRowList.drawScreen(mouseX, mouseY, partialTicks);
        drawCenteredString(this.fontRendererObj, "Kina's Mods' Setting", this.width / 2, 5, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
