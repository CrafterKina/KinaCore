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

package jp.crafterkina.KinaCore.asm.transformer;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.*;

import static org.objectweb.asm.Opcodes.*;

public class TransformerSlotFurnaceFuel implements IClassTransformer{
    private static final String TARGET = "net.minecraft.inventory.SlotFurnaceFuel";

    public byte[] transform(String name, String transformedName, byte[] bytes){
        if(!TARGET.equals(transformedName)) return bytes;
        try{
            ClassReader reader = new ClassReader(bytes);
            ClassWriter writer = new ClassWriter(1);
            reader.accept(slotClassVisitor(writer), 8);
            return writer.toByteArray();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private ClassVisitor slotClassVisitor(ClassWriter writer){
        return new ClassVisitor(ASM4, writer){
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
                String mappedName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(FMLDeobfuscatingRemapper.INSTANCE.unmap(name).replace('/', '.'), name, desc);
                if("isBucket".equals(mappedName))
                    return isBucketMethodVisitor(api, super.visitMethod(access, name, desc, signature, exceptions));
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        };
    }

    private MethodVisitor isBucketMethodVisitor(int api, MethodVisitor methodVisitor){
        return new MethodVisitor(api, methodVisitor){
            int count = 0;

            public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf){
                count++;
                if(count == 2) return;
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }

            public void visitFieldInsn(int opcode, String owner, String name, String desc){
                if(opcode == GETSTATIC && "net/minecraft/init/Items".equals(owner) && "bucket".equals(FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(owner, name, desc)))
                    return;
                super.visitFieldInsn(opcode, owner, name, desc);
            }

            public void visitJumpInsn(int opcode, Label label){
                if(opcode == IF_ACMPNE){
                    super.visitMethodInsn(INVOKESTATIC, "com/mods/kina/KinaCore/event/hooks/KinaCoreHooks", "isBucket", "(Lnet/minecraft/item/ItemStack;)Z", false);
                    super.visitJumpInsn(IFEQ, label);
                }else{
                    super.visitJumpInsn(opcode, label);
                }
            }
        };
    }
}
