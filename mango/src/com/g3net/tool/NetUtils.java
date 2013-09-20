package com.g3net.tool;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

public class NetUtils {
	private static Logger log = Logger.getLogger(NetUtils.class);

	
	public static String getQueryStrFromURL(String url) throws Exception{
		
		int index=url.indexOf("?");
		if(index==-1) return "";
		return url.substring(index+1); 
		
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

	public static String getAbsoluteURLFromGivenURL(String url, String url2) {
		try {
			URI u = new URI(url);
			URI u2 = u.resolve(url2);
			return u2.toString();
		} catch (Exception ex) {
			log.error("", ex);
		}
		return "";
	}

	/**
	 * 用法：
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * map.put("name","123"); 
	 * map.put("code",new String[]{"1234","4567"};
	 * map.put("uu",new String("567");
	 * </pre>
	 * 
	 * </blockquote>
	 * </p>
	 * 
	 * @param url
	 * @param map
	 *            可以为字符串数组
	 * @param requestCharset
	 * @return
	 * @throws Exception
	 */
	public static String getMethodUrl(String url, Map map, String requestCharset)
			throws Exception {
		// String result=null;
		try {
			String extParms = "";
			if (!CollectionUtils.isEmpty(map)) {
				List<NameValuePair> qparams = getListParams(map);

				extParms = URLEncodedUtils.format(qparams, requestCharset);
			}
			URI newUrl = new URI(url);

			String scheme = newUrl.getScheme();
			url = "";
			if (StringUtils.hasText(scheme)) {
				url = newUrl.getScheme() + "://";
			} else {
				url = "http://";
			}
			String auth = newUrl.getAuthority();
			if (StringUtils.hasText(auth)) {
				url = url + newUrl.getAuthority();
			}
			url = url + newUrl.getPath();

			String queryStr = newUrl.getRawQuery();

			if (StringUtils.hasText(queryStr)) {
				if (StringUtils.hasText(extParms)) {
					queryStr = queryStr + "&" + extParms;
				}

			} else {
				if (StringUtils.hasText(extParms)) {
					queryStr = extParms;
				}
			}
			if (StringUtils.hasText(queryStr)) {
				url = url + "?" + queryStr;
			}
			// log.debug("+++++++" + url);
		} catch (Exception ex) {

			log.error("", ex);
			throw ex;
		}
		return url;
	}

	public static String urlMapToQueryStr(Map map, String encoding) {
		String extParms = "";
		if (!CollectionUtils.isEmpty(map)) {
			List<NameValuePair> qparams = getListParams(map);

			extParms = URLEncodedUtils.format(qparams, encoding);
		}
		return extParms;
	}

	/**
	 * 用法：
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * map.put("name","123"); 
	 * map.put("code",new String[]{"1234","4567"};
	 * map.put("uu",new String("567");
	 * 
	 * String url=urlMapToQueryStr(map); 
	 * log.info(url); 
	 * ----------
	 * name=123&code=1234&code=4567&uu=567
	 * </pre>
	 * 
	 * </blockquote>
	 * </p>
	 * 
	 * @param map
	 * @return
	 */
	public static String urlMapToQueryStr(Map map) {
		return StringUtils.urlMapToQueryStr(map, null);
	}

	/**
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * query字符串转化成map 
	 * 如： Map map=urlQueryStrToMap("name=su&code=123&class=66&class=77");
	 * 形式的字符串转化成map里的元素为： name="su" class={"66","77"}
	 * </pre>
	 * 
	 * </blockquote>
	 * </p>
	 * 
	 * 
	 * @param s
	 * @return
	 */
	public static Map<String, String[]> urlQueryStrToMap(String s) {
		return StringUtils.urlQueryStrToMap(s, null);
	}

	public static Map<String, String[]> urlQueryStrToMap(String s,
			String urlEncoding) {
		return StringUtils.urlQueryStrToMap(s, urlEncoding);
	}

	/**
	 * 从contentType提取charset
	 * 
	 * @param contentType
	 * @return
	 */
	public static String fetchCharset(String contentType) {

		BasicHeader bh = new BasicHeader("Content-Type", contentType);
		return fetchCharset(bh);

	}

	private static String fetchCharset(Header contentType) {

		
		HeaderElement[] elments = contentType.getElements();
		String charset = "";
		boolean flag = false;
		for (int i = 0; i < elments.length; i++) {
			HeaderElement he = elments[i];
			NameValuePair[] nv = he.getParameters();
			for (int n = 0; n < nv.length; n++) {
				NameValuePair np = nv[n];
				String parmName = np.getName();
				if (parmName.equalsIgnoreCase("charset")) {
					charset = np.getValue();
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
			// he.getParameterByName(charset);
		}
		return charset;
	}

	public static String fetchContentType(Map headers) {

		if (headers == null || headers.size() == 0)
			return "";
		Set<String> keys = headers.keySet();

		String contentType = "";

		for (String key : keys) {
			if (key.trim().equalsIgnoreCase("content-type")) {
				contentType = (String) headers.get(key);
			}
		}
		return contentType;
	}

	public static String getAuthorityFromURL(String url) {
		try {
			if (StringUtils.hasText(url)) {

				URL url2 = new URL(url);
				// log.info(url2.getAuthority());
				// log.info(url2.getFile());
				// log.info(url2.getPath());
				// log.info(url2.getProtocol());
				String s = url2.getProtocol() + "://" + url2.getAuthority();
				return s;
			}
		} catch (Exception ex) {
			log.error("", ex);
		}
		return "";
	}

	public static String getParentPathFromURL(String url) {
		return FileUtils.getFileParentPath(url);
	}

	public static void main(String[] args) throws Exception {
		// log.info(getAuthorityFromURL("http://3g.sina.com.cn:8080/prog/wapsite/sss.jsp"));
		// log.info(getParentPathFromURL("http://3g.sina.com.cn:8080/prog/wapsite/sss/"));
		Map map = new HashMap();
		map.put("usr", new String[] { "中国", "中国" });
		log.info(NetUtils.getMethodUrl("192.168.112.55/ss/s.jsp",
				map, "utf-8"));

		log.info(NetUtils.urlMapToQueryStr(map, "gbk"));
		log.info(NetUtils.getAbsoluteURLFromGivenURL(
				"http://192.168.23.44/test/1.jsp", "/2.jsp"));
		log.info("+++"+NetUtils.getQueryStrFromURL("/image?"));
	}

}
