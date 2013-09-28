package cn.net.mpay.dao;

import java.util.List;

import cn.net.mpay.bean.Member;
import cn.net.mpay.bean.User;

public interface MbDao {
	
	public int editUserInfo(String sql,Object[] objs);
	
	public List<Member> getIndexMembers(int pageNum);


}
