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
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class TransformerTileEntityFurnace implements IClassTransformer{
    private static final String TARGET = "net.minecraft.tileentity.TileEntityFurnace";

    public byte[] transform(String name, String transformedName, byte[] bytes){
        if(!TARGET.equals(transformedName)) return bytes;
        try{
            ClassReader reader = new ClassReader(bytes);
            ClassWriter writer = new ClassWriter(1);
            reader.accept(furnaceClassVisitor(writer), 8);
            return writer.toByteArray();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private ClassVisitor furnaceClassVisitor(ClassWriter writer){
        return new ClassVisitor(ASM4, writer){
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
                String mappedName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(FMLDeobfuscatingRemapper.INSTANCE.unmap(TARGET.replace('.', '/')).replace('/', '.'), name, desc);
                if("update".equals(mappedName) && "()V".equals(desc))
                    return onUpdateMethodVisitor(api, super.visitMethod(access, name, desc, signature, exceptions));
                if("smeltItem".equals(mappedName) && "()V".equals(desc))
                    return smeltItemMethodVisitor(api, super.visitMethod(access, name, desc, signature, exceptions));
                if("canExtractItem".equals(mappedName) && "(ILnet/minecraft/item/ItemStack;Lnet/minecraft/util/EnumFacing;)Z".equals(desc))
                    return canExtractItemMethodVisitor(api, super.visitMethod(access, name, desc, signature, exceptions));
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        };
    }

    private MethodVisitor onUpdateMethodVisitor(int api, MethodVisitor parent){
        return new MethodVisitor(api, parent){
            boolean iadd = false;
            boolean isFirst = true;

            public void visitInsn(int opcode){
                if(opcode == IADD && isFirst) iadd = true;
                super.visitInsn(opcode);
            }

            public void visitFieldInsn(int opcode, String owner, String name, String desc){
                if(iadd && isFirst && "cookTime".equals(FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(TARGET.replace('.', '/'), name, desc)) && opcode == PUTFIELD){
                    isFirst = false;
                    super.visitFieldInsn(opcode, owner, name, desc);
                    super.visitVarInsn(ALOAD, 0);
                    super.visitMethodInsn(INVOKESTATIC, "com/mods/kina/KinaCore/event/hooks/KinaCoreEventFactory", "onItemAttemptSmelt", "(Lnet/minecraft/tileentity/TileEntityFurnace;)V", false);
                }else{
                    super.visitFieldInsn(opcode, owner, name, desc);
                }
            }
        };
    }

    private MethodVisitor smeltItemMethodVisitor(int api, MethodVisitor methodVisitor){
        return new MethodVisitor(api, methodVisitor){
            boolean isub = false;
            boolean isFirst = true;

            public void visitInsn(int opcode){
                if(opcode == ISUB && isFirst) isub = true;
                super.visitInsn(opcode);
            }

            public void visitFieldInsn(int opcode, String owner, String name, String desc){
                if(isub && isFirst && "stackSize".equals(FMLDeobfuscatingRemapper.INSTANCE.mapFieldName(TARGET.replace('.', '/'), name, desc)) && opcode == PUTFIELD){
                    isFirst = false;
                    super.visitFieldInsn(opcode, owner, name, desc);
                    super.visitVarInsn(ALOAD, 0);
                    super.visitMethodInsn(INVOKESTATIC, "com/mods/kina/KinaCore/event/hooks/KinaCoreEventFactory", "onItemSmelted", "(Lnet/minecraft/tileentity/TileEntityFurnace;)V", false);
                }else{
                    super.visitFieldInsn(opcode, owner, name, desc);
                }
            }
        };
    }

    private MethodVisitor canExtractItemMethodVisitor(int api, MethodVisitor methodVisitor){
        return new MethodVisitor(api, methodVisitor){
            boolean isFirst = true;

            public void visitInsn(int opcode){
                if(opcode == ICONST_0 && isFirst){
                    isFirst = false;
                    super.visitVarInsn(ALOAD, 2);
                    super.visitMethodInsn(INVOKESTATIC, "net/minecraft/inventory/SlotFurnaceFuel", "func_178173_c_", "(Lnet/minecraft/item/ItemStack;)Z", false);
                }else{
                    super.visitInsn(opcode);
                }
            }
        };
    }
}