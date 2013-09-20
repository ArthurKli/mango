<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>DWR Test</title>
    <script type="text/javascript" src="dwr/engine.js"></script>
    <script type="text/javascript" src="dwr/util.js"></script>
    <script type="text/javascript" src="dwr/interface/RemoteTest.js"></script>
    <style type="text/css">
    #chatDiv{
    position: fixed;
    bottom: 0px;
    right:0px;
    width: 300px;
    height: 274px;
    border: 1px solid #AFAFAF;
    }
    #chatDiv_head{
       border-bottom: 1px solid #AFAFAF;
    color: #808080;
    cursor: pointer;
    height: 32px;
    line-height: 30px;
    padding: 0 1px;
    background-color: rgba(156, 144, 144, 0.25);
    }
    
        #chatDiv_main{
       border-bottom: 1px solid #AFAFAF;
    color: #808080;
    height: 160px;
    line-height: 30px;
    padding: 0 1px;
    }
    #chatDiv_edit{
       border-bottom: 1px solid #AFAFAF;
    color: #808080;
    height: 60px;
    line-height: 30px;
    padding: 0 1px;
    }
    #chatDiv_edit textarea{
    width: 100%;
    height: 100%;
    }
    #chatDiv_bottom{
    height:100%;
    background-color: rgba(156, 144, 144, 0.25);
    }
    #chatDiv_bottom div{
    float: left;
    margin-right: 15;
    }
    
    
    </style>
      <script type="text/javascript">
      function helloTest(){
          var name='lfk';
          var pwd='123';
    	  RemoteTest.login(name,pwd,function(data){
              alert(data);
              
          });
      }
      function uuid(){
    	  RemoteTest.createUUID(function(data){
              alert(data);
              
          });
      }
      function getFris(){
    	  RemoteTest.getFriendsByID("1",function(data){
              alert(data[0].phone);
              
          });
      }
  </script>
  </head>
  
  <body>
   <input type="button" value="click" onclick="getFris();"/>
		
		<div id="chatDiv">
		<div id="chatDiv_head"></div>
		<div id="chatDiv_main"></div>
		<div id="chatDiv_edit">
		<textarea id="content2" name="content"></textarea> 
		</div>
		<div id="chatDiv_bottom">
		<div>隐 藏</div><div><a href="#">发 送</a></div>
		</div>
		</div>
	</body>
</html>
