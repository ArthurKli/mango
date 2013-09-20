package com.g3net.tool;

import java.io.InputStream;

import java.util.Map;
import java.util.Properties;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.g3net.tool.http.HttpClient;
import com.g3net.tool.http.OneThreadHttpClient;
import com.g3net.tool.http.MultiThreadHttpClient;

public class HttpUtils {
	private static Logger log = Logger.getLogger(HttpUtils.class);

	public static InputStream postAndGetStream(String url, Map parms)
			throws Exception {
		try {
			MultiThreadHttpClient client = new MultiThreadHttpClient();
			client.setBody(parms);
			client.post(url);
			return client.getInputStream();

		} catch (Exception e) {
			log.error(":" + url + ":" + NetUtils.urlMapToQueryStr(parms), e);
			throw e;
		}

	}

	public static InputStream postAndGetStream(String url, Map parms,
			int timeout) throws Exception {
		HttpClient client = new HttpClient(timeout, timeout);
		try {

			client.setBody(parms);
			client.post(url);
			return client.getInputStream();

		} catch (Exception e) {
			log.error(":" + url + ":" + NetUtils.urlMapToQueryStr(parms), e);
			throw e;
		} finally {
			client.shutdown();
		}

	}

	public static InputStream postAndGetStream(String url, byte[] postBody,
			String requestContentType) throws Exception {
		try {
			MultiThreadHttpClient client = new MultiThreadHttpClient();
			client.setRequestContentType(requestContentType);
			client.setBody(postBody);
			client.post(url);
			return client.getInputStream();

		} catch (Exception e) {
			log.error(":" + url + ":requestContentType=" + requestContentType,
					e);
			throw e;
		}

	}

	public static String post(String url, Map parms, String requestCharset,
			String ReturnDefaultCharset) throws Exception {
		try {
			MultiThreadHttpClient client = new MultiThreadHttpClient();
			client.setRequestContentCharset(requestCharset);
			client.setBody(parms);
			client.post(url);
			return client.getString(ReturnDefaultCharset);

		} catch (Exception e) {
			log.error(":" + url + ":" + NetUtils.urlMapToQueryStr(parms), e);
			throw e;
		}

	}

	public static String post(String url, Map parms, String requestCharset,
			String ReturnDefaultCharset, int timeout) throws Exception {
		HttpClient client = new HttpClient(timeout, timeout);
		try {

			// OneThreadHttpClient client = new
			// OneThreadHttpClient(timeout,timeout);
			client.setBody(parms);
			client.post(url);
			return client.getString(ReturnDefaultCharset);

		} catch (Exception e) {
			log.error(":" + url + ":" + NetUtils.urlMapToQueryStr(parms), e);
			throw e;
		} finally {
			client.shutdown();
		}

	}

	/**
	 * 
	 * @param url
	 *            请求的url地址
	 * @param parms
	 *            <p>
	 *            <blockquote>
	 * 
	 *            <pre>
	 * 模拟的窗体的参数,如：
	 * map.put("name",new String[]{"11","22"});
	 * map.put("code","456");
	 * map.put("class",new String("67"));
	 * 设置的值可以为数组或单个值
	 * </pre>
	 * 
	 *            </blockquote>
	 *            </p>
	 * @param requestCharset
	 *            请求参数编码
	 * @param defaultCharset
	 *            如果返回的响应里没有找到字符集信息，就根据此值进行编码
	 * @param isLikeExplorerPost
	 *            是否为模仿浏览器的提交方式。如果为模仿浏览器的提交方式，服务器端必须转码，并且支持重定向。
	 * @return
	 */
	public static String post(String url, Map parms, String requestCharset,
			String ReturnDefaultCharset, boolean isLikeExplorerPost)
			throws Exception {

		try {
			MultiThreadHttpClient client = new MultiThreadHttpClient();
			client.setBody(parms);
			client.post(url);
			return client.getString(ReturnDefaultCharset);

		} catch (Exception e) {
			log.error(":" + url + ":" + NetUtils.urlMapToQueryStr(parms), e);
			throw e;
		}

	}

	public static String post(String url, Map parms, String requestCharset,
			String ReturnDefaultCharset, boolean isLikeExplorerPost, int timeout)
			throws Exception {
		HttpClient client = new HttpClient(timeout, timeout);
		try {
			// OneThreadHttpClient client = new
			// OneThreadHttpClient(timeout,timeout);
			client.setBody(parms);
			client.post(url);
			return client.getString(ReturnDefaultCharset);

		} catch (Exception e) {
			log.error(":" + url + ":" + NetUtils.urlMapToQueryStr(parms), e);
			throw e;
		} finally {
			client.shutdown();
		}

	}

