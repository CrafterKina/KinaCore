package com.mods.kina.KinaCore.movelib.config;

import com.google.common.collect.Sets;
import com.mods.kina.KinaCore.asm.transformer.TransformerKinaProp;
import com.mods.kina.KinaCore.misclib.utils.reflect.InstancedField;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.regex.Pattern;

public class ElementProcessor{
    //    public static Set<String> classNames = new HashSet<String>();
    private Set<KinaPropContainer> containers = Sets.newHashSet();

    public void process(Pattern suffix){
        String[] strings = TransformerKinaProp.className.toArray(new String[TransformerKinaProp.className.size()]);
        for(final String className : strings){
            if(suffix.matcher(className).matches()){
                process(className);
            }
        }
    }

    private void process(String className){
        try{
            Class<?> clazz = Class.forName(className);
            fieldProcess(clazz);
        } catch(ClassNotFoundException e){
            throw new AssertionError(e);
        }
    }

    private void fieldProcess(Class<?> clazz){
        for(Field field : clazz.getDeclaredFields()){
            field.setAccessible(true);
            System.out.println(field.toString());
            if(!(Modifier.isStatic(field.getModifiers()))) continue;
            KinaProp prop = field.getAnnotation(KinaProp.class);
            if(prop == null) continue;
            fieldProcess(new InstancedField(field, null), prop);
        }
    }

    private void fieldProcess(InstancedField field, KinaProp prop){
        if(prop.isInstance()){
            instanceProcess(field);
        }else{
            normalFieldProcess(field);
        }
    }

    private void instanceProcess(InstancedField instanceField){
        Object instance = instanceField.get();
        for(Field holdingField : instance.getClass().getDeclaredFields()){
            holdingField.setAccessible(true);
            if(Modifier.isStatic(holdingField.getModifiers())) continue;
            KinaProp prop = holdingField.getAnnotation(KinaProp.class);
            if(prop == null) continue;
            fieldProcess(new InstancedField(holdingField, instanceField), prop);
        }
    }

    private void normalFieldProcess(InstancedField normalField){
        KinaProp fieldTypeProp = normalField.getField().getType().getAnnotation(KinaProp.class);
        KinaProp instanceProp = normalField.getInstance() == null ? null : normalField.getInstance().getField().getAnnotation(KinaProp.class);
        KinaProp fieldProp = normalField.getField().getAnnotation(KinaProp.class);
        KinaPropContainer container = new KinaPropContainer(normalField, fieldTypeProp, instanceProp, fieldProp);
        containers.add(container);
    }

    public Set<KinaPropContainer> getContainers(){
        return containers;
    }
}
