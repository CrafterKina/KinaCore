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
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

import java.util.Set;

public class KinaCoreGuiFactory implements IModGuiFactory{
    /**
     Called when instantiated to initialize with the active minecraft instance.

     @param minecraftInstance
     the instance
     */
    public void initialize(Minecraft minecraftInstance){
        minecraftInstance.getVersion();
    }

    /**
     Return the name of a class extending {@link net.minecraft.client.gui.GuiScreen}. This class will be instantiated
     when the "config" button is pressed in the mod list. It will have a single argument constructor - the "parent"
     screen, the same as all Minecraft GUIs. The expected behaviour is that this screen will replace the "mod list"
     screen completely, and will return to the mod list screen through the parent link, once the appropriate action is
     taken from the config screen.
     <p/>
     A null from this method indicates that the mod does not provide a "config" button GUI screen, and the config button
     will be hidden/disabled.
     <p/>
     This config GUI is anticipated to provide configuration to the mod in a friendly visual way. It should not be abused
     to set internals such as IDs (they're gonna keep disappearing anyway), but rather, interesting behaviours. This
     config GUI is never run when a server game is running, and should be used to configure desired behaviours that
     affect server state. Costs, mod game modes, stuff like that can be changed here.

     @return A class that will be instantiated on clicks on the config button or null if no GUI is desired.
     */
    public Class<? extends GuiScreen> mainConfigGuiClass(){
        return ModGuiMain.class;
    }

    /**
     Return a list of the "runtime" categories this mod wishes to populate with GUI elements.
     <p/>
     Runtime categories are created on demand and organized in a 'lite' tree format. The parent represents the parent
     node in the tree. There is one special parent 'Help' that will always list first, and is generally meant to provide
     Help type content for mods. The remaining parents will sort alphabetically, though this may change if there is a lot
     of alphabetic abuse. "AAA" is probably never a valid category parent.
     <p/>
     Runtime configuration itself falls into two flavours: in-game help, which is generally non interactive except for
     the text it wishes to show, and client-only affecting behaviours. This would include things like toggling minimaps,
     or cheat modes or anything NOT affecting the behaviour of the server. Please don't abuse this to change the state of
     the server in any way, this is intended to behave identically when the server is local or remote.

     @return the set of options this mod wishes to have available, or empty if none
     */
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories(){
        return null;
    }

    /**
     Return an instance of a {@link net.minecraftforge.fml.client.IModGuiFactory.RuntimeOptionGuiHandler} that handles
     painting the right hand side option screen for the specified {@link net.minecraftforge.fml.client.IModGuiFactory.RuntimeOptionCategoryElement}.

     @param element
     The element we wish to paint for
     @return The Handler for painting it
     */
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element){
        return null;
    }
}
