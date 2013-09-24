package cn.net.mpay.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Member;
import cn.net.mpay.bean.User;
import cn.net.mpay.dao.LoginDao;
@Component("loginDao")
public class LoginDaoImpl implements LoginDao {
	
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

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
	
		

//		jdbcTemplate.update(sql,new Object[]{u.getUsername(),u.getPassword(),u.getSex(),u.getPhone(),u.getBirthday()});
		
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

}
