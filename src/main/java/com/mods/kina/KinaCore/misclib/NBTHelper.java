package com.mods.kina.KinaCore.misclib;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NBTHelper{
    public static final NBTHelper helper = new NBTHelper();

    public static void writeList(NBTTagCompound nbt, String tagName, List<? extends NBTBase> list){
        NBTTagList nbtList = new NBTTagList();
        for(int i = 0; i < list.size(); i++){
            nbtList.set(i, list.get(i));
        }
        nbt.setTag(tagName, nbtList);
    }

    public static List<? extends NBTBase> readList(NBTTagCompound nbt, String tagName){
        NBTTagList nbtList = nbt.getTagList(tagName, ((NBTTagList) nbt.getTag(tagName)).getTagType());
        NBTBase[] result = new NBTBase[nbtList.tagCount()];
        for(int i = 0; i < nbtList.tagCount(); i++){
            result[i] = nbtList.get(i);
        }
        return Arrays.asList(result);
    }

    public static void writeMap(NBTTagCompound nbt, String tagName, Map<? extends NBTBase,? extends NBTBase> map){
        List<?> list = Lists.newArrayList(map.entrySet());
        NBTTagList nbtList = new NBTTagList();
        for(int i = 0; i < list.size(); i++){
            NBTTagCompound entry = new NBTTagCompound();
            entry.setTag("key", (NBTBase) ((Map.Entry) list.get(i)).getKey());
            entry.setTag("value", (NBTBase) ((Map.Entry) list.get(i)).getValue());
            nbtList.set(i, entry);
        }
        nbt.setTag(tagName, nbtList);
    }

    public static Map<? extends NBTBase,? extends NBTBase> readMap(NBTTagCompound nbt, String tagName){
        NBTTagList nbtList = nbt.getTagList(tagName, ((NBTTagList) nbt.getTag(tagName)).getTagType());
        Map<NBTBase,NBTBase> result = Maps.newHashMap();
        for(int i = 0; i < nbtList.tagCount(); i++){
            NBTTagCompound entry = nbtList.getCompoundTagAt(i);
            result.put(entry.getTag("key"), entry.getTag("value"));
        }
        return result;
    }
}
