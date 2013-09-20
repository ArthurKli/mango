package com.g3net.tool.cache;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger;

import com.g3net.tool.CTime;
import com.g3net.tool.NumberUtils;

/**
 * ttserver由于不完全兼容memcached协议，协议作一些额外的工作
 * 
 * @author sunchaojin
 * 
 */
public class JavaCachedClient implements CachedClient {
	private static Logger log = Logger.getLogger(JavaCachedClient.class);

	private static class CachedObject implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4601520610530039052L;
		long timestamp;// 时间戳格式为yyyyMMddHHmmss,设置为<=0表示用不过期
		Object entity;// 实体对象

		CachedObject(Object entity, long timestamp) {
			this.entity = entity;
			this.timestamp = timestamp;
		}
	}

	private ConcurrentHashMap<String, Object> client = new ConcurrentHashMap<String, Object>();

	public void setClient(MemcachedClient client) {
		// this.client = client;

	}


	public void delete(String key) {
		client.remove(key);
	}


	private static long addSeconds(int seconds) {
		if (seconds <= 0) {
			return 0;
		}
		TimeZone tz = TimeZone.getTimeZone("GMT+8:00");
		Calendar calendar = Calendar.getInstance(tz);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime().getTime();
	}
	/**
	 * 
	 * @param key
	 * @param value
	 * @param exp 为0表示永不过期
	 */
	public void set(String key, Object value, int exp) {
		long timestamp = addSeconds(exp);
		// String temp = ObjectUtils.converToString(new CachedObject(value,
		// timestamp),isCompress);
		// log.info("sss="+temp.length());
		// TTServerTranscoder stc = new TTServerTranscoder();
		// stc.setCompressionThreshold(Integer.MAX_VALUE);
		client.put(key, new CachedObject(value, timestamp));
	}

	/**
	 * 异步存，永远不过期
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value) {
		this.set(key, value, 0);
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
		long exp = expdate.getTime();
		long cur = CTime.getCurrentDateTime().getTime();
		long interval = (exp - cur) / 1000;
		int intExp = (int) interval;
		if (intExp <= 0) // 表明过期
			return;
		else {
			this.set(key, value, intExp);
		}
		// long timestamp = addSeconds(exp);
		// String temp = ObjectUtils.converToString(new CachedObject(value,
		// timestamp),isCompress);
		// log.info("sss="+temp.length());
		// TTServerTranscoder stc = new TTServerTranscoder();
		// stc.setCompressionThreshold(Integer.MAX_VALUE);

	}

	/**
	 * 同步取
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		try {

			CachedObject co = null;
			co = (CachedObject) client.get(key);

			if (co != null) {
				if (co.timestamp <= 0
						|| co.timestamp >= CTime.getCurrentDateTime().getTime()) {
					return co.entity;
				} else {
					log.debug("[key=" + key + " timestamp=" + co.timestamp
							+ " 已到期]");
					client.remove(key);
					return null;
				}
			}
			log.debug("key=" + key + " 缓存未命中");
			return null;
		} catch (Exception e) {
			log.error("", e);
			return null;
		}
	}

	public boolean keyExists(String key) {

		return get(key) == null ? false : true;
	}

	public void shutdown() {
		client.clear();
	}

	public void flushAll() {
		client.clear();
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
		CachedObject c = new CachedObject("fd", NumberUtils.parseLong(CTime
				.formatDate()));
		log.info(c.entity + "," + c.timestamp);
	}

	public void add(String key, Object value, int exp) {
		this.set(key, value, exp);
	}

	/**
	 * 异步存，永远不过期
	 * 
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value) {
		this.set(key, value);
	}

	/**
	 * 异步存
	 * 
	 * @param key
	 * @param expdate
	 *            将来某一时刻过期
	 * @param value
	 */
	public void add(String key, Object value, Date expdate) {
		this.set(key, value, expdate);
	}


	
	public Object get(String key, long milsec) {
		// TODO Auto-generated method stub
		return this.get(key);
	}

}
