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

package jp.crafterkina.KinaCore.misclib.utils.reflect;

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
