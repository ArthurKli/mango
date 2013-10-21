package cn.net.mpay.dao;

import cn.net.mpay.bean.Member;
import cn.net.mpay.bean.Organization;
import cn.net.mpay.bean.User;

public interface LoginDao {
	
	public int findAll();
	
	public int regist(Member mb);
	
	public int registOrg(Organization org);
	
	public int registSimple(Member mb);
	
	public boolean checkAccount(String account);
	
	public boolean checkOrg(String login_name);

}
