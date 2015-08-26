/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *  Neither the name of the CrafterKina nor the　names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CrafterKina BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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