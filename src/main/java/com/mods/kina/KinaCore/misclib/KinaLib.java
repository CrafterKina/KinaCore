package com.mods.kina.KinaCore.misclib;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.AbstractIterator;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.RegistrySimple;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public enum KinaLib{
    lib;

    public boolean areItemStacksEqual(ItemStack a, ItemStack b){
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

    public <K, V> V getOrDefault(Map<K,V> map, K key, V defaultValue){
        V v;
        return (((v = map.get(key)) != null) || map.containsKey(key)) ? v : defaultValue;
    }

    public <W> W wrapDefault(W value, W defaultValue){
        return Objects.firstNonNull(value, defaultValue);
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
        return aabb.addCoord(Math.pow(ignored.getFrontOffsetX() - 1, 2), Math.pow(ignored.getFrontOffsetY() - 1, 2), Math.pow(ignored.getFrontOffsetZ() - 1, 2));
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

    public boolean areItemStacksEqualForRecipe(ItemStack l, ItemStack r){
        if(l != null || r != null){
            if(l == null || r == null){
                return false;
            }

            if(r.getItem() != l.getItem()){
                return false;
            }

            if(r.getMetadata() != 32767 && r.getMetadata() != l.getMetadata()){
                return false;
            }
        }
        return true;
    }

    public void normalizeItemStacks(ItemStack[] args){
        for(int i = 0; i < args.length; i++){
            ItemStack arg = args[i];
            args[i] = arg == null || arg.stackSize <= 0 ? null : arg;
        }
    }

    public int cycleNumber(int now, int min, int max){
        if(max < ++now){
            now = min;
        }
        return now;
    }

    public int cycleNumber(int now, int max){
        return cycleNumber(now, 0, max);
    }

    public ItemStack toItemStack(IBlockState state){
        try{
            return (ItemStack) ReflectionHelper.findMethod(Block.class, state.getBlock(), new String[]{"createStackedBlock"}, IBlockState.class).invoke(state.getBlock(), state);
        } catch(IllegalAccessException e){
            throw new AssertionError(new IllegalArgumentException(e));
        } catch(InvocationTargetException e){
            throw new AssertionError(new IllegalArgumentException(e));
        }
    }

    public boolean isOre(IBlockState state){
        return isOre(toItemStack(state));
    }

    public boolean isOre(ItemStack stack){
        for(int id : OreDictionary.getOreIDs(stack.getItem() == null ? new ItemStack(Items.diamond_sword) : stack)){
            if(wrapDefault(OreDictionary.getOreName(id), "").matches("ore[A-Z].*")){
                return true;
            }
        }
        return false;
    }


    /**
     t,t,both;</br> t,f,only me;</br> f,t,if has orig don't do me;</br> f,f,do not register;</br>
     */
    public void registerBehaviorDispenser(RegistrySimple to, Item target, final IBehaviorDispenseItem behavior, final boolean me, final boolean orig){
        if(!(me || orig)) return;
        final boolean hasOrig = to.containsKey(target);
        final IBehaviorDispenseItem def = (IBehaviorDispenseItem) to.getObject(target);
        to.putObject(target, new IBehaviorDispenseItem(){
            public ItemStack dispense(IBlockSource source, ItemStack stack){
                if(me || !hasOrig){
                    behavior.dispense(source, stack);
                }
                if(orig && stack != null && stack.stackSize == 0){
                    def.dispense(source, stack);
                }
                return stack;
            }
        });
    }

    public <K, V> Function<K,V> listsToMapFunc(final List<K> key, final List<V> value){
        return new Function<K,V>(){
            @Nullable
            @Override
            public V apply(K input){
                return value.get(key.indexOf(input));
            }
        };
    }
}
