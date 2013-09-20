package cn.net.mpay.util;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

public class TestScriptSessionFilter implements ScriptSessionFilter {

	private int userid;

	public  TestScriptSessionFilter(int userid)
    {
        this.userid = userid;
    }

    /* (non-Javadoc)
     * @see org.directwebremoting.ScriptSessionFilter#match(org.directwebremoting.ScriptSession)
     */
    public boolean match(ScriptSession session)
    {
    	
        Object check = session.getAttribute("userid");
        //System.out.println(check);
        return (check != null && check.equals(userid));
    }
}