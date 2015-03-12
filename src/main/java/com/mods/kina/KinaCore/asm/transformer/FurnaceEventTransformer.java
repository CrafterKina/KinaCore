package com.mods.kina.KinaCore.asm.transformer;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

public class FurnaceEventTransformer implements IClassTransformer, Opcodes{
    private static final String TARGET = "net.minecraft.tileentity.TileEntityFurnace";

    public byte[] transform(String name, String transformedName, byte[] bytes){
        if(!TARGET.equals(transformedName)) return bytes;
        try{
            ClassReader reader = new ClassReader(bytes);
            ClassWriter writer = new ClassWriter(1);
            reader.accept(furnaceClassVisitor(writer), 8);
            System.out.println(new String(writer.toByteArray()));
            return writer.toByteArray();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private ClassVisitor furnaceClassVisitor(ClassWriter writer){
        return new ClassVisitor(ASM4, writer){
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
                if(!"update".equals(name) || !"()V".equals(desc))
                    return super.visitMethod(access, name, desc, signature, exceptions);
                return onUpdateMethodVisitor(api, super.visitMethod(access, name, desc, signature, exceptions));
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
                if(iadd && isFirst && "cookTime".equals(name) && opcode == PUTFIELD){
                    isFirst = false;
                    super.visitFieldInsn(PUTFIELD, "net/minecraft/tileentity/TileEntityFurnace", "cookTime", "I");
                    super.visitVarInsn(ALOAD, 0);
                    super.visitMethodInsn(INVOKESTATIC, "com/mods/kina/KinaCore/event/KinaCoreEventFactory", "onItemAttemptSmelt", "(Lnet/minecraft/tileentity/TileEntityFurnace;)V", false);
                }else{
                    super.visitFieldInsn(opcode, owner, name, desc);
                }
            }
        };
    }
}