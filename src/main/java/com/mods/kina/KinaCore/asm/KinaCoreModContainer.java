package com.mods.kina.KinaCore.asm;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

import java.util.Collections;

public class KinaCoreModContainer extends DummyModContainer{
    public KinaCoreModContainer(){
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "kina_core";
        meta.name = "KinaCore";
        meta.version = "1.0.1";
        meta.authorList = Collections.singletonList("CrafterKina");
        meta.description = "";
        meta.url = "";
        meta.credits = "";
        setEnabledState(true);
    }

    public boolean registerBus(EventBus bus, LoadController controller){
        bus.register(this);
        return true;
    }
}
