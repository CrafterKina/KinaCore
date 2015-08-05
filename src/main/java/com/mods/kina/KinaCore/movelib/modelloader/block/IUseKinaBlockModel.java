package com.mods.kina.KinaCore.movelib.modelloader.block;

import com.google.common.base.Function;
import com.mods.kina.KinaCore.movelib.modelloader.block.simple.SimpleBlockBakedModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModelState;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.List;

public interface IUseKinaBlockModel{
    Collection<ResourceLocation> registerTextures(Collection<ResourceLocation> locations);

    List<Pair<Integer,ResourceLocation>> getTextures(EnumFacing side, IBlockState state);

    IBlockState getDefaultState();

    IBlockState toBlockState(ItemStack stack);

    ItemStack createStackedBlock(IBlockState state);

    IFlexibleBakedModel getModel(IModelState modelState, VertexFormat format, Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter, IBlockState blockState);

    IFlexibleBakedModel getModel(IModelState modelState, VertexFormat format, Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter, ItemStack itemStack);

    enum Type{
        Simple{
            IFlexibleBakedModel newInstance(IUseKinaBlockModel block, IModelState modelState, VertexFormat format, Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter, IBlockState blockState){
                return new SimpleBlockBakedModel(block, bakedTextureGetter, blockState);
            }

            IFlexibleBakedModel newInstance(IUseKinaBlockModel block, IModelState modelState, VertexFormat format, Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter, ItemStack itemStack){
                return newInstance(block, modelState, format, bakedTextureGetter, block.toBlockState(itemStack));
            }
        },;

        abstract IFlexibleBakedModel newInstance(IUseKinaBlockModel block, IModelState modelState, VertexFormat format, Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter, IBlockState blockState);

        abstract IFlexibleBakedModel newInstance(IUseKinaBlockModel block, IModelState modelState, VertexFormat format, Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter, ItemStack itemStack);
    }
}
