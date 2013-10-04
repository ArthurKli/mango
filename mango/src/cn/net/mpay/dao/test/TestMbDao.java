package cn.net.mpay.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.net.mpay.bean.Member;
import cn.net.mpay.dao.LoginDao;
import cn.net.mpay.dao.MbDao;
import cn.net.mpay.mdao.MemberDao;
import cn.net.mpay.util.Constants;

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
	
	@Test
	public void testGetIndexMembers(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");        
		MbDao mbDao = (MbDao)context.getBean("mbDao");
		List<Member> mbList =mbDao.getIndexMembers(1);
		System.out.println(mbList.size());
		
		
		
//		mbDao.editUserInfo(m);
	}
	
	@Test 
	public void searchMembers(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");        
		MbDao mbDao = (MbDao)context.getBean("mbDao");
		List<Member> mbList =mbDao.searchMembers(" and qq=?", new Object[]{"12345"},1);
		System.out.println(mbList.size());
		
	}
	
	@Test 
	public void testQueryMemberById(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");        
		MbDao mbDao = (MbDao)context.getBean("mbDao");
		
		Member mb =mbDao.queryMemberById(2);
		System.out.println(mb.toString());
	}

}
