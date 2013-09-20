package cn.net.mpay.mdao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.transaction.annotation.Transactional;

import cn.net.mpay.bean.Member;

@Transactional
public interface MemberDao {
	
	@Insert("insert into member(org_id,account,password,email) values(#{org_id},#{account},#{password},#{email})")
	public int createMember(Member mb);

}
