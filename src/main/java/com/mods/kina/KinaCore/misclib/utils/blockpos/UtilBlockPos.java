package com.mods.kina.KinaCore.misclib.utils.blockpos;

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
