package cn.net.mpay.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.directwebremoting.Container;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.ScriptSessionManager;

public class InitScriptSession extends HttpServlet {

	ServletContext application;

	public void init() {
		System.out.println("初始化。。。");
		Container container = ServerContextFactory.get().getContainer();
		ScriptSessionManager manager = container
				.getBean(ScriptSessionManager.class);
		ScriptSessionListener listener = new ScriptSessionListener() {
			public void sessionCreated(ScriptSessionEvent ev) {
//				HttpSession session = WebContextFactory.get().getSession();
				ScriptSession scriptSession = ev.getSession();
//				String userId = session.getAttribute("userId").toString();
//				scriptSession.setAttribute("userId", userId);
				System.err.println("创建---"+scriptSession.getId()+"------- put userId into scriptSession as "
						);
			}

			public void sessionDestroyed(ScriptSessionEvent ev) {
				System.out.println("销毁-----"+ev.getSession().getId());
			}
		};
		manager.addScriptSessionListener(listener);
	}

	public void service(ServletRequest req, ServletResponse res) {
		init();
	}
}