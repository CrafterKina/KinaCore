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
