package com.g3net.tool;

  
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger; 

import com.g3net.tool.cache.CachedClient;
import com.g3net.tool.cache.JavaCachedClient;
import com.g3net.tool.cache.MemCachedClient;
import com.g3net.tool.cache.TTServerClient;

public class CachedClientFactory {
	static {
		System.setProperty("net.spy.log.LoggerImpl",
				"net.spy.memcached.compat.log.Log4JLogger");
	}
	private static Logger log = Logger.getLogger(CachedClientFactory.class);
	private static Map<String, CachedClient> map = new ConcurrentHashMap<String, CachedClient>();

	public static enum CachedClientType {
		MemCachedClient, TTServerClient, JavaCachedClient
	}

	public static CachedClient getCachedClient(String name) {
		return map.get(name);
	}

	public static void remove(String name) {

		// log.info("222");
		CachedClient c = (CachedClient) map.get(name);
		if (c != null) {
			c.shutdown();
		}
		map.remove(name);

	}
	public static boolean isRegisted(String name){
		
		if(StringUtils.hasText(name)){
			if (map.containsKey(name)) {
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	public static void clearAll() {

		Set<String> set = map.keySet();
		Iterator<String> i = set.iterator();
		while (i.hasNext()) {
			// log.info("eeeeeeeeeeeee");
			String key = i.next();
			CachedClient c = (CachedClient) map.get(key);
			if (c != null) {
				c.shutdown();
			}
		}
		map.clear();

	}

	/**
	 * 必须保证在最后调用shutdownAll()释放资源
	 */
	public static void shutdownAll() {
		clearAll();
	}

	private static CachedClient create(CachedClientType cachedType)
			throws Exception {

		CachedClient cachedClient = null;

		if (cachedType == CachedClientType.MemCachedClient) {
			cachedClient = new MemCachedClient();
		} else if (cachedType == CachedClientType.TTServerClient) {
			cachedClient = new TTServerClient();
		} else if (cachedType == CachedClientType.JavaCachedClient) {
			cachedClient = new JavaCachedClient();
		} else {
			throw new Exception("cached type is not supported!");
		}
		return cachedClient;

	}

	/**
	 * 
	 * @param name
	 * @param ipAndPortList
	 *<blockquote><pre>
	 *            例如: "192.168.0.1:306 192.168.0.3:3377"
	 *            如果为CachedClientType.JavaCachedClient类型，则不需要指定
	 *</pre></blockquote>
	 * @return
	 */
	public static boolean regist(String name, String ipAndPortList,
			CachedClientType cachedType) {
		try {
			
			if(!StringUtils.hasText(ipAndPortList)){
				ipAndPortList="localhost";
			}
			name = name.trim();

			if (map.containsKey(name)) {
				// log.info("222");
				CachedClient c = (CachedClient) map.get(name);
				c.shutdown();
			}

			CachedClient cachedClient = CachedClientFactory.create(cachedType);
			if (cachedType != CachedClientType.JavaCachedClient) {
				MemcachedClient client = new MemcachedClient(AddrUtil
						.getAddresses(ipAndPortList));
				cachedClient.setClient(client);
			}
			map.put(name, cachedClient);
			return true;

		} catch (Exception e) {
			log.error("regist error!", e);
			return false;
		}
	}

	public static boolean regist(String name, String ip, String port,
			CachedClientType cachedType) {

		if (!StringUtils.hasText(ip))
			ip = "localhost";
		if (!StringUtils.hasText(port))
			port = "";
		else {
			port = ":" + port;
		}
		return CachedClientFactory.regist(name, ip + port, cachedType);
	}

	public static void main(String[] args) throws Exception {
		System.setProperty("net.spy.log.LoggerImpl",
				"net.spy.memcached.compat.log.Log4JLogger"); // mem 2.3

		CachedClientFactory.regist("223", "192.168.0.223:37373",
				CachedClientFactory.CachedClientType.MemCachedClient);
		// System.setProperty("net.spy.log.LoggerImpl",
		// "net.spy.log.Log4JLogger"); //mem 2.2

		// CachedClientFactory.regist(Constants.CachedQuick, "61.145.124.55",
		// "37776");
		CachedClient c = CachedClientFactory.getCachedClient("223");
		// c.delete("45");
		// log.info(c.getAndSet("45", "123456",2));
		// log.info(c.get("45"));
		// //byte[] bs=(byte[])c.get("LIST_PAGE_62_0_1_10");
		// log.info(bs.length+"："+c.get("45"));
		// c.flushAll();
		// TestObj obj = new TestObj();
		// obj.setId(new Long(1));
		// obj.setName("test1");
		byte[] p = new byte[10000001];

		// p.setDescription("ssss");
		c.set("45", p);
		log.info(((byte[]) c.get("45")).length);
		// c.setCommon("45","fffff",true);
		// Integer[] i=new Integer[]{1,2};
		// log.info((c.get("45")));

		// ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		// DataOutputStream dos2 = new DataOutputStream(byteOut);
		//		
		// dos2.writeUTF("中国123");
		// dos2.flush();
		// dos2.close();
		// byte[] bs=byteOut.toByteArray();
		//		
		// c.set("11", bs);
		// bs=(byte[])c.get("11");
		// ByteArrayInputStream inbyteOut = new ByteArrayInputStream(bs);
		// DataInputStream indos2 = new DataInputStream(inbyteOut);
		// log.info(indos2.readUTF());
		// //IOUtils.
		// try {
		// Thread.sleep(4000);
		// log.info("sleeping over 40 s");
		//			
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// log.info("*******"+c.get("45"));
		// c.flushAll();
		CachedClientFactory.clearAll();

	}
}
