package cn.net.mpay.business.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Marry;
import cn.net.mpay.business.PublishService;
import cn.net.mpay.dao.PublishDao;
@Component
public class PublishMarryImpl implements PublishService {
	@Resource
	private PublishDao publishDao;

	public int publishMarryInfo(Marry marry) {
		// TODO Auto-generated method stub
		return publishDao.publishMarryInfo(marry);
	}

}
