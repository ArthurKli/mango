<%@ page language="java"  pageEncoding="UTF-8"%>
<%@include file="head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
</head>

<body class="lover">
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
<%@include file="foot.jsp" %>
</body>
</html>