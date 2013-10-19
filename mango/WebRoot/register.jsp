<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>名仕之约</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="top">
    	<div class="head">
        	<div class="w980">
            	<a href="index.html"><div class="logo fl"></div></a>                
          </div>
        </div>
        <div class="nav"></div>
    </div>
    <form name="form1" method="post" action="regist.pa">
    <div class="middle w980">
   	  <div class="login">
            <div class="login_left fl">
                <img src="images/register.jpg" width="488" height="320" />
       	</div>
            <div class="register_form fr">
            	<div>
            	  <label for="account">用户名</label>
            	  <input type="text" name="account" id="account" class="shuru" />
           	  	</div>
                <div class="red">
            	  <label for="textfield">&nbsp;</label>请填写用户名
           	  	</div>
                <div>
                  <label for="password">密码</label>
            	  <input type="text" name="password" id="password" class="shuru" />	
                </div>
                <div class="red">
            	  <label for="textfield">&nbsp;</label>请填写密码
           	  	</div>
                <div>
                	<label for="password2">确认密码</label>
            	  <input type="text" name="password2" id="password2" class="shuru" />
                </div>
                <div class="red">
            	  <label for="textfield">&nbsp;</label>请填写密码
   	  	    </div>
                <div>
                	<label for="email">邮箱</label>
            	  <input type="text" name="email" id="email" class="shuru" />
                </div>
                <div class="red">
            	  <label for="textfield">&nbsp;</label>请填写用邮箱
   	  	    </div>
                <div>
                   	<label for="mobile">手机号</label>
            	  <input type="text" name="mobile" id="mobile" class="shuru" />
                </div>
                <div class="red">
            	  <label for="textfield">&nbsp;</label>请填写用手机号码
           	  	</div>
                
                <div>
               	  <label for="textfield">验证码</label>
            	  <input type="text" name="textfield" id="textfield" class="shuru1" /><img src="images/yz.jpg" width="75" height="25" />
                </div>
                <div class="red">
            	  <label for="textfield">&nbsp;</label>验证码不正确
           	  	</div>
                <div><label for="textfield">&nbsp;</label><input name="" type="checkbox" value="" />已经阅读并同意 用户协议</div>
                <div class="register_tj"><input name="" type="submit" value="免费注册" />&nbsp;&nbsp;我是会员，立即<a href="login.jsp" class="red">登录</a> </div>
            </div>
      </div>
    </div>
    </form>
    <div class="clear"></div>
    <div class="mb30">&nbsp;</div>
    <%@include file="foot.jsp" %>

</body>
</html>

