package com.mods.kina.KinaCore.movelib.gui;

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
