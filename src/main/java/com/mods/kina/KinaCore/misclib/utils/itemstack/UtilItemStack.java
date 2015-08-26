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
