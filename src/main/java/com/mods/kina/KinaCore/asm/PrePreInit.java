package com.mods.kina.KinaCore.asm;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mods.kina.KinaCore.event.hooks.mod.ModStatusChangedEvent;
import com.mods.kina.KinaCore.misclib.KinaLib;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.relauncher.IFMLCallHook;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PrePreInit implements IFMLCallHook{
    private LaunchClassLoader loader;

    public void injectData(Map<String,Object> data){
        loader = (LaunchClassLoader) data.get("classLoader");
    }

    public Void call() throws Exception{
        /*Downloader downloader = new Downloader();
        while(downloader.isVisible()||downloader.isShowing()){
            System.out.printf("\r%b,%b\n",downloader.isVisible(),downloader.isShowing());
        }
        System.out.println("GUI EXIT");*/
        //checkModState();
        return null;
    }

    private void checkModState(){
        new Thread(){
            Map<ModContainer,LoaderState.ModState> prevStateMap = Maps.newHashMap();
            public void run(){
                while(true){
                    try{
                        loader.findClass(Loader.class.getName());
                        //loader.findClass(KinaLib.class.getName());
                        Loader.instance().getModList();
                    } catch(Throwable ignored){
                        continue;
                    }
                    break;
                }
                while(true){
                    List<ModContainer> mods = Loader.instance().getModList();
                    final Map<ModContainer,LoaderState.ModState> stateMap = Maps.newHashMap(Maps.asMap(Sets.newHashSet(mods), new Function<ModContainer,LoaderState.ModState>(){
                        public LoaderState.ModState apply(ModContainer input){
                            return Loader.instance().getModState(input);
                        }
                    }));
                    if(!Objects.equals(prevStateMap, stateMap)){
                        new Thread(){
                            @Override
                            public void run(){
                                MinecraftForge.EVENT_BUS.post(new ModStatusChangedEvent(prevStateMap, stateMap));
                            }
                        }.start();
                    }
                    if(!mods.isEmpty() && KinaLib.lib.allValueEqual(stateMap, LoaderState.ModState.POSTINITIALIZED)){
                        new Thread(){
                            @Override
                            public void run(){
                                MinecraftForge.EVENT_BUS.post(new ModStatusChangedEvent(prevStateMap, stateMap));
                            }
                        }.start();
                        break;
                    }
                    prevStateMap = stateMap;
                }
            }
        }/*.start()*/;
    }
}
