package com.g3net.tool;

/**
 * <p>Title:Path </p>
 * <p>Description: 文件路径的处�?</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company:ldsoft </p>
 * @author xuyw
 * @version 1.0
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Path {

	public static String getClassPath() {
		return Path.class.getResource("/").getPath();
	}

	public static String getClassPath(String fileName) {
		return Path.class.getResource("/").getPath() + fileName;
	}

	public static String getParentPath(String pathName){
		return FileUtils.getFileParentPath(pathName);
	}
	public static String getFileName(String pathName){
		return FileUtils.getFileName(pathName);
	}
	/**
	 * 可读入jar包里的文件,传入的参数如：/1.xml
	 * 
	 * @param relaPathFile
	 * @return
	 */
	public static InputStream getResource(String relaPathFile) {
		return Path.class.getResourceAsStream(relaPathFile);
	}

	public static InputStream getClassPathResource(String fileName)
			throws IOException {

		FileInputStream fin = new FileInputStream(getClassPath(fileName));
		return fin;
	}
	
	 public static void main(String args[]) {
		
	  }
	

}
