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
                	<h1>成功案例</h1>
                    <div class="center_search">
                        <label>搜索约会：<input name="" type="text" /></label><span><a href="#">搜 索</a></span> <a href="#">条件搜索</a>
                        <div class="condition_search">
                            <ul>
                                <li class="col1"> 
                                     <label>成交时间：</label>&nbsp;&nbsp;
                                    从 <input type="text" size="10" value="">
                                    到 <input type="text" size="10" value="">
                              </li>
                                 <li class="col2 ">
                                     <label>约会状态：</label>
                                     <select>
                                     <option>全部</option>
                                     <option >已申请</option>
                                     <option>待付款</option>
                                     <option>待确认约会</option>
                                     <option>成功约会</option>
                                     </select>
                                 </li>
                                 <li class="col1">
                                    <label> 机构名称：</label>&nbsp;&nbsp;<input type="text" size="15" value="">
                                 </li>
                                 <li class="col2">
                                    <label> 女孩名字：</label>&nbsp;&nbsp;<input type="text" size="15" value="">
                                 </li>
                            </ul>
                            <div class="clear"></div>
                        </div>
                    </div>
                    <div class="choice">
                        <dt class="choice_hover"><a href="#">全部</a></dt>
                        <dt><a href="#">成功约会</a></dt>
                        <dt><a href="#">成功恋爱</a></dt>
                        <dt><a href="#">成功结婚</a></dt>
                    </div>
                    <div class="date">
                    	<ul>
                    	  <li>
                    	    <div class="title"><b>支付红包：<strong class="red">￥500</strong></b><span ></span><a href="#">520婚恋机构 红娘</a></div>
                    	    <div class="contain">
                    	      <div class="confirm"> 
                              	<span class="confirm_btn1"> <a href="succeed_detail.html">查看详情</a> </span> 
                                <span class="confirm_btn3"> <a href="succeed_from.html">填写/修改详情</a> </span> </div>
                    	      <dd> <img src="images/p1.jpg" width="220" height="220" />
                    	        <center>
                    	          黄先生
                  	          </center>
                  	        </dd>
                    	      <dd> <img src="images/p2.jpg" width="220" height="220" />
                    	        <center>
                    	          李小姐
                  	          </center>
                  	        </dd>
                    	      <dt>
                    	        <div class="succeed_con">
                    	          <div class="l1">2013-09-10 </div>
                    	          <div class="l2">花园酒店</div>
                    	          <div class="l1"><b class="bule">约会成功</b></div>
                  	          </div>
                  	        </dt>
                  	      </div>
                  	    </li>
                        <li>
                    	    <div class="title"><b>支付红包：<strong class="red">￥500</strong></b><span ></span><a href="#">520婚恋机构 红娘</a></div>
                    	    <div class="contain">
                    	      <div class="confirm">
                               <span class="confirm_btn1"> <a href="#">查看详情</a> </span> 
                              	<span class="confirm_btn3"> <a href="succeed_from.html">填写/修改详情</a> </span> </div>
                    	      <dd> <img src="images/p1.jpg" width="220" height="220" />
                    	        <center>
                    	          黄先生
                  	          </center>
                  	        </dd>
                    	      <dd> <img src="images/p2.jpg" width="220" height="220" />
                    	        <center>
                    	          李小姐
                  	          </center>
                  	        </dd>
                    	      <dt>
                    	        <div class="succeed_con">
                    	          <div class="l1">2013-09-10 </div>
                    	          <div class="l2">花园酒店</div>
                    	          <div class="l1"><b class="bule">约会成功</b></div>
                  	          </div>
                  	        </dt>
                  	      </div>
                  	    </li>
                  	  </ul>
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

