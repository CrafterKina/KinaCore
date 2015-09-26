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

package jp.crafterkina.KinaCore.movelib.config;

import com.google.common.collect.Sets;
import jp.crafterkina.KinaCore.asm.transformer.TransformerKinaProp;
import jp.crafterkina.KinaCore.misclib.utils.reflect.InstancedField;

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
