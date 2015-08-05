package com.mods.kina.KinaCore.movelib.modelloader.block;

import com.google.common.base.Function;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.ISmartBlockModel;
import net.minecraftforge.client.model.ISmartItemModel;

import java.util.List;

public class BlockSwitcherBakedModel implements IFlexibleBakedModel, ISmartBlockModel, ISmartItemModel{
    private final IUseKinaBlockModel block;
    private final IModelState modelState;
    private final VertexFormat format;
    private final Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter;
    private IFlexibleBakedModel parent;

    BlockSwitcherBakedModel(IUseKinaBlockModel block, IModelState modelState, VertexFormat format, Function<ResourceLocation,TextureAtlasSprite> bakedTextureGetter){
        this.block = block;
        this.modelState = modelState;
        this.format = format;
        this.bakedTextureGetter = bakedTextureGetter;
        parent = block.getModel(modelState, format, bakedTextureGetter, block.getDefaultState());
    }

    public List<BakedQuad> getFaceQuads(EnumFacing side){
        return parent.getFaceQuads(side);
    }

    public List<BakedQuad> getGeneralQuads(){
        return parent.getGeneralQuads();
    }

    public boolean isAmbientOcclusion(){
        return parent.isAmbientOcclusion();
    }

    public boolean isGui3d(){
        return parent.isGui3d();
    }

    public boolean isBuiltInRenderer(){
        return parent.isBuiltInRenderer();
    }

    public TextureAtlasSprite getTexture(){
        return parent.getTexture();
    }

    public ItemCameraTransforms getItemCameraTransforms(){
        return parent.getItemCameraTransforms();
    }

    public VertexFormat getFormat(){
        return parent.getFormat();
    }

    public IFlexibleBakedModel handleBlockState(IBlockState state){
        parent = block.getModel(modelState, format, bakedTextureGetter, state);
        parent = parent instanceof ISmartBlockModel ? (IFlexibleBakedModel) ((ISmartBlockModel) parent).handleBlockState(state) : parent;
        return this;
    }

    public IFlexibleBakedModel handleItemState(ItemStack stack){
        parent = block.getModel(modelState, format, bakedTextureGetter, stack);
        parent = parent instanceof ISmartItemModel ? (IFlexibleBakedModel) ((ISmartItemModel) parent).handleItemState(stack) : parent;
        return this;
    }
}
