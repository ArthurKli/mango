package cn.net.mpay.mdao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import cn.net.mpay.bean.User;

@Transactional
public interface ChatDao {
	@Select("SELECT u.* FROM USER AS u,friends AS f WHERE u.uid=f.fri_id AND f.uid=#{uid}")
	public List<User> findFriendById(String uid);

}
