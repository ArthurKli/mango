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
		<link href="<%=basePath%>css/dialog/zebra_dialog.css" rel="stylesheet"  type="text/css" />
		<script type='text/javascript' src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
		<script type='text/javascript' src="<%=basePath%>/js/zebra_dialog.js"></script> 
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
    <form name="form1" method="post" action="regist.pa" onsubmit="return checkLogin()">
    <div class="middle w980">
   	  <div class="login">
            <div class="login_left fl">
                <img src="images/register.jpg" width="488" height="320" />
       	</div>
            <div class="register_form fr">
            	<div>
            	  <label for="account">用户名</label>
            	  <input type="text" name="login_name" id="login_name" class="shuru" />
           	  	</div>
                <div class="red">
            	  <label for="textfield">&nbsp;</label><span class="tipper" id="name_tip">请填写用户名</span>
           	  	</div>
                <div>
                  <label for="password">密码</label>
            	  <input type="text" name="password" id="password" class="shuru" />	
                </div>
                <div class="red">
            	  <label for="textfield">&nbsp;</label>
            	  <span class="tipper" id="pwd_tip">请填写密码</span>
           	  	</div>
                <div>
                	<label for="password2">确认密码</label>
            	  <input type="text" name="password2" id="password2" class="shuru" />
                </div>
                <div class="red">
            	  <label for="textfield">&nbsp;</label><span class="tipper" id="pwd2_tip">请填写密码</span>
   	  	    </div>
                <div>
                	<label for="email">邮箱</label>
            	  <input type="text" name="mailbox" id="mailbox" class="shuru" />
                </div>
                <div class="red">
            	  <label for="textfield">&nbsp;</label><span class="tipper" id="mail_tip">请填写用邮箱</span>
   	  	    </div>
                <div>
                   	<label for="mobile">手机号</label>
            	  <input type="text" name="mobile" id="mobile" class="shuru" />
                </div>
                <div class="red">
            	  <label for="textfield">&nbsp;</label><span class="tipper" id="phone_tip">请填写用手机号码</span>
           	  	</div>
                
                <div>
               	  <label for="textfield">验证码</label>
            	  <input type="text" name="textfield" id="textfield" class="shuru1" /><img src="images/yz.jpg" width="75" height="25" />
                </div>
                <div class="red">
            	  <label for="textfield">&nbsp;</label><span class="tipper" id="yzm_tip">验证码不正确</span>
           	  	</div>
                <div><label for="agree">&nbsp;</label><input name="agree" id="agree" type="checkbox"  />已经阅读并同意 用户协议</div>
                <div class="register_tj"><input name="" type="submit" value="免费注册" />&nbsp;&nbsp;我是会员，立即<a href="login.jsp" class="red">登录</a> </div>
            </div>
      </div>
    </div>
    </form>
    <div class="clear"></div>
    <div class="mb30">&nbsp;</div>
    
    <script type="text/javascript">
		function checkLogin(){
			$(".tipper").hide();
			if($("#login_name").val()==""){
				$("#name_tip").show();
				return false;
			}
			if($("#password").val()==""){
				$("#pwd_tip").show();
				return false;
			}
			if($("#password2").val()==""){
				$("#pwd2_tip").show();
				return false;
			}
			if($("#mailbox").val()==""){
				$("#mail_tip").show();
				return false;
			}
			if($("#mobile").val()==""){
				$("#phone_tip").show();
				return false;
			}
			if(!$("#agree").is(':checked')){
				alert("你需要同意协议！");
				return false;
			}


		}

		function showError(msg){
	        $.Zebra_Dialog('<strong>'+msg+'</strong>', {
	        	    'type':     'error',
	        	    'title':    '系 统 错 误'
	        	});
		}
		
		$(function(){
			var msg ="${msg}";
			if(msg!=''){
				showError(msg);
			}
			

			});
		
		</script>
    <%@include file="foot.jsp" %>

</body>
</html>

