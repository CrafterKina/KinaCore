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

package jp.crafterkina.KinaCore.misclib.utils.render;

import jp.crafterkina.KinaCore.misclib.utils.aabb.UtilAABB;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.BlockPartRotation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ITransformation;

import javax.vecmath.Vector3f;
import java.util.Arrays;

public class BakedQuadBuilder{
    private Vector3f from = new Vector3f(0, 0, 0);
    private Vector3f to = new Vector3f(16, 16, 16);
    private int tintindex = -1;
    private float[] uv = new float[]{0, 0, 16, 16};
    private int textureRotation = 0;
    private TextureAtlasSprite texture;
    private ITransformation modelRotation = ModelRotation.X0_Y0;
    private BlockPartRotation partRotation = null;
    private boolean uvLocked = true;
    private boolean shade = true;

    public BakedQuadBuilder(TextureAtlasSprite texture){
        this.texture = texture;
    }

    public BakedQuad build(EnumFacing facing){
        return UtilRender.faceBakery.makeBakedQuad(from, to, new BlockPartFace(EnumFacing.NORTH, tintindex, "", new BlockFaceUV(uv, textureRotation)), texture, facing, modelRotation, partRotation, uvLocked, shade);
    }

    public BakedQuadBuilder setQuadsCube(AxisAlignedBB quadsCube){
        float[] floats = UtilAABB.toFloatArray(quadsCube);
        from = new Vector3f(Arrays.copyOfRange(floats, 0, 3));
        to = new Vector3f(Arrays.copyOfRange(floats, 3, 6));
        return this;
    }

    public BakedQuadBuilder setTintindex(int tintindex){
        this.tintindex = tintindex;
        return this;
    }

    public BakedQuadBuilder setUV(float[] uv){
        this.uv = uv;
        return this;
    }

    public BakedQuadBuilder setTextureRotation(int textureRotation){
        this.textureRotation = textureRotation;
        return this;
    }

    public BakedQuadBuilder setModelRotation(ITransformation modelRotation){
        this.modelRotation = modelRotation;
        return this;
    }

    public BakedQuadBuilder setPartRotation(BlockPartRotation partRotation){
        this.partRotation = partRotation;
        return this;
    }

    public BakedQuadBuilder setUvLocked(boolean uvLocked){
        this.uvLocked = uvLocked;
        return this;
    }

    public BakedQuadBuilder setShade(boolean shade){
        this.shade = shade;
        return this;
    }
}
