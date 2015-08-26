/*
 * Copyright (c) 2015, CrafterKina
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *  Neither the name of the CrafterKina nor the　names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CrafterKina BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.mods.kina.KinaCore.movelib.config;

import com.google.common.primitives.Primitives;
import com.mods.kina.KinaCore.misclib.utils.reflect.InstancedField;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Set;

public class ValueAssigner{
    public static void assignFields(Configuration cfg, Set<KinaPropContainer> fields){
        for(KinaPropContainer field : fields){
            Object o = field.getField().get();

            try{
                if(o.getClass().isArray()){
                    insertObject(field.getField(), cfg.get(field.getCategory(), field.getName(), arrayToString(o), field.getComment()));
                }else{
                    insertObject(field.getField(), cfg.get(field.getCategory(), field.getName(), String.valueOf(o), field.getComment()));
                }
            } catch(IllegalAccessException e){
                e.printStackTrace();
            }
        }
    }

    private static void insertObject(InstancedField field, Property property) throws IllegalAccessException{
        switch(getType(field.getField())){
            case INTEGER:
                if(property.isList()) field.set(property.getIntList());
                else field.setInt(property.getInt());
                return;
            case BOOLEAN:
                if(property.isList()) field.set(property.getBooleanList());
                else field.setBoolean(property.getBoolean());
                return;
            case DOUBLE:
                if(property.isList()) field.set(property.getDoubleList());
                else field.setDouble(property.getDouble());
                return;
            default:
                field.set(property.isList() ? property.getStringList() : property.getString());

        }
    }

    //Forge�̏�����؂�o���������B
    private static String[] arrayToString(Object obj){
        String[] values = new String[Array.getLength(obj)];
        for(int i = 0; i < Array.getLength(obj); i++){
            values[i] = String.valueOf(Array.get(obj, i));
        }
        return values;
    }

    private static Property.Type getType(Field field) throws IllegalAccessException{
        if(Primitives.wrap(field.getType()) == Integer.class){
            return Property.Type.INTEGER;
        }
        if(Primitives.wrap(field.getType()) == Boolean.class){
            return Property.Type.BOOLEAN;
        }

        if(Primitives.wrap(field.getType()) == Double.class){
            return Property.Type.DOUBLE;
        }

        if(field.getType().isArray()){
            if(field.getType().getComponentType() == int.class){
                return Property.Type.INTEGER;
            }
            if(field.getType().getComponentType() == boolean.class){
                return Property.Type.BOOLEAN;
            }

            if(field.getType().getComponentType() == double.class){
                return Property.Type.DOUBLE;
            }
        }

        return Property.Type.STRING;
    }
}
