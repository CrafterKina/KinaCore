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

package com.mods.kina.KinaCore.misclib.helpers.blockfield;

import com.google.common.collect.Maps;
import com.mods.kina.KinaCore.misclib.utils.collection.UtilCollection;
import net.minecraft.util.BlockPos;

import java.util.HashMap;
import java.util.Map;

public class BlockFieldHelper{
    private static final Map<Long,Map<String,Object>> fieldMap = Maps.newHashMap();

    private BlockFieldHelper(){}

    private static Map<String,Object> getFieldMap(long pos){
        return UtilCollection.putIfAbsent(fieldMap, pos, new HashMap<String,Object>());
    }

    public static Map<String,Object> getFieldMap(BlockPos pos){
        return getFieldMap(pos.toLong());
    }

    public static Object getField(BlockPos pos, String name){
        return getFieldMap(pos).get(name);
    }

    public static void putField(BlockPos pos, String name, Object value){
        getFieldMap(pos).put(name, value);
    }
}
