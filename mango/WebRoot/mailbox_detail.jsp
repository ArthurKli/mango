<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="head.jsp" %>
<%
   String you =(String)request.getParameter("mid");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>名仕之约</title>

</head>

<body class="center">
    
    <div class=" w980">
    <div class="wrapper">
        <%@include file="centerLeft.jsp" %>
        
        <div class="center_contain" id="right">
          <div class="center_item">
        	<h6>信箱</h6>
                <div class="message" id="message_send_to"> 
                	<div class="message_li">
                    	<div class="message_pic fr"><img src="images/logo.jpg" /></div>
                		<div class="for fr">
                        	<div class="for_ico"></div>
                    		<b class="bule">名仕之约公告</b><span>2013-09-13</span>
                            <p>欢迎来到名仕之约！</p>
                    	</div>
                        
                    </div>

            </div>
                                <div class="sent_message">
                    	<textarea name="" cols="" rows=""></textarea>
                        <span class="confirm_btn2"><a href="javascript:void(0);" onclick="sendMessage();">发 送</a></span>
                    </div>
         	</div>
            
        </div>
        </div>
      </div>
    </div>
    
    <div class="clear"></div>
 <%@include file="foot.jsp" %>
<script language="javascript">
var l=document.getElementById("left").scrollHeight;
var n=document.getElementById("right").scrollHeight;
layoutHeight=Math.max(l,n);
document.getElementById("left").style.height=layoutHeight+"px";
document.getElementById("right").style.height=layoutHeight+"px";
function sendMessage(uid){
    var msg=$(".sent_message textarea").val();
    var htmlStr = "";
    var now = new Date();
    var time = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
    htmlStr += " <div class='message_li'>";
    htmlStr += "<div class='message_pic fl'><img src='images/p1.jpg' /></div>";
    htmlStr += "<div class='sent fl'>";
    htmlStr += "<div class='sent_ico'></div>";
    htmlStr += "<b class='orange'>我</b><span>"+time+"</span>";
    htmlStr += "<p>"+msg+"</p></div></div>";
    $(".message").append(htmlStr);
    $(".sent_message textarea").val('');

    //sc(uid);
    RemoteTest.sendMsg(msg,"1","2");
  
}

</script>
</body>
</html>
