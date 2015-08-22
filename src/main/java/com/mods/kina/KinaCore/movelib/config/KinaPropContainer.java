package com.mods.kina.KinaCore.movelib.config;

import com.google.common.base.Objects;
import com.mods.kina.KinaCore.misclib.utils.reflect.InstancedField;

public class KinaPropContainer{
    private InstancedField field;
    private String category;
    private String name;
    private String comment;

    public KinaPropContainer(InstancedField field, KinaProp... props){
        String category = null;
        String name = null;
        String comment = null;
        for(KinaProp prop : props){
            if(prop == null) continue;
            category = prop.category().isEmpty() ? category : prop.category();
            name = prop.name().isEmpty() ? name : prop.name();
            comment = prop.comment().isEmpty() ? comment : prop.comment();
        }
        this.field = field;
        this.category = Objects.firstNonNull(category, "general");
        this.name = Objects.firstNonNull(name, field.getField().getName());
        this.comment = Objects.firstNonNull(comment, "");
    }

    public String getCategory(){
        return category;
    }

    public String getComment(){
        return comment;
    }

    public String getName(){
        return name;
    }

    public InstancedField getField(){
        return field;
    }
}
