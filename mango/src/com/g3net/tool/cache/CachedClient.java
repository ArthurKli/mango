package com.g3net.tool.cache;

import java.util.Date;

import net.spy.memcached.MemcachedClient;


/**
 * 封装了google的spy memcached客户端 ，

 * @author sunchaojin
 * 
 */
public interface CachedClient {

	
	public void setClient(MemcachedClient client);
	/**
	 * 异步存
	 * 
	 * @param key
	 * @param exp
	 *            距离当前的秒内有效
	 * @param value
	 */
	public void set(String key, Object value, int exp);

	/**
	 * 异步存，永远不过期
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value) ;

	/**
	 * 异步存
	 * 
	 * @param key
	 * @param expdate
	 *            将来某一时刻过期
	 * @param value
	 */
	public void set(String key, Object value, Date expdate) ;
	/**
	 * 异步存，永远不过期
	 * 
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value) ;
	/**
	 * 
	 * @param key
	 * @param value
	 * @param exp
	 *            单位是秒，距离现在的秒数
	 */
	public void add(String key, Object value, int exp) ;

	/**
	 * 
	 * @param key
	 * @param value
	 * @param expdate
	 *            将来某一刻
	 */
	public void add(String key, Object value, Date expdate) ;

	public void delete(String key) ;

	/**
	 * 同步取
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) ;


	/**
	 * 
	 * @param key
	 * @param milsecTimeOut
	 * @return
	 */
	public Object get(String key, long milsecTimeOut);
	public boolean keyExists(String key) ;

	public void shutdown() ;

	public void flushAll() ;



}
