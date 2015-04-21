package com.mods.kina.KinaCore.event.hooks.render;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
@Deprecated
public class RenderBlockEvent extends Event{
    public final IBlockAccess world;
    public final IBlockState state;
    public final BlockPos blockPos;
    public final WorldRenderer renderer;

    public RenderBlockEvent(IBlockAccess world, IBlockState state, BlockPos pos, WorldRenderer renderer){
        this.world = world;
        this.state = state;
        blockPos = pos;
        this.renderer = renderer;
    }
}
