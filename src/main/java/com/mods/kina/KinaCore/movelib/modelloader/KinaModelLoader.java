package com.mods.kina.KinaCore.movelib.modelloader;

import com.mods.kina.KinaCore.movelib.modelloader.block.BlockSwitcherModel;
import com.mods.kina.KinaCore.movelib.modelloader.block.IUseKinaBlockModel;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;

public class KinaModelLoader implements ICustomModelLoader{
    public boolean accepts(ResourceLocation modelLocation){
        return modelLocation.getResourceDomain().equals("kina_misc") && modelLocation.getResourcePath().endsWith(".class");
    }

    public IModel loadModel(ResourceLocation modelLocation){
        Object instance;
        try{
            instance = toClass(modelLocation.getResourcePath()).newInstance();
        } catch(InstantiationException e){
            throw new AssertionError(new IllegalArgumentException(e));
        } catch(IllegalAccessException e){
            throw new AssertionError(new IllegalArgumentException(e));
        }

        if(instance instanceof IUseKinaBlockModel){
            return new BlockSwitcherModel((IUseKinaBlockModel) instance);
        }

        return null;
    }

    public void onResourceManagerReload(IResourceManager resourceManager){

    }

    @SuppressWarnings("unchecked")
    private <T> Class<T> toClass(String rawPath){
        String path = rawPath.replaceFirst("models/", "");
        try{
            return (Class<T>) Class.forName(path);
        } catch(ClassNotFoundException e){
            throw new AssertionError(new IllegalArgumentException(e));
        }
    }
}
