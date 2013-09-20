      function helloTest(){
          var name='lfk';
          var pwd='123';
          var id=$("#myid").val();
    	  RemoteTest.login(id,function(data){
              $("#userid").val(data);
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
              appendFri("inline",data);
          });
      }

      function appendFri(type,data){
          var pdiv="#"+type;
          $(pdiv).show();
          $(pdiv).empty();
          for(var i=0;i<data.length;i++){
        	  var li = "<li><a class='fri_list' id='fri_"+data[i].uid+"' href='javascript:void(0);' onclick='createChatDiv(this);'>"+data[i].username+"</a></li>";
        	  $(pdiv).append(li);
          }
      }

      function toggleTheChatTab(){
          $(".chatDiv").toggle();
          $(".miniChatDiv").toggle();
      }
      function sendMessage(uid){
            var msg=$("#chatDiv_"+uid+" textarea").val();
    	    var htmlStr = "";
	        var now = new Date();
	        var time = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
	        htmlStr += "<font color='#CC6633'>&nbsp;&nbsp;我说:" + time+"</font>";
	        htmlStr += "</br>";
	        htmlStr += msg+"</br>";
	        $("#chatDiv_main_"+uid).append(htmlStr);
	        $("#chatDiv_"+uid+" textarea").val('');

	        sc(uid);
	        RemoteTest.sendMsg(msg,uid,$("#userid").val());
          
      }

  	//处理结果推送给用户
  	function talking(msg,ppid,ppname){
  	  var her=document.getElementById("chatDiv_"+ppid);
  	  if(!her){
  		createChatDiv(ppid); 
  	  }

  	 var htmlStr = "";
     var now = new Date();
     var time = now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
     htmlStr += "<font color='#CC6633'>&nbsp;&nbsp;"+ppname+"说:" + time+"</font>";
     htmlStr += "</br>";
     htmlStr += msg+"</br>";
     $("#chatDiv_main_"+ppid).append(htmlStr);

     sc(ppid);
  	  	 }
  	

      function createChatDiv(obj){
          var uid;
          var uname;
          if( typeof obj=='object'){
        	  uid=obj.id.substring(4);
        	  uname=obj.innerHTML;
          }else{
        	  uid= obj;  
        	  uname='sender';
          }
		  
        	 
		  
          var editText="<textarea class='edit_value' name='edit_value'></textarea>";
          var bottomTip="<div><a href='javascript:void(0);' onclick='toggleTheChatTab();'>隐  藏</a></div><div><a href='javascript:void(0);' onclick='sendMessage(\""+uid+"\");'>发  送</a></div>";
    	  var chatDiv = document.createElement("DIV");
    	  chatDiv.id="chatDiv_"+uid;
    	  chatDiv.setAttribute("class", "chatDiv");
    	  chatDiv.appendChild(createDiv(null,"chatDiv_head",null,uname,null));
    	  chatDiv.appendChild(createDiv("chatDiv_main_"+uid,"chatDiv_main",null,null,null));
    	  chatDiv.appendChild(createDiv(null,"chatDiv_edit",null,editText,null));
    	  chatDiv.appendChild(createDiv(null,"chatDiv_bottom",null,bottomTip,null));
    	  $("body").append(chatDiv);
    	  $("body").append(createDiv("miniChatDiv_"+uid,"miniChatDiv",null,"展开",null));
    	  
      }
		function createDiv(id,cName, align, context,eve) {
			var divss = document.createElement("div");
			if(id!=null){
				divss.id = id;
				}
			if(cName!=null){
				divss.setAttribute("class", cName);
				}
			if(align!=null){
				divss.align = align;
				}
			if(context!=null){
				divss.innerHTML = context;
				}
			
			return divss;
		}

		//发送时自动滚动到底部    
		function sc(uid)
		      {
		      var e=document.getElementById("chatDiv_main_"+uid);
		      e.scrollTop=e.scrollHeight;
		      }
