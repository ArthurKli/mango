package cn.net.mpay.bean;

	/**
	*此类由MySQLToBean工具自动生成
	*备注(数据表的comment字段)：无备注信息
	*@author Kai 
	*@since 2013-09-20 17:22:17
	*/

	public class Pay_set{
	private int pset_id;
	private int org_id;
	private int mb_id;
	private String date_money;//约会红包
	private String contact_money;//交往红包
	private String marry_money;//结婚红包
	private String setting_date;//设置时间
	public int getPset_id(){
		return this.pset_id;
	}
	public void setPset_id(int pset_id){
		this.pset_id=pset_id;
	}
	public int getOrg_id(){
		return this.org_id;
	}
	public void setOrg_id(int org_id){
		this.org_id=org_id;
	}
	public int getMb_id(){
		return this.mb_id;
	}
	public void setMb_id(int mb_id){
		this.mb_id=mb_id;
	}
	public String getDate_money(){
		return this.date_money;
	}
	public void setDate_money(String date_money){
		this.date_money=date_money;
	}
	public String getContact_money(){
		return this.contact_money;
	}
	public void setContact_money(String contact_money){
		this.contact_money=contact_money;
	}
	public String getMarry_money(){
		return this.marry_money;
	}
	public void setMarry_money(String marry_money){
		this.marry_money=marry_money;
	}
	public String getSetting_date(){
		return this.setting_date;
	}
	public void setSetting_date(String setting_date){
		this.setting_date=setting_date;
	}

}