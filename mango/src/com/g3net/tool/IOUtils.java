package com.g3net.tool;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.apache.log4j.Logger;

public class IOUtils {
	private static Logger log = Logger.getLogger(IOUtils.class);

	
	
	/**
	 * Get the contents of an InputStream as a String using the default
	 * character encoding of the platform.
	 * 
	 * @param in
	 * @return
	 */
	public static String toString(InputStream in,boolean close) {
		try {
			return org.apache.commons.io.IOUtils.toString(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("", e);

		}finally{
			try {
				if(close)
					in.close();
			} catch (IOException e) {
				
			}
		}
		return "";
	}

	/**
	 * 没有关闭流的连接
	 * 
	 * @param in
	 * @param encoding
	 * @return
	 */
	public static String toString(InputStream in, String encoding,boolean close) {
		try {
			return org.apache.commons.io.IOUtils.toString(in, encoding);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}finally{
			try {
				if(close)
					in.close();
			} catch (IOException e) {
				
			}
		}
		return "";
	}

	public static void main(String[] args)throws Exception {
		
		byte[] ii="123456789".getBytes("gbk");
		log.info(ByteUtils.format(ii));
		ByteArrayInputStream bi=new ByteArrayInputStream("1234567890".getBytes());
		
		
		bi.read();
		bi.read();
	
		
		byte[] ss=IOUtils.readFully(bi, 3, false);
		log.info(ByteUtils.format(ss));
		byte[] oo=new byte[]{(byte)bi.read(),(byte)bi.read(),(byte)bi.read()};
		log.info(ByteUtils.format(oo));
		bi.close();
	}
	
	/**
	 * 读固定长度的字节,如果少于给定的字节,抛出异常
	 * @param in
	 * @param readLen
	 * @param close
	 * @return
	 * @throws Exception
	 */
	public static byte[] readFully(InputStream in, int readLen,boolean close) throws IOException{

		
		//DataInputStream s=new DataInputStream(in);
		
		byte[] bs = new byte[readLen];
		// in.read(bs);
		// 读满一个数组
		int bytesRead = 0;
		try {
			
			//BufferedInputStream br=new BufferedInputStream(in);
			while (bytesRead < readLen) {

				int result = in.read(bs, bytesRead, readLen - bytesRead);

				if (result == -1)
					break;

				bytesRead += result;

			}
			if (bytesRead < readLen) {
				//bs = Arrays.copyOfRange(bs, 0, bytesRead);
				throw new IOException("haven't  read given" +
						" number[" +readLen+
						"]bytes");
			}
		} catch (IOException ex) {
			log.error("", ex);
			throw ex;
			//bs = new byte[0];
		}finally{
			try {
				if(close)
					in.close();
			} catch (IOException e) {
				
			}
		}
		return bs;
	}
	
	/**
	 * 读固定长度的字符,如果少于给定的字节,抛出异常
	 * @param in
	 * @param readLen
	 * @param close
	 * @return
	 * @throws Exception
	 */
	public static char[] readFully(Reader in, int readLen,boolean close) throws IOException{

		
		
		char[] bs = new char[readLen];
		// in.read(bs);
		// 读满一个数组
		int bytesRead = 0;
		try {
			while (bytesRead < readLen) {

				int result = in.read(bs, bytesRead, readLen - bytesRead);

				if (result == -1)
					break;

				bytesRead += result;

			}
			if (bytesRead < readLen) {
				throw new IOException("haven't  read given" +
						" number[" +readLen+
						"]chars");
			}
		} catch (IOException ex) {
			log.error("", ex);
			throw ex;
			//bs = new char[0];
		}finally{
			try {
				if(close)
					in.close();
			} catch (IOException e) {
				
			}
		}
		return bs;
	}

	public static byte[] toByteArray(InputStream in,boolean close) {
		try {
			
			return org.apache.commons.io.IOUtils.toByteArray(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}finally{
			try {
				if(close)
					in.close();
			} catch (IOException e) {
				
			}
		}
		return new byte[0];
	}

	
	public static byte[] toByteArray(Reader in,String charset,boolean close) {
		try {
			return org.apache.commons.io.IOUtils.toByteArray(in,charset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}finally{
			try {
				if(close)
					in.close();
			} catch (IOException e) {
				
			}
		}
		return new byte[0];
	}
	public static String toString(Reader in,boolean close) {
		try {
			return org.apache.commons.io.IOUtils.toString(in);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}finally{
			try {
				if(close)
					in.close();
			} catch (IOException e) {
				
			}
		}
		return "";
	}

	public static void close(InputStream in) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("", e);
				// e.printStackTrace();
			}
		}
	}

	public static void close(OutputStream out) {
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("", e);
				// e.printStackTrace();
			}
		}
	}

	public static void close(Writer out) {
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("", e);
				// e.printStackTrace();
			}
		}
	}

	public static void close(Reader in) {
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("", e);
				// e.printStackTrace();
			}
		}
	}
}
