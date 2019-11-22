package com.agent.main;

import java.io.*;

public class FileOperate {
	/*
	* 将字节码写到class文件中，这里第一个参数是xxx.class，例如./Test.class，第二个是字节数组
	* 为什么不能直接sout这个字节数组？因为输出的是真正的数组，只有写成class文件才会自动转化成真正的可读的代码
	* 但是不能直接调用这个函数，否则输出的是最后一个类，需要判断下输出希望输出的类
	*
	*eg:
	if (className!=null&&className.contains("JavaAgentTest")) {
		FileOperate.writeClass("./Test.class", bytes);
	}
	* */
	public static void  writeClass(String fileName, byte[] buf){
		try {
			File file = new File(fileName);
			if(file.exists()){
				file.delete();
			}
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(fileName);
			InputStream is = new ByteArrayInputStream(buf);
			byte[] buff = new byte[1024];
			int len = 0;
			while((len=is.read(buff))!=-1){
				out.write(buff, 0, len);
			}
			is.close();
			out.close();
		} catch (IOException e) {

		}
	}
}
