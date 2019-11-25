package com.agent.main;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

public class JavaAgentMain {
	public static void premain(String agentArgs, Instrumentation inst) {
		System.out.println("agentArgs : " + agentArgs);
		inst.addTransformer(new ClassFileTransformer() {
			@Override
			public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
									ProtectionDomain protectionDomain, byte[] bytes)
					throws IllegalClassFormatException {
				ClassWriter cw = new ClassWriter(0);
				ClassReader cr = new ClassReader(bytes);
				cr.accept(cw, 0);
				byte[] b2 = cw.toByteArray();
//				System.out.println(b2);
				if (className != null && className.contains("JavaAgentTest")) {
					FileOperate.writeClass("./Test.class", bytes);
				}
				return b2;
			}
		}, true);
	}


}