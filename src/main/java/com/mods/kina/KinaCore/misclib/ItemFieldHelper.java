package com.mods.kina.KinaCore.misclib;

import com.google.common.collect.Maps;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ItemFieldHelper{
    public static final ItemFieldHelper instance = new ItemFieldHelper();
    private static final Map<Integer,Map<String,Object>> fieldMap = Maps.newHashMap();

    private ItemFieldHelper(){}

    private Map<String,Object> getFieldMap(int hash){
        return KinaLib.lib.putIfAbsent(fieldMap, hash, new HashMap<String,Object>());
    }

    public Map<String,Object> getFieldMap(ItemStack stack){
        return getFieldMap(stack.hashCode());
    }

    public Object getField(ItemStack stack, String name){
        return getFieldMap(stack).get(name);
    }

    public void putField(ItemStack stack, String name, Object value){
        getFieldMap(stack).put(name, value);
    }
}
