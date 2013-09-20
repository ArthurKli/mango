package cn.net.mpay.bean;

	/**
	*此类由MySQLToBean工具自动生成
	*备注(数据表的comment字段)：无备注信息
	*@author Kai 
	*@since 2013-09-20 17:22:17
	*/

	public class Organization{
	private int org_id;
	private String login_name;//登录名
	private String turn_name;//机构名
	private String password;
	private String regist_time;
	private String mobile;
	private String mailbox;//邮箱
	private String money;//帐号余额
	public int getOrg_id(){
		return this.org_id;
	}
	public void setOrg_id(int org_id){
		this.org_id=org_id;
	}
	public String getLogin_name(){
		return this.login_name;
	}
	public void setLogin_name(String login_name){
		this.login_name=login_name;
	}
	public String getTurn_name(){
		return this.turn_name;
	}
	public void setTurn_name(String turn_name){
		this.turn_name=turn_name;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getRegist_time(){
		return this.regist_time;
	}
	public void setRegist_time(String regist_time){
		this.regist_time=regist_time;
	}
	public String getMobile(){
		return this.mobile;
	}
	public void setMobile(String mobile){
		this.mobile=mobile;
	}
	public String getMailbox(){
		return this.mailbox;
	}
	public void setMailbox(String mailbox){
		this.mailbox=mailbox;
	}
	public String getMoney(){
		return this.money;
	}
	public void setMoney(String money){
		this.money=money;
	}

}