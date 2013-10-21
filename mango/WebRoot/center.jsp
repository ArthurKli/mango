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
                	<h1>会员列表</h1>
                    <div class="number"> 
                    	<ul>
                        	<li>
                            	<a href="#">
                               		<img src="images/p1.jpg" width="220" height="220" /> 
                                    <center>
                                    	<span class="bule">李先生</span><br />新推荐：10
                                    </center>
                                </a>
                            </li>
                            <li>
                            	<a href="#">
                               		<img src="images/p1.jpg" width="220" height="220" /> 
                                    <center>
                                    	<span class="bule">李先生</span><br />新推荐：10
                                    </center>
                                </a>
                            </li>
                            <li>
                            	<a href="#">
                               		<img src="images/p1.jpg" width="220" height="220" /> 
                                    <center>
                                    	<span class="bule">李先生</span><br />新推荐：10
                                    </center>
                                </a>
                            </li>
                            <li>
                            	<a href="#">
                               		<img src="images/p1.jpg" width="220" height="220" /> 
                                    <center>
                                    	<span class="bule">李先生</span><br />新推荐：10
                                    </center>
                                </a>
                            </li>
                            <li>
                            	<a href="#">
                               		<img src="images/p1.jpg" width="220" height="220" /> 
                                    <center>
                                    	<span class="bule">李先生</span><br />新推荐：10
                                    </center>
                                </a>
                            </li>
                            <li>
                            	<a href="#">
                               		<img src="images/p2.jpg" width="220" height="220" /> 
                                    <center>
                                    	<span class="bule">李小姐</span><br />
                                    	新申请：10
                                    </center>
                                </a>
                            </li>
                            <li>
                            	<a href="#">
                               		<img src="images/p2.jpg" width="220" height="220" /> 
                                    <center>
                                    	<span class="bule">李小姐</span><br />
                                    	新申请：10
                                    </center>
                                </a>
                            </li>
                            <li>
                            	<a href="#">
                               		<img src="images/p2.jpg" width="220" height="220" /> 
                                    <center>
                                    	<span class="bule">李小姐</span><br />
                                    	新申请：10
                                    </center>
                                </a>
                            </li>
                            <li>
                            	<a href="#">
                               		<img src="images/p2.jpg" width="220" height="220" /> 
                                    <center>
                                    	<span class="bule">李小姐</span><br />
                                    	新申请：10
                                    </center>
                                </a>
                            </li>
                            <li>
                            	<a href="#">
                               		<img src="images/p2.jpg" width="220" height="220" /> 
                                    <center>
                                    	<span class="bule">李小姐</span><br />
                                    	新申请：10
                                    </center>
                                </a>
                            </li>
                            
                        </ul>
                    </div>
                </div>
            <div class="clear"></div>
                <div class="center_item">
                	<h1>约会列表</h1>
                    <div class="date">
                    	<h2>待付款>></h2>
                    	<ul>
                        	<li>
                            	<div class="title"><span ></span><a href="#">520婚恋机构 红娘</a></div>
                          		<div class="contain">
                         		 <div class="confirm">
                                    	<span class="confirm_btn1">
                                        	<a href="#">确认约会</a>
                                        </span>
                                        <span class="confirm_btn2">
                                        	<a href="#">取消约会</a>
                                        </span>
                                    </div>
                                	<dd><img src="images/p1.jpg" width="220" height="220" /></dd>
                                    <dd><img src="images/p2.jpg" width="220" height="220" /></dd>
                                    <dt>
                                    	<div class="contain_tit"><strong class="bule">黄一</strong> &  <strong class="bule">吴小姐</strong> <b>待付红包：<i class="red">￥500</i></b></div>
                                      <p>2013-09-10 12：00  <span class="bule">申请约会</span>  </p>
                                        <p>2013-09-10 12：00  <span class="bule">通过申请</span>  </p>
                                    </dt>
                              		
                              </div> 
                          </li>
                        </ul>
                        
                        <h2>待确认>></h2>
                    	<ul>
                        	<li>
                            	<div class="title"><span ></span><a href="#">520婚恋机构</a></div>
                          		<div class="contain">
                         		 <div class="confirm">
                                    	<span class="confirm_btn1">
                                        	<a href="#">通过申请</a>
                                        </span>
                                        <span class="confirm_btn2">
                                        	<a href="#">取消约会</a>
                                        </span>
                                    </div>
                                	<dd><img src="images/p1.jpg" width="220" height="220" /></dd>
                                    <dd><img src="images/p2.jpg" width="220" height="220" /></dd>
                                    <dt>
                                    	<div class="contain_tit"><strong class="bule">黄一</strong> &  <strong class="bule">吴小姐</strong> <b>待付红包：<i class="red">￥500</i></b></div>
                                      <p>2013-09-10 12：00  <span class="bule">申请约会</span>  </p>
                                    </dt>
                              </div> 
                          </li>
                        </ul>
                        
                        <h2>待收款>></h2>
                    	<ul>
                        	<li>
                            	<div class="title"><span ></span><a href="#">520婚恋机构</a></div>
                          		<div class="contain">
                         		 <div class="confirm">
                                    	<span class="confirm_btn1">
                                        	<a href="#">催收款</a>
                                        </span>
                                        <span class="confirm_btn2">
                                        	<a href="#">取消约会</a>
                                        </span>
                                    </div>
                                	<dd><img src="images/p1.jpg" width="220" height="220" /></dd>
                                    <dd><img src="images/p2.jpg" width="220" height="220" /></dd>
                                    <dt>
                                    	<div class="contain_tit"><strong class="bule">黄一</strong> &  <strong class="bule">吴小姐</strong> <b>待收红包：<i class="red">￥500</i></b></div>
                                      <p>2013-09-10 12：00  <span class="bule">申请约会</span>  </p>
                                      <p>2013-09-10 12：00  <span class="bule">通过申请</span>  </p>
                                    </dt>
                              		
                              </div> 
                          </li>
                        </ul>
                    </div>
                </div>
                
                <div class="center_item">
                	<h1>投诉列表</h1>
                    <div class="date">
                    	<h2>被投诉>></h2>
                    	<ul>
                        	<li>
                            	<div class="title"><span ></span><a href="#">520婚恋机构</a></div>
                          		<div class="contain">
                         		 <div class="confirm">
                                    	<span class="confirm_btn5">
                                        	<a href="#">投诉详情</a>
                                        </span>
                                        <span class="confirm_btn3">
                                        	<a href="#">我要申诉</a>
                                        </span>
                                    </div>
                                	<dd><img src="images/p1.jpg" width="220" height="220" /></dd>
                                    <dd><img src="images/p2.jpg" width="220" height="220" /></dd>
                                    <dt>
                                    	<div class="contain_tit"><strong class="bule">黄一</strong> &  <strong class="bule">吴小姐</strong> <b>待收红包：<i class="red">￥500</i></b></div>
                                      <p>2013-09-10 12：00  <span class="bule">申请约会</span>  </p>
                                        <p>2013-09-10 12：00  <span class="bule">通过申请</span>  </p>
                                    </dt>
                              		
                              </div> 
                          </li>
                        </ul>
                        
                        <h2>我的投诉>></h2>
                    	<ul>
                        	<li>
                            	<div class="title"><span ></span><a href="#">520婚恋机构</a></div>
                          		<div class="contain">
                         		 <div class="confirm">
                                    	<span class="confirm_btn5">
                                        	<a href="#">投诉结果</a>
                                        </span>
                                        <span class="confirm_btn2">
                                        	<a href="#">我要撤诉</a>
                                        </span>
                                    </div>
                                	<dd><img src="images/p1.jpg" width="220" height="220" /></dd>
                                    <dd><img src="images/p2.jpg" width="220" height="220" /></dd>
                                    <dt>
                                    	<div class="contain_tit"><strong class="bule">黄一</strong> &  <strong class="bule">吴小姐</strong> <b>待付红包：<i class="red">￥500</i></b></div>
                                      <p>2013-09-10 12：00  <span class="bule">申请约会</span>  </p>
                                      <p>2013-09-10 12：00  <span class="bule">通过申请</span>  </p>
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

