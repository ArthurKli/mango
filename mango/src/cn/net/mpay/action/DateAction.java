package cn.net.mpay.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
@Component
@Scope("prototype")
public class DateAction extends ActionSupport{	 
	
	
	
	private String msg;
	
	private final Log log = LogFactory.getLog(getClass());
	


	
	

}
