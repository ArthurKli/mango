<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.print(path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UploadiFive Test</title>
<base href="<%=basePath%>">
<link href="<%=basePath%>css/uploadifive.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=basePath%>css/dialog/zebra_dialog.css" type="text/css">
<script type='text/javascript' src="<%=basePath%>/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.uploadifive.min.js"></script>
<script type='text/javascript' src="<%=basePath%>/js/zebra_dialog.js"></script> 
<style type="text/css">
body {
	font: 13px Arial, Helvetica, Sans-serif;
}
.uploadifive-button {
	float: left;
	margin-right: 10px;
}
#queue {
	border: 1px solid #E5E5E5;
	height: 177px;
	overflow: auto;
	margin-bottom: 10px;
	padding: 0 3px 3px;
	width: 300px;
}
</style>
</head>
<body>
	<h1>UploadiFive Demo</h1>
	<form>
		<div id="queue"></div>
		<input id="uploadify" name="uploadify" type="file" multiple="true">
		<a style="position: relative; top: 8px;" href="javascript:$('#uploadify').uploadifive('upload')">Upload Files</a>
	</form>

	<script type="text/javascript">
		
		$(function() {
			$('#uploadify').uploadifive({
				'removeCompleted' : true,
				'auto'             : false,
				'fileSizeLimit'   :1000,
				'scriptData':	{'name':'lfk','age':24},
				'queueID'          : 'queue',
		        'itemTemplate' : '<div class="uploadifive-queue-item"><span class="filename"></span><span class="fileinfo"></span><div class="close"></div></div>',
		        
				'uploadScript'     : '<%=basePath%>upload?type=photo&id=1',
				'onUploadComplete' : function(file, data) { 
					if(data=='error'){
						showError();
					}

					}
			});
		});
		function showError(){
	        $.Zebra_Dialog('<strong>上传文件总数超出限制</strong>', {
	        	    'type':     'error',
	        	    'title':    '系 统 错 误'
	        	});
		}
	</script>
</body>

</html>