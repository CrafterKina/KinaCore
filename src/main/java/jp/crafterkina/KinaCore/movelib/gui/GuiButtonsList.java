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

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

@SuppressWarnings("unchecked")
public class GuiButtonsList extends GuiListExtended{
    private final List<Row> buttons = Lists.newArrayList();

    public GuiButtonsList(Minecraft mcIn, int width, int height, int topIn, int bottomIn, int buttonHeightIn){
        super(mcIn, width, height, topIn, bottomIn, buttonHeightIn);
        this.field_148163_i = false;
    }

    public void addRows(Row... rows){
        buttons.addAll(Lists.newArrayList(rows));
    }

    public void addRows(GuiButton[]... rows){
        buttons.addAll(Lists.transform(Lists.newArrayList(rows), new Function<GuiButton[],Row>(){
            @Nullable
            @Override
            public Row apply(GuiButton[] input){
                return new Row(input);
            }
        }));
    }

    public void addButtons(GuiButton... buttons1){
        buttons.addAll(Lists.transform(Lists.newArrayList(buttons1), new Function<GuiButton,Row>(){
            @Nullable
            @Override
            public Row apply(@Nullable GuiButton input){
                return new Row(input);
            }
        }));
    }

    protected int getSize(){
        return this.buttons.size();
    }

    /**
     Gets the width of the list
     */
    public int getListWidth(){
        return 400;
    }

    protected int getScrollBarX(){
        return super.getScrollBarX() + 32;
    }

    /**
     Gets the IGuiListEntry object for the given index
     */
    public GuiListExtended.IGuiListEntry getListEntry(int index){
        return buttons.get(index);
    }

    @SideOnly(Side.CLIENT)
    private class Row implements GuiListExtended.IGuiListEntry{
        private final Minecraft mc = Minecraft.getMinecraft();
        private final GuiButton[] buttons;

        public Row(GuiButton... p_i45014_1_){
            buttons = p_i45014_1_;
        }

        public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected){
            for(GuiButton button : buttons){
                if(button == null) continue;
                button.yPosition = y;
                button.drawButton(mc, mouseX, mouseY);
            }
        }

        /**
         Returns true if the mouse has been pressed on this control.
         */
        public boolean mousePressed(int p_148278_1_, int mouseX, int mouseY, int p_148278_4_, int p_148278_5_, int p_148278_6_){
            for(GuiButton button : buttons){
                if(button.mousePressed(mc, mouseX, mouseY)) return true;
            }
            return false;
        }

        /**
         Fired when the mouse button is released. Arguments: index, x, y, mouseEvent, relativeX, relativeY
         */
        public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY){
            for(GuiButton button : buttons){
                if(button != null) button.mouseReleased(x, y);
            }
        }

        public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_){}
    }
}
