<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cut.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


<style>
body
{
	padding: 0;
	margin: 0;	
	height: 100%;
	#background-color: #eee;
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
	position: absolute;
	left: 20px;
	top: 20px;
}

#wrapper
{
	position: absolute;
	left: 0px;
	top: 40px;
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
	position: absolute;
	left: 340px;
	top: 60px;
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
	top: 315px;
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
	top: 315px;
	width: 50px;
	height: 22px;
	padding-right: 25px;
	padding-top: 2px;
	background: url(images/right.png) no-repeat right;
}

</style>

<!--<script type="text/javascript" src="ImageCropper.js"></script>-->
<script type="text/javascript" src="js/ImageCropper.js"></script>
<script type="text/javascript">

var cropper;

function init()
{	
	cropper = new ImageCropper(300, 300, 180, 180);
	cropper.setCanvas("cropper");
	cropper.addPreview("preview180");
	//cropper.addPreview("preview100");
	//cropper.addPreview("preview50");

	if(!cropper.isAvaiable())
	{
		alert("Sorry, your browser doesn't support FileReader, please use Firefox3.6+ or Chrome10+ to run it.");
	}
}

function selectImage(fileList)
{
	//init();
	cropper.loadImage(fileList[0]);
}

function rotateImage(e)
{
	switch(e.target.id)
	{
		case "rotateLeftBtn":
			cropper.rotate(-90);
			break;
		case "rotateRightBtn":
			cropper.rotate(90);
			break;
	}
}

function saveImage()
{
	var imgData = cropper.getCroppedImageData(180, 180);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(e)
	{
		if(xhr.readyState == 4)
		{
			if(xhr.status == 200)
			{
				//document.getElementById("status").innerHTML = "<font color='#f00'>上传成功！</font>";
			}
		}
	};
	
	xhr.open("post", "http://localhost:8080/mango/upload", true);
	var data = new FormData();
	data.append("sampleText", "flashlizi");
	data.append("sampleFile", imgData);
	xhr.send(data);
	xhr.onload = function(e) {

        if (this.status == 200) {

         //  alert(this.responseText);
        	//document.getElementById("status").innerHTML = "<font color='#f00'>上传成功！</font><img alt='' src='"+this.responseText+"'>";
        	document.getElementById("status").innerHTML = "<font color='#f00'>"+this.responseText+"</font>";
        }

    };                    
}

function trace()
{
	if(typeof(console) != "undefined") console.log(Array.prototype.slice.apply(arguments).join(" "));
};

</script>

</head>

<body onload="init();">
	<div id="container">
		<a id="selectBtn" href="javascript:void(0);" onclick="document.getElementById('input').click();"></a>
		<a id="saveBtn" href="javascript:void(0);" onclick="saveImage();"></a>
		<input type="file" id="input" size="10" style="visibility:hidden;" onchange="selectImage(this.files)" />
		
		<div id="wrapper">
			<canvas id="cropper"></canvas>
			<a id="rotateLeftBtn" href="javascript:void(0);" onclick="rotateImage(event);">向左旋转</a>
			<a id="rotateRightBtn" href="javascript:void(0);" onclick="rotateImage(event);">向右旋转</a>

			<span id="status" style="position:absolute;left:350px;top:10px;width:100px;"></span>
			<div id="previewContainer">
				<canvas id="preview180" width="180" height="180" class="preview"></canvas>
				<span style="display:block;width:100%;padding-top:5px;text-align:center;">大尺寸图片，180x180像素</span>

			</div>
		</div>
		
	</div>
</body>

</html>
