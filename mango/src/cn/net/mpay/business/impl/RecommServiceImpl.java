package cn.net.mpay.business.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Marry;
import cn.net.mpay.bean.Recommend;
import cn.net.mpay.business.RecommService;
import cn.net.mpay.dao.RecommDao;
@Component
public class RecommServiceImpl implements RecommService {
	@Resource
	RecommDao recommDao;


	public int addRecomm(Recommend red) {
		// TODO Auto-generated method stub
		return recommDao.addRecomm(red);
	}

}
