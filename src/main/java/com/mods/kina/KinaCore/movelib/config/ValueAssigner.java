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

    //ForgeÇÃèàóùÇêÿÇËèoÇµÇΩÇæÇØÅB
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
