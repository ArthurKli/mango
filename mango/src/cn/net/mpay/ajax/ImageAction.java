package cn.net.mpay.ajax;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import cn.net.mpay.business.MemberService;

import com.opensymphony.xwork2.ActionContext;

@Component
public class ImageAction {
	private int id;
	private String result;
	private String image;
	@Resource
	private MemberService mbServ;
	
	
	
	private final Log log = LogFactory.getLog(getClass());
	
	public String getAvatar() throws IOException{
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		image =mbServ.getAvatarImage(id);
//		response.getWriter().write("hheeell");
//		result="i come back";
		return "data";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}



}
