package cn.net.mpay.dao.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.expressme.simplejdbc.Db;
import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Recommend;
import cn.net.mpay.dao.RecommDao;
@Component("recommDao")
public class RecommDaoImpl implements RecommDao {
	@Resource
	private Db simpJdbc;
	private final Log log = LogFactory.getLog(getClass());
	public int addRecomm(Recommend red) {
		// TODO Auto-generated method stub
		try {
			simpJdbc.create(red);
			return 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
