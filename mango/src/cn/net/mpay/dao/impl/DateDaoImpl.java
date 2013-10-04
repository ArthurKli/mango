package cn.net.mpay.dao.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.expressme.simplejdbc.Db;
import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Dating;
import cn.net.mpay.dao.DateDao;
@Component("dateDao")
public class DateDaoImpl implements DateDao {
	@Resource
	private Db simpleJdbc;
	private final Log log = LogFactory.getLog(getClass());
	public int addDating(Dating da) {
		try {
			simpleJdbc.create(da);
		} catch (Exception e) {
			log.error("[db] error,simpDb.create|||"+e.getMessage());
			return 0;
		}
		return 1;
	}

}
