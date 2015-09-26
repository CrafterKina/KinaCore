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

package jp.crafterkina.KinaCore.misclib.utils.blockpos;

import com.google.common.collect.AbstractIterator;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import java.util.Iterator;

public class UtilBlockPos{
    public static BlockPos getFrontPosWithDistance(EnumFacing facing, BlockPos pos, int distance){
        return pos.add(facing.getFrontOffsetX() * distance, facing.getFrontOffsetY() * distance, facing.getFrontOffsetZ() * distance);
    }

    public static BlockPos getFrontPos(EnumFacing facing, BlockPos pos){
        return getFrontPosWithDistance(facing, pos, 1);
    }

    public static Iterable getAllInBox(final BlockPos from, final BlockPos to){
        final EnumFacing dir = EnumFacing.getFacingFromVector(to1(to.getX() - from.getX()), to1(to.getY() - from.getY()), to1(to.getZ() - from.getZ()));
        return new Iterable(){
            public Iterator iterator(){
                return new AbstractIterator(){
                    private BlockPos lastReturned = null;

                    protected Object computeNext(){
                        return to.equals(this.lastReturned) ? endOfData() : (lastReturned = (lastReturned == null) ? from : getFrontPos(dir, lastReturned));
                    }
                };
            }
        };
    }


    private static int to1(int raw){
        return raw == 0 ? 0 : raw < 0 ? -1 : 1;
    }
}
