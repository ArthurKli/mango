package cn.net.mpay.business.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Member;
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

	public List<Member> getIndexMembers(int pageNum) {
		// TODO Auto-generated method stub
		return mbDao.getIndexMembers(pageNum);
	}

	public List<Member> searchMembers(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer();
//		Object[] args = new Object[params.size()];
		ArrayList<Object> array =new ArrayList<Object>();

		Set<String> keyset = params.keySet();
		int i = 0;
		try {
			for (Iterator it = keyset.iterator(); it.hasNext();) {
				String key = (String) it.next();
				if (key.equals("id") || key.equals("sortName") || key.equals("sortType") || key.equals("pageNum")) {
					continue;
				}
				
				array.add(params.get(key));

				sql.append("and "+key + "=? ");
				i++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sortName =(String)params.get("sortName");
		int sortType =(Integer)params.get("sortType");
		if(sortName==null){
			sql.append(" order by regist_date");
		}else {
			sql.append(" order by "+sortName);
		}
		if(sortType==1){
			sql.append(" desc ");
		}
		
		
		return mbDao.searchMembers(sql.toString(), array.toArray(), (Integer)params.get("pageNum"));
	}

	public Member queryMemberById(int id) {
		// TODO Auto-generated method stub
		return mbDao.queryMemberById(id);
	}

}
