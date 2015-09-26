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

import com.google.common.collect.Lists;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class UtilReflection{
    /**
     * Create instance by given info.
     * Earlier param is preferentially-used.
     *
     * @param constructor to create instance.
     * @param params put all information.(can contain not necessary info)
     * @param <T> return type.
     * @return created instance.
     * @exception IllegalAccessException
     * @exception InvocationTargetException
     * @exception InstantiationException
     */
    public static <T> T createInstance(Constructor<? extends T> constructor, Object... params) throws IllegalAccessException, InvocationTargetException, InstantiationException{
        List<Object> unsorted = Lists.newArrayList(params);
        List<Object> param = Lists.newArrayList();
        for(Class<?> type : constructor.getParameterTypes()){
            for(Object o : unsorted){
                if(type.isInstance(o)){
                    param.add(o);
                    unsorted.remove(o);
                    break;
                }
            }
        }
        return constructor.newInstance(param.toArray());
    }
}