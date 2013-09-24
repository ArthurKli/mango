package cn.net.mpay.business.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Member;
import cn.net.mpay.bean.User;
import cn.net.mpay.business.LoginService;
import cn.net.mpay.dao.LoginDao;
import cn.net.mpay.mdao.MybatisDao;
@Component("loginService")
public class LoginServiceImpl implements LoginService {
	@Resource(name="loginDao")
	private LoginDao loginDao;
	@Resource
	private MybatisDao myDao;

	public int findAll() {
		return myDao.countALL();
	}

	public int regist(Member mb) {
		System.out.println("進入service");
		return loginDao.regist(mb);
		
	}

	public boolean checkAccount(String account) {
		// TODO Auto-generated method stub
		return loginDao.checkAccount(account);
	}

}
