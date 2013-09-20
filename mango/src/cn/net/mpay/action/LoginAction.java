package cn.net.mpay.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.net.mpay.bean.User;
import cn.net.mpay.business.LoginService;
import cn.net.mpay.util.ActionValidate;

import com.g3net.tool.MD5;
import com.opensymphony.xwork2.ActionSupport;
@Component("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport{	 
	@Resource(name = "loginService")
	private LoginService loginService;
	private String username;
	private String password;
	private String password2;
	
	private final Log log = LogFactory.getLog(getClass());
	
	private User user;
	//0正常 1某个选项为空  2pwd！=pwd2
	private Integer msg;
	
	
	public  String execute() throws Exception{
		msg =ActionValidate.dataValidate(user);
//		log.info("状态码:"+msg);
		if (msg!=0) {
			return "error";
		}
		password=user.getPassword();
		user.setPassword(MD5.MD5generator(password,"utf-8"));
		loginService.regist(user);
		return SUCCESS;
	}
	public String countAll()throws Exception{
		int a=loginService.findAll();
		log.info("总人数："+a);
		
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Integer getMsg() {
		return msg;
	}

	public void setMsg(Integer msg) {
		this.msg = msg;
	}
	

}
