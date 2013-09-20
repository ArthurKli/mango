package cn.net.mpay.dao;

import cn.net.mpay.bean.User;

public interface LoginDao {
	
	public int findAll();
	
	public void regist(User u);

}
