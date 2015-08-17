package com.mods.kina.KinaCore.movelib.config;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.primitives.Primitives;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class ValueAssigner{
    public static Multimap<String,String> configurableFields = HashMultimap.create();
    public static Multimap<String,String> instanceFields = HashMultimap.create();
    public static Set<String> defaultedClasses = Sets.newHashSet();

    public static Set<Pair<String,Function<Configuration,Void>>> getFieldAssigner(String classNameSuffix){
        Set<Pair<String,Function<Configuration,Void>>> result = Sets.newHashSet();
        Pattern pattern = Pattern.compile(classNameSuffix);
        try{
            for(Map.Entry<String,String> rawFields : configurableFields.entries()){
                if(!pattern.matcher(rawFields.getKey()).matches()) continue;
                final Class<?> clazz = Class.forName(rawFields.getKey());
                for(final Field field : clazz.getDeclaredFields()){
                    final Collection<Object> instance = Modifier.isStatic(field.getModifiers()) ? Collections.emptySet() : getInstances(clazz);
                    field.setAccessible(true);
                    result.add(Pair.<String,Function<Configuration,Void>>of(getPropertyName(clazz, field), new Function<Configuration,Void>(){
                        @Nullable
                        public Void apply(Configuration input){
                            try{
                                Object o = field.get(instance);
                                if(o.getClass().isArray()){
                                    insertObject(instance, field, input.get(getCategory(clazz, field), getPropertyName(clazz, field), arrayToString(o), getComment(clazz, field)));
                                }else{
                                    insertObject(instance, field, input.get(getCategory(clazz, field), getPropertyName(clazz, field), String.valueOf(o), getComment(clazz, field)));
                                }
                                return null;
                            } catch(IllegalAccessException e){
                                e.printStackTrace();
                                return null;
                            }
                        }
                    }));
                }
            }
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return result;
    }

    private static String getPropertyName(Class<?> clazz, Field field){
        String name = field.getAnnotation(KinaProp.class).name();
        if(name.isEmpty()){
            name = defaultedClasses.contains(clazz.getCanonicalName()) ? clazz.getAnnotation(KinaProp.class).name() : field.getName();
        }
        return name;
    }

    private static String getCategory(Class<?> clazz, Field field){
        String name = field.getAnnotation(KinaProp.class).category();
        if(name.isEmpty()){
            name = defaultedClasses.contains(clazz.getCanonicalName()) ? clazz.getAnnotation(KinaProp.class).category() : "general";
        }
        return name;
    }

    private static String getComment(Class<?> clazz, Field field){
        String name = field.getAnnotation(KinaProp.class).comment();
        if(name.isEmpty()){
            name = defaultedClasses.contains(clazz.getCanonicalName()) ? clazz.getAnnotation(KinaProp.class).comment() : "";
        }
        return name;
    }

    private static Collection<Object> getInstances(final Class<?> clazz){
        Collection<String> rawInstances = instanceFields.get(clazz.getCanonicalName());
        return Collections2.transform(rawInstances, new Function<String,Object>(){
            @Override
            public Object apply(String input){
                try{
                    Field field = clazz.getDeclaredField(input);
                    field.setAccessible(true);
                    return field.get(null);
                } catch(IllegalAccessException e){
                    throw new AssertionError(e);
                } catch(NoSuchFieldException e){
                    throw new AssertionError(e);
                }
            }
        });
    }

    private static void insertObject(Object instance, Field field, Property property) throws IllegalAccessException{
        switch(getType(field)){
            case INTEGER:
                if(property.isList()) field.set(instance, property.getIntList());
                else field.setInt(instance, property.getInt());
                return;
            case BOOLEAN:
                if(property.isList()) field.set(instance, property.getBooleanList());
                else field.setBoolean(instance, property.getBoolean());
                return;
            case DOUBLE:
                if(property.isList()) field.set(instance, property.getDoubleList());
                else field.setDouble(instance, property.getDouble());
                return;
            default:
                field.set(instance, property.isList() ? property.getStringList() : property.getString());

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
