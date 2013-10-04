package cn.net.mpay.business.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Dating;
import cn.net.mpay.business.DateService;
import cn.net.mpay.dao.DateDao;
@Component
public class DateServiceImpl implements DateService {
	@Resource 
	DateDao dateDao;

	public int addDating(Dating da) {
		// TODO Auto-generated method stub
		return dateDao.addDating(da);
	}

}
