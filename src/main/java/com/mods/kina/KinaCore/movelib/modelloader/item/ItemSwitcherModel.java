package com.mods.kina.KinaCore.movelib.modelloader.item;

import com.google.common.base.Function;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelState;

import java.util.Collection;
import java.util.Collections;

public class ItemSwitcherModel implements IModel{
    private final IUseKinaItemModel info;

    public ItemSwitcherModel(IUseKinaItemModel info){
        this.info = info;
    }

    public Collection<ResourceLocation> getDependencies(){
        return Collections.emptySet();
    }

    public Collection<ResourceLocation> getTextures(){
        return info.getTextures(info.getDefaultStack().copy());
    }

    public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter){
        return null;
    }

    public IModelState getDefaultState(){
        return ModelRotation.X0_Y0;
    }
}
