package cn.net.mpay.mdao.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.net.mpay.bean.Member;
import cn.net.mpay.mdao.MemberDao;


public class TestMemberDao {
	@Test
	public void testCreateMember(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");        
		MemberDao mbDao = (MemberDao)context.getBean("memberDao");
		
		Member mb =new Member();
		mb.setAccount("r3656878");
		mb.setCareer("std.");
		mb.setOrg_id(2);
		mb.setEmail("kai@kli.com");
		mb.setMobile("16374676788");
		mb.setPassword("1xcv557");
		
		int i = mbDao.createMember(mb);
		System.out.println("成功否："+i);
		
		
	}

}
