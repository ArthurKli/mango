package cn.net.mpay.dao.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.expressme.simplejdbc.Db;
import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Marry;
import cn.net.mpay.dao.PublishDao;
@Component
public class publishDaoImpl implements PublishDao {
	@Resource
	private Db simpDb;
	private final Log log = LogFactory.getLog(getClass());
	public int publishMarryInfo(Marry marry) {
		// TODO Auto-generated method stub
		try {
			simpDb.create(marry);
		} catch (Exception e) {
			log.error("[db] error,simpDb.create|||"+e.getMessage());
			return 0;
		}
		return 1;
	}

}
