package com.mods.kina.KinaCore;

import com.mods.kina.KinaCore.movelib.O18n.O18nConfig;
import com.mods.kina.KinaCore.movelib.O18n.O18nRegister;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "kina_Core")
public class KinaCoreMove{
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        O18nConfig.makeConfig(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        O18nRegister.registerThing(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){
    }
}
