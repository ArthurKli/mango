package cn.net.mpay.dao.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.expressme.simplejdbc.Db;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Member;
import cn.net.mpay.dao.MbDao;
@Component("mbDao")
public class MbDaoImpl implements MbDao {
	
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Resource
	private Db simpleJdbc;
	private final Log log = LogFactory.getLog(getClass());

	public int editUserInfo(String sql,Object[] args) {
		StringBuffer sb=new StringBuffer("UPDATE member set ");
		sb.append(sql);
		
		log.info("[args]:"+args.length+" [SQL]:"+sb.toString());
		
		
		try {
			return jdbcTemplate.update(sb.toString(),args);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		
	}
	public static void main(String[] args) {
		StringBuffer sb=new StringBuffer("UPDATE member set");
		System.out.println(sb.deleteCharAt(sb.length()-1));
	}

}
