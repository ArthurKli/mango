<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My97DatePicker/</title>
    

	<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
  <script>

  </script>
  </head>
  
  <body>
<input class="Wdate" type="text" onClick="WdatePicker({skin:'green'})">
  </body>
</html>
