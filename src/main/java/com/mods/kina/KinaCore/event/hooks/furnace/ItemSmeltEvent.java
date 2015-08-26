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

package com.mods.kina.KinaCore.event.hooks.furnace;

import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ItemSmeltEvent extends Event{
    private final World world;
    private final BlockPos pos;
    private ItemStack[] stacks;

    protected ItemSmeltEvent(World world, BlockPos pos, ItemStack[] stacks){
        this.world = world;
        this.pos = pos;
        this.stacks = stacks;
    }

    public World getWorld(){
        return world;
    }

    public BlockPos getPos(){
        return pos;
    }

    public ItemStack getItem(int index){
        if(index >= stacks.length) return null;
        return stacks[index];
    }

    public void setItem(int index, ItemStack stack){
        if(index < stacks.length){
            stacks[index] = stack;
        }
    }

    public ItemStack[] getStacks(){
        return stacks.clone();
    }

    public int getLength(){
        return stacks.length;
    }

    /**
     * ItemSmeltEvent.Pre is fired before vanilla smelting takes place.
     * All changes made to the event's array will be made to the TileEntity if the event is canceled.
     * <br>
     * The event is fired during the TileEntityFurnace#update() method invocation.<br>
     * <br>
     * {@link #stacks} contains the itemstack array from the TileEntitySmelter holding all items in Smelter.<br>
     * <br>
     * {@link #cookTime} contains the integer from the TileEntitySmelter holding processing time in Smelter.<br>
     * <br>
     * {@link #totalCookTime} contains the integer from the TileEntitySmelter holding processing total time in Smelter.<br>
     * <br>
     * This event is not {@link Cancelable}.<br>
     * <br>
     * This event does not have a result. {@link HasResult}<br>
     **/
    public static class Pre extends ItemSmeltEvent{
        private int cookTime;
        private int totalCookTime;

        public Pre(World world, BlockPos pos, ItemStack[] stacks, int cookTime, int totalCookTime){
            super(world, pos, stacks);
            this.cookTime = cookTime;
            this.totalCookTime = totalCookTime;
        }

        public int getCookTime(){
            return cookTime;
        }

        public void setCookTime(int cookTime){
            this.cookTime = cookTime;
        }

        public int getTotalCookTime(){
            return totalCookTime;
        }

        public void setTotalCookTime(int totalCookTime){
            this.totalCookTime = totalCookTime;
        }
    }

    /**
     * ItemSmeltEvent.Post is fired when a item is smelted in the furnace.
     * <br>
     * The event is fired during the TileEntityFurnace#smeltItem() method invocation.<br>
     * <br>
     * {@link #stacks} contains the itemstack array from the TileEntitySmelter holding all items in Smelter.<br>
     * <br>
     * This event is not {@link Cancelable}.<br>
     * <br>
     * This event does not have a result. {@link HasResult}<br>
     **/
    public static class Post extends ItemSmeltEvent{
        public Post(World world, BlockPos pos, ItemStack[] stacks){
            super(world, pos, stacks);
        }
    }
}
