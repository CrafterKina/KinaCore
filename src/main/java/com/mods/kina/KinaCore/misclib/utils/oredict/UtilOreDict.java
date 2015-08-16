package com.mods.kina.KinaCore.misclib.utils.oredict;

import com.google.common.base.Objects;
import com.mods.kina.KinaCore.misclib.utils.itemstack.UtilItemStack;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class UtilOreDict{
    public static boolean isOre(IBlockState state){
        return isOre(UtilItemStack.toItemStack(state));
    }

    public static boolean isOre(ItemStack stack){
        for(int id : OreDictionary.getOreIDs(stack.getItem() == null ? new ItemStack(Items.diamond_sword) : stack)){
            if(Objects.firstNonNull(OreDictionary.getOreName(id), "").matches("ore[A-Z].*")){
                return true;
            }
        }
        return false;
    }
}
