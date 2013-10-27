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
            	<h3>520婚恋机构<span class="red">已认证</span><span>待付款<b class="red">(1)</b></span>  <span>我的信箱<b class="red">(1)</b></span></h3>
                <div class="grade">
                    <div class="fl"><img src="images/vip_grade.gif" width="24" height="17" alt="会员等级为0，投诉0" /></div>
                    <div class="vip"><span><i>VO</i> 会员</span></div>
                </div>
             </div>
             
                <div class="center_item">
                	<h1>添加会员</h1>
                    <div class="info_jg">
                         <div class="list">
                            <div class="list_tit">添加头像：</div>
                            <div class="fl"><span class="confirm_btn2"><a href="#">添加头像</a></span></div>
                         </div>
                         <div class="list">
                            <div class="list_tit"><span class="red">*</span>真实姓名：</div>
                            <div class="list_con"><input name="" type="text" /></div>
                         </div>
                         <div class="list">
                            <div class="list_tit">上传身份证：</div>
                            <div class="list_con"><input name="" type="text" /></div>
                   			<div class="fl"><span class="confirm_btn2"><a href="#">选择图片</a></span></div>
                         </div>
                         <div class="list">
                            <div class="list_tit">出生年月：</div>
                            <div class="list_con w200">
                            <input class="Wdate" type="text" onClick="WdatePicker({skin:'green'})">
                            </div>
                            <div class="list_tit1">属相：</div>
                            <div class="list_con w70">
                            	<select name="">
                            	  <option>龙</option>
                            	</select>
                            </div>
                            <div class="list_tit1">星座：</div>
                            <div class="list_con w70">
                            	<select name="">
                            	  <option>水瓶座</option>
                            	</select>
                            </div>
                         </div>
                         <div class="list">
                            <div class="list_tit">身高：</div>
                            <div class="list_con w200"><input name="" type="text" /></div>
                            <div class="list_tit1 ">体重：</div>
                            <div class="list_con w200"><input name="" type="text" /></div>
                         </div>
                         <div class="list">
                            <div class="list_tit">所在地：</div>
                            <div class="list_con w200">
                            	<select name="liveProvince">
                                 </select>
                                 <select name="liveCity">
                                 </select>
                            </div>
                            <div class="list_tit1 ">籍贯：</div>
                            <div class="list_con w200">
                            	<select name="birthProvince">
                                 </select>
                                 <select name="birthCity">
                                 </select>
                            </div>
                         </div>
                         <div class="list">
                            <div class="list_tit">婚姻状况：</div>
                            <div class="list_con w200">
                            	<select name="">
                                    <option>未婚</option>
                                    <option>离婚</option>
                                 </select>
                            </div>
                         </div>
						 <div class="list">
                            <div class="list_tit">学历：</div>
                            <div class="list_con w200">
                            	<select name="">
                                    <option>大专</option>
                                    <option>本科</option>
                                 </select>
                            </div>
                            <div class="list_tit1 ">毕业院校：</div>
                            <div class="list_con w200"><input name="" type="text" /></div>
                         </div>
                         <div class="list">
                            <div class="list_tit">工作行业：</div>
                            <div class="list_con w200"><input name="" type="text" /></div>
                            <div class="list_tit1 ">工作职位：</div>
                            <div class="list_con w200"><input name="" type="text" /></div>
                         </div>
                         <div class="list">
                            <div class="list_tit">工资水平：</div>
                            <div class="list_con ">
                            	<select name="">
                                    <option>年薪50万以下</option>
                                    <option>年薪50</option>
                                 </select>
                            </div>
                    	</div>
                         <div class="list">
                            <div class="list_tit">自我介绍：</div>
                            <div class="list_con "><textarea name="" cols="" rows=""></textarea>  </div>
                    	</div>
                         <div class="list">
                            <div class="list_tit">择偶要求：</div>
                            <div class="list_con "><textarea name="" cols="" rows=""></textarea>  </div>
                    	</div>
                         
                    </div>
                </div>
            	<div class="clear"></div>
                <div class="center_item">
                	<h1>添加相册</h1>
                    <div class="info_jg">
                    	<div class="list">
                            <div class="list_tit">上次图片：</div>
                            <div class="list_con"><input name="" type="text" /></div>
                   			<div class="fl"><span class="confirm_btn2"><a href="#">选择图片</a></span></div><span class="red">注意：上次不要超过1M</span>
                         </div>
                    </div>
                </div>  
                <div class="clear"></div>
                <div class="center_item">
                	<h1>添加视频</h1>
                    <div class="info_jg">
                    	<div class="list">
                            <div class="list_tit">上传视频：</div>
                            <div class="list_con"><input name="" type="text" /></div>
                   			<div class="fl"><span class="confirm_btn2"><a href="#">选择视频</a></span></div><span class="red">注意：上次不要超过4M</span>
                         </div>
                    </div>
                </div>
                <div class="center_item">
                       <div class="list_tit">&nbsp;</div>
                       <div class="list_con2"><label><input name="" type="checkbox" value="" style="" />发布征婚</label></div>
						<div class="fl"><span class="confirm_btn1"><a href="#">保 存</a></span></div>
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
new PCAS("liveProvince","liveCity","广东省");
new PCAS("birthProvince","birthCity","广东省");

</script>
<%@include file="foot.jsp" %>
	</body>
</html>

