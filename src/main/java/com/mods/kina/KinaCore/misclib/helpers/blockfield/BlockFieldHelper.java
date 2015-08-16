package com.mods.kina.KinaCore.misclib.helpers.blockfield;

import com.google.common.collect.Maps;
import com.mods.kina.KinaCore.misclib.utils.collection.UtilCollection;
import net.minecraft.util.BlockPos;

import java.util.HashMap;
import java.util.Map;

public class BlockFieldHelper{
    private static final Map<Long,Map<String,Object>> fieldMap = Maps.newHashMap();

    private BlockFieldHelper(){}

    private static Map<String,Object> getFieldMap(long pos){
        return UtilCollection.putIfAbsent(fieldMap, pos, new HashMap<String,Object>());
    }

    public static Map<String,Object> getFieldMap(BlockPos pos){
        return getFieldMap(pos.toLong());
    }

    public static Object getField(BlockPos pos, String name){
        return getFieldMap(pos).get(name);
    }

    public static void putField(BlockPos pos, String name, Object value){
        getFieldMap(pos).put(name, value);
    }
}
