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
            <div class="recommend_option">
                <ul>
                    <li><a href="#" class="recom_hover" style="color:#fff">新推荐 </a></li>
                    <li><a href="#">官方推荐</a><div class="hg"></div></li>
                    <li><a href="#">过往推荐</a></li>
                </ul>
            </div>
            <div class="recommend_list">
                   <ul>
                       <li>
                       	<div class="contain">
                           <dd><img src="images/p1.jpg" width="220" height="220" /></dd>
                           <div class="fl">
                           		<p><i><a href="number_recommend.html">更多>></a></i><strong class="bule">黄先生</strong> <span>征婚红包<b class="red">￥500</b></span></p>
                                <div class="lit_pic">
                                	<dt><a href="recommended_detail_g.html"><img src="images/p2.jpg" width="220" height="220" /></a></dt>
                                    <dt><a href="recommended_detail_g.html"><img src="images/p2.jpg" width="220" height="220" /></a></dt>
                                    <dt><a href="recommended_detail_g.html"><img src="images/p2.jpg" width="220" height="220" /></a></dt>
                                    <dt><a href="recommended_detail_g.html"><img src="images/p2.jpg" width="220" height="220" /></a></dt>
                                    <dt><a href="recommended_detail_g.html"><img src="images/p2.jpg" width="220" height="220" /></a></dt>
                                    <dt><a href="recommended_detail_g.html"><img src="images/p2.jpg" width="220" height="220" /></a></dt>
                                </div>
                           </div>
                        </div> 
                       </li>
                    </ul>     
                    <div class="page">
                      	<span class="fy"><a href="#">上一页</a></span>
                        <span class="ym"><a href="#">1</a></span>
                        <span class="fy" ><a href="#">下一页</a></span>
                     </div>                   
            </div>
        	<div class="clear"></div>
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

