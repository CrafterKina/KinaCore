package com.mods.kina.KinaCore.misclib;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Primitives;
import net.minecraft.nbt.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NBTHelper{
    public static final NBTHelper helper = new NBTHelper();

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

    public static void writeList(NBTTagCompound nbt, String tagName, List<?> list){
        NBTTagList nbtList = new NBTTagList();
        for(int i = 0; i < list.size(); i++){
            nbtList.set(i, setObjToNBT(new NBTTagCompound(), "value", list.get(i)));
        }
        nbt.setTag(tagName, nbtList);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> readList(NBTTagCompound nbt, String tagName){
        NBTTagList nbtList = nbt.getTagList(tagName, ((NBTTagList) nbt.getTag(tagName)).getTagType());
        List<T> result = Lists.newArrayList();
        for(int i = 0; i < nbtList.tagCount(); i++){
            result.add(i, (T) getObjFromNBT((NBTTagCompound) nbtList.get(i), "value"));
        }
        return result;
    }

    public static void writeMap(NBTTagCompound nbt, String tagName, Map<?,?> map){
        List<Map.Entry<?,?>> list = new ArrayList<Map.Entry<?,?>>(map.entrySet());
        NBTTagList nbtList = new NBTTagList();
        for(int i = 0; i < list.size(); i++){
            NBTTagCompound entry = new NBTTagCompound();
            setObjToNBT(entry, "key", map.get(list.get(i).getKey()));
            setObjToNBT(entry, "value", map.get(list.get(i).getValue()));
            nbtList.set(i, entry);
        }
        nbt.setTag(tagName, nbtList);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K,V> readMap(NBTTagCompound nbt, String tagName){
        NBTTagList nbtList = nbt.getTagList(tagName, ((NBTTagList) nbt.getTag(tagName)).getTagType());
        Map<K,V> result = Maps.newHashMap();
        for(int i = 0; i < nbtList.tagCount(); i++){
            NBTTagCompound entry = nbtList.getCompoundTagAt(i);
            result.put((K) getObjFromNBT(entry, "key"), (V) getObjFromNBT(entry, "value"));
        }
        return result;
    }

    public static NBTTagCompound setObjToNBT(NBTTagCompound nbt, String key, Object obj){
        try{
            NBTBase put = transformTable.inverse().get(obj.getClass()).getConstructor(Primitives.unwrap(obj.getClass())).newInstance(obj);
            nbt.setTag(key, put);
        } catch(InstantiationException e){
            e.printStackTrace();
        } catch(IllegalAccessException e){
            e.printStackTrace();
        } catch(InvocationTargetException e){
            e.printStackTrace();
        } catch(NoSuchMethodException e){
            e.printStackTrace();
        }
        return nbt;
    }

    public static Object getObjFromNBT(NBTTagCompound nbt, String key){
        NBTBase get = nbt.getTag(key);
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Method write;
        try{
            write = get.getClass().getDeclaredMethod("func_74734_a", DataOutput.class);
        } catch(NoSuchMethodException e){
            try{
                write = get.getClass().getDeclaredMethod("write", DataOutput.class);
            } catch(NoSuchMethodException e1){
                throw new NoSuchMethodError();
            }
        }
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            write.invoke(get, oos);
            ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
            return ois.readObject();
        } catch(IllegalAccessException e){
            e.printStackTrace();
        } catch(InvocationTargetException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            if(ois != null){
                try{
                    ois.close();
                } catch(IOException ignored){
                }
            }
            if(oos != null){
                try{
                    oos.close();
                } catch(IOException ignored){
                }
            }
        }
        throw new IllegalArgumentException();
    }
}
