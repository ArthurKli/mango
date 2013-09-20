package cn.net.mpay.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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

	public void regist(User u) {
		System.out.println("進入dao"+u.getUsername());
//		String sql="insert into user(username,password,sex,phone,birthday) values(?,?,?,?,?)";
		String sql="insert into user(username) values('师傅')";
		jdbcTemplate.execute(sql);

//		jdbcTemplate.update(sql,new Object[]{u.getUsername(),u.getPassword(),u.getSex(),u.getPhone(),u.getBirthday()});
		
	}

}
