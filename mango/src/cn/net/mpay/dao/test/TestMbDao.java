package cn.net.mpay.dao.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.net.mpay.bean.Member;
import cn.net.mpay.dao.LoginDao;
import cn.net.mpay.dao.MbDao;
import cn.net.mpay.mdao.MemberDao;

public class TestMbDao {
	
	@Test
	public void testRegistSimple(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");        
		MbDao mbDao = (MbDao)context.getBean("mbDao");
		Member m =new Member();
		m.setAccount("abcx");
		m.setPassword("3466ggg");
		m.setOrg_id(9);
		m.setId(2);
//		mbDao.editUserInfo(m);
		
		
	}

}
