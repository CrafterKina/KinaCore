package com.mods.kina.KinaCore.misclib.utils.render;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nullable;
import java.util.Map;

public class UtilRender{
    public static final FaceBakery faceBakery = new FaceBakery();
    public static final BlockRendererDispatcher rendererDispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
    public static final BlockModelRenderer blockRenderer = rendererDispatcher.getBlockModelRenderer();
    public static final RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
    public static final ItemRenderer itemRenderer = Minecraft.getMinecraft().getItemRenderer();
    public static final Tessellator tessellator = Tessellator.getInstance();
    public static final WorldRenderer worldRenderer = tessellator.getWorldRenderer();

    public static Map<EnumFacing,BakedQuad> bakeAllFaceQuads(final BakedQuadBuilder builder){
        return Maps.asMap(Sets.newHashSet(EnumFacing.values()), new Function<EnumFacing,BakedQuad>(){
            @Nullable
            @Override
            public BakedQuad apply(@Nullable EnumFacing input){
                return builder.build(input);
            }
        });
    }
}