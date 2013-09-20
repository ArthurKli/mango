package cn.net.mpay.bean;

	/**
	*此类由MySQLToBean工具自动生成
	*备注(数据表的comment字段)：无备注信息
	*@author Kai 
	*@since 2013-09-20 17:22:17
	*/

	public class Letter{
	private int lt_id;
	private int send_org_id;
	private int send_mb_id;
	private int receive_org_id;
	private int receive_mb_id;
	private String content;
	public int getLt_id(){
		return this.lt_id;
	}
	public void setLt_id(int lt_id){
		this.lt_id=lt_id;
	}
	public int getSend_org_id(){
		return this.send_org_id;
	}
	public void setSend_org_id(int send_org_id){
		this.send_org_id=send_org_id;
	}
	public int getSend_mb_id(){
		return this.send_mb_id;
	}
	public void setSend_mb_id(int send_mb_id){
		this.send_mb_id=send_mb_id;
	}

	public int getReceive_mb_id(){
		return this.receive_mb_id;
	}
	public void setReceive_mb_id(int receive_mb_id){
		this.receive_mb_id=receive_mb_id;
	}
	public String getContent(){
		return this.content;
	}
	public void setContent(String content){
		this.content=content;
	}
	public void setReceive_org_id(int receive_org_id) {
		this.receive_org_id = receive_org_id;
	}
	public int getReceive_org_id() {
		return receive_org_id;
	}

}