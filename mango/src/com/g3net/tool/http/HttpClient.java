package com.g3net.tool.http;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.ProxySelector;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.ProxySelectorRoutePlanner;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.g3net.tool.ArrayUtils;
import com.g3net.tool.CollectionUtils;
import com.g3net.tool.NetUtils;

import com.g3net.tool.StringUtils;

public class HttpClient {
	private static Logger log = Logger.getLogger(HttpClient.class);

	private static int connecttion_timeout=5000;
	private static int so_timeout=5000;
	private  DefaultHttpClient httpClient = null;
		

	private HttpEntity requestEntity = null;
	private HttpEntity responseEntity = null;
	private HttpRequestBase method = null;
	private String requestURL = "";
	private int reponseStatusCode = 0;

	private boolean consumeContent = true;
	private HashMap<String, String> requestHeaders = new HashMap<String, String>();
	private static HashMap<String, String> defaultHeaders = new HashMap<String, String>();
	static {
		defaultHeaders.put("User-Agent",
				"Opera/9.80 (Windows NT 5.1; U; zh-cn) "
						+ "Presto/2.2.15 Version/10.10");
		defaultHeaders
				.put(
						"Accept",
						"text/html, application/xml;q=0.9, application/xhtml+xml, image/png, image/jpeg, image/gif, image/x-xbitmap, */*;q=0.1");
		defaultHeaders.put("Accept-Language", "zh-cn,en;q=0.9,zh;q=0.8");
		defaultHeaders.put("Accept-Encoding", "identity, *;q=0");
		defaultHeaders.put("Accept-Charset",
				"iso-8859-1, utf-8, utf-16, *;q=0.1");
		defaultHeaders.put("Content-Type", "text/html; charset=utf-8");

	}
	{
		requestHeaders.putAll(defaultHeaders);
	}
	private HashMap<String, String> responseHeaders = new HashMap<String, String>();

	public HttpClient(int connecttion_timeout, int so_timeout) {
		this.httpClient=HttpClient.getHttpClient(connecttion_timeout, so_timeout);
	}
	public HttpClient() {
		this.httpClient=HttpClient.getHttpClient(connecttion_timeout, so_timeout);
	}
	private  DefaultHttpClient getClient() {
		return httpClient;
	}
	public void setTimeOut(int connecttion_timeout, int so_timeout){
		HttpParams params=httpClient.getParams();
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, so_timeout);// 从socket读数据时发生阻塞的超时时间
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
				connecttion_timeout);// 连接的超时时间
	}
	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

	public int getReponseStatusCode() {
		return reponseStatusCode;
	}

	public static DefaultHttpClient getHttpClient(int connectionTimeOut,
			int soTimeOut) {

		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeOut);// 从socket读数据时发生阻塞的超时时间
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
				connectionTimeOut);// 连接的超时时间

		DefaultHttpClient httpClient = new DefaultHttpClient(params);
		ProxySelectorRoutePlanner routePlanner = new ProxySelectorRoutePlanner(
				httpClient.getConnectionManager().getSchemeRegistry(),
				ProxySelector.getDefault());
		httpClient.setRoutePlanner(routePlanner);
		return httpClient;
	}

	public void setRequestHeaders(Map<String, String> headers) {
		this.requestHeaders.putAll(headers);
	}

	public HashMap<String, String> getRequestHeaders() {
		return this.requestHeaders;
	}

	public void setRequestHeader(String header, String value) {
		this.requestHeaders.put(header, value);
	}

	public HashMap<String, String> getResponseHeaders() {
		return responseHeaders;
	}

	public long getResponseContentLength() {
		String len = this.getResponseHeaders().get("Content-Length");
		return Long.valueOf(len);
	}

	public String getResponseContentType() {
		String type = this.getResponseHeaders().get("Content-Type");
		return type;
	}

	public String getResponseLocation() {
		String s = this.getResponseHeaders().get("Location");
		return s;
	}

	public String getResponseServer() {
		String s = this.getResponseHeaders().get("Server");
		return s;
	}

	public String getRequestHeaderValue(String header) {
		return this.getRequestHeaders().get(header);
	}

	public void reset() {
		this.requestHeaders.clear();
		this.requestHeaders.putAll(defaultHeaders);
		this.reponseStatusCode = 0;
		this.requestURL = "";
		this.requestEntity = null;
		this.responseEntity = null;
		this.responseHeaders.clear();
		this.method = null;
		this.consumeContent = true;
	}

	public void setRequestContentType(String value) {
		this.requestHeaders.put("Content-Type", value);
	}

	public String getRequestContentType() {
		return this.requestHeaders.get("Content-Type");
	}

	public void setRequestContentCharset(String value) {
		String contentType = StringUtils.getNotNullString(this.requestHeaders
				.get("Content-Type"));

		int pos = contentType.indexOf(";");
		if (pos != -1) {
			contentType = contentType.substring(0, pos).trim();
		}
		this.requestHeaders.put("Content-Type", contentType + ";charset="
				+ value);
	}

	// Content-Encoding
	public String getRequestContentEncoding() {
		return this.requestHeaders.get("Content-Encoding");
	}

	public String getResponseContentEncoding() {
		return this.responseHeaders.get("Content-Encoding");
	}

	public String getRequestContentCharset() {
		String contentType = StringUtils.getNotNullString(this.requestHeaders
				.get("Content-Type"));
		// log.debug("contentType="+contentType);
		String charset = NetUtils.fetchCharset(contentType);
		// log.debug("charset="+charset);
		return charset;
	}

	public void setRequestUserAgent(String value) {
		this.requestHeaders.put("User-Agent", value);
	}

	public String getRequestUserAgent() {
		return this.requestHeaders.get("User-Agent");
	}

	public void setRequestAccept(String value) {
		this.requestHeaders.put("Accept", value);
	}

	public String getRequestAccept() {
		return this.requestHeaders.get("Accept");
	}

	public void setRequestAcceptLanguage(String value) {
		this.requestHeaders.put("Accept-Language", value);
	}

	public String getRequestAcceptLanguage() {
		return this.getRequestHeaders().get("Accept-Language");
	}

	public void setRequestAcceptEncoding(String value) {
		this.requestHeaders.put("Accept-Encoding", value);
	}

	public String getRequestAcceptEncoding() {
		return this.getRequestHeaders().get("Accept-Encoding");
	}

	public void setRequestAcceptCharset(String value) {
		this.requestHeaders.put("Accept-Charset", value);
	}

	public String getRequestAcceptCharset() {
		return this.getRequestHeaders().get("Accept-Charset");
	}

	public void setRequestHost(String value) {
		this.requestHeaders.put("Host", value);
	}

	public String getRequestHost() {
		return this.getRequestHeaders().get("Host");
	}

	public void setRequestReferer(String value) {
		this.requestHeaders.put("Referer", value);
	}

	public String getRequestReferer() {
		return this.getRequestHeaders().get("Referer");
	}

	/**
	 * 提交字节数组
	 * 
	 * @param body
	 */
	public void setBody(byte[] body) {
		if (ArrayUtils.isEmpty(body)) {
			body = new byte[0];
		}
		ByteArrayEntity requestEntity = new ByteArrayEntity(body);
		requestEntity.setChunked(false);
		this.requestEntity = requestEntity;
		this.setRequestContentType(this.requestEntity.getContentType()
				.getValue());
	}

	/**
	 * 提交窗体的参数
	 * 
	 * @param form
	 */
	public void setBody(Map formParamMap)  throws Exception{
		List<NameValuePair> formparams = getListParams(formParamMap);
		try {
			// log.debug("===="+ this.getRequestContentCharset());
			UrlEncodedFormEntity entityParms = new UrlEncodedFormEntity(
					formparams, this.getRequestContentCharset());
			entityParms.setChunked(false);

			this.requestEntity = entityParms;
			// log.debug("==contentType="+this.requestEntity.getContentType().getValue());
			this.setRequestContentType(this.requestEntity.getContentType()
					.getValue());

		} catch (Exception ex) {
			throw ex;
		}

	}

	/**
	 * 提交流
	 * 
	 * @param stream
	 */
	public void setBody(InputStream stream) {
		InputStreamEntity entity = new InputStreamEntity(stream, -1);// 消费所有内容
		entity.setChunked(false);
		this.requestEntity = entity;
		this.setRequestContentType(this.requestEntity.getContentType()
				.getValue());
	}

	/**
	 * 提交字符串
	 * 
	 * @param content
	 */
	public void setBody(StringBuilder content)  throws Exception{
		try {
			StringEntity entity = new StringEntity(content.toString(), this
					.getRequestContentCharset());
			this.requestEntity = entity;
			this.setRequestContentType(this.requestEntity.getContentType()
					.getValue());
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void setBody(String filePath) {
		FileEntity entity = new FileEntity(new File(filePath), this
				.getRequestContentType());
		this.requestEntity = entity;
		this.setRequestContentType(this.requestEntity.getContentType()
				.getValue());
	}

	private HttpEntity getResponseEntity() {
		return this.responseEntity;
	}

	private HttpEntity getRequestEntity() {
		return this.requestEntity;
	}

	private void checkIsConsumeContent() throws Exception {
		if (!consumeContent) {
			if (this.requestEntity != null) {
				this.requestEntity.consumeContent();
			}
			this.consumeContent = true;
		}
	}

	private HttpResponse redirect(HttpResponse response) throws Exception {

		// 特殊处理post产生都302响应，HTTP协议规定post产生都302响应需要用户确认才能重定向，
		// 但一般都浏览器实现更HTTP协议规定都不一样，都是自动重定向，所以，这里模拟这种情况。

		int status = response.getStatusLine().getStatusCode();
		// String origUrl=this.getRequestURL();
		// this.setReferer(this.getRequestURL();
		if (status >= 300 && status <= 307) {// 重定向都范围
			Header location = response.getFirstHeader("Location");
			String redirectUrl = location.getValue();
			HttpGet httpget = null;
			try {

				response.getEntity().consumeContent();

				log.debug("redirectUrl=" + redirectUrl);

				if (StringUtils.hasText(this.getRequestReferer())) {
					redirectUrl = NetUtils.getAbsoluteURLFromGivenURL(this
							.getRequestReferer(), redirectUrl);
				} else {
					// redirectUrl =
					// NetUtils.getAbsoluteURLFromGivenURL("http://"+, url2);
				}
				
				httpget = new HttpGet(redirectUrl);
				httpget.setHeaders(getHeadersFromMap(this.getRequestHeaders()));
				if (StringUtils.hasText(this.getRequestReferer())) {
					httpget.setHeader("Referer", this.getRequestReferer());
				}
				response = getClient().execute(httpget);
				
				this.setRequestURL(redirectUrl);
				return redirect(response);

			} catch (Exception ex) {
				if (httpget != null) {
					httpget.abort();

				}
				log.error("", ex);
				throw ex;
			}
		}
		return response;
	}

	private HttpResponse executePost(String url) throws Exception {

		this.checkIsConsumeContent();
		this.setRequestURL(url);
		HttpPost httppost = new HttpPost(url);
		this.method = httppost;
		httppost.setHeaders(getHeadersFromMap(this.getRequestHeaders()));
		httppost.setEntity(this.getRequestEntity());
		DefaultHttpClient client = getClient();
		HttpResponse response = client.execute(httppost);

		response = this.redirect(response);
		this.responseHeaders = getMapFromHeaderArray(response.getAllHeaders());
		this.reponseStatusCode = response.getStatusLine().getStatusCode();
		consumeContent = false;
		return response;
	}

	private HashMap<String, String> getMapFromHeaderArray(
			Header[] responseHeaders) {
		HashMap<String, String> responseHeadersMap = new HashMap<String, String>();
		if (!ArrayUtils.isEmpty(responseHeaders)) {
			for (int i = 0; i < responseHeaders.length; i++) {
				Header head = responseHeaders[i];
				responseHeadersMap.put(head.getName(), head.getValue());
			}
		}
		return responseHeadersMap;
	}

	private HttpResponse executeGet(String url) throws Exception {

		this.checkIsConsumeContent();
		this.setRequestURL(url);
		//log.debug("headers="+CollectionUtils.toString(this.getRequestHeaders()));
		HttpGet httpget = new HttpGet(url);
		this.method = httpget;
		httpget.setHeaders(getHeadersFromMap(this.getRequestHeaders()));
		//put("Content-Type", "text/html; charset=utf-8")
		
		DefaultHttpClient client = getClient();
		HttpResponse response = client.execute(httpget);
		response = this.redirect(response);
		this.responseHeaders = getMapFromHeaderArray(response.getAllHeaders());
		this.reponseStatusCode = response.getStatusLine().getStatusCode();
		consumeContent = false;
		return response;
	}

	public void post(String url) throws Exception {
		try {
			HttpResponse response = this.executePost(url);
			this.responseEntity = response.getEntity();
		} catch (Exception e) {
			if (this.method != null) {
				this.method.abort();
				this.consumeContent = true;

			}
			throw e;
		}

	}

	public void get(String url) throws Exception {
		try {
			HttpResponse response = this.executeGet(url);
			this.responseEntity = response.getEntity();
		} catch (Exception e) {
			if (this.method != null) {
				this.method.abort();
				this.consumeContent = true;
			}
			throw e;
		}
	}

	private Header[] getHeadersFromMap(Map map) {
		Header[] requestHeaders = new Header[0];

		if (!CollectionUtils.isEmpty(map)) {
			Set<String> keys = map.keySet();
			requestHeaders = new BasicHeader[map.size()];

			int i = 0;
			for (String key : keys) {
				Header header = new BasicHeader(key, (String) map.get(key));
				requestHeaders[i++] = header;

			}
			return requestHeaders;

		}
		return requestHeaders;
	}

	public void consumeContent() throws Exception {
		if (consumeContent) {
			throw new Exception("内容已经消费！不能再此调用此方法，必须重新执行get或post!");
		}

		this.responseEntity.consumeContent();

	}

	public String getString(String defalutCharset) throws Exception {
		if (consumeContent) {
			throw new Exception("内容已经消费！不能再此调用此方法，必须重新执行get或post!");
		}
		try {
			if (this.responseEntity == null)
				return null;
			String s = EntityUtils
					.toString(this.responseEntity, defalutCharset);

			return s;
		} catch (Exception e) {
			if (this.method != null) {
				this.method.abort();
			}
			throw e;
		} finally {
			this.consumeContent = true;

		}
	}

	public byte[] getBytes() throws Exception {
		if (consumeContent) {
			throw new Exception("内容已经消费！不能再此调用此方法，必须重新执行get或post!");
		}
		try {
			if (this.responseEntity == null)
				return null;
			byte[] temps = EntityUtils.toByteArray(this.responseEntity);

			return temps;
		} catch (Exception e) {
			if (this.method != null) {
				this.method.abort();
			}
			throw e;
		} finally {
			this.consumeContent = true;

		}

	}

	public InputStream getInputStream() throws Exception {
		if (consumeContent) {
			throw new Exception("内容已经消费！不能再此调用此方法，必须重新执行get或post!");
		}
		try {
			if (this.responseEntity == null)
				return null;
			byte[] temps = EntityUtils.toByteArray(this.responseEntity);
			return new ByteArrayInputStream(temps);
		} catch (Exception e) {
			if (this.method != null) {
				this.method.abort();
			}
			throw e;
		} finally {
			this.consumeContent = true;
			requestHeaders.clear();
			requestHeaders.putAll(defaultHeaders);
		}
	}

	public void shutdown() {
		if (httpClient != null) {
			httpClient.getConnectionManager().shutdown();
		}
	}

	private static List<NameValuePair> getListParams(Map map) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();

		if (CollectionUtils.isEmpty(map))
			return formparams;
		Set keys = map.keySet();

		for (Object key : keys) {
			Object value = map.get(key);
			if (value.getClass().isArray()) {
				String[] values = (String[]) value;
				for (int i = 0; i < values.length; i++) {
					formparams.add(new BasicNameValuePair((String) key,
							values[i]));
				}
			} else {
				formparams.add(new BasicNameValuePair((String) key, value
						.toString()));
			}

		}
		return formparams;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map map=new HashMap();
		map.put("11", "222");
		Map map2=new HashMap(map);
		map2.put("22", "333");
		log.info(CollectionUtils.toString(map));
		

	}

}
