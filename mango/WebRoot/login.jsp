<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
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
					<a href="index.html"><div class="logo fl"></div>
					</a>
				</div>
			</div>
			<div class="nav"></div>
		</div>
<form name="form1" method="post" action="loginNormal.pa" onsubmit="return checkLogin()">
		<div class="middle w980">
			<div class="login">
				<div class="login_left1 fl">
					<img src="images/login.jpg" width="488" height="320" />
				</div>
				<div class="login_form fr">
					<h1>
						用户登录
					</h1>
					<div>
						<label for="login_name">
							用户名
						</label>
						<input type="text" name="login_name" id="login_name" class="shuru" />
					<div class="red" >
						<label for="login_name">
							&nbsp;
						</label>
						<span class="tipper" id="name_tip">请填写用户名</span>
					</div>


					</div>

					<div>
						<label for="password">
							密码
						</label>
						<input type="password" name="password" id="password" class="shuru" />
											<div class="red" >
						<label for="password">
							&nbsp;
						</label>
						<span class="tipper" id="pwd_tip">请填写密码</span>
					</div>
					</div>
					<div class="register_tj">
						<input name="" type="submit" value="登 录" />
						&nbsp;&nbsp;还不是会员？
						<a href="register.jsp" class="red">免费注册</a>
					</div>
				</div>
			</div>
		</div>
</form>
		<div class="clear"></div>
		<div class="mb30">
			&nbsp;
		</div>
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
