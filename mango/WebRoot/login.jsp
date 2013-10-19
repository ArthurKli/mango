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
<form name="form1" method="post" action="loginNormal.pa">
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
						<label for="textfield">
							用户名
						</label>
						<input type="text" name="account" id="account" class="shuru" />
					</div>
					<div class="red">
						<label for="textfield">
							&nbsp;
						</label>
						请填写用户名
					</div>
					<div>
						<label for="textfield">
							密码
						</label>
						<input type="password" name="password" id="password" class="shuru" />
					</div>
					<div class="red">
						<label for="textfield">
							&nbsp;
						</label>
						请填写密码
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
		<%@include file="foot.jsp" %>

	</body>
</html>
