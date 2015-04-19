package com.mods.kina.KinaCore.misclib;

import com.google.common.collect.Maps;
import net.minecraft.util.BlockPos;

import java.util.HashMap;
import java.util.Map;

public class BlockFieldHelper{
    public static final BlockFieldHelper instance = new BlockFieldHelper();
    private static final Map<Long,Map<String,Object>> fieldMap = Maps.newHashMap();

    private BlockFieldHelper(){}

    private Map<String,Object> getFieldMap(long pos){
        return KinaLib.lib.putIfAbsent(fieldMap, pos, new HashMap<String,Object>());
    }

    public Map<String,Object> getFieldMap(BlockPos pos){
        return getFieldMap(pos.toLong());
    }

    public Object getField(BlockPos pos, String name){
        return getFieldMap(pos).get(name);
    }

    public void putField(BlockPos pos, String name, Object value){
        getFieldMap(pos).put(name, value);
    }
}
