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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Component("memberAction")
@Scope("prototype")
public class MemberAction extends ActionSupport{	 
	@Resource(name = "loginService")
	private LoginService loginService;
	private Integer org_id;
	private String account;//登陆账号
	private String password;
	private String email;
	private String true_name;//真实姓名
	private String nick_name;//昵称
	
	private final Log log = LogFactory.getLog(getClass());
	
	
//	public  String regist() throws Exception{
//		Member mb =new Member();
//		mb.setAccount(account);
//		return SUCCESS;
//	}
	/**
	 * demo : http://localhost:8080/mango/login2.action?org_id=1
	 */
	public  String login() throws Exception{
		Map session = ActionContext.getContext().getSession();
		session.put("uid", org_id);
		
		log.info("登陆成功：session=="+org_id);
		
		return "chat";
	}


	public Integer getOrg_id() {
		return org_id;
	}


	public void setOrg_id(Integer orgId) {
		org_id = orgId;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTrue_name() {
		return true_name;
	}


	public void setTrue_name(String trueName) {
		true_name = trueName;
	}


	public String getNick_name() {
		return nick_name;
	}


	public void setNick_name(String nickName) {
		nick_name = nickName;
	}


	
	

}
