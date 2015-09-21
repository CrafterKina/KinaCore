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
 *  Neither the name of the CrafterKina nor theÅ@names of its contributors
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

package com.mods.kina.KinaCore.misclib.utils.nbt;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Primitives;
import net.minecraft.nbt.*;

import java.util.List;
import java.util.Map;

public enum EnumNBT{
    END(NBTTagEnd.class, Void.class){
        @Override
        public NBTTagEnd translate(Object orig){
            return new NBTTagEnd();
        }

        @Override
        public Void translate(NBTBase orig){
            return null;
        }
    },
    BYTE(NBTTagByte.class, Byte.class){
        @Override
        public NBTTagByte translate(Object orig){
            return new NBTTagByte((Byte) orig);
        }

        @Override
        public Byte translate(NBTBase orig){
            return ((NBTTagByte) orig).getByte();
        }
    },
    SHORT(NBTTagShort.class, Short.class){
        @Override
        public NBTTagShort translate(Object orig){
            return new NBTTagShort((Short) orig);
        }

        @Override
        public Short translate(NBTBase orig){
            return ((NBTTagShort) orig).getShort();
        }
    },
    INT(NBTTagInt.class, Integer.class){
        @Override
        public NBTTagInt translate(Object orig){
            return new NBTTagInt((Integer) orig);
        }

        @Override
        public Integer translate(NBTBase orig){
            return ((NBTTagInt) orig).getInt();
        }
    },
    LONG(NBTTagLong.class, Long.class){
        @Override
        public NBTTagLong translate(Object orig){
            return new NBTTagLong((Long) orig);
        }

        @Override
        public Long translate(NBTBase orig){
            return ((NBTTagLong) orig).getLong();
        }
    },
    FLOAT(NBTTagFloat.class, Float.class){
        @Override
        public NBTTagFloat translate(Object orig){
            return new NBTTagFloat((Float) orig);
        }

        @Override
        public Float translate(NBTBase orig){
            return ((NBTTagFloat) orig).getFloat();
        }
    },
    DOUBLE(NBTTagDouble.class, Double.class){
        @Override
        public NBTTagDouble translate(Object orig){
            return new NBTTagDouble((Double) orig);
        }

        @Override
        public Double translate(NBTBase orig){
            return ((NBTTagDouble) orig).getDouble();
        }
    },
    BYTE_ARRAY(NBTTagByteArray.class, byte[].class){
        @Override
        public NBTTagByteArray translate(Object orig){
            return new NBTTagByteArray((byte[]) orig);
        }

        @Override
        public byte[] translate(NBTBase orig){
            return ((NBTTagByteArray) orig).getByteArray();
        }
    },
    STRING(NBTTagString.class, String.class){
        @Override
        public NBTTagString translate(Object orig){
            return new NBTTagString((String) orig);
        }

        @Override
        public String translate(NBTBase orig){
            return ((NBTTagString) orig).getString();
        }
    },
    LIST(NBTTagList.class, List.class){
        @Override
        @SuppressWarnings("unchecked")
        public NBTTagList translate(Object orig){
            List<?> list = (List<?>) orig;
            NBTTagList tagList = new NBTTagList();
            for(Object raw : list){
                NBTBase nbt = isNBT(raw) ? (NBTBase) raw : getFunc(raw.getClass()).translate(raw);
                tagList.appendTag(nbt);
            }
            return tagList;
        }

        @Override
        public List<Object> translate(NBTBase orig){
            NBTTagList tagList = (NBTTagList) orig;
            List<Object> list = Lists.newArrayListWithCapacity(tagList.tagCount());
            for(int i = 0; i < tagList.tagCount(); i++){
                NBTBase raw = tagList.get(i);
                Object o = getFunc(raw.getClass()).translate(raw);
                list.set(i, o);
            }
            return list;
        }
    },
    COMPOUND(NBTTagCompound.class, Map.class){
        @Override
        public NBTTagCompound translate(Object orig){
            Map rawMap = (Map) orig;
            NBTTagCompound compound = new NBTTagCompound();
            for(Object raw : rawMap.entrySet()){
                Map.Entry rawEntry = ((Map.Entry) raw);
                String key = (String) rawEntry.getKey();
                NBTBase value = isNBT(rawEntry.getValue()) ? (NBTBase) rawEntry.getValue() : getFunc(rawEntry.getValue().getClass()).translate(rawEntry.getValue());
                compound.setTag(key, value);
            }
            return null;
        }

        @Override
        public Map<String,?> translate(NBTBase orig){
            NBTTagCompound compound = (NBTTagCompound) orig;
            Map<String,Object> map = Maps.newHashMap();
            for(Object rawKey : compound.getKeySet()){
                String key = (String) rawKey;
                NBTBase rawValue = compound.getTag(key);
                EnumNBT func = values[compound.getTagId(key)];
                map.put(key, func.translate(rawValue));
            }
            return map;
        }
    },
    INT_ARRAY(NBTTagIntArray.class, int[].class){
        @Override
        public NBTTagIntArray translate(Object orig){
            return new NBTTagIntArray((int[]) orig);
        }

        @Override
        public int[] translate(NBTBase orig){
            return ((NBTTagIntArray) orig).getIntArray();
        }
    };
    public static final EnumNBT[] values = values();

    private final Class<? extends NBTBase> nbt;
    private final Class<?> clazz;

    EnumNBT(Class<? extends NBTBase> nbt, Class<?> clazz){
        this.nbt = nbt;
        this.clazz = clazz;
    }

    public static EnumNBT getFunc(Class<?> clazz){
        for(EnumNBT enumNBT : values){
            if(enumNBT.nbt.isAssignableFrom(clazz) || enumNBT.clazz.isAssignableFrom(clazz) || Primitives.unwrap(enumNBT.clazz).isAssignableFrom(clazz)){
                return enumNBT;
            }
        }
        throw new IllegalArgumentException();
    }

    public static boolean isNBT(Object o){
        return o instanceof NBTBase;
    }

    public abstract NBTBase translate(Object orig);

    public abstract Object translate(NBTBase orig);
}
