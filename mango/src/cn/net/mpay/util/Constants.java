package cn.net.mpay.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author hxq
 * 
 */
public class Constants { 
//------------------------新加-------------------------------------------------------
	private static Log log = LogFactory.getLog(Constants.class);
	private static String CONFIG_FILE = "/config.properties";
	private static HashMap resourceMap = new HashMap();
	private static long lastTime;
	private static File f = null;
	
	private static void getConfigFile(){
        URL fileUrl=Constants.class.getResource(CONFIG_FILE);
        CONFIG_FILE = fileUrl.getPath();
        log.info("CONFIG_FILE="+CONFIG_FILE);
        log.error("CONFIG_FILE="+CONFIG_FILE);
   }

	public static String getString(String key) {

		try {
			String value = null;
			if (resourceMap.isEmpty()) {
				loadResource();
			}
			if (fileChanged()) {
				log.info("fileChanged()");
				loadResource();
			}
			value = (String) resourceMap.get(key);
			return value;
		} catch (Exception e) {
			log.error("getString异常" + e.toString());
			return null;
		}
	}

	public static int getInteger(String key) {
		return Integer.valueOf(getString(key)).intValue();
	}

	private static void loadResource() throws IOException {
		resourceMap.clear();
		getConfigFile();
		String resourceFile = CONFIG_FILE;
		f = new File(resourceFile);
		lastTime = f.lastModified();
		FileReader fr = new FileReader(resourceFile);
		BufferedReader br = new BufferedReader(fr);

		String line = "";
		String key = "";
		String value = "";

		while ((line = br.readLine()) != null) {
			if (line.startsWith("#") || line.indexOf("=") == -1) {
				continue;
			} else {
				key = line.substring(0, line.indexOf("="));
				value = line.substring(line.indexOf("=") + 1);

				resourceMap.put(key, value);
			}
		}

	}

	private static boolean fileChanged() {
		if (lastTime == f.lastModified())
			return false;
		else {
			lastTime = f.lastModified();
			// MessagesLogger.info("配置文件被更新时间："+lastTime);
			return true;
		}
	}
//-------------------------------------------------------------------------------
	/**
	 * 加载"config.properties"
	 * 
	 * @param name
	 *            属件名
	 * @return 请求的属性的值
	 */
	public static String loadConfig(String proName) {
		return baseLoad("config.properties", proName);
	}

	/**
	 * 加载文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param proName
	 *            属性名
	 * @return 请求的属性的值
	 */
	public static String loadFile(String fileName, String proName) {
		return baseLoad(fileName, proName);
	}

	/**
	 * 重构加载文件的方法
	 * 
	 * @param fileName
	 * @param proName
	 * @return
	 */
	private static String baseLoad(String fileName, String proName) {
		Properties properties;
		String name = getReasourcePath(fileName);
		properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(name);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties.getProperty(proName);
	}

	/**
	 * 获得资源路径
	 * 
	 * @param resourceName
	 *            资源
	 * @return 资源路径
	 */
	public static String getReasourcePath(String resourceName) {
		if (Constants.class.getClassLoader().getResource(resourceName) == null) {
			return resourceName;
		}
		return Constants.class.getClassLoader().getResource(resourceName)
				.getPath();
	}

	public static void main(String[] args) {
		 //log.info(System.getProperty("java.class.path"));
		log.info(Constants.loadConfig("android_page"));

	}
}
