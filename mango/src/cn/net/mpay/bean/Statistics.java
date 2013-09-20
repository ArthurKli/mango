package cn.net.mpay.bean;

	/**
	*此类由MySQLToBean工具自动生成
	*备注(数据表的comment字段)：无备注信息
	*@author Kai 
	*@since 2013-09-20 17:22:17
	*/

	public class Statistics{
	private int sta_id;
	private int org_id;
	private int mb_id;
	private int msg_count;//新消息数
	private int wating_pay_count;//待付款数
	private int complain_count;//新投诉数
	private int date_count;//新约会数
	private int recom_count;//新推荐数
	public int getSta_id(){
		return this.sta_id;
	}
	public void setSta_id(int sta_id){
		this.sta_id=sta_id;
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
	public int getMsg_count(){
		return this.msg_count;
	}
	public void setMsg_count(int msg_count){
		this.msg_count=msg_count;
	}
	public int getWating_pay_count(){
		return this.wating_pay_count;
	}
	public void setWating_pay_count(int wating_pay_count){
		this.wating_pay_count=wating_pay_count;
	}
	public int getComplain_count(){
		return this.complain_count;
	}
	public void setComplain_count(int complain_count){
		this.complain_count=complain_count;
	}
	public int getDate_count(){
		return this.date_count;
	}
	public void setDate_count(int date_count){
		this.date_count=date_count;
	}
	public int getRecom_count(){
		return this.recom_count;
	}
	public void setRecom_count(int recom_count){
		this.recom_count=recom_count;
	}

}