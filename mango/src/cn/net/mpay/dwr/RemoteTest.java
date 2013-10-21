package cn.net.mpay.dwr;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.directwebremoting.ServerContext;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import cn.net.mpay.bean.User;
import cn.net.mpay.mdao.ChatDao;
@Controller  
@RemoteProxy  
public class RemoteTest {
	private final Log log = LogFactory.getLog(getClass());
	
	@Resource
	private ChatDao cDao;
	
	@RemoteMethod
	public int login(int id) {
//		HttpSession httpSession =WebContextFactory.get().getSession();
//		Integer sid =(Integer) httpSession.getAttribute("uid");
		log.info("绑定httpsession:"+id);
		try {
			this.setScriptSessionFlag(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return id;
	}
	@RemoteMethod
	public String createUUID() {
		int result = 0;
		String uuid=UUID.randomUUID().toString();
		log.info("uuid="+uuid);

		return "uuid:"+uuid;
	}
	
	@RemoteMethod
	public void removeSession() {
		
		this.removeScriptSession();
	}
	
	@RemoteMethod
	public List<User> getFriendsByID(String uid) {
        List<User> list = cDao.findFriendById(uid);
        log.info(list.size());
		return list;
	}
	
	/**
	 * @Title: sendMessage
	 * @Description: 发送消息
	 * @param1： message 消息内容
	 * @param2： receiver 接收者
	 * @param3： sender 发送者
	 * @return： void
	 */

	@RemoteMethod
	public void sendMsg(String message, int receiver, int sender) {
		
		this.getScriptSession(receiver, message, sender );
	}
	
	
	// 绑定session
	public void setScriptSessionFlag(int userid) {
		WebContext webContext = WebContextFactory.get();
		webContext.getScriptSession().setAttribute("userid",
				userid);
	}
	
	public void removeScriptSession() {
		WebContext webContext = WebContextFactory.get();
		webContext.getScriptSession().removeAttribute("userid");
	}
	
	// 通过filter捕获当前网页上与userid匹配的session，并把信息推送给该好友
	public void getScriptSession(int userid, final String message,
			final int ppid) {
		WebContext webContext = WebContextFactory.get();
		String page = webContext.getContextPath()
				+"/chat.jsp";
		// final ScriptSession myScSession = webContext.getScriptSession();

		final ScriptSessionFilter filter = new cn.net.mpay.util.TestScriptSessionFilter(userid);// 
		Collection<ScriptSession> colls = Browser.getTargetSessions();
		// Browser类是dwr3.0新增加的类，能够简化2.0版本的循环操作,而且更稳定和快速
		Browser.withAllSessionsFiltered(filter, new Runnable() {
			public void run() {
				Collection<ScriptSession> colls = Browser.getTargetSessions();
				for (ScriptSession session : colls) {

					if (filter.match(session)) // session匹配
					{

						String ppname = "sender";
						ScriptBuffer scriptBuffer = new ScriptBuffer();
						// 对 DWR ScriptBuffer对象调用appendData()将自动把 Java对象封送给JSON
						scriptBuffer.appendScript("talking(");
						scriptBuffer.appendData(message);
						scriptBuffer.appendScript(",");
						scriptBuffer.appendData(ppid);
						scriptBuffer.appendScript(",");
						scriptBuffer.appendData(ppname);
						scriptBuffer.appendScript(")");
						session.addScript(scriptBuffer);
					}
				}
			}

		});

	}
}
