package cn.net.mpay.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *此类由MySQLToBean工具自动生成 备注(数据表的comment字段)：无备注信息
 * 
 * @author Kai
 *@since 2013-09-20 17:22:17
 */
@Entity
public class Marry {
	
	private int marry_id;
	private int org_id;
	private int mb_id;
	private String title;
	private String content;
	private String start_time;
	private String end_time;
	@Id
	public int getMarry_id() {
		return this.marry_id;
	}

	public void setMarry_id(int marry_id) {
		this.marry_id = marry_id;
	}

	public int getOrg_id() {
		return this.org_id;
	}

	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}

	public int getMb_id() {
		return this.mb_id;
	}

	public void setMb_id(int mb_id) {
		this.mb_id = mb_id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStart_time() {
		return this.start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String endTime) {
		end_time = endTime;
	}

}