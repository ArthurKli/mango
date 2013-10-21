<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  </head>
  <body>
   <div class="center_menu" id="left">
        	<div class="user_pic"><img src="images/p1.jpg" width="220" height="220" /></div>
   	  		<div>
                <div class="menu">
                    <a href="center.jsp" class="menu_hover fistmenu">我的名仕/我的商户</a>                    
                </div>
                <div class="menu">
                    <a href="datum.jsp" class="fistmenu" >基本资料</a>                    
                </div>
                <div class="menu">
                    <a href="javascript:void(0);" class="fistmenu" onclick="showHide(this,'items0');">我的会员</a>
                    <ul id="items0">
                    	<li><a href="marriage_list.jsp">征婚列表</a></li>
                        <li><a href="recommend_list.jsp">推荐列表</a></li>
                    </ul>
                </div>
                <div class="menu">
                    <a href="javascript:void(0);" class="fistmenu" onclick="showHide(this,'items1');">红娘推荐</a>
                    <ul id="items1">
                    	<li><a href="new_recommend.jsp">新推荐<b>5</b></a></li>
                        <li><a href="new_recommend.jsp">官网推荐</a></li>                        
                        <li><a href="new_recommend.jsp">过往推荐</a></li>
                    </ul>
                </div>
                <div class="menu">
                    <a href="data_list.jsp" class="fistmenu" onclick="showHide(this,'items2');">约会列表</a>
                    <ul id="items2">
                    	<li><a href="#">待支付</a></li>
                        <li><a href="#">已支付</a></li>
                    	<li><a href="#">待确认</a></li>
                        <li><a href="#">待收款</a></li>
                        <li><a href="#">已收款</a></li>
                    </ul>
                </div>
                <div class="menu">
                    <a href="succeed.jsp " class="fistmenu" >成功案例</a>                    
                </div>
                 <div class="menu">
                    <a href="complain.jsp" class="fistmenu" onclick="showHide(this,'items3');">投诉列表</a>
                    <ul id="items3">
                    	<li><a href="#">被投诉<b>5</b></a></li>
                        <li><a href="#">投诉</a></li>         
                    </ul>
                </div>
                <div class="menu">
                    <a href="mailbox.jsp" class="fistmenu" >信箱<b>1</b></a>                    
                </div>
                <div class="menu">
                    <a href="collection.jsp" class="fistmenu" >我的收藏</a>                    
                </div>
                <div class="menu">
                    <a href="javascript:void(0);" class="fistmenu" onclick="showHide(this,'items4');">红包帐户</a>
                    <ul id="items4">
                    	<li><a href="account_details.jsp">明细</a></li>
                        <li><a href="account_details.jsp">余额</a></li>
                    	<li><a href="account_details.jsp">充值</a></li>
                        <li><a href="#">联系我们</a></li>
                    </ul>
                </div>
            </div>	
        </div>	
  </body>
</html>
