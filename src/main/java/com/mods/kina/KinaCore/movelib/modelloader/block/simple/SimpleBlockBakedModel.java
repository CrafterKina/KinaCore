package com.mods.kina.KinaCore.movelib.modelloader.block.simple;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.mods.kina.KinaCore.movelib.modelloader.block.IUseKinaBlockModel;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.ISmartBlockModel;
import net.minecraftforge.client.model.ISmartItemModel;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import javax.vecmath.Vector3f;
import java.util.Collections;
import java.util.List;

public class SimpleBlockBakedModel implements IFlexibleBakedModel, ISmartBlockModel, ISmartItemModel{
    private final IUseKinaBlockModel block;
    private final Function<ResourceLocation,TextureAtlasSprite> textures;
    private final IBlockState blockState;
    private FaceBakery faceBakery = new FaceBakery();

    public SimpleBlockBakedModel(IUseKinaBlockModel blockIn, Function<ResourceLocation,TextureAtlasSprite> texturesIn, IBlockState blockState){
        block = blockIn;
        textures = texturesIn;
        this.blockState = blockState;
    }

    public List<BakedQuad> getFaceQuads(final EnumFacing side){
        //面の始点
        final Vector3f from = new Vector3f(0, 0, 0);

        //面の終点
        final Vector3f to = new Vector3f(16, 16, 16);

        //TextureのUVの指定
        final BlockFaceUV uv = new BlockFaceUV(new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0);

        return Lists.transform(block.getTextures(side, blockState), new Function<Pair<Integer,ResourceLocation>,BakedQuad>(){
            @Nullable
            @Override
            public BakedQuad apply(Pair<Integer,ResourceLocation> input){
                BlockPartFace partFace = new BlockPartFace(side, input.getLeft(), "", uv);
                return faceBakery.makeBakedQuad(from, to, partFace, textures.apply(input.getRight()), side, ModelRotation.X0_Y0, null, true, true);
            }
        });
    }

    public List<BakedQuad> getGeneralQuads(){
        return Collections.emptyList();
    }

    public boolean isAmbientOcclusion(){
        return true;
    }

    public boolean isGui3d(){
        return true;
    }

    public boolean isBuiltInRenderer(){
        return false;
    }

    public TextureAtlasSprite getTexture(){
        return textures.apply(block.getTextures(null, blockState).get(0).getRight());
    }

    public ItemCameraTransforms getItemCameraTransforms(){
        return ItemCameraTransforms.DEFAULT;
    }

    public VertexFormat getFormat(){
        return DefaultVertexFormats.BLOCK;
    }

    public IFlexibleBakedModel handleBlockState(IBlockState state){
        return new SimpleBlockBakedModel(block, textures, state);
    }

    public IFlexibleBakedModel handleItemState(ItemStack stack){
        return this;
    }
}
