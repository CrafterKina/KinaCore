package com.mods.kina.KinaCore.asm.transformer;

import com.mods.kina.KinaCore.misclib.utils.asm.UtilASM;
import com.mods.kina.KinaCore.movelib.config.KinaProp;
import com.mods.kina.KinaCore.movelib.config.ValueAssigner;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

public class TransformerKinaProp implements IClassTransformer{
    public byte[] transform(String name, String transformedName, byte[] bytes){
        if(bytes == null) return null;

        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);

        searchAnnotatedClass(classNode);

        searchAnnotatedFields(classNode);

        return bytes;
    }

    private void searchAnnotatedFields(ClassNode classNode){
        String className = classNode.name;
        for(FieldNode fieldNode : classNode.fields){
            for(AnnotationNode annotation : fieldNode.visibleAnnotations){
                if(annotation.desc.equals(Type.getDescriptor(KinaProp.class))){
                    Boolean isInstance = UtilASM.getValueFromAsMapList(annotation.values, "isInstance");
                    if(isInstance){
                        ValueAssigner.instanceFields.put(className, fieldNode.name);
                    }else{
                        ValueAssigner.configurableFields.put(className, fieldNode.name);
                    }
                }
            }
        }
    }

    private void searchAnnotatedClass(ClassNode classNode){
        for(AnnotationNode annotation : classNode.visibleAnnotations){
            if(annotation.desc.equals(Type.getDescriptor(KinaProp.class))){
                ValueAssigner.defaultedClasses.add(classNode.name.replace('/', '.'));
            }
        }
    }
}
