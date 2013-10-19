<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="cn.net.mpay.util.Constants"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String avatarPath=Constants.loadConfig("AvatarImagePath");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/uploadifive.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/dialog/zebra_dialog.css" rel="stylesheet"  type="text/css" />
<script type='text/javascript' src="<%=basePath%>/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.uploadifive.min.js"></script>
<script type='text/javascript' src="<%=basePath%>/js/zebra_dialog.js"></script> 
<script type='text/javascript' src="<%=basePath%>/js/jquery.foucs.js" type="text/javascript"></script>
<script type='text/javascript' src="<%=basePath%>/js/jquery.wookmark.js" type="text/javascript"></script>
<script type='text/javascript' src="<%=basePath%>/js/area.js" type="text/javascript"></script>
</head>
  
  <body>
  <div class="top">
    	<div class="head">
        	<div class="w980">
            	<a href="index.jsp"><div class="logo fl"></div></a>
                <div class="fr">
                	<div class="top_nav white tr">
                    	<span>欢迎来名仕之约！请<a href="login.jsp">登录</a> / <a href="register.jsp">免费注册</a></span>    
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
                                  <select name="sex">
                                      <option>名仕</option>
                                      <option>女士</option>
                                    </select>          
                          <span> 年龄
                          <select name="minAge">
                             <option value="18">18以下</option>
                             <option value="19" selected="selected">19</option>
                             <option value="20">20</option>
                             <option value="21">21</option>
                             <option value="22">22</option>
                             <option value="23">23</option>
                             <option value="24">24</option>
                             <option value="25">25</option>
                             <option value="26">26</option>
                             <option value="27">27</option>
                             <option value="28">28</option>
                             <option value="29">29</option>
                             <option value="30">30以上</option>
                          </select>      
                          至  <select name ="maxAge">
                             <option value="18">18以下</option>
                             <option value="19" >19</option>
                             <option value="20">20</option>
                             <option value="21">21</option>
                             <option value="22">22</option>
                             <option value="23" selected="selected">23</option>
                             <option value="24">24</option>
                             <option value="25">25</option>
                             <option value="26">26</option>
                             <option value="27">27</option>
                             <option value="28">28</option>
                             <option value="29">29</option>
                             <option value="30">30以上</option>
                          </select>      </span>    
                          <span> 地区 
                          <select id="s_province" name="s_province"></select> 
						  <select id="s_city" name="s_city"></select>
						  <select id="s_county" name="s_county"></select> 
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
                <li><a href="indexRec.pa?pageNum=1">红娘推荐</a></li>
                <li class="line"></li>
                <li><a href="#">名仕服务</a></li>
                <li class="line"></li>
                <li><a href="active.html">活动介绍</a></li>
                <li class="line"></li>
                <li><a href="www.mingshizhiyue.com">关于我们</a></li>
            </ul>
        </div>
    </div>
    <script language="javascript">
$(function(){
	_init_area();
	});

var Gid  = document.getElementById ;
var showArea = function(){
	Gid('show').innerHTML = "<h3>" + Gid('s_province').value + " - " + 
							Gid('s_city').value + " - " + 
							Gid('s_county').value + "</h3>"
}
Gid('s_county').setAttribute('onchange','showArea()');
</script>
  </body>
</html>
