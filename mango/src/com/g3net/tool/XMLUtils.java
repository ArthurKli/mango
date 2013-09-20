package com.g3net.tool;

import org.apache.log4j.Logger;

public class XMLUtils {
	private static Logger log = Logger.getLogger(XMLUtils.class);

	public static String elementContentFilter(String s){
		return s.replaceAll("<", "&lt;").replaceAll("&", "&amp;").replaceAll("]]>", "]]&amp;");
	}
	public static String cdataSectionContentFilter(String s){
		return s.replaceAll("]]>", "]]&amp;");
	}
	
	public static String commentContentFilter(String s){
		return s.replaceAll("--", "");
	}
	
	/**
	 * xml的属性里不应该包含<,&,"字符，如果出现要进行转义
	 * @param s
	 * @return
	 */
	public static String arributeContentFilter(String s){
		return s.replaceAll("<", "&lt;").replaceAll("&", "&amp;").replaceAll("\"", "&quot;");
	}
	
	public static void main(String[] args) {
		log.info(ObjectUtils.toJsonString(new String[]{"a'&b","gg"}));
		log.info(ObjectUtils.toString(new String[]{"a'\'b","gg"}));
		log.info(ObjectUtils.toJsonString(new int[]{23,45}));
	}
}
