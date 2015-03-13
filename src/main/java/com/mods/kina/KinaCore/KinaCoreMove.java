package com.mods.kina.KinaCore;

import com.mods.kina.KinaCore.movelib.O18n.O18nConfig;
import com.mods.kina.KinaCore.movelib.O18n.O18nRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "kina_misc")
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
