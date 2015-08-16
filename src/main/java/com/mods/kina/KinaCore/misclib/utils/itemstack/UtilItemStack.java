package com.mods.kina.KinaCore.misclib.utils.itemstack;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;

public class UtilItemStack{
    public static boolean areItemStacksEqualForRecipe(ItemStack l, ItemStack r){
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

    public static void normalizeItemStacks(ItemStack[] args){
        for(int i = 0; i < args.length; i++){
            ItemStack arg = args[i];
            args[i] = arg == null || arg.stackSize <= 0 ? null : arg;
        }
    }


    public static ItemStack toItemStack(IBlockState state){
        try{
            return (ItemStack) ReflectionHelper.findMethod(Block.class, state.getBlock(), new String[]{"createStackedBlock", "func_180643_i"}, IBlockState.class).invoke(state.getBlock(), state);
        } catch(IllegalAccessException e){
            throw new AssertionError(new IllegalArgumentException(e));
        } catch(InvocationTargetException e){
            throw new AssertionError(new IllegalArgumentException(e));
        }
    }
}
