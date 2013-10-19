<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.net.mpay.bean.Member"%>
<%@page import="cn.net.mpay.util.Constants"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String avatarPath=Constants.getString("AvatarImagePath");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
	<script type='text/javascript' src="./js/jquery-1.7.1.min.js"></script>
  </head>
  
  <body>
  <script type="text/javascript">
	    $(document).ready(function() {
		    alert("init");
		    $("#test").click(function(){
		    	$.ajax({
		    		   type: "POST",
		    		   url: "getAvatar.pa",
		    		   data: "id="+$("#memberID").val(),
		    		   success: function(msg){
		    		   var src="<%=avatarPath %>"+msg.image;
		    		    // alert( "Data Saved: "+msg.image);
		    		     $("#avatarImage").attr("src",src);
		    		   }
		    		});
				    
			    });
			    
	    	

		    
	    });
	    </script>
   ID:
   <input type="text" id="memberID"  />
   <br/>
   <input type="button" id="test" value="click me" />
   <img alt="" src="" id="avatarImage">
   
  </body>
</html>
