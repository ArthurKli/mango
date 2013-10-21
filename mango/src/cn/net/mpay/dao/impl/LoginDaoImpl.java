package cn.net.mpay.dao.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.expressme.simplejdbc.Db;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Member;
import cn.net.mpay.bean.Organization;
import cn.net.mpay.bean.User;
import cn.net.mpay.dao.LoginDao;
@Component("loginDao")
public class LoginDaoImpl implements LoginDao {
	
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Resource
	private Db simpleJdbc;
	
	private final Log log = LogFactory.getLog(getClass());

	public int findAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int regist(Member mb) {
		String sql="insert into member(account,password,email,mobile,regist_date) values(?,?,?,?,?)";
//		String sql="insert into user(username) values('师傅')";
		Object[] args = new Object[] {
				mb.getAccount(),
				mb.getPassword(),
				mb.getEmail(),
				mb.getMobile(),
				mb.getRegist_date()
				 };
		try {
			return jdbcTemplate.update(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public int registSimple(Member mb) {
		try {
			simpleJdbc.create(mb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean checkAccount(String account) {
		boolean hasExist =false;
		try {
			int count = jdbcTemplate.queryForInt("SELECT COUNT(id) FROM member WHERE account = ?",new Object[]{account});
		    if(count>0){
		    	hasExist = true;
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasExist;
	}

	public boolean checkOrg(String loginName) {
		boolean hasExist =false;
		try {
			int count = jdbcTemplate.queryForInt("SELECT COUNT(org_id) FROM organization WHERE login_name = ?",new Object[]{loginName});
		    if(count>0){
		    	hasExist = true;
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hasExist;
	}

	public int registOrg(Organization org) {
		String sql="insert into organization(login_name,password,mailbox,mobile,regist_time) values(?,?,?,?,?)";
		Object[] args = new Object[] {
				org.getLogin_name(),
				org.getPassword(),
				org.getMailbox(),
				org.getMobile(),
				org.getRegist_time()
				 };
		try {
			return jdbcTemplate.update(sql,args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}

}
