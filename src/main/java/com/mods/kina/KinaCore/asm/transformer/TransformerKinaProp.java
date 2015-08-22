package com.mods.kina.KinaCore.asm.transformer;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TransformerKinaProp implements IClassTransformer{
    public static Set<String> className = new HashSet<String>();
    public byte[] transform(String name, String transformedName, byte[] bytes){
        if(bytes == null) return null;
        byte[] copyOf = Arrays.copyOf(bytes, bytes.length);

        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode, 0);


        className.add(classNode.name.replace('/', '.'));

        return copyOf;
    }
}
