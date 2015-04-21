package com.mods.kina.KinaCore.misclib;

import com.google.common.collect.AbstractIterator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import java.util.Iterator;
import java.util.Map;

public class KinaLib{
    public static final KinaLib lib = new KinaLib();

    public boolean areItemStackEqual(ItemStack a, ItemStack b){
        return ItemStack.areItemsEqual(a, b) && ItemStack.areItemStackTagsEqual(a, b);
    }

    public <K, V> V putIfAbsent(Map<K,V> map, K key, V value){
        V v = map.get(key);
        if(v == null){
            map.put(key, value);
            v = value;
        }

        return v;
    }

    public <V> boolean allValueEqual(Map<?,V> map, V value){
        Iterator<? extends Map.Entry<?,V>> i = map.entrySet().iterator();
        if(value == null){
            while(i.hasNext()){
                Map.Entry<?,V> e = i.next();
                if(e.getValue() != null) return false;
            }
        }else{
            while(i.hasNext()){
                Map.Entry<?,V> e = i.next();
                if(!value.equals(e.getValue())) return false;
            }
        }
        return true;
    }

    public BlockPos getFrontPosWithDistance(EnumFacing facing, BlockPos pos, int distance){
        return pos.add(facing.getFrontOffsetX() * distance, facing.getFrontOffsetY() * distance, facing.getFrontOffsetZ() * distance);
    }

    public BlockPos getFrontPos(EnumFacing facing, BlockPos pos){
        return getFrontPosWithDistance(facing, pos, 1);
    }

    public AxisAlignedBB getFixedAABB(BlockPos a, BlockPos b){
        return new AxisAlignedBB(a.getX(), a.getY(), a.getZ(), b.getX(), b.getY(), b.getZ());
    }

    public AxisAlignedBB getBlocklizedAABB(AxisAlignedBB aabb, EnumFacing ignored){
        return aabb.addCoord(ignored.getFrontOffsetX() == 1 ? 0 : 1, ignored.getFrontOffsetY() == 1 ? 0 : 1, ignored.getFrontOffsetZ() == 1 ? 0 : 1);
    }

    public Iterable getAllInBox(final BlockPos from, final BlockPos to){
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

    private int to1(int raw){
        return raw == 0 ? 0 : raw < 0 ? -1 : 1;
    }
}
