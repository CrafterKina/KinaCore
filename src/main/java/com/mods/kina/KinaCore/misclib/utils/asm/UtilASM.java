package com.mods.kina.KinaCore.misclib.utils.asm;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class UtilASM{
    @SuppressWarnings("unchecked")
    public static <K, V> V getValueFromAsMapList(List<?> list, K key){
        ListIterator<?> iterator = list.listIterator();
        for(Object o = null; iterator.hasNext(); o = iterator.next()){
            if(key.equals(o)){
                return (V) iterator.next();
            }
        }
        throw new IllegalArgumentException(new NoSuchElementException());
    }
}
