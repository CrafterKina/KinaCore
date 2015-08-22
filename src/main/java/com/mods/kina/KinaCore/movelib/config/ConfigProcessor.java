package com.mods.kina.KinaCore.movelib.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import java.io.File;
import java.util.regex.Pattern;

public class ConfigProcessor{
    public static void makeConfigFile(File cfgDir, String fileName, Pattern classNameSuffix){
        fileName += fileName.matches(".+\\.[a-zA-z0-9]+") ? "" : ".cfg";
        ModContainer mc = Loader.instance().activeModContainer();
        Configuration cfg = new Configuration(new File(cfgDir, "kina/" + mc.getName() + "/" + fileName));
        try{
            cfg.load();
            ElementProcessor processor = new ElementProcessor();
            processor.process(classNameSuffix);
            ValueAssigner.assignFields(cfg, processor.getContainers());
        } catch(Exception e){
            e.printStackTrace();
        }
        cfg.save();
    }
}