	public static String post(String url, byte[] postBody,
			String requestContentType, String ReturnDefaultCharset)
			throws Exception {
		try {
			MultiThreadHttpClient client = new MultiThreadHttpClient();
			client.setRequestContentType(requestContentType);
			client.setBody(postBody);
			client.post(url);
			return client.getString(ReturnDefaultCharset);

		} catch (Exception e) {
			log.error(":" + url + ":ReturnDefaultCharset="
					+ ReturnDefaultCharset, e);
			throw e;
		}

	}

	public static String post(String url, byte[] postBody,
			String requestContentType, String ReturnDefaultCharset, int timeout)
			throws Exception {
		HttpClient client = new HttpClient(timeout, timeout);
		try {
			// OneThreadHttpClient client = new
			// OneThreadHttpClient(timeout,timeout);
			client.setRequestContentType(requestContentType);
			client.setBody(postBody);
			client.post(url);
			return client.getString(ReturnDefaultCharset);

		} catch (Exception e) {
			log.error(":" + url + ":requestContentType=" + requestContentType,
					e);
			throw e;
		} finally {
			client.shutdown();
		}

	}

	public static String get(String url, String defaultCharset)
			throws Exception {
		try {
			MultiThreadHttpClient client = new MultiThreadHttpClient();

			client.get(url);
			return client.getString(defaultCharset);

		} catch (Exception e) {
			log.error(":" + url + ":defaultCharset=" + defaultCharset, e);
			throw e;
		}

	}

	public static String get(String url, String defaultCharset,
			Map<String, String> headers) throws Exception {
		try {
			MultiThreadHttpClient client = new MultiThreadHttpClient();
			client.setRequestHeaders(headers);
			client.get(url);
			return client.getString(defaultCharset);

		} catch (Exception e) {
			log.error(":" + url + ":defaultCharset=" + defaultCharset, e);
			throw e;
		}

	}

	public static byte[] getBytes(String url) throws Exception {
		try {
			MultiThreadHttpClient client = new MultiThreadHttpClient();
			client.get(url);
			return client.getBytes();

		} catch (Exception e) {
			log.error("" + url + "", e);
			throw e;
		}

	}

	public static byte[] getBytes(String url, int timeout) throws Exception {
		HttpClient client = new HttpClient(timeout, timeout);
		try {
			// OneThreadHttpClient client = new
			// OneThreadHttpClient(timeout,timeout);
			client.get(url);
			return client.getBytes();

		} catch (Exception e) {
			log.error("" + url + "", e);
			throw e;
		} finally {
			client.shutdown();
		}

	}

	public static InputStream get(String url) throws Exception {
		try {
			MultiThreadHttpClient client = new MultiThreadHttpClient();
			client.get(url);
			return client.getInputStream();

		} catch (Exception e) {
			throw e;
		}

	}

	public static int getResponseStatusCode(String url) throws Exception {
		try {
			MultiThreadHttpClient client = new MultiThreadHttpClient();
			client.get(url);
			client.consumeContent();
			return client.getReponseStatusCode();

		} catch (Exception e) {
			log.error(":" + url + ":", e);
			throw e;
		}

	}

	public static InputStream get(String url, int timeout) throws Exception {
		HttpClient client = new HttpClient(timeout, timeout);
		try {
			// OneThreadHttpClient client = new
			// OneThreadHttpClient(timeout,timeout);
			client.get(url);
			return client.getInputStream();

		} catch (Exception e) {
			log.error(":" + url + ":", e);
			throw e;
		} finally {
			client.shutdown();
		}

	}

	public static String get(String url, String defaultCharset, int timeout)
			throws Exception {
		HttpClient client = new HttpClient(timeout, timeout);
		try {
			// OneThreadHttpClient client = new
			// OneThreadHttpClient(timeout,timeout);
			client.get(url);
			return client.getString(defaultCharset);

		} catch (Exception e) {
			log.error(":" + url + ":defaultCharset=" + defaultCharset, e);
			throw e;
		} finally {
			client.shutdown();
		}

	}

	public static void main(String[] args) throws Exception {

		HttpClient client = new HttpClient(20, 20);
		try {
			// OneThreadHttpClient client = new
			// OneThreadHttpClient(timeout,timeout);
			try {
				client.get("http://www.cool3c.com/");
				log.info(client.getString("gbk"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				client.setTimeOut(20000, 20000);
				client.get("http://www.cool3c.com/");
				log.info(client.getString("gbk"));
				log.info(client.getRequestContentType());
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} catch (Exception e) {
			// log.error("":defaultCharset="+defaultCharset, e);
			e.printStackTrace();
			// throw e;
		} finally {
			client.shutdown();
		}

	}
}
