package com.mods.kina.KinaCore.movelib.config;

import com.google.common.base.Function;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;

public class ConfigProcessor{
    public void makeConfigFile(File cfgDir, String fileName, String classNameSuffix){
        fileName += fileName.endsWith("\\.[a-zA-z0-9]+") ? "" : ".cfg";
        ModContainer mc = Loader.instance().activeModContainer();
        Configuration cfg = new Configuration(new File(cfgDir, "kina/" + mc.getName() + "/" + fileName));
        try{
            cfg.load();
            for(Pair<String,Function<Configuration,Void>> nameAssignerPair : ValueAssigner.getFieldAssigner(classNameSuffix)){
                nameAssignerPair.getRight().apply(cfg);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        cfg.save();
    }
}
