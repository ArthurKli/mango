package cn.net.mpay.bean;

	/**
	*此类由MySQLToBean工具自动生成
	*备注(数据表的comment字段)：无备注信息
	*@author Kai 
	*@since 2013-09-20 17:22:17
	*/

	public class Dating{
	private int d_id;
	private int re_id;
	private int status;//1申请约会2确认约会（冻结部分资金）3完成约会4付款5投诉
	public int getD_id(){
		return this.d_id;
	}
	public void setD_id(int d_id){
		this.d_id=d_id;
	}
	public int getRe_id(){
		return this.re_id;
	}
	public void setRe_id(int re_id){
		this.re_id=re_id;
	}
	public int getStatus(){
		return this.status;
	}
	public void setStatus(int status){
		this.status=status;
	}

}