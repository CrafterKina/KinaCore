package com.mods.kina.KinaCore.movelib.modelloader.item;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.ISmartItemModel;

import java.util.List;

public class ItemSwitcherBakedModel implements IFlexibleBakedModel, ISmartItemModel{
    public List<BakedQuad> getFaceQuads(EnumFacing side){
        return null;
    }

    public List<BakedQuad> getGeneralQuads(){
        return null;
    }

    public boolean isAmbientOcclusion(){
        return false;
    }

    public boolean isGui3d(){
        return false;
    }

    public boolean isBuiltInRenderer(){
        return false;
    }

    public TextureAtlasSprite getTexture(){
        return null;
    }

    public ItemCameraTransforms getItemCameraTransforms(){
        return null;
    }

    public VertexFormat getFormat(){
        return null;
    }

    public IBakedModel handleItemState(ItemStack stack){
        return null;
    }
}
