package cn.net.mpay.business;

import cn.net.mpay.bean.Member;
import cn.net.mpay.bean.User;

public interface LoginService {
	
	public int findAll();
	
	public int regist(Member mb);
	
	public boolean checkAccount(String account);

}
