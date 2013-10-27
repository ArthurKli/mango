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
<%--<script type="text/javascript" src="js/ImageCropper.js"></script>
<script type="text/javascript" src="js/diyImage.js"></script>
--%><script type="text/javascript">



</script>
	</head>

	<body class="center">
		<div class=" w980">
			<div class="wrapper">
				<%@include file="centerLeft.jsp" %>
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
								<span class="confirm_btn2"><a href="#">添加头像</a></span>
							<%--<iframe src="cut.jsp" width="550px" height="400px"></iframe>	
								--%>
								
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

	$(".confirm_btn2").click(function(){
		new $.Zebra_Dialog({
		    source: {'iframe': {
		        'src':  'cut.jsp',
		        'height': 400
		    }},
		    width: 700,
		    title:  '上传个人头像',
		    buttons:  [
		                    {caption: 'Yes', callback: function() {saveImage();}},
		                    {caption: 'Cancel', callback: function() { }}
		                ]
		});


		});
	});
</script>
<%@include file="foot.jsp" %>
	</body>
</html>

