package com.mods.kina.KinaCore.misclib;

public class KinaLib{
    /*public World world;
    public static KinaLib lib;
    public static int h;

    *//**
     Sets the block ID and metadata at a given location. Args: X, Y, Z, new block ID, new metadata, flags. Flag 1 will
     cause a block update. Flag 2 will send the change to clients (you almost always want pblockItem). Flag 4 prevents the
     block from being re-rendered, if pblockItem is a client world. Flags can be added together.
     *//*
    public static boolean setOnBlock(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (block == Blocks.snow_layer && (Integer) iblockstate.getValue(BlockSnow.LAYERS_PROP) < 1)
        {
            side = EnumFacing.UP;
        }
        else if (!block.isReplaceable(worldIn, pos))
        {
            pos = pos.offset(side);
        }

        if (stack.stackSize == 0)
        {
            return false;
        }
        else if (!playerIn.func_175151_a(pos, side, stack))
        {
            return false;
        }
        else if (pos.getY() == 255 && ((ItemBlock)stack.getItem()).block.getMaterial().isSolid())
        {
            return false;
        }
        else if (worldIn.canBlockBePlaced(((ItemBlock)stack.getItem()).block, pos, false, side, (Entity)null, stack))
        {
            int i = ((ItemBlock)stack.getItem()).getMetadata(stack.getMetadata());
            IBlockState iblockstate1 = ((ItemBlock)stack.getItem()).block.onBlockPlaced(worldIn, pos, side, hitX, hitY, hitZ, i, playerIn);

            if (((ItemBlock)stack.getItem()).placeBlockAt(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ, iblockstate1))
            {
                worldIn.playSoundEffect((double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), ((ItemBlock)stack.getItem()).block.stepSound.getPlaceSound(), (((ItemBlock)stack.getItem()).block.stepSound.getVolume() + 1.0F) / 2.0F, ((ItemBlock)stack.getItem()).block.stepSound.getFrequency() * 0.8F);
                --stack.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean setOnBlock(int x, int y, int z, Block block, World world, EntityPlayer player, ItemStack itemStack, int side){
        setOnBlock(x, y, z, block, world, player, itemStack, side, 0, 3);
        return true;
    }

    public static boolean setOnBlockVanilla(int x, int y, int z, Block pblock, World world, EntityPlayer player, ItemStack itemStack, int side,float hitX,float hitY,float hitZ){
        Block block = world.getBlock(x, y, z);
        ItemBlock pblockItem=(ItemBlock)Item.getItemFromBlock(pblock);

        if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1)
        {
            side = 1;
        }
        else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(world, x, y, z))
        {
            if (side == 0)
            {
                --y;
            }

            if (side == 1)
            {
                ++y;
            }

            if (side == 2)
            {
                --z;
            }

            if (side == 3)
            {
                ++z;
            }

            if (side == 4)
            {
                --x;
            }

            if (side == 5)
            {
                ++x;
            }
        }

        if (itemStack.stackSize == 0)
        {
            return false;
        }
        else if (!player.canPlayerEdit(x, y, z, side, itemStack))
        {
            return false;
        }
        else if (y == 255 && pblock.getMaterial().isSolid())
        {
            return false;
        }
        else if (world.canPlaceEntityOnSide(pblock, x, y, z, false, side, player, itemStack))
        {
            int i1 = pblockItem.getMetadata(itemStack.getItemDamage());
            int j1 = pblock.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, i1);

            if (pblockItem.placeBlockAt(itemStack, player, world, x, y, z, side,hitX , hitY, hitZ, j1))
            {
                world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), pblock.stepSound.func_150496_b(), (pblock.stepSound.getVolume() + 1.0F) / 2.0F, pblock.stepSound.getPitch() * 0.8F);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean spawnThunder(World world, int x, int y, int z){
        Random rand = new Random();
        EntityLightningBolt entitylightningbolt = new EntityLightningBolt(world, x, y, z);
        world.spawnEntityInWorld(entitylightningbolt);
        if(!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doFireTick") && (world.difficultySetting == EnumDifficulty.PEACEFUL || world.difficultySetting == EnumDifficulty.EASY) && world.doChunksNearChunkExist(MathHelper.floor_double((double) x), MathHelper.floor_double((double) y), MathHelper.floor_double((double) z), 10)){
            int i = MathHelper.floor_double((double) x);
            int j = MathHelper.floor_double((double) y);
            int k = MathHelper.floor_double((double) z);

            if(world.getBlock(i, j, k).getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(world, i, j, k)){
                world.setBlock(i, j, k, Blocks.fire);
            }

            for(i = 0; i < 4; ++i){
                j = MathHelper.floor_double((double) x) + rand.nextInt(3) - 1;
                k = MathHelper.floor_double((double) y) + rand.nextInt(3) - 1;
                int l = MathHelper.floor_double((double) z) + rand.nextInt(3) - 1;

                if(world.getBlock(j, k, l).getMaterial() == Material.air && Blocks.fire.canPlaceBlockAt(world, j, k, l)){
                    world.setBlock(j, k, l, Blocks.fire);
                }
            }
        }
        return true;
    }

    *//*public static ItemStack copyItemStackWithoutStackSize(ItemStack stack){
        ItemStack itemstack = new ItemStack(stack.getItem(), stack.stackSize, stack.getItemDamage());

        if (stack.stackTagCompound != null)
        {
            itemstack.stackTagCompound = (NBTTagCompound)stack.stackTagCompound.copy();
        }

        return itemstack;
    }*//*
    
    public int getH(){
        return h;
    }
    public static boolean isBlock(Item item){
       return item instanceof ItemBlock;
    }
    public boolean isBlock(int id){
        return isBlock(Item.getItemById(id));
    }
    public boolean isBlock(String name){
        return isBlock((Item)Item.itemRegistry.getObject(name));
    }
    public boolean isBlock(ItemStack stack){
        return isBlock(stack.getItem());
    }
    public boolean isBlock(EntityItem entityItem){
        return isBlock(entityItem.getEntityItem());
    }*/
}
