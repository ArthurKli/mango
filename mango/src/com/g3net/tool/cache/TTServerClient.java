package com.g3net.tool.cache;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import net.spy.memcached.CachedData;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.BaseSerializingTranscoder;
import net.spy.memcached.transcoders.Transcoder;
import net.spy.memcached.transcoders.TranscoderUtils;

import org.apache.log4j.Logger;

import com.g3net.tool.ArrayUtils;
import com.g3net.tool.ByteUtils;
import com.g3net.tool.CTime;
import com.g3net.tool.NumberUtils;

/**
 * ttserver由于不完全兼容memcached协议，协议作一些额外的工作
 * @author sunchaojin
 *
 */
public class TTServerClient implements CachedClient {
	private static Logger log = Logger.getLogger(TTServerClient.class);

	public static int intWatiTime = 1000;// 1秒

	public static class TTServerTranscoder extends BaseSerializingTranscoder
			implements Transcoder<Object> {

		static final int MAX_SIZE = 50 * 1024 * 1024;// 100M
		// General flags
		static final int SERIALIZED = 1;
		static final int COMPRESSED = 2;

		// Special flags for specially handled types.
		private static final int SPECIAL_MASK = 0xff00;
		static final int SPECIAL_BOOLEAN = (1 << 8);
		static final int SPECIAL_INT = (2 << 8);
		static final int SPECIAL_LONG = (3 << 8);
		static final int SPECIAL_DATE = (4 << 8);
		static final int SPECIAL_BYTE = (5 << 8);
		static final int SPECIAL_FLOAT = (6 << 8);
		static final int SPECIAL_DOUBLE = (7 << 8);
		static final int SPECIAL_BYTEARRAY = (8 << 8);

		private final TranscoderUtils tu = new TranscoderUtils(true);

		/**
		 * Get a serializing transcoder with the default max data size.
		 */
		public TTServerTranscoder() {
			this(MAX_SIZE);
		}

		/**
		 * Get a serializing transcoder that specifies the max data size.
		 */
		public TTServerTranscoder(int max) {
			super(max);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * net.spy.memcached.Transcoder#decode(net.spy.memcached.CachedData)
		 */
		public Object decode(CachedData d) {

			byte[] ddata = d.getData();

			byte[] flagBytes = Arrays.copyOfRange(ddata, 0, 4);

			byte[] data = Arrays.copyOfRange(ddata, 4, ddata.length);

			Object rv = null;
			int dflags = ByteUtils.convertBytes2Int(flagBytes);
			if ((dflags & COMPRESSED) != 0) {
				data = decompress(data);
			}
			
			int flags = dflags & SPECIAL_MASK;
			if ((dflags & SERIALIZED) != 0 && data != null) {
				rv = deserialize(data);
			} else if (flags != 0 && data != null) {
				switch (flags) {
				case SPECIAL_BOOLEAN:
					rv = Boolean.valueOf(tu.decodeBoolean(data));
					break;
				case SPECIAL_INT:
					rv = new Integer(tu.decodeInt(data));
					break;
				case SPECIAL_LONG:
					rv = new Long(tu.decodeLong(data));
					break;
				case SPECIAL_DATE:
					rv = new Date(tu.decodeLong(data));
					break;
				case SPECIAL_BYTE:
					rv = new Byte(tu.decodeByte(data));
					break;
				case SPECIAL_FLOAT:
					rv = new Float(Float.intBitsToFloat(tu.decodeInt(data)));
					break;
				case SPECIAL_DOUBLE:
					rv = new Double(Double
							.longBitsToDouble(tu.decodeLong(data)));
					break;
				case SPECIAL_BYTEARRAY:
					rv = data;
					break;
				default:
					log.info("Undecodeable with flags --" + flags);
				}
			} else {
				rv = decodeString(data);
			}
			return rv;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.spy.memcached.Transcoder#encode(java.lang.Object)
		 */
		public CachedData encode(Object o) {
			byte[] b = null;
			int flags = 0;
			if (o instanceof String) {
				b = encodeString((String) o);
			} else if (o instanceof Long) {
				b = tu.encodeLong((Long) o);
				flags |= SPECIAL_LONG;
			} else if (o instanceof Integer) {
				b = tu.encodeInt((Integer) o);
				flags |= SPECIAL_INT;
			} else if (o instanceof Boolean) {
				b = tu.encodeBoolean((Boolean) o);
				flags |= SPECIAL_BOOLEAN;
			} else if (o instanceof Date) {
				b = tu.encodeLong(((Date) o).getTime());
				flags |= SPECIAL_DATE;
			} else if (o instanceof Byte) {
				b = tu.encodeByte((Byte) o);
				flags |= SPECIAL_BYTE;
			} else if (o instanceof Float) {
				b = tu.encodeInt(Float.floatToRawIntBits((Float) o));
				flags |= SPECIAL_FLOAT;
			} else if (o instanceof Double) {
				b = tu.encodeLong(Double.doubleToRawLongBits((Double) o));
				flags |= SPECIAL_DOUBLE;
			} else if (o instanceof byte[]) {
				b = (byte[]) o;
				flags |= SPECIAL_BYTEARRAY;
			} else {
				b = serialize(o);
				flags |= SERIALIZED;
			}
			assert b != null;
			if (b.length > compressionThreshold) {
				byte[] compressed = compress(b);
				if (compressed.length < b.length) {
					log.info(String.format("Compressed %s from %d to %d",
									o.getClass().getName(), b.length,
									compressed.length));
					b = compressed;
					flags |= COMPRESSED;
				} else {
					log.info(String.format(
											"Compression increased the size of %s from %d to %d",
											o.getClass().getName(), b.length,
											compressed.length));
				}
			}
			byte[] bs = (byte[])ArrayUtils.insert(b, 0, ByteUtils.convertInt2Bytes(flags));
			return new CachedData(flags, bs, getMaxSize());
		}

	}

	private static class CachedObject implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4601520610530039252L;
		long timestamp;// 时间戳格式为yyyyMMddHHmmss,设置为<=0表示用不过期
		Object entity;// 实体对象

		CachedObject(Object entity, long timestamp) {
			this.entity = entity;
			this.timestamp = timestamp;
		}
	}

	private MemcachedClient client = null;

	public void setClient(MemcachedClient client) {
		this.client = client;
	}

	public void delete(String key) {
		client.delete(key);
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

	public void set(String key, Object value, int exp) {
		long timestamp = addSeconds(exp);
		// String temp = ObjectUtils.converToString(new CachedObject(value,
		// timestamp),isCompress);
		// log.info("sss="+temp.length());
		TTServerTranscoder stc = new TTServerTranscoder();
		//stc.setCompressionThreshold(Integer.MAX_VALUE);
		client.set(key, exp, new CachedObject(value, timestamp), stc);
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
//		log.info("intExp==="+intExp);
		if (intExp <= 0) // 表明过期
			return;
		this.set(key, value, intExp);
	}

	/**
	 * 同步取
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return this.get(key,TTServerClient.intWatiTime);
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
		long timestamp = addSeconds(exp);
		// String temp = ObjectUtils.converToString(new CachedObject(value,
		// timestamp),isCompress);
		// log.info("sss="+temp.length());
		TTServerTranscoder stc = new TTServerTranscoder();
		//stc.setCompressionThreshold(Integer.MAX_VALUE);
		client.add(key, exp, new CachedObject(value, timestamp), stc);
	}

	/**
	 * 异步存，永远不过期
	 * 
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value) {
		this.add(key, value, 0);
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
		long exp = expdate.getTime();
		long cur = CTime.getCurrentDateTime().getTime();
		long interval = (exp - cur) / 1000;
		int intExp = (int) interval;
		if (intExp <= 0) // 表明过期
			return;
		this.add(key, value, intExp);
	}

	/**
	 * 
	 */
	public Object get(String key, long milsecTimeOut) {
		// TODO Auto-generated method stub
		try {
			TTServerTranscoder stc = new TTServerTranscoder();
			CachedObject co = null;
		//	stc.setCompressionThreshold(Integer.MAX_VALUE);

			Future<Object> f = client.asyncGet(key,stc);
			try {
				co = (CachedObject) f.get(milsecTimeOut, TimeUnit.SECONDS);
			} catch (TimeoutException e) {
				// Since we don't need this, go ahead and cancel the operation.
				// This
				// is not strictly necessary, but it'll save some work on the
				// server.
				log.error(e);
				f.cancel(true);
				// Do other timeout related stuff
			}

			// CachedObject co=(CachedObject)client.get(key,stc);
			// log.info(co);
			// return co.entity;
			// CachedObject co=(CachedObject)ObjectUtils.converToObject((String)
			// client.get(key,stc),isCompress);
			if (co != null) {
				if (co.timestamp <= 0
						|| co.timestamp >= CTime.getCurrentDateTime().getTime()) {
					return co.entity;
				} else {
					log.debug("[key=" + key + " timestamp=" + co.timestamp
							+ " 已到期]");
					client.delete(key);
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

}
