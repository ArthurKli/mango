package cn.net.mpay.util;

import cn.net.mpay.bean.User;

public class ActionValidate {
	
	public static int dataValidate(String name,String pwd,String pwd2){
		if (name==null) {
			return 1;
		}
		if (pwd==null) {
			return 2;
		}
		if (pwd2==null) {
			return 3;
		}
		if (!pwd.equals(pwd2)) {
			return 4;
		}
		
		return 0;
	}
	
	
	public static int dataValidate(User u){
		if (u.getBirthday()==null||u.getPassword()==null||
		u.getPassword2()==null||u.getPhone()==null||
		u.getUsername()==null
		) {
			return 1;
		} if (!u.getPassword().equals(u.getPassword2())) {
			return 2;
		}
		return 0;
	}

}
