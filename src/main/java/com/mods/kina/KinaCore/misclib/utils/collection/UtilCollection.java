package com.mods.kina.KinaCore.misclib.utils.collection;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class UtilCollection{
    public static <K, V> V putIfAbsent(Map<K,V> map, K key, V value){
        V v = map.get(key);
        if(v == null){
            map.put(key, value);
            v = value;
        }

        return v;
    }

    public static <K, V> V getOrDefault(Map<K,V> map, K key, V defaultValue){
        V v;
        return (((v = map.get(key)) != null) || map.containsKey(key)) ? v : defaultValue;
    }

    public static <K, V> Function<K,V> listsToMapFunc(final List<K> key, final List<V> value){
        return new Function<K,V>(){
            Map<K,V> map = Maps.newHashMap();

            @Nullable
            @Override
            public V apply(K input){
                if(!map.containsKey(input)){
                    map.put(input, value.get(key.indexOf(input)));
                }
                return map.get(input);
            }
        };
    }


}
