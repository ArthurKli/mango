<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="head.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>名仕之约222</title>
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
	//$.Zebra_Dialog("你妹");
});
</script>
    
    
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
                	<input name="" type="button" class="quick_reg_btn" value="快速注册" onclick="location.href='register.jsp'" />
                    <center>
                		我是会员，立刻<a href="login.jsp">登录</a>
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

<%@include file="foot.jsp" %>
</body>
</html>
