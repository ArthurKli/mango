package cn.net.mpay.business.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.net.mpay.business.MemberService;
import cn.net.mpay.dao.MbDao;

@Component("memberService")
public class MemberServiceImpl implements MemberService {

	@Resource
	private MbDao mbDao;

	public int editUserInfo(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer();
		Object[] args = new Object[params.size()];

		Set<String> keyset = params.keySet();
		int i = 0;
		for (Iterator it = keyset.iterator(); it.hasNext();) {
			String key = (String) it.next();
			if (key.equals("id")) {
				continue;
			}
			args[i] = params.get(key);

			sql.append(key + "=? , ");
			i++;
		}
		sql.deleteCharAt(sql.length() - 2);
		args[i] = params.get("id");
		sql.append(" where id=? ");

		// TODO Auto-generated method stub
		return mbDao.editUserInfo(sql.toString(), args);
	}

}
