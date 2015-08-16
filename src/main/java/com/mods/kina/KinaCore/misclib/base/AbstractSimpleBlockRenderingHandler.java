package com.mods.kina.KinaCore.misclib.base;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.*;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import java.util.Collections;
import java.util.List;

public abstract class AbstractSimpleBlockRenderingHandler implements IFlexibleBakedModel, ISmartBlockModel, ISmartItemModel, IPerspectiveAwareModel{
    private IBlockState blockstate = null;
    private ItemStack itemstack = null;
    private ItemCameraTransforms.TransformType transformType = null;

    ////////////abstract/////////////

    protected abstract void renderBlock(IBlockState state);

    protected abstract List<BakedQuad> simpleBlockModel(IBlockState state);

    protected abstract void renderItem(ItemStack stack, ItemCameraTransforms.TransformType type);

    protected abstract List<BakedQuad> simpleItemModel(ItemStack stack, ItemCameraTransforms.TransformType type);

    protected abstract Matrix4f viewpointMatrix(ItemCameraTransforms.TransformType transformType);

    protected abstract AbstractSimpleBlockRenderingHandler copy(AbstractSimpleBlockRenderingHandler origin);

    public abstract TextureAtlasSprite getTexture();

    ////////////set&get/////////////

    protected IBlockState getBlockstate(){
        return blockstate;
    }

    protected void setBlockstate(IBlockState blockstate){
        this.blockstate = blockstate;
    }

    protected ItemStack getItemstack(){
        return itemstack;
    }

    protected void setItemstack(ItemStack itemstack){
        this.itemstack = itemstack;
    }

    protected ItemCameraTransforms.TransformType getTransformType(){
        return transformType;
    }

    protected void setTransformType(ItemCameraTransforms.TransformType transformType){
        this.transformType = transformType;
    }

    ////////////impls/////////////
    @Override
    public final List<BakedQuad> getFaceQuads(EnumFacing side){
        return Collections.emptyList();
    }

    @Override
    public final List<BakedQuad> getGeneralQuads(){
        if(getBlockstate() == null && ((getItemstack() == null) || getTransformType() == null))
            return Collections.emptyList();

        Tessellator.getInstance().getWorldRenderer().finishDrawing();

        GlStateManager.pushMatrix();
        {
            if(getBlockstate() != null) renderBlock(getBlockstate());
            else renderItem(getItemstack(), getTransformType());
        }
        GlStateManager.popMatrix();

        Tessellator.getInstance().getWorldRenderer().startDrawingQuads();
        Tessellator.getInstance().getWorldRenderer().setVertexFormat(DefaultVertexFormats.ITEM);
        return getBlockstate() != null ? simpleBlockModel(getBlockstate()) : simpleItemModel(getItemstack(), getTransformType());
    }

    @Override
    public boolean isAmbientOcclusion(){
        return true;
    }

    @Override
    public boolean isGui3d(){
        return true;
    }

    @Override
    public final boolean isBuiltInRenderer(){
        return false;
    }

    @Override
    public final ItemCameraTransforms getItemCameraTransforms(){
        return ItemCameraTransforms.DEFAULT;
    }

    @Override
    public VertexFormat getFormat(){
        return Attributes.DEFAULT_BAKED_FORMAT;
    }

    @Override
    public final Pair<IBakedModel,Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType){
        AbstractSimpleBlockRenderingHandler copy = copy(this);
        copy.setTransformType(cameraTransformType);
        copy.setItemstack(getItemstack());
        return Pair.of((IBakedModel) copy, copy.viewpointMatrix(cameraTransformType));
    }

    @Override
    public IFlexibleBakedModel handleBlockState(IBlockState state){
        AbstractSimpleBlockRenderingHandler copy = copy(this);
        copy.setBlockstate(state);
        return copy;
    }

    @Override
    public IFlexibleBakedModel handleItemState(ItemStack stack){
        AbstractSimpleBlockRenderingHandler copy = copy(this);
        copy.setItemstack(stack);
        copy.setTransformType(getTransformType());
        return copy;
    }
}