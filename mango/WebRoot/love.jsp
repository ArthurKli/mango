<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.net.mpay.util.Constants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String avatarPath=Constants.getString("AvatarImagePath");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
 <title>名仕之约</title>

    <style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
body {font-family:"helvetica neue",arial,sans-serif;font-size:12px;color:#211922;}
.btnimg{cursor:pointer;}
#ScrollToTop{
	position:fixed;_position:absolute;z-index:4;right:30px;bottom:10px; display:block; background:url(images/top_arrow.png) top left no-repeat; width:50px; height:50px; margin:30px; z-index:1
}
#ScrollToTop:hover{ background:url(images/top_arrow.png) top right no-repeat;}
#ScrollToTop.Offscreen{
	bottom:-100px;
	-moz-transition-duration:250ms;
	-webkit-transition-duration:250ms;
}
</style>
<script type="text/javascript">
var ScrollToTop=ScrollToTop||{
	setup:function(){
		
		var a=$(window).height()/2;
		
		$(window).scroll(function(){
			(window.innerWidth?window.pageYOffset:document.documentElement.scrollTop)>=a?$("#ScrollToTop").removeClass("Offscreen"):$("#ScrollToTop").addClass("Offscreen")
		});
		$("#ScrollToTop").click(function(){
			$("html, body").animate({scrollTop:"0px"},400);
			return false
		})
	}
};
</script>
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body class="lover">
    
    <div class="top">
    	<div class="head">
        	<div class="w980">
            	<a href="index.html"><div class="logo fl"></div></a>
                <div class="fr">
                	<div class="top_nav white tr">
                    	<span>欢迎来名仕之约！请<a href="login.html">登录</a> / <a href="register.html">免费注册</a></span>    
                        <span><a href="#">个人中心</a></span>  
                        <span><a href="#">我的约会<strong>0</strong></a></span>  
                        <span><a href="#">待付款<strong>0</strong></a></span>  
                        <span><a href="#">投诉<strong>0</strong></a></span>  
                        <span><a href="#">消息<strong>0</strong></a></span>    
                        <span>服务热线：<strong>4008-652-520</strong></span>  
					</div>
                    <div class="top_search">
                    	<div class="condition fl">
                            我要找                             
                                  <select name="">
                                      <option>名仕</option>
                                      <option>女士</option>
                                    </select>          
                          <span> 年龄<select name="">
                             <option>18</option>
                          </select>      
                          至  <select name ="">
                              <option>20</option>
                          </select>      </span>    
                          <span> 地区
                            	 
                                    <select name="">
                                      <option>广东</option>
                                    </select>
                                    <select name="">
                                      <option>广州</option>
                                      <option>西藏自治区</option>
                            </select>
                                </span>
                      </div>
                        <div class="fl"><a href="#"><span class="top_search_btn"></span></a></div>
                        <div class="fl"><a href="#"><span class="top_search_btn1"></span></a></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="nav">
        	<ul class="w980">
            	<li class="hover"><a href="index.html">首页</a></li>
                <li class="line"></li>
                <li><a href="center.html">我的名仕</a></li>
                <li class="line"></li>
                <li><a href="marriage.html">征婚大厅</a></li>
                <li class="line"></li>
                <li><a href="lover.html">红娘推荐</a></li>
                <li class="line"></li>
                <li><a href="#">名仕服务</a></li>
                <li class="line"></li>
                <li><a href="active.html">活动介绍</a></li>
                <li class="line"></li>
                <li><a href="www.mingshizhiyue.com">关于我们</a></li>
            </ul>
        </div>
    </div>
    <c:set var="mbList" value="${members}"/>
    	<div class=" w980">
    		<div id="main" role="main" class="fl" style="width:695px; position:relative">
            <h2>悬赏推荐</h2>
				<ul id="tiles">
					<li>
						<img src="images/pic02.jpg" style="margin-bottom: 20px">
					</li>
					
					<c:forEach items="${members}" var="mb" >
					  <c:set value="${mb.avatar}" var="img"  scope="request"/>
						<li>
						<img src="<%=avatarPath %>${mb.avatar }">
						<div class="describe">
							<h3>${mb.member_desc }</h3>
							<center>
								<a href="#"><span class="recommend_btn">申请约会</span>
								</a>
							</center>
							<center>
								<b>${mb.nick_name }</b>
								<br />
								${mb.age } ${mb.career } ${mb.resident_place }
							</center>
						</div>
					</li>
					</c:forEach>

					<li>
						<img src="images/pic03.jpg" style="margin-bottom: 20px">
					</li>
					
				</ul>
			</div>        
   	  <div class="success fr">
      	  <h2>成功案例</h2>
          <ul>
          	<a href="#">
           	  <li>
               	  <div class="success_pic">
                      <div class="fl"><img src="images/p1.jpg" /><center>黄先生</center></div>
                      <div class="fr"><img src="images/p2.jpg" /><center>李小姐</center></div>
                  </div>
                  <div>发布者：520婚恋机构</div>
				  <div>1314婚恋机构 获取约会红包<b class="red"> ￥2500</b></div>
           	  </li>
             </a>
             <a href="#">
           	  <li>
               	  <div class="success_pic">
                      <div class="fl"><img src="images/p1.jpg" /><center>黄先生</center></div>
                      <div class="fr"><img src="images/p2.jpg" /><center>李小姐</center></div>
                  </div>
                  <div>发布者：520婚恋机构</div>
				  <div>1314婚恋机构 获取约会红包<b class="red"> ￥2500</b></div>
           	  </li>
             </a>
             <a href="#">
           	  <li>
               	  <div class="success_pic">
                      <div class="fl"><img src="images/p1.jpg" /><center>黄先生</center></div>
                      <div class="fr"><img src="images/p2.jpg" /><center>李小姐</center></div>
                  </div>
                  <div>发布者：520婚恋机构</div>
				  <div>1314婚恋机构 获取约会红包<b class="red"> ￥2500</b></div>
           	  </li>
             </a>
             <a href="#">
           	  <li>
               	  <div class="success_pic">
                      <div class="fl"><img src="images/p1.jpg" /><center>黄先生</center></div>
                      <div class="fr"><img src="images/p2.jpg" /><center>李小姐</center></div>
                  </div>
                  <div>发布者：520婚恋机构</div>
				  <div>1314婚恋机构 获取约会红包<b class="red"> ￥2500</b></div>
           	  </li>
             </a>
             <a href="#">
           	  <li>
               	  <div class="success_pic">
                      <div class="fl"><img src="images/p1.jpg" /><center>黄先生</center></div>
                      <div class="fr"><img src="images/p2.jpg" /><center>李小姐</center></div>
                  </div>
                  <div>发布者：520婚恋机构</div>
				  <div>1314婚恋机构 获取约会红包<b class="red"> ￥2500</b></div>
           	  </li>
             </a>
          </ul>
          <a id="ScrollToTop" class="btnimg Offscreen" type="button"></a >

        </div>
    </div>
    
    <div class="clear"></div>
    <div class="footer">
    	<div class="w980">
            <div class="fl">
                <div>
                	<a href="#">关于我们</a>   <a href="#">帮助中心</a>   <a href="#">诚聘英才</a>  <a href="#"> 联系我们</a>   <a href="#">商务合作</a>    <a href="#">版权说明</a> 
                </div>
                <div>Copyright © 2011-2015 520cecs.com All RightReserved  粤ICP备12009586号</div>
            </div>
            <div class="subfooter fr">
            	<a href="#" class="sub1"></a>
                <a href="#" class="sub2"></a>
                <a href="#" class="sub3"></a>
                <a href="#" class="sub4"></a>
            </div>
        </div>
    </div>

			  <script src="js/jquery-1.7.1.min.js"></script>
              <script src="js/jquery.wookmark.js"></script>
              <script type="text/javascript">
                $(document).ready(new function() {
                  // Prepare layout options.
                  var options = {
                    autoResize: true, // This will auto-update the layout when the browser window is resized.
                    container: $('#main'), // Optional, used for some extra CSS styling
                    offset: 2, // Optional, the distance between grid items
                    itemWidth: 230 // Optional, the width of a grid item
                  };
                  
                  // Get a reference to your grid items.
                  var handler = $('#tiles li');
                  
                  // Call the layout function.
                  handler.wookmark(options);
                  
                  // Capture clicks on grid items.
                  handler.click(function(){
                    // Randomize the height of the clicked item.
                    $(this).css('height');
                    
                    // Update the layout.
                    handler.wookmark();
                  });
                });
              </script>
              <script type="text/javascript">
$(document).ready(function(){
	ScrollToTop.setup();
});
</script>
</body>
</html>