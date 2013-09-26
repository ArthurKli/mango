package cn.net.mpay.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Member;
import cn.net.mpay.bean.User;
import cn.net.mpay.business.LoginService;
import cn.net.mpay.util.ActionValidate;

import com.g3net.tool.CTime;
import com.g3net.tool.MD5;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Component("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport{	 
	@Resource(name = "loginService")
	private LoginService loginService;
	private String username;
	private String account;
	private String password;
	private String password2;
	private String email;
	private String mobile;
	
	private String msg;
	
	
	private final Log log = LogFactory.getLog(getClass());
	
	private User user;
	//0正常 1某个选项为空  2pwd！=pwd2
	private Integer code;
	
	
	public  String execute() throws Exception{
		code =ActionValidate.dataValidate(user);
//		log.info("状态码:"+msg);
		if (code!=0) {
			return "error";
		}
		password=user.getPassword();
		user.setPassword(MD5.MD5generator(password,"utf-8"));
//		loginService.regist(user);
		return SUCCESS;
	}
	/**
	 * 注册
	 * http://localhost:8080/mango/regist.action?account=hong&password=123&password2=123&email=123@qq.com&mobile=110
	 * @return
	 * @throws Exception
	 */
	public String regist()throws Exception{
		int code=ActionValidate.dataValidate(account, password, password2);
		if (code!=0) {
			return "error";
		}
		if(loginService.checkAccount(account)){
			msg="用户名已存在！";
			return "error";
		}
		Member mb =new Member();
		mb.setPassword(MD5.MD5generator(password,"utf-8"));
		mb.setAccount(account);
		mb.setEmail(email);
		mb.setMobile(mobile);
		mb.setRegist_date(CTime.formatWholeDate());
//		int i =loginService.regist(mb);
		int i =loginService.regist(mb);
		log.info("注册信息："+i);
	
		if(i!=0){
			msg="注册成功！  account="+ mb.getAccount();
			
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 登陆
	 * http://localhost:8080/mango/loginNormal.action?account=hong&password=123
	 * @return
	 * @throws Exception
	 */
	public String loginNormal()throws Exception{
		
		if(account==null ||password==null){
			msg="不允许为空！";
			return "error";
		}
		Member mb =loginService.loginNormal(account, password);
		if(mb==null){
			msg="用户名或密码错误。";
			return "error";
		}
		Map session = ActionContext.getContext().getSession();
		session.put("user", mb);
		
		log.info("登陆成功：sessionID=="+mb.getId());
		msg="登陆成功，sessionID="+mb.getId();
		
		
		
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


	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	

}
