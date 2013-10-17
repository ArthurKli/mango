<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>名仕之约</title>

<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="js/jquery.foucs.js" type="text/javascript"></script>

</head>

<body>
<div class="focusbox">
	<div id="fullbanner">
		<div class="wrappic">
			<ul>
				<li class="plan"><a href="#"><img src="images/guide01.jpg" width="1003" height="100" /></a></li>
				<li class="plan"><a href="#"><img src="images/guide02.jpg" width="1003" height="100" /></a></li>
                
				<li class="plan"><a href="#"><img src="images/guide01.jpg" width="1003" height="100" /></a></li>
				<li class="plan"><a href="#"><img src="images/guide02.jpg" width="1003" height="100" /></a></li>
			</ul>
		</div>
		
		<div class="helper">
			<div class="mask-left"></div>
			<div class="mask-right"></div>
            <a href="javascript:void();" class="prev arrow-left"></a>
			<a href="javascript:void();" class="next arrow-right"></a>
		</div>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$.foucs({
		direction: 'right'
	});
});
</script>
    
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
    <div class="middle w980">
    	<div class="banner">
        	<div class="xibao fl">
            	<div class="slidebox">
	<ul class="slidepic">
		<li><a href="#"><img src="images/banner01.jpg" ></a></li>
		<li><a href="#"><img src="images/banner01.jpg" ></a></li>
		<li><a href="#"><img src="images/banner01.jpg" ></a></li>
		<li><a href="#"><img src="images/banner01.jpg" ></a></li>
	</ul>
	<div class="slidebtn">
		<ul>
			<li class="current">1</li>
			<li>2</li>
			<li>3</li>		
			<li>4</li>				
		</ul>
	</div>
</div><!--slidebox end-->
<script type="text/javascript">
$(function(){
	
	// 图片上下翻滚
	var len = $('.slidebtn>ul>li').length;
	var index = 0;
	var autoplay;
	$('.slidebtn li').mouseover(function(){
		index = $('.slidebtn li').index(this);
		showImg(index);
	}).eq(0).mouseover();
	
	$('.slidebox').hover(function(){
		clearInterval(autoplay);
	},function(){
		autoplay = setInterval(function(){
			showImg(index)
			index++;
			if(index==len){
				index=0;
			}
		},3000);
	}).trigger('mouseleave');

	function showImg(index){
		var picheight = $('.slidebox').height();
		$('.slidepic').stop(true,false).animate({top:-picheight*index},600)
		$('.slidebtn li').removeClass('current').eq(index).addClass('current');
	};
	

	
});


</script>
            </div>
            <div class="quick_reg fr">
            	<div class="register">
                	<input name="" type="button" class="quick_reg_btn" value="快速注册" onclick="location.href='register.html'" />
                    <center>
                		我是会员，立刻<a href="login.html">登录</a>
                	</center>
                </div>
            	
            </div>
        </div>
        
        <div class="recommend">
        	<h1><span><a href="#">更多悬赏&gt;&gt;</a></span>悬赏推荐</h1>
            <ul>
            	<li>
                	<div><img src="images/p1.jpg" width="220" height="220" /></div>
                    推荐约会对象，领取红包<b class="red">￥500</b>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                
                <li>
                	<div><img src="images/p1.jpg" width="220" height="220" /></div>
                    推荐约会对象，领取红包<b class="red">￥500</b>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                
                <li>
                	<div><img src="images/p1.jpg" width="220" height="220" /></div>
                    推荐约会对象，领取红包<b class="red">￥500</b>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                
                <li>
                	<div><img src="images/p1.jpg" width="220" height="220" /></div>
                    推荐约会对象，领取红包<b class="red">￥500</b>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                
                <li>
                	<div><img src="images/p1.jpg" width="220" height="220" /></div>
                    推荐约会对象，领取红包<b class="red">￥500</b>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                
                <li>
                	<div><img src="images/p1.jpg" width="220" height="220" /></div>
                    推荐约会对象，领取红包<b class="red">￥500</b>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                
                <li>
                	<div><img src="images/p1.jpg" width="220" height="220" /></div>
                    推荐约会对象，领取红包<b class="red">￥500</b>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                
                <li>
                	<div><img src="images/p1.jpg" width="220" height="220" /></div>
                    推荐约会对象，领取红包<b class="red">￥500</b>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
          </ul>    
      </div>
      
      <div class="recommend">
        	<h1><span><a href="#">更多推荐&gt;&gt;</a></span>红娘推荐</h1>
            <ul>
            	<li>
                	<div><img src="images/p2.jpg" width="220" height="220" /></div>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                <li>
                	<div><img src="images/p2.jpg" width="220" height="220" /></div>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                <li>
                	<div><img src="images/p2.jpg" width="220" height="220" /></div>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                <li>
                	<div><img src="images/p2.jpg" width="220" height="220" /></div>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                <li>
                	<div><img src="images/p2.jpg" width="220" height="220" /></div>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                <li>
                	<div><img src="images/p2.jpg" width="220" height="220" /></div>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                <li>
                	<div><img src="images/p2.jpg" width="220" height="220" /></div>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
                <li>
                	<div><img src="images/p2.jpg" width="220" height="220" /></div>
                    <dt><a href="#">预约见面约</a></dt>
              	</li>
            </ul>
        </div>
        
        
        <div class="mb20">
       	  <div class="mingshi_service fl">
            	<h1><span><a href="#">更多服务&gt;&gt;</a></span>名仕服务</h1>
            	<div><img src="images/service.jpg" width="550" height="260" /></div>
            </div>
            
      		<div class="fl">
            	<h1><span><a href="#">更多活动&gt;&gt;</a></span>最新活动</h1>
                <div><img src="images/active.jpg" width="400" height="260" /></div>
            </div>
      </div>
    </div>
    
    <div class="clear"></div>
    <div class="mb30">&nbsp;</div>
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

</body>
</html>
