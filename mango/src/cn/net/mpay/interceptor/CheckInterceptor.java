package cn.net.mpay.interceptor;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckInterceptor extends AbstractInterceptor {

	private static Logger log = Logger.getLogger(CheckInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 请求进
		HttpServletRequest request= (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST); 
		Enumeration enu=request.getParameterNames();  
		StringBuffer param=new StringBuffer("");
		while(enu.hasMoreElements()){   
		String paraName=(String)enu.nextElement();  
		param.append(paraName+"="+request.getParameter(paraName)+"&");
		}
		if (enu.hasMoreElements()) {
			param.deleteCharAt(param.length()-1);
		}
//		param.append("]");
		
		Thread t = Thread.currentThread();
		String name = t.getName();
		Object action = invocation.getAction();
//		if (action instanceof LoginAction||action instanceof UploadPicAction||action instanceof GoodsAction) {
////			log.info("对登录action不拦截");
//			return invocation.invoke();
//		}
		log.info("------ThreadName:"+name+"------URL:"+request.getRequestURL()+"?"+param.toString());

		Map session = invocation.getInvocationContext().getSession();
//		Map request = (Map) invocation.getInvocationContext().get("request");
		Object user = session.get("user");
		if (user == null) {
//			log.info("用户未登录！！");
			session
					.put("mes",
							"<script type=\"text/javascript\">alert('请先登录!!');</script>");
			return "login";
		}
		String result = invocation.invoke();
		// 请求出
		return result;
	}

}
