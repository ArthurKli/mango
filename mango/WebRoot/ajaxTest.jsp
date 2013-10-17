<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.net.mpay.bean.Member"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<script type='text/javascript' src="js/jquery-1.7.1.min.js"></script>
	    
  </head>
  
  <body>
  <script type="text/javascript">
	    $(document).ready(function() {
		    alert("init");
	    	$.ajax({
	    		   type: "POST",
	    		   url: "getAvatar.pa",
	    		   data: "id=1&location=Boston",
	    		   success: function(msg){
	    		   var i=msg;
	    		    // alert( "Data Saved: "+msg.image);
	    		     $("#avatarImage").attr("src",msg.image);
	    		   }
	    		});


		    
	    });
	    </script>
   ajax test page<br/>
   <img alt="" src="" id="avatarImage">
   
  </body>
</html>
