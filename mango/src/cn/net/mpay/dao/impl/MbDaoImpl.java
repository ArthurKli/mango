package cn.net.mpay.dao.impl;

import java.sql.Blob;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.expressme.simplejdbc.Db;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sun.misc.BASE64Decoder;
import cn.net.mpay.bean.Member;
import cn.net.mpay.dao.MbDao;
import cn.net.mpay.util.Constants;
@Transactional
@Component("mbDao")
public class MbDaoImpl implements MbDao {
	
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Resource
	private Db simpleJdbc;
	private final Log log = LogFactory.getLog(getClass());

	public int editUserInfo(String sql,Object[] args) {
		StringBuffer sb=new StringBuffer("UPDATE member set ");
		sb.append(sql);
		log.info("[args]:"+args.length+" [SQL]:"+sb.toString());
		try {
			return jdbcTemplate.update(sb.toString(),args);
		} catch (Exception e) {
			
			log.info("[DB error]:"+e.getMessage());
			return 0;
		}
		
		
	}
	public static void main(String[] args) {
		StringBuffer sb=new StringBuffer("UPDATE member set");
		System.out.println(sb.deleteCharAt(sb.length()-1));
	}
	public List<Member> getIndexMembers(int pageNum) {
		String sql= Constants.loadConfig("IndexMemberSql");
		String IndexMemberSize= Constants.loadConfig("IndexMemberSize");
		if (sql==null) {
			sql = "select * from Member order by regist_date desc";
		}
		int pageSize =Integer.parseInt(IndexMemberSize);
		int start = (pageNum-1)*pageSize;
		try {
			return simpleJdbc.queryForLimitedList(sql, start, pageSize);
		} catch (Exception e) {
			log.info("[DB error]:"+e.getMessage());
		}
		return null;
	}
	public List<Member> searchMembers(String sql, Object[] args ,int pageNum) {
		StringBuffer sb=new StringBuffer("select * from Member where 0=0 ");
		sb.append(sql);
		int pageSize =10;
		int start = (pageNum-1)*pageSize;
		log.info("[args]:"+args.length+" [SQL]:"+sb.toString());
		try {
			return simpleJdbc.queryForLimitedList(sb.toString(),start, pageSize, args);
//			return jdbcTemplate.queryForList(sb.toString(),args,Member.class);
		} catch (Exception e) {
			log.info("[DB error]:"+e.getMessage());
		}
		return null;
	}
	public Member queryMemberById(int id) {
		// TODO Auto-generated method stub
		
		try {
			return simpleJdbc.getById(Member.class, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("[DB error]:"+e.getMessage());
		}
		return null;
	}
	public int setMbAvatar(int id, byte[] img) {
		String sql="UPDATE member SET avatar=? WHERE id=?";
		
		try {
			return jdbcTemplate.update(sql, new Object[]{img,id});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public String getAvatarImage(int id) {
		final byte[] bytes;
        final float[] floats;
		String sql="SELECT avatar FROM member WHERE id = ?";
		Blob blob=jdbcTemplate.queryForObject(sql,new Object[] {id},Blob.class);
		if (blob==null) {
			return null;
		}
        try {
			bytes = blob.getBytes(1, (int) blob.length());
			log.info("byte size:"+bytes.length);
			String data=new String(bytes,"utf-8");
//			BASE64Decoder decoder =new BASE64Decoder();
//			return decoder.decodeBuffer(data.split(",")[1]);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
