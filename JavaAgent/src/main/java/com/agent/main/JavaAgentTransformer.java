package com.agent.main;

import com.sun.org.apache.bcel.internal.classfile.LocalVariableTable;
import com.sun.org.apache.bcel.internal.classfile.StackMap;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import com.agent.domain.ClassInfo;

public class JavaAgentTransformer implements ClassFileTransformer {
	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
							ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {
		/*打印出来当前的类的class文件，查看是否被修改成功了*/
		/*if (className != null && className.contains("JavaAgentTest")) {
			FileOperate.writeClass("./Test.class", bytes);
		}*/

		/*https://blog.csdn.net/dataiyangu/article/details/103240580*/
		/*ClassWriter的构造函数需要传入一个 flag，其含义为：
		ClassWriter(0)：表示 ASM 不会自动自动帮你计算栈帧和局部变量表和操作数栈大小。
		ClassWriter(ClassWriter.COMPUTE_MAXS)：表示 ASM 会自动帮你计算局部变量表和操作数栈的大小，但是你还是需要调用visitMaxs方法，但是可以使用任意参数，因为它们会被忽略。带有这个标识，对于栈帧大小，还是需要你手动计算。
		ClassWriter(ClassWriter.COMPUTE_FRAMES)：表示 ASM 会自动帮你计算所有的内容。你不必去调用visitFrame，但是你还是需要调用visitMaxs方法（参数可任意设置，同样会被忽略）。
		使用这些标识很方便，但是会带来一些性能上的损失：COMPUTE_MAXS标识会使ClassWriter慢10%，COMPUTE_FRAMES标识会使ClassWriter慢2倍，

		ClassReader.accept(ClassVisitor classVisitor, int parsingOptions)中，第二个参数parsingOptions的取值有以下选项：
		ClassReader.SKIP_DEBUG：表示不遍历调试内容，即跳过源文件，源码调试扩展，局部变量表，局部变量类型表和行号表属性，即以下方法既不会被解析也不会被访问（ClassVisitor.visitSource，MethodVisitor.visitLocalVariable，MethodVisitor.visitLineNumber）。使用此标识后，类文件调试信息会被去除，请警记。
		ClassReader.SKIP_CODE：设置该标识，则代码属性将不会被转换和访问，例如方法体代码不会进行解析和访问。
		ClassReader.SKIP_FRAMES：设置该标识，表示跳过栈图（StackMap）和栈图表（StackMapTable）属性，即MethodVisitor.visitFrame方法不会被转换和访问。当设置了ClassWriter.COMPUTE_FRAMES时，设置该标识会很有用，因为他避免了访问帧内容（这些内容会被忽略和重新计算，无需访问）。
		ClassReader.EXPAND_FRAMES：该标识用于设置扩展栈帧图。默认栈图以它们原始格式（V1_6以下使用扩展格式，其他使用压缩格式）被访问。如果设置该标识，栈图则始终以扩展格式进行访问（此标识在ClassReader和ClassWriter中增加了解压/压缩步骤，会大幅度降低性能）
		*/

		/*
		https://www.cnblogs.com/hebaibai/p/11011004.html
		ClassReader.SKIP_CODE：跳过代码属性的标志（个人感觉就是没有方法会被特地跳过）
		ClassReader.SKIP_FRAMES：跳过StackMap和StackMapTable属性的标志。跳过MethodVisitor.visitFrame方法。
		ClassReader.SKIP_DEBUG：跳过SourceFile，SourceDebugExtension，LocalVariableTable，LocalVariableTypeTable和LineNumberTable属性的标志。跳过ClassVisitor.visitSource, MethodVisitor.visitLocalVariable, MethodVisitor.visitLineNumber方法。
		ClassReader.EXPAND_FRAMES：用于展开堆栈映射帧的标志。这会大大降低性能。（文档上写的，感觉上用不到）
		建议EXPAND_FRAMES
		*/

		//		try {
		//			ClassReader cr = new ClassReader(className);
		//			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		//			TimeCountAdpter timeCountAdpter = new TimeCountAdpter(cw);
		//			cr.accept(timeCountAdpter, ClassReader.EXPAND_FRAMES);
		//
		//			return cw.toByteArray();
		//			return b2;
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}

		//		每次加载一次类都提交一次
		//		jdk自带和cloudwise类相关的classload和classname是为null的
		ClassInfo classInfo = new ClassInfo();
		classInfo.setClassLoader(loader);
		classInfo.setClassName(className);
		classInfo.setProtectionDomain(protectionDomain);
		InfoFile.getInstance().submit(classInfo);

		return null;


	}

	public static void main(String[] args) {

		System.out.println("a;b;c".split(";")[0]+" "+"a;b;c".split(";")[1]+" "+"a;b;c".split(";")[2]);
	}
}
