package com.g3net.tool.cache;

import java.util.Date;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger;

public class MemCachedClient implements CachedClient {
	private static Logger log = Logger.getLogger(MemcachedClient.class);
	private MemcachedClient client = null;
	public static int intWatiTime = 1000;// 1秒

	public void setClient(MemcachedClient client) {
		this.client = client;
	}
	/**
	 * 异步存
	 * 
	 * @param key
	 * @param exp
	 *            距离当前的秒内有效
	 * @param value
	 */
	public void set(String key, Object value, int exp) {
		client.set(key, exp, value);
	}
	/**
	 * 异步存，永远不过期
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value) {
		client.set(key, 0, value);
	}
	/**
	 * 异步存
	 * 
	 * @param key
	 * @param expdate
	 *            将来某一时刻过期
	 * @param value
	 */
	public void set(String key, Object value, Date expdate) {
		long exp = expdate.getTime() / 1000;
		int intExp = (int) exp;
		client.set(key, intExp, value);
	}
	/**
	 * 异步存，永远不过期
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value) {
		client.add(key, 0, value);
	}
	/**
	 * 
	 * @param key
	 * @param value
	 * @param exp
	 *            单位是秒，距离现在的秒数
	 */
	public void add(String key, Object value, int exp) {
		client.add(key, exp, value);
	}
	/**
	 * 
	 * @param key
	 * @param value
	 * @param expdate
	 *            将来某一刻
	 */
	public void add(String key, Object value, Date expdate) {
		long exp = expdate.getTime() / 1000;
		int intExp = (int) exp;
		client.add(key, intExp, value);
	}
	public void delete(String key) {
		client.delete(key);
	}

	/**
	 * 同步取
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		try {

			return this.get(key, MemCachedClient.intWatiTime);
			// return null;
		} catch (Exception e) {

			log.error("", e);
			return null;
		}
	}
	public boolean keyExists(String key) {

		return get(key) == null ? false : true;
	}
	public void shutdown() {
		client.shutdown();
	}
	public void flushAll() {
		client.flush();
	}
	public Object get(String key, long milsecTimeOut) {
		// TODO Auto-generated method stub
		try {

			Future<Object> f = client.asyncGet(key);
			try {
				Object co = f.get(milsecTimeOut, TimeUnit.MILLISECONDS);
				return co;
			} catch (TimeoutException e) {
				// Since we don't need this, go ahead and cancel the operation.
				// This
				// is not strictly necessary, but it'll save some work on the
				// server.
				log.error(e);
				f.cancel(true);
				// Do other timeout related stuff
			}

			return null;
		} catch (Exception e) {
			log.error("", e);
			return null;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Tree_class t=new Tree_class();
		// t.setAuto_pub(2);
		// t.setDesc1("ee");
		// List list=new ArrayList();
		// list.add(t);

		// CachedClient.set("ttt", list);
		// log.info(CachedClient.get("ttt"));
		// t=(Tree_class)CachedClient.get("ttt");
		// log.info(t.getDesc1());
		// List list2=(ArrayList)CachedClient.get("ttt");
		// t=(Tree_class)list2.get(0);
		// log.info(t.getDesc1());
	}

}
