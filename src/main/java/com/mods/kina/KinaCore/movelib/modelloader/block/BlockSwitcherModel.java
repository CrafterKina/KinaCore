package com.mods.kina.KinaCore.movelib.modelloader.block;

import com.google.common.base.Function;
import com.google.common.collect.Sets;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelState;

import java.util.Collection;
import java.util.Collections;

public class BlockSwitcherModel implements IModel{
    private final IUseKinaBlockModel info;

    public BlockSwitcherModel(IUseKinaBlockModel info){
        this.info = info;
    }

    public Collection<ResourceLocation> getDependencies(){
        return Collections.emptySet();
    }

    public Collection<ResourceLocation> getTextures(){
        return info.registerTextures(Sets.<ResourceLocation>newHashSet());
    }

    public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter){
        return new BlockSwitcherBakedModel(info, state, format, bakedTextureGetter);
    }

    public IModelState getDefaultState(){
        return ModelRotation.X0_Y0;
    }
}
