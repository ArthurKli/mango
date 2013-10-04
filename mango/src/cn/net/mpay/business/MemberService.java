package cn.net.mpay.business;

import java.util.List;
import java.util.Map;

import cn.net.mpay.bean.Member;
import cn.net.mpay.bean.User;

public interface MemberService {
	
	
	public int editUserInfo(Map<String, Object> params);

	public List<Member> getIndexMembers(int pageNum);
	
	public List<Member> searchMembers(Map<String, Object> params);
	
	public Member queryMemberById(int id);
}
