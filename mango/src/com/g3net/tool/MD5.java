package com.g3net.tool;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class MD5 implements java.io.Serializable {
	private static Logger log = Logger.getLogger(MD5.class);

	private static String to32BitString(String plainText,boolean is32or16,String charset) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			if(StringUtils.hasText(charset))
				md.update(plainText.getBytes(charset));
			else{
				md.update(plainText.getBytes());
			}
			byte[] b = md.digest();
			String buf=ByteUtils.toHexAscii(b,"");
			if(is32or16){
				return buf;
			}else{
				return  buf.substring(8, 24);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}
		return "";
	}
	/**
	 * 产生32位md5加密字符串
	 * @param s
	 * @return
	 */
	public final static String MD5generator(String s) {
		//String charset =System.getProperties()
		return MD5.to32BitString(s, true,"");
	}
	/**
	 * 产生32位md5加密字符串
	 * @param s
	 * @return
	 */
	public final static String MD5generator(String s,String charset) {
		return MD5.to32BitString(s, true,charset);
	}
	/**
	 * 产生16为md5加密字符串
	 * @param s
	 * @return
	 */
	public final static String MD5generator16Bit(String s,String charset) {
		return MD5.to32BitString(s, true,charset);
	}
	public final static String MD5generator16Bit(String s) {
		return MD5.to32BitString(s, true,"");
	}
	public static void main(String args[]) {

		MD5 m = new MD5();

		String ss ="1";
		//String s1=MD5.toString(ss);
		String s2 =MD5.MD5generator(ss);
		log.info(s2);

	}

}
