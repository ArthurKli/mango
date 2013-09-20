package cn.net.mpay.bean;

	/**
	*此类由MySQLToBean工具自动生成
	*备注(数据表的comment字段)：无备注信息
	*@author Kai 
	*@since 2013-09-20 17:22:17
	*/

	public class Account{
	private int acc_id;
	private int org_id;
	private int mb_id;//机构对应的某个会员
	private int d_id;
	private int type;//0 花费1 收费2 冻结
	private String money;//金额
	public int getAcc_id(){
		return this.acc_id;
	}
	public void setAcc_id(int acc_id){
		this.acc_id=acc_id;
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
	public int getD_id(){
		return this.d_id;
	}
	public void setD_id(int d_id){
		this.d_id=d_id;
	}
	public int getType(){
		return this.type;
	}
	public void setType(int type){
		this.type=type;
	}
	public String getMoney(){
		return this.money;
	}
	public void setMoney(String money){
		this.money=money;
	}

}