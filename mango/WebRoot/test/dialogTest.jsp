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
    <link rel="stylesheet" href="./css/dialog/zebra_dialog.css" type="text/css">
	<script type='text/javascript' src="./js/jquery-1.7.1.min.js"></script>
	  <script type='text/javascript' src="./js/zebra_dialog.js"></script>  
  </head>
  
  <body>
  <script type="text/javascript">
  $(document).ready(function() {

	    // show a dialog box when clicking on a link
	    $("#test").bind('click', function(e) {
	        e.preventDefault();
	      //  $.Zebra_Dialog('The link was clicked!');
	        $.Zebra_Dialog('<strong>Zebra_Dialog</strong>, a small, compact and highly' +
	        	    'configurable dialog box plugin for jQuery', {
	        	    'type':     'error',
	        	    'title':    'Error'
	        	});
	    });

	 });
	    </script>
   ajax test page<br/>
   <input type="button" id="test" value="click me" />
   
  </body>
</html>
