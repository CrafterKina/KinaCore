package com.mods.kina.KinaCore.asm;

import com.google.common.base.Function;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mods.kina.KinaCore.event.hooks.mod.ModStatusChangedEvent;
import com.mods.kina.KinaCore.misclib.KinaLib;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.relauncher.IFMLCallHook;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class PrePreInit implements IFMLCallHook{
    public void injectData(Map<String,Object> data){

    }

    public Void call() throws Exception{
        new Thread(){
            Map<ModContainer,LoaderState.ModState> prevStateMap = Maps.newHashMap();

            public void run(){
                while(true){
                    List<ModContainer> mods;
                    try{
                        Class.forName("net.minecraftforge.fml.common.Loader");
                        mods = Loader.instance().getModList();
                    } catch(Throwable e){
                        continue;
                    }
                    Map<ModContainer,LoaderState.ModState> stateMap = Maps.asMap(Sets.newHashSet(mods), new Function<ModContainer,LoaderState.ModState>(){
                        @Nullable
                        public LoaderState.ModState apply(ModContainer input){
                            return Loader.instance().getModState(input);
                        }
                    });
                    Map<ModContainer,MapDifference.ValueDifference<LoaderState.ModState>> differenceMap = Maps.difference(stateMap, prevStateMap).entriesDiffering();
                    for(ModContainer key : differenceMap.keySet()){
                        MinecraftForge.EVENT_BUS.post(new ModStatusChangedEvent(key, differenceMap.get(key)));
                    }
                    if(KinaLib.lib.allValueEqual(stateMap, LoaderState.ModState.POSTINITIALIZED)) break;
                    prevStateMap = stateMap;
                }
            }
        }.start();
        return null;
    }
}
