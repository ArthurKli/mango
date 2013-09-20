package com.g3net.tool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

/**
 * 封装了apache开源项目commons-configuration,用于读写xml属性文件
 */
public class XMLPropertyUtils {

	public XMLConfiguration config = new XMLConfiguration();
	private static Logger log = Logger.getLogger(XMLPropertyUtils.class);
   
	/**  
     * 过虑xml的无效字符。<p/>  
     * <ol>  
     *  <li>0x00 - 0x08</li>  
     *  <li>0x0b - 0x0c</li>  
     *  <li>0x0e - 0x1f</li>  
     * </ol>  
     * 
     */  
    public  static String filter(String xmlStr) {   
        StringBuilder sb = new StringBuilder();   
        char[] chs = xmlStr.toCharArray();   
        //log.debug("filter before=" +chs.length);   
        for(char ch : chs) {   
            if((ch >= 0x00 && ch <= 0x08)   
                || (ch >= 0x0b && ch <= 0x0c)   
                || (ch >= 0x0e && ch <= 0x1f)) {   
                //eat...   
            } else {   
                sb.append(ch);   
            }   
        }   
        //log.debug("filter after=" +sb.length());   
        return sb.toString();   
    }   
	/**
	 *  
	 * @param in 会被关闭
	 * @param encoding
	 * @param validating
	 */
	public XMLPropertyUtils(InputStream in, String encoding, boolean validating) {
		try {
			this.setValidating(validating);
			config.load(in, encoding);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			}
		}
	}


	public XMLPropertyUtils(String fileName,String encoding, boolean validating) throws FileNotFoundException {
		//FileInputStream fin=new FileInputStream();
		this(new FileInputStream(fileName),encoding,validating);
	}
	public XMLPropertyUtils(Reader reader, boolean validating) {
		try {
			this.setValidating(validating);
			config.load(reader);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("", e);
			}
		}
	}

	private void setValidating(boolean validating) {
		config.setValidating(validating);
	}

	public void addProperty(String key, Object value) {
		config.addProperty(key, value);
	}

	public Object getProperty(String key) {
		return config.getProperty(key);
	}

	public List getList(String key) {
		return config.getList(key);
	}

	public String getString(String key) {
		return config.getString(key);
	}

	public BigDecimal getBigDecimal(String key) {
		return config.getBigDecimal(key);
	}

	public BigInteger getBigInteger(String key) {
		return config.getBigInteger(key);
	}

	public boolean getBoolean(String key) {
		return config.getBoolean(key);
	}

	public byte getByte(String key) {
		return config.getByte(key);
	}

	public int getInt(String key) {
		return config.getInt(key);
	}

	public double getDouble(String key) {
		return config.getDouble(key);
	}

	public float getFloat(String key) {
		return config.getFloat(key);
	}

	public long getLong(String key) {
		return config.getLong(key);
	}

	public short getShort(String key) {
		return config.getShort(key);
	}

	public void setProperty(String key,Object value){
		config.setProperty(key, value);
	}
	public boolean containsKey(String key) {
		
		return config.containsKey(key);
	}

	/**
	 * 得到保存文件的编码格式
	 * @return
	 */
	public String getEncoding() {
		return config.getEncoding();
	}

	/**
	 * 保存文件的编码格式
	 * @param encoding
	 */
	public void setEncoding(String encoding) {
		config.setEncoding(encoding);
	}

	public void clearTree(String key) {
		config.clearTree(key);
	}

	public Iterator getKeys() {
		return config.getKeys();
	}

	public void save(String fileName,String encoding) {
		FileOutputStream fo=null;
		try {
			fo=new FileOutputStream(fileName);
			this.save(fo, encoding,true);
			//config.setReloadingStrategy(strategy)
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		}finally{
			try{
				fo.close();
			}catch(Exception e){
				log.error("", e);
			}
		}
	}


	/**
	 * @param out
	 * @param encoding
	 */
	public void save(OutputStream out, String encoding) {
		this.save(out, encoding,true);
	}
	/**
	 * @param out
	 * @param encoding
	 * @param close 是否关闭 out
	 */
	public void save(OutputStream out, String encoding,boolean close) {
		try {
			config.save(out, encoding);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("", e);
		} finally{
			if(close){
				try{
					out.close();
				}catch(Exception e){
					log.error("",e);
				}
			}
		}
	}

	public Iterator getKeys(String prefix) {
		return config.getKeys(prefix);
	}

	public String[] getStringArray(String key) {
		return config.getStringArray(key);
		// config.
		// config.getEncoding()
	}

	// Support for variable interpolation. Property values containing special
	// variable tokens (like ${var}) will be replaced by their corresponding
	// values.

	/**
	 * 删除指定的属性
	 * 
	 * @param key
	 */
	public void clearProperty(String key) {
		config.clearProperty(key);
	}

	public void clear() {
		config.clear();
	}

	public static void main(String[] args) {
		XMLPropertyUtils config = new XMLPropertyUtils(Path.getResource("/test.xml"),"utf-8",false);
		config.setEncoding("utf-8");
		//config.setProperty("background", "sss");
		
		log.info(config.getString("colors.background"));
		log.info(config.getString("colors.link[@normal]"));
		//config.addProperty("colors.background", "sss");
		//config.save();
	//	config.save("f:/1.xml","utf-8");
	}
}
