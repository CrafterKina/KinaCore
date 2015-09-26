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

package jp.crafterkina.KinaCore;

import com.google.common.collect.Lists;
import jp.crafterkina.KinaCore.misclib.base.fle.IFMLStateEvents;
import jp.crafterkina.KinaCore.movelib.O18n.O18nHooks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;

import java.util.Collections;
import java.util.List;

@Mod(modid = "kina_misc", guiFactory = "jp.crafterkina.KinaCore.movelib.gui.KinaCoreGuiFactory")
public class KinaCoreMove implements IFMLStateEvents{
    private List<? extends IFMLStateEvents> handlers;

    @Mod.EventHandler
    public void construction(FMLConstructionEvent event){
        handlers = Collections.unmodifiableList(Lists.newArrayList(new O18nHooks()/*,new EffectHooks()*/));
        for(IFMLStateEvents handler : handlers){
            handler.construction(event);
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        for(IFMLStateEvents handler : handlers){
            handler.preInit(event);
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        for(IFMLStateEvents handler : handlers){
            handler.init(event);
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
        for(IFMLStateEvents handler : handlers){
            handler.postInit(event);
        }
    }

    @Mod.EventHandler
    public void complete(FMLLoadCompleteEvent event){
        for(IFMLStateEvents handler : handlers){
            handler.complete(event);
        }
    }
}
