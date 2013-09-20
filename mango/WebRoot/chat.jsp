<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>DWR Test</title>
    <link rel="stylesheet" type="text/css" href="css/panel.css">
     <link rel="stylesheet" type="text/css" href="css/chat.css">
     <style type="text/css">
    </style>
    <script type="text/javascript" src="dwr/engine.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/interface/RemoteTest.js"></script>
    
    <script type='text/javascript' src="js/jquery-1.4.4.min.js"></script>
    <script type='text/javascript' src="js/dom_init.js"></script>
    <script type='text/javascript' src="js/chat.js"></script>
   
      <script type="text/javascript">
  </script>
  </head>
  
  <body>
  
  <input type="text" id="myid" value=""  />
  <input type="button" value="登陆" onclick="helloTest();" />
   <input type="button" value="获取好友" onclick="getFris();" />
   <input type="hidden" id="userid" name="userid" value=""  />
		<div id="footpanel" class="header_demo0">
			<ul id="mainpanel">
				<li id="chatpanel" class="chatpanel">
					<a id="chat" href="#" class="active">联系人</a>
					<div align="" id="subpanel" style="height: auto; display: block;">
						<h3 id="h3skin" class="header_demo0">
							<span> &ndash; </span>我的联系人
						</h3>
						<ul id="list" style="height: auto;">
							<div id="friendss">
								--------最近联系人-----
							</div>
							<div id="inline" style="display: none;"></div>
							<div id="friends">
								---------我的好友-------
							</div>
							<div id="outline"></div>
						</ul>
					</div>
				</li>
			</ul>
		</div>
	</body>
</html>
