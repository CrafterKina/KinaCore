package com.mods.kina.KinaCore;

import com.mods.kina.KinaCore.movelib.O18n.O18nConfig;
import com.mods.kina.KinaCore.movelib.O18n.O18nRegister;
import com.mods.kina.KinaCore.movelib.autoswitch.AutoSwitch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

@Mod(modid = "kina_core")
public class KinaCoreMove/*  implements IFMLLoadingPlugin*/{

    public static File location;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        new AutoSwitch();
        O18nConfig.makeConfig(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        //new RemoveGrasses().replacePlains();
        O18nRegister.registerThing(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){ }

    /**
     Return a list of classes that implements the IClassTransformer interface

     @return a list of classes that implements the IClassTransformer interface
     *//*
    public String[] getASMTransformerClass()
    {
        return new String[]{"com.mods.kina.KinaCore.movelib.removeGrass.RemoveGrasses"};
    }

    *//**
     Return a class name that implements "ModContainer" for injection into the mod list The "getName" function should
     return a name that other mods can, if need be, depend on. Trivially, this modcontainer will be loaded before all
     regular mod containers, which means it will be forced to be "immutable" - not susceptible to normal sorting
     behaviour. All other mod behaviours are available however- this container can receive and handle normal loading
     events
     *//*
    public String getModContainerClass()
    {
        return "com.mods.kina.KinaCore.KinaCoreModContainer";
    }

    *//**
     Return the class name of an implementor of "IFMLCallHook", that will be run, in the main thread, to perform any
     additional setup this coremod may require. It will be run <strong>prior</strong> to Minecraft starting, so it CANNOT
     operate on minecraft itself. The game will deliberately crash if this code is detected to trigger a minecraft class
     loading (TODD: implement crash ;) )
     *//*
    public String getSetupClass(){
        return null;
    }

    *//**
     Inject coremod data into this coremod This data includes: "mcLocation" : the location of the minecraft directory,
     "coremodList" : the list of coremods "coremodLocation" : the file this coremod loaded from,

     @param data
     *//*
    public void injectData(Map<String, Object> data)
    {
        if (data.containsKey("coremodLocation"))
        {
            location = (File) data.get("coremodLocation");
        }
    }

    *//**
     Return an optional access transformer class for this coremod. It will be injected post-deobf so ensure your ATs
     conform to the new srgnames scheme.

     @return the name of an access transformer class or null if none is provided
     *//*
    public String getAccessTransformerClass(){
        return null;
    }*/
}
