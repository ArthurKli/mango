package cn.net.mpay.bean;

	/**
	*此类由MySQLToBean工具自动生成
	*备注(数据表的comment字段)：无备注信息
	*@author Kai 
	*@since 2013-09-20 17:22:17
	*/

	public class Collect{
	private int col_id;
	private int org_id;//收藏者
	private int collect_org_id;//被收藏机构id
	private int collect_mb_id;//被收藏个人id
	public int getCol_id(){
		return this.col_id;
	}
	public void setCol_id(int col_id){
		this.col_id=col_id;
	}
	public int getOrg_id(){
		return this.org_id;
	}
	public void setOrg_id(int org_id){
		this.org_id=org_id;
	}
	public int getCollect_org_id(){
		return this.collect_org_id;
	}
	public void setCollect_org_id(int collect_org_id){
		this.collect_org_id=collect_org_id;
	}
	public int getCollect_mb_id(){
		return this.collect_mb_id;
	}
	public void setCollect_mb_id(int collect_mb_id){
		this.collect_mb_id=collect_mb_id;
	}

}