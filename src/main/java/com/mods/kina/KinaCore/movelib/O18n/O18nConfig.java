package com.mods.kina.KinaCore.movelib.O18n;

import com.mods.kina.KinaCore.movelib.config.ConfigProcessor;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.regex.Pattern;

public class O18nConfig{
    public static void makeConfig(FMLPreInitializationEvent event){
        O18nField.class.getCanonicalName();
        ConfigProcessor.makeConfigFile(event.getModConfigurationDirectory(), "OverwriteInformationMod.cfg", Pattern.compile("com\\.mods\\.kina\\.KinaCore\\.movelib\\.O18n\\.O18nField"));
    }
}
