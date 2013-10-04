package cn.net.mpay.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

	/**
	*此类由MySQLToBean工具自动生成
	*备注(数据表的comment字段)：无备注信息
	*@author Kai 
	*@since 2013-09-20 17:22:17
	*/
    @Entity
	public class Recommend{
    
	private int re_id;
	private int marry_id;
	private int sell_mb_id;//推荐人id（商家会员）
	private int sell_org_id;//推荐机构id（商家）
	private String time;//推荐时间
	@Id
	public int getRe_id(){
		return this.re_id;
	}
	public void setRe_id(int re_id){
		this.re_id=re_id;
	}
	public int getMarry_id(){
		return this.marry_id;
	}
	public void setMarry_id(int marry_id){
		this.marry_id=marry_id;
	}
	public int getSell_mb_id(){
		return this.sell_mb_id;
	}
	public void setSell_mb_id(int sell_mb_id){
		this.sell_mb_id=sell_mb_id;
	}
	public int getSell_org_id(){
		return this.sell_org_id;
	}
	public void setSell_org_id(int sell_org_id){
		this.sell_org_id=sell_org_id;
	}
	public String getTime(){
		return this.time;
	}
	public void setTime(String time){
		this.time=time;
	}

}