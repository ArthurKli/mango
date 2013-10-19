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

	<body class="center">
		<div class=" w980">
			<div class="wrapper">
				<div class="center_menu" id="left">
					<div class="user_pic">
						<img src="images/p1.jpg" width="220" height="220" id="avatarImage"/>
					</div>
					<div>
						<div class="menu">
							<a href="center.html" class="menu_hover fistmenu">我的名仕/我的商户</a>
						</div>
						<div class="menu">
							<a href="datum.html" class="fistmenu">基本资料</a>
						</div>
						<div class="menu">
							<a href="javascript:void(0);" class="fistmenu"
								onclick="showHide(this,'items0');">我的会员</a>
							<ul id="items0">
								<li>
									<a href="marriage_list.html">征婚列表</a>
								</li>
								<li>
									<a href="recommend_list.html">推荐列表</a>
								</li>
							</ul>
						</div>
						<div class="menu">
							<a href="javascript:void(0);" class="fistmenu"
								onclick="showHide(this,'items1');">红娘推荐</a>
							<ul id="items1">
								<li>
									<a href="new_recommend.html">新推荐<b>5</b>
									</a>
								</li>
								<li>
									<a href="#">官网推荐</a>
								</li>
								<li>
									<a href="#">过往推荐</a>
								</li>
							</ul>
						</div>
						<div class="menu">
							<a href="data_list.html" class="fistmenu"
								onclick="showHide(this,'items2');">约会列表</a>
							<ul id="items2">
								<li>
									<a href="#">待支付</a>
								</li>
								<li>
									<a href="#">已支付</a>
								</li>
								<li>
									<a href="#">待确认</a>
								</li>
								<li>
									<a href="#">待收款</a>
								</li>
								<li>
									<a href="#">已收款</a>
								</li>
							</ul>
						</div>
						<div class="menu">
							<a href="succeed.html " class="fistmenu">成功案例</a>
						</div>
						<div class="menu">
							<a href="complain.html" class="fistmenu"
								onclick="showHide(this,'items3');">投诉列表</a>
							<ul id="items3">
								<li>
									<a href="#">被投诉<b>5</b>
									</a>
								</li>
								<li>
									<a href="#">投诉</a>
								</li>
							</ul>
						</div>
						<div class="menu">
							<a href="mailbox.html" class="fistmenu">信箱<b>1</b>
							</a>
						</div>
						<div class="menu">
							<a href="collection.html" class="fistmenu">我的收藏</a>
						</div>
						<div class="menu">
							<a href="javascript:void(0);" class="fistmenu"
								onclick="showHide(this,'items4');">红包帐户</a>
							<ul id="items4">
								<li>
									<a href="account_details.html">明细</a>
								</li>
								<li>
									<a href="balance.html">余额</a>
								</li>
								<li>
									<a href="recharge.html">充值</a>
								</li>
								<li>
									<a href="#">联系我们</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			<form id="editMember" method="post" action="editMember.pa">	
				<div class="center_contain" id="right">
					<div class="center_item">
						<h1>
							基本信息 <span style="color: red">
							<c:if test="${msg!=null}">
							${msg}
							</c:if>
							</span>
						</h1>
						<div class="info_jg">
							<div class="list">
								<div class="list_tit">
									当前头像：
								</div>
								<div class="fl">
									<%--<div id="container">
		<a id="selectBtn" href="javascript:void(0);" onclick="document.getElementById('input').click();"></a>
		<a id="saveBtn" href="javascript:void(0);" onclick="saveImage();"></a>
		<input type="file" id="input" size="10" style="visibility:hidden;" onchange="selectImage(this.files)" />
		
		<div id="wrapper">
			<canvas id="cropper"></canvas>
			<a id="rotateLeftBtn" href="javascript:void(0);" onclick="rotateImage(event);">向左旋转</a>
			<a id="rotateRightBtn" href="javascript:void(0);" onclick="rotateImage(event);">向右旋转</a>


		</div>
		
	</div>
								--%>
								
							<iframe src="cut.jsp" width="550px" height="400px"></iframe>	
								
								
								
								
								</div>

							</div>
							<div class="list">
								<div class="list_tit">
									<span class="red">*</span> 名称：
								</div>
								<div class="list_con">
									<input name="true_name" id="true_name" type="text" 
									value="<c:if test="${true_name!=null}">${true_name}</c:if>"/>
								</div>
							</div>
							<div class="list">
								<div class="list_tit">
									负责人：
								</div>
								<div class="list_con">
									<input name="org_id" id="org_id" type="text" 
									value="<c:if test="${org_id!=null}">${org_id}</c:if>"/>
								</div>
							</div>
							<div class="list">
								<div class="list_tit">
									证件类型：
								</div>
								<div class="list_con">
									<select name="card_type" id="card_type">
										<option  value="1" <c:if test="${card_type == 1}">selected="selected"</c:if> >
											身份证
										</option>
										<option value="2" <c:if test="${card_type == 2}">selected="selected"</c:if> >
											营业执照
										</option>
									</select>
								</div>
							</div>
							<div class="list">
								<div class="list_tit">
									证件号码：
								</div>
								<div class="list_con">
									<input name="card_id" id="card_id" type="text" 
									value="<c:if test="${card_id!=null}">${card_id}</c:if>"/>
								</div>
							</div><%--
							<div class="list">
								<div class="list_tit">
									上传证件号码：
								</div>
								<div class="list_con">
									<input name="" type="text" />
								</div>
								<div class="fl">
									<span class="confirm_btn2"><a href="#">选择图片</a>
									</span>
								</div>
							</div>
							--%><div class="list">
								<div class="list_tit">
									所在地区：
								</div>
								<div class="list_con">
									<select name="">
										<option>
											广东
										</option>
									</select>
									<select name="">
										<option>
											广州
										</option>
									</select>
								</div>
							</div>
							<div class="list">
								<div class="list_tit">
									通信地址：
								</div>
								<div class="list_con">
									<select name="">
										<option>
											广东
										</option>
									</select>
									<select name="">
										<option>
											广州
										</option>
									</select>
								</div>
							</div>
							<div class="list">
								<div class="list_tit">
									办公室号码：
								</div>
								<div class="list_con">
									<input name="tel_phone" id="tel_phone" type="text" 
									value="<c:if test="${tel_phone!=null}">${tel_phone}</c:if>"/>
								</div>
							</div>
							<div class="list">
								<div class="list_tit">
									<span class="red">*</span>手机号码：
								</div>
								<div class="list_con">
									<input name="mobile" id="mobile" type="text" 
									value="<c:if test="${mobile!=null}">${mobile}</c:if>"/>
								</div>
							</div>
							<div class="list">
								<div class="list_tit">
									<span class="red">*</span> 电子邮箱：
								</div>
								<div class="list_con">
									<input name="email" id="email" type="text" 
									value="<c:if test="${email!=null}">${email}</c:if>"/>
								</div>
							</div>
							<div class="list">
								<div class="list_tit">
									备注：
								</div>
								<div class="list_con">
									<textarea name="member_desc" id="member_desc" cols="" rows="" 
									value="<c:if test="${member_desc!=null}">${member_desc}</c:if>"></textarea>
								</div>
							</div>
							<div class="list">
								<div class="list_tit">
									&nbsp;
								</div>
								<div class="list_con">
									<span class="confirm_btn1"><a href="javascript:void(0);" onclick="saveInfo();">保 存</a>
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				</form>
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
function saveInfo(){
	//do someting check
	$("#editMember").submit();
}
$(function(){
	$.ajax({
		   type: "POST",
		   url: "getAvatar.pa",
		   data: "id=1&location=Boston",
		   success: function(msg){
		   var i=msg;
		     //alert( "Data Saved: "+msg.image);
		     $("#avatarImage").attr("src",msg.image);
		   }
		});
	});
</script>
<%@include file="foot.jsp" %>
	</body>
</html>

