package com.mods.kina.KinaCore.asm.transformer;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.*;

public class TransformerTileEntityFurnace implements IClassTransformer, Opcodes{
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
                if("update".equals(name) && "()V".equals(desc))
                    return onUpdateMethodVisitor(api, super.visitMethod(access, name, desc, signature, exceptions));
                if("smeltItem".equals(name) && "()V".equals(desc))
                    return smeltItemMethodVisitor(api, super.visitMethod(access, name, desc, signature, exceptions));
                if("canExtractItem".equals(name) && "(ILnet/minecraft/item/ItemStack;Lnet/minecraft/util/EnumFacing;)Z".equals(desc))
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

    private MethodVisitor smeltItemMethodVisitor(int api, MethodVisitor methodVisitor){
        return new MethodVisitor(api, methodVisitor){
            boolean isub = false;
            boolean isFirst = true;

            public void visitInsn(int opcode){
                if(opcode == ISUB && isFirst) isub = true;
                super.visitInsn(opcode);
            }

            public void visitFieldInsn(int opcode, String owner, String name, String desc){
                if(isub && isFirst && "stackSize".equals(name) && opcode == PUTFIELD){
                    isFirst = false;
                    super.visitFieldInsn(PUTFIELD, "net/minecraft/item/ItemStack", "stackSize", "I");
                    super.visitVarInsn(ALOAD, 0);
                    super.visitMethodInsn(INVOKESTATIC, "com/mods/kina/KinaCore/event/KinaCoreEventFactory", "onItemSmelted", "(Lnet/minecraft/tileentity/TileEntityFurnace;)V", false);
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
                    super.visitMethodInsn(INVOKESTATIC, "net/minecraft/inventory/SlotFurnaceFuel", "isBucket", "(Lnet/minecraft/item/ItemStack;)Z", false);
                }else{
                    super.visitInsn(opcode);
                }
            }
        };
    }
}