package com.mods.kina.KinaCore.toExtends;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockBreakableContainer extends BlockContainer{
    protected BlockBreakableContainer(Material par1){
        super(par1);
    }

    /**
     Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube(){
        return false;
    }

    /**
     Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     coordinates.  Args: blockAccess, x, y, z, side
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_){
        return true;
    }
}
