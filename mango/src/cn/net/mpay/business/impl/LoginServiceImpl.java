package cn.net.mpay.business.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.g3net.tool.MD5;

import cn.net.mpay.bean.Member;
import cn.net.mpay.bean.Organization;
import cn.net.mpay.bean.User;
import cn.net.mpay.business.LoginService;
import cn.net.mpay.dao.LoginDao;
import cn.net.mpay.mdao.MemberDao;
import cn.net.mpay.mdao.MybatisDao;
@Component("loginService")
public class LoginServiceImpl implements LoginService {
	@Resource(name="loginDao")
	private LoginDao loginDao;
	@Resource
	private MybatisDao myDao;
	@Resource
	private MemberDao memDao;

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

	public Member loginNormal(String account, String password) {
		// TODO Auto-generated method stub
		Member mb =new Member();
		mb.setAccount(account);
		mb.setPassword(MD5.MD5generator(password,"utf-8"));
		return memDao.loginNormal(mb);
	}

	public boolean checkOrg(String loginName) {
		// TODO Auto-generated method stub
		return loginDao.checkOrg(loginName);
	}

	public int registOrg(Organization org) {
		// TODO Auto-generated method stub
		return loginDao.registOrg(org);
	}

	public Organization loginOrg(String loginName, String password) {
		Organization org =new Organization();
		org.setLogin_name(loginName);
		org.setPassword(MD5.MD5generator(password,"utf-8"));
		return memDao.loginOrg(org);
	}

}
