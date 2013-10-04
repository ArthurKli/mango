package cn.net.mpay.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.net.mpay.bean.Recommend;
import cn.net.mpay.business.RecommService;

import com.opensymphony.xwork2.ActionSupport;
@Component
@Scope("prototype")
public class RecommendAction extends ActionSupport{	 
	private Integer re_id;
	private Integer marry_id;
	private Integer sell_mb_id;//推荐人id（商家会员）
	private Integer sell_org_id;//推荐机构id（商家）
	private String time;//推荐时间
	private String msg;
	@Resource
	RecommService reService;
	
	private final Log log = LogFactory.getLog(getClass());
	
	/**
	 * 加入推荐表
	 * http://localhost:8080/mango/addRecomm.action?marry_id=1&sell_mb_id=1&sell_org_id=1
	 * @return
	 */
	public String addRecommend(){
		Recommend red=new Recommend();
	    red.setMarry_id(marry_id);
	    red.setSell_mb_id(sell_mb_id);
	    red.setSell_org_id(sell_org_id);
	    red.setTime(time);
	    
	    int i =reService.addRecomm(red);
	    if(i==1){
	    	msg ="成功加入推荐表";
	    	return SUCCESS;
	    }
	    msg ="失败加入推荐表";
	    return "error";
		
		
	}

	public Integer getRe_id() {
		return re_id;
	}

	public void setRe_id(Integer reId) {
		re_id = reId;
	}

	public Integer getMarry_id() {
		return marry_id;
	}

	public void setMarry_id(Integer marryId) {
		marry_id = marryId;
	}

	public Integer getSell_mb_id() {
		return sell_mb_id;
	}

	public void setSell_mb_id(Integer sellMbId) {
		sell_mb_id = sellMbId;
	}

	public Integer getSell_org_id() {
		return sell_org_id;
	}

	public void setSell_org_id(Integer sellOrgId) {
		sell_org_id = sellOrgId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	


	
	

}
