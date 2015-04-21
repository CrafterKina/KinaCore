package com.mods.kina.KinaCore.event.hooks.mod;

import com.google.common.collect.MapDifference;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ModStatusChangedEvent extends Event{
    public final ModContainer mod;
    public final MapDifference.ValueDifference<LoaderState.ModState> state;

    public ModStatusChangedEvent(ModContainer mod, MapDifference.ValueDifference<LoaderState.ModState> state){
        this.mod = mod;
        this.state = state;
    }
}
