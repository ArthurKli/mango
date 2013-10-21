 /**
  * 私信功能测试：
  * 1、准备两个账号
  * 2、登陆两个账号
  * 3、分别访问私信界面http://localhost:8080/mango/mailbox_detail.jsp?mid=2   /mid为对方id
  * 4、一方发送消息  一方接受消息
  * 
  */
 
 
 //处理结果推送给用户
	function talking(msg,ppid,ppname){
		
		var to=document.getElementById("message_send_to");
		if(to==null||typeof(to)=="undefined"){
			var i =$("#unread_msg_acc").html();
//			alert("总共："+i);
			i++;
			$("#unread_msg_acc").html(i);
			return ;
		}
		
	    var htmlStr = "";
	    var now = new Date();
	    var time = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
	    htmlStr += " <div class='message_li'>";
	    htmlStr += "<div class='message_pic fr'><img src='images/p1.jpg' /></div>";
	    htmlStr += "<div class='for fr'>";
	    htmlStr += "<div class='for_ico'></div>";
	    htmlStr += "<b class='bule'>"+ppname+"</b><span>"+time+"</span>";
	    htmlStr += "<p>"+msg+"</p></div></div>";
	    $(".message").append(htmlStr);
	  	 }
    function helloTest(id){
        if(id==0){
          	return ;
          }
  	  RemoteTest.login(id,function(data){
//            alert(data);
        });
    }

		//发送时自动滚动到底部    
		function sc(uid)
		      {
		      var e=document.getElementById("chatDiv_main_"+uid);
		      e.scrollTop=e.scrollHeight;
		      }
