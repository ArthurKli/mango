package cn.net.mpay.mdao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import cn.net.mpay.bean.Member;
import cn.net.mpay.bean.Organization;

@Transactional
public interface MemberDao {
	
	@Insert("insert into member(org_id,account,password,email) values(#{org_id},#{account},#{password},#{email})")
	public int createMember(Member mb);
	
	@Select("SELECT * FROM member WHERE account=#{account} AND password=#{password}")
	public Member loginNormal(Member mb);
	
	@Select("SELECT * FROM organization WHERE login_name=#{login_name} AND password=#{password}")
	public Organization loginOrg(Organization org);
	
	@Update("UPDATE member SET org_id=#{org_id},password=#{password},email=#{email} where id=#{id}")
	public int updateMember(Member mb);

}
