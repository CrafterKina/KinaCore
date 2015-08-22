package com.mods.kina.KinaCore.misclib.utils.reflect;

import com.google.common.base.Objects;

import java.lang.reflect.Field;

public class InstancedField{
    private final Field field;
    private final InstancedField instance;
    private Object getObj = null;

    public InstancedField(Field field, InstancedField instance){
        field.setAccessible(true);
        this.field = field;
        this.instance = instance;
    }

    public Object get(){
        if(getObj == null){
            try{
                getObj = field.get(instance == null ? null : instance.get());
            } catch(IllegalAccessException e){
                throw (IllegalAccessError) new IllegalAccessError().initCause(e);
            }
        }
        return getObj;
    }

    public void set(Object value){
        try{
            field.set(instance == null ? null : instance.get(), value);
        } catch(IllegalAccessException e){
            throw (IllegalAccessError) new IllegalAccessError().initCause(e);
        }
    }

    public void setBoolean(boolean value){
        try{
            field.setBoolean(instance == null ? null : instance.get(), value);
        } catch(IllegalAccessException e){
            throw (IllegalAccessError) new IllegalAccessError().initCause(e);
        }
    }

    public void setDouble(double value){
        try{
            field.setDouble(instance == null ? null : instance.get(), value);
        } catch(IllegalAccessException e){
            throw (IllegalAccessError) new IllegalAccessError().initCause(e);
        }
    }

    public void setInt(int value){
        try{
            field.setInt(instance == null ? null : instance.get(), value);
        } catch(IllegalAccessException e){
            throw (IllegalAccessError) new IllegalAccessError().initCause(e);
        }
    }

    public Field getField(){
        return field;
    }

    public InstancedField getInstance(){
        return instance;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof InstancedField)) return false;

        InstancedField that = (InstancedField) o;

        return field.equals(that.field) && !(instance != null ? !instance.equals(that.instance) : that.instance != null);

    }

    @Override
    public int hashCode(){
        int result = field.hashCode();
        result = 31 * result + (instance != null ? instance.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return Objects.toStringHelper(this).add("field", field).add("instance", instance).toString();
    }
}
