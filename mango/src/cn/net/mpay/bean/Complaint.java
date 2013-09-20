package cn.net.mpay.bean;

	/**
	*此类由MySQLToBean工具自动生成
	*备注(数据表的comment字段)：无备注信息
	*@author Kai 
	*@since 2013-09-20 17:22:17
	*/

	public class Complaint{
	private int comp_id;
	private int d_id;
	private int plaintiff_org_id;//原告机构id
	private int plaintiff_mb_id;//原告个人id
	private int defendant_org_id;//被告机构id
	private int defendant_mb_id;//被告个人id
	private int content;//投诉内容
	private String time;//处理时间
	public int getComp_id(){
		return this.comp_id;
	}
	public void setComp_id(int comp_id){
		this.comp_id=comp_id;
	}
	public int getD_id(){
		return this.d_id;
	}
	public void setD_id(int d_id){
		this.d_id=d_id;
	}
	public int getPlaintiff_org_id(){
		return this.plaintiff_org_id;
	}
	public void setPlaintiff_org_id(int plaintiff_org_id){
		this.plaintiff_org_id=plaintiff_org_id;
	}
	public int getPlaintiff_mb_id(){
		return this.plaintiff_mb_id;
	}
	public void setPlaintiff_mb_id(int plaintiff_mb_id){
		this.plaintiff_mb_id=plaintiff_mb_id;
	}
	public int getDefendant_org_id(){
		return this.defendant_org_id;
	}
	public void setDefendant_org_id(int defendant_org_id){
		this.defendant_org_id=defendant_org_id;
	}
	public int getDefendant_mb_id(){
		return this.defendant_mb_id;
	}
	public void setDefendant_mb_id(int defendant_mb_id){
		this.defendant_mb_id=defendant_mb_id;
	}
	public int getContent(){
		return this.content;
	}
	public void setContent(int content){
		this.content=content;
	}
	public String getTime(){
		return this.time;
	}
	public void setTime(String time){
		this.time=time;
	}

}