package cn.net.mpay.bean;

	/**
	*此类由MySQLToBean工具自动生成
	*备注(数据表的comment字段)：无备注信息
	*@author Kai 
	*@since 2013-09-20 17:22:17
	*/

	public class Media{
	private int media_id;
	private int mb_id;
	private String media_url;//媒体地址
	private int media_type;//1视频 2生活照 3头像
	private int is_show;//0显示，1不显示
	public int getMedia_id(){
		return this.media_id;
	}
	public void setMedia_id(int media_id){
		this.media_id=media_id;
	}
	public int getMb_id(){
		return this.mb_id;
	}
	public void setMb_id(int mb_id){
		this.mb_id=mb_id;
	}
	public String getMedia_url(){
		return this.media_url;
	}
	public void setMedia_url(String media_url){
		this.media_url=media_url;
	}
	public int getMedia_type(){
		return this.media_type;
	}
	public void setMedia_type(int media_type){
		this.media_type=media_type;
	}
	public int getIs_show(){
		return this.is_show;
	}
	public void setIs_show(int is_show){
		this.is_show=is_show;
	}

}