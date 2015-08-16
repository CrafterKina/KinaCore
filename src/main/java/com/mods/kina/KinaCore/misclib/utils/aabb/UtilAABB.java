package com.mods.kina.KinaCore.misclib.utils.aabb;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.apache.commons.lang3.tuple.Pair;

public class UtilAABB{
    public static AxisAlignedBB getFixedAABB(BlockPos a, BlockPos b){
        return new AxisAlignedBB(a.getX(), a.getY(), a.getZ(), b.getX(), b.getY(), b.getZ());
    }

    public static AxisAlignedBB getBlocklizedAABB(AxisAlignedBB aabb, EnumFacing ignored){
        return aabb.addCoord(Math.pow(ignored.getFrontOffsetX() - 1, 2), Math.pow(ignored.getFrontOffsetY() - 1, 2), Math.pow(ignored.getFrontOffsetZ() - 1, 2));
    }

    public static double[] toArray(AxisAlignedBB aabb){
        return new double[]{aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ};
    }

    public static float[] toFloatArray(AxisAlignedBB aabb){
        return new float[]{(float) aabb.minX, (float) aabb.minY, (float) aabb.minZ, (float) aabb.maxX, (float) aabb.maxY, (float) aabb.maxZ};
    }

    public static Pair<? extends BlockPos,? extends BlockPos> toBlockPos(AxisAlignedBB aabb){
        return Pair.of(new BlockPos(aabb.minX, aabb.minY, aabb.minZ), new BlockPos(aabb.maxX, aabb.maxY, aabb.maxZ));
    }
}
