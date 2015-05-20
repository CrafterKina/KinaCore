package com.mods.kina.KinaCore.event.hooks.mod;

import com.google.common.collect.MapDifference;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Collections;
import java.util.Map;

public class ModStatusChangedEvent extends Event{
    public final Map<ModContainer,LoaderState.ModState> prevStateMap;
    public final Map<ModContainer,LoaderState.ModState> stateMap;
    @Deprecated
    public final ModContainer mod;
    @Deprecated
    public final MapDifference.ValueDifference<LoaderState.ModState> state;

    @Deprecated
    public ModStatusChangedEvent(Map<ModContainer,LoaderState.ModState> stateMap, ModContainer mod, MapDifference.ValueDifference<LoaderState.ModState> state){
        prevStateMap = Collections.emptyMap();
        this.stateMap = stateMap;
        this.mod = mod;
        this.state = state;
    }

    public ModStatusChangedEvent(Map<ModContainer,LoaderState.ModState> prevStateMap, Map<ModContainer,LoaderState.ModState> stateMap){
        this.prevStateMap = prevStateMap;
        this.stateMap = stateMap;
        mod = null;
        state = null;
    }
}
