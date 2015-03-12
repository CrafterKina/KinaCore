package com.mods.kina.KinaCore.asm;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

public class KinaCoreModContainer extends DummyModContainer{
    public KinaCoreModContainer(){
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "kina_core";
        setEnabledState(true);
    }

    public boolean registerBus(EventBus bus, LoadController controller){
        bus.register(this);
        return true;
    }
}
