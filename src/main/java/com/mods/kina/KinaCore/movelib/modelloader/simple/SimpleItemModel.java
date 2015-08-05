package com.mods.kina.KinaCore.movelib.modelloader.simple;

import com.google.common.base.Function;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelState;

import java.util.Collection;

public class SimpleItemModel implements IModel{
    public Collection<ResourceLocation> getDependencies(){
        return null;
    }

    public Collection<ResourceLocation> getTextures(){
        return null;
    }

    public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter){
        return null;
    }

    public IModelState getDefaultState(){
        return null;
    }
}
