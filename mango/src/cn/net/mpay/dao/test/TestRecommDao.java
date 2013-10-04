package cn.net.mpay.dao.test;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.net.mpay.bean.Recommend;
import cn.net.mpay.dao.RecommDao;

import com.g3net.tool.CTime;

public class TestRecommDao {
	
	@Test
	public void addRecomm(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");        
		RecommDao reDao = (RecommDao)context.getBean("recommDao");
		Recommend red =new Recommend();
		red.setMarry_id(1);
		red.setSell_mb_id(2);
		red.setSell_org_id(2);
		red.setTime(CTime.formatWholeDate());
		
		Assert.assertEquals("no", 1, reDao.addRecomm(red));
	}

}
