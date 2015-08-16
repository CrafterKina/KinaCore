package com.mods.kina.KinaCore.asm.transformer;

import com.mods.kina.KinaCore.misclib.utils.asm.UtilASM;
import net.minecraft.item.ItemStack;
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
                    super.visitMethodInsn(INVOKESTATIC, "com/mods/kina/KinaCore/event/hooks/KinaCoreHooks", "isBucket", UtilASM.getDesc(boolean.class, ItemStack.class), false);
                    super.visitJumpInsn(IFEQ, label);
                }else{
                    super.visitJumpInsn(opcode, label);
                }
            }
        };
    }
}
