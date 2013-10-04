package cn.net.mpay.dao.test;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.net.mpay.bean.Dating;
import cn.net.mpay.bean.Recommend;
import cn.net.mpay.dao.DateDao;

import com.g3net.tool.CTime;

public class TestDateDao {
	
	@Test
	public void addDate(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");        
		DateDao daDao = (DateDao)context.getBean("dateDao");
		Dating dating =new Dating();
		dating.setRe_id(2);
		dating.setStatus(1);
		dating.setTime(CTime.formatWholeDate());
		
		
		Assert.assertEquals("no", 1, daDao.addDating(dating));
	}

}
