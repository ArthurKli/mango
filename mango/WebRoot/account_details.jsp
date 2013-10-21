<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="head.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>名仕之约</title>
		
<style>
body
{
      padding: 0;
      margin: 0;    
      height: 100%;
      background-color: #eee;
      font-size: 12px;
      color: #666;
}

a
{
      text-decoration: none;
      color: #333;
}

a:hover
{
      text-decoration: none;
      color: #f00;
}

#container
{
      position: relative;
      left: 2px;
      top: 2px;
      width: 330px;
}

#wrapper
{
      position: relative;
      left: 0px;
      top: 10px;
      height: 250px;
     
}

#cropper
{
      position: absolute;
      left: 0px;
      top: 0px;
      border: 1px solid #ccc;
}

#previewContainer
{

}

.preview
{
      border: 1px solid #ccc;
}

#selectBtn
{
      position: absolute;
      left: 0px;
      top: 0px;
      width: 119px;
      height: 27px;
      background: url(images/select.png);
}

#saveBtn
{
      position: absolute;
      left: 150px;
      top: 0px;
      width: 67px;
      height: 27px;
      background: url(images/save.png);
}

#rotateLeftBtn
{
      position: absolute;
      left: 0px;
      top: 205px;
      width: 100px;
      height: 22px;
      padding-left: 25px;
      padding-top: 2px;
      background: url(images/left.png) no-repeat;
}

#rotateRightBtn
{
      position: absolute;
      left: 225px;
      top: 205px;
      width: 50px;
      height: 22px;
      padding-right: 25px;
      padding-top: 2px;
      background: url(images/right.png) no-repeat right;
}


</style>
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<%--<script type="text/javascript" src="js/ImageCropper.js"></script>
<script type="text/javascript" src="js/diyImage.js"></script>
--%><script type="text/javascript">



</script>
	</head>
<div class=" w980">
    <div class="wrapper">
        <%@include file="centerLeft.jsp" %>
        <div class="center_contain" id="right">
        	<div class="center_infor">
           		<h3>520婚恋机构
                    <span class="red">已认证</span>
                    <span><a href="#">待付款<b class="red">(1)</b></a></span> 
                    <span><a href="#">待确认<b class="red">(1)</b></a></span>
                    <span><a href="#">待付款<b class="red">(1)</b></a></span>
                    <span><a href="#">待收款<b class="red">(1)</b></a></span>
                    <span><a href="#">我的信箱<b class="red">(1)</b></a></span>
                </h3>
                <div class="grade">
                    <div class="fl"><img src="images/vip_grade.gif" width="24" height="17" alt="会员等级为0，投诉0" /></div>
                    <div class="vip"><span><i>VO</i> 会员</span></div>
            	</div>
            </div>
          <div class="center_item">
            <h1>账户余额</h1>
            <div class="yue">
            	<h5>账户余额：<b class="red">￥2500</b></h5>
                <span class="confirm_btn1 fl"><a href="#">我要充值</a></span>
                <span  class="confirm_btn3 fl"><a href="#">我要提现</a></span>
            </div>
          </div>
          <div class="clear"></div>
          
          <div class="center_item">
            <h1>账户明细</h1>
            <div class="account_detail">
                <div class="account_detail_top">
                	<div class="a1">&nbsp;</div>
                    <div class="a1">姓名</div>
                    <div class="a1">时间</div>
                    <div class="a1">金额</div>
                    <div class="a1">余额</div>
                    <div class="a2">备注</div>
                </div>
            	<div class="account_detail_list">
                	<div class="a1"><img src="images/p2.jpg" width="220" height="220" /></div>
                    <div class="a1">李小姐</div>
                    <div class="a1">2013-03-03</div>
                    <div class="a1"><span class="red">500</span></div>
                    <div class="a1">3500</div>
                    <div class="a2">成功约会，获得红包</div>
                </div>
                <div class="account_detail_list">
                	<div class="a1"><img src="images/p1.jpg" width="220" height="220" /></div>
                    <div class="a1">黄先生</div>
                    <div class="a1">2013-09-03</div>
                    <div class="a1"><span class="bule">-500</span></div>
                    <div class="a1">3000</div>
                    <div class="a2">成功约会，支付红包</div>
                </div>
            </div>
          </div>
        </div>
        </div>
    </div>
 
								

		<div class="clear"></div>
		
		<script language="javascript">
var l=document.getElementById("left").scrollHeight;
var n=document.getElementById("right").scrollHeight;
layoutHeight=Math.max(l,n);
document.getElementById("left").style.height=layoutHeight+"px";
document.getElementById("right").style.height=layoutHeight+"px";

</script>
<%@include file="foot.jsp" %>
	</body>
</html>

