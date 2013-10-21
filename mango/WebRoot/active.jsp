<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>名仕之约</title>
</head>
<body class="news">

    <div class="news_banner">
    	<div class="w980">
        	<div class="slidebox1">
                <ul class="slidepic1">
                    <li><a href="#"><img src="images/top_banner.jpg" ></a></li>
                    <li><a href="#"><img src="images/top_banner.jpg" ></a></li>
                    <li><a href="#"><img src="images/top_banner.jpg" ></a></li>
                </ul>
                <div class="slidebtn1">
                    <ul>
                        <li class="current">1</li>
                        <li>2</li>
                        <li>3</li>				
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
$(function(){
	
	// 图片上下翻滚
	var len = $('.slidebtn1>ul>li').length;
	var index = 0;
	var autoplay;
	$('.slidebtn1 li').mouseover(function(){
		index = $('.slidebtn1 li').index(this);
		showImg(index);
	}).eq(0).mouseover();
	
	$('.slidebox1').hover(function(){
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
		var picheight = $('.slidebox1').height();
		$('.slidepic1').stop(true,false).animate({top:-picheight*index},600)
		$('.slidebtn1 li').removeClass('current').eq(index).addClass('current');
	};
	

	
});


</script>
    <div class="w980">
    	<div class="option_news">
        	<ul>
            	<li><a href="#" class="news_hover" style="color:#fff;">名仕活动</a></li>
                <li><a href="#">经典回顾</a></li>
            </ul>
        </div>
        <div class="news_list">
    	<ul>
   	    <li>
            	<div class="news_img"><img src="images/n1.jpg" width="500" height="350" /></div>
                <div class="news_infor">
                	<h3>[爱在云端，云派对]</h3>
                    <p>活动时间：	2013年10月</p>
                  <p>活动地点：	待定</p>
                  <p>截止报名：	2013-09-30</p>
                  <p>活动人数：	严格限制人数</p>
                  <p>联系方式：	4008-652-520</p>
                    <div class="bm_r"><b class="red">724</b>人报名参加</div>
                    <div class="news_line"></div>
      <center>
                    	<span class="news_detail_btn"><a href="#">活动详情</a></span>
                        <span class="news_bm_btn"><a href="#">参加活动</a></span>
                    </center>
                </div>
          </li>
        </ul>
    </div>
    </div>
    
    <div class="clear"></div>
    <%@include file="foot.jsp" %>

</body>
</html>

