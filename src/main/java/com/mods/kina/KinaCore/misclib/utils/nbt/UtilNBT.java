package com.mods.kina.KinaCore.misclib.utils.nbt;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.nbt.*;

public class UtilNBT{
    public static final BiMap<Class<? extends NBTBase>,Class<?>> transformTable = HashBiMap.create(9);

    static{
        transformTable.put(NBTTagByte.class, Byte.class);
        transformTable.put(NBTTagShort.class, Short.class);
        transformTable.put(NBTTagInt.class, Integer.class);
        transformTable.put(NBTTagLong.class, Long.class);
        transformTable.put(NBTTagFloat.class, Float.class);
        transformTable.put(NBTTagDouble.class, Double.class);
        transformTable.put(NBTTagByteArray.class, byte[].class);
        transformTable.put(NBTTagString.class, String.class);
        transformTable.put(NBTTagIntArray.class, int[].class);
    }
}
