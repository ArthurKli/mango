package cn.net.mpay.business;

import cn.net.mpay.bean.Member;
import cn.net.mpay.bean.Organization;
import cn.net.mpay.bean.User;

public interface LoginService {
	
	public int findAll();
	
	public int regist(Member mb);
	
	public int registOrg(Organization org);
	
	public boolean checkAccount(String account);
	
	public boolean checkOrg(String login_name);
	
	public Member loginNormal(String account,String password);
	
	public Organization loginOrg(String login_name,String password);

}
