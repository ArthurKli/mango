package cn.net.mpay.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Marry;
import cn.net.mpay.bean.Member;
import cn.net.mpay.bean.User;
import cn.net.mpay.business.LoginService;
import cn.net.mpay.business.PublishService;
import cn.net.mpay.util.ActionValidate;

import com.g3net.tool.CTime;
import com.g3net.tool.MD5;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Component
@Scope("prototype")
public class PublishAction extends ActionSupport{	 
	@Resource
	private PublishService publishService;
	private Integer org_id;
	private Integer mb_id;
	private String title;
	private String content;
	private String start_time;
	private String end_time;
	
	private String msg;
	
	
	private final Log log = LogFactory.getLog(getClass());
	
	/**
	 * 发布征婚
	 * http://localhost:8080/mango/publiMarry.pa?org_id=2&title=%E6%88%91%E8%A6%81%E4%B8%80%E4%B8%AA%E7%99%BD%E5%AF%8C%E7%BE%8E&content=%E6%88%91%E6%98%AF%E9%AB%98%E5%AF%8C%E5%B8%85
	 * @return
	 * @throws Exception
	 */
	public  String publishMarryInfo() throws Exception{
		Map session = ActionContext.getContext().getSession();
		Member member =(Member)session.get("user");	
		Marry marry =new Marry();
//		marry.setMb_id(member.getId());
		marry.setMb_id(1);
		marry.setOrg_id(org_id);
		marry.setContent(content);
		marry.setTitle(title);
		marry.setStart_time(start_time);
		marry.setEnd_time(end_time);
		
		int i =publishService.publishMarryInfo(marry);
		
		
		
		return SUCCESS;
	}
	
	public void getRequestParam(Map<String, Object> params){
		if(org_id!=null){
			params.put("org_id", org_id);
			
		}
		if(title!=null){
			params.put("title", title);
			
		}
		if(content!=null){
			params.put("content", content);
			
		}
		if(start_time!=null){
			params.put("start_time", start_time);
			
		}
		if(end_time!=null){
			params.put("end_time", end_time);
			
		}
		
	}


	public Integer getOrg_id() {
		return org_id;
	}


	public void setOrg_id(Integer orgId) {
		org_id = orgId;
	}


	public Integer getMb_id() {
		return mb_id;
	}


	public void setMb_id(Integer mbId) {
		mb_id = mbId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getStart_time() {
		return start_time;
	}


	public void setStart_time(String startTime) {
		start_time = startTime;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String endTime) {
		end_time = endTime;
	}
	
	

}
