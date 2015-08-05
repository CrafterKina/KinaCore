package com.mods.kina.KinaCore.asm.transformer;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.*;

public class TransformerBlockRendererDispatcher implements IClassTransformer, Opcodes{
    private static final String TARGET = "net.minecraft.client.renderer.BlockRendererDispatcher";

    public byte[] transform(String name, String transformedName, byte[] bytes){
        if(!TARGET.equals(transformedName)) return bytes;
        try{
            ClassReader reader = new ClassReader(bytes);
            ClassWriter writer = new ClassWriter(1);
            reader.accept(rendererClassVisitor(writer), 8);
            return writer.toByteArray();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private ClassVisitor rendererClassVisitor(ClassWriter writer){
        return new ClassVisitor(ASM4, writer){
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
                String mappedName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(FMLDeobfuscatingRemapper.INSTANCE.unmap(TARGET.replace('.', '/')).replace('/', '.'), name, desc);
                if("renderBlock".equals(mappedName) && "(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/BlockPos;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/WorldRenderer;)Z".equals(desc))
                    return renderBlockMethodVisitor(api, super.visitMethod(access, name, desc, signature, exceptions));
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        };
    }

    private MethodVisitor renderBlockMethodVisitor(int api, MethodVisitor parent){
        return new MethodVisitor(api, parent){
            Label visitLabel;
            boolean enable = false;

            public void visitInsn(int opcode){
                /*if(enable && opcode == ICONST_0){
                    mv.visitVarInsn(ALOAD, 1);
                    mv.visitVarInsn(ALOAD, 2);
                    mv.visitVarInsn(ALOAD, 3);
                    mv.visitVarInsn(ALOAD, 4);
                    mv.visitMethodInsn(INVOKESTATIC, "com/mods/kina/KinaCore/event/hooks/KinaCoreEventFactory", "onRenderBlock", "(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/util/BlockPos;Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/client/renderer/WorldRenderer;)Z", false);
                    enable = false;
                    return;
                }*/
                super.visitInsn(opcode);
            }

            public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels){
                visitLabel = dflt;
                super.visitTableSwitchInsn(min, max, dflt, labels);
            }

            public void visitLabel(Label label){
                enable = label == visitLabel;
                super.visitLabel(label);
            }
        };
    }
}
