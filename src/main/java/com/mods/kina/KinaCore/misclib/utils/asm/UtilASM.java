package com.mods.kina.KinaCore.misclib.utils.asm;

import org.objectweb.asm.Type;

public class UtilASM{
    public static String getDesc(Class<?> returnType, Class<?>... descTypes){
        StringBuilder buf = new StringBuilder();
        buf.append('(');
        for(Class<?> descType : descTypes){
            buf.append(Type.getDescriptor(descType));
        }
        buf.append(')');
        buf.append(Type.getDescriptor(returnType));
        return buf.toString();
    }
}
