$(document).ready(function() {

//	bringSelectedIframeToTop(true);
  	 $(window).load(function() {
         dwr.engine.setActiveReverseAjax(true);
         dwr.engine.setNotifyServerOnPageUnload(true);
   	});
  	 //页面关闭时销毁session
	 $(window).unload(function() {
	      var id = DWRUtil.getValue("userid");      
	      RemoteTest.removeSession(id);      
		 });

		$('.miniChatDiv').live('click', function() {
			toggleTheChatTab();
			});

	$("#friends").click(function() {
		$(this).next().slideToggle('normal');
	});
	$("#friendss").click(function() {
		$(this).next().slideToggle('normal');
	});

	$.fn.adjustPanel = function() {
		$(this).find("ul, #subpanel").css( {
			'height' : 'auto'
		}); //Reset subpanel and ul height
		var windowHeight = $(window).height(); //Get the height of the browser viewport
		var panelsub = $(this).find("#subpanel").height(); //Get the height of subpanel	
		var panelAdjust = windowHeight - 100; //Viewport height - 100px (Sets max height of subpanel)
		var ulAdjust = panelAdjust - 25; //Calculate ul size after adjusting sub-panel (27px is the height of the base panel)
		if (panelsub >= panelAdjust) { //If subpanel is taller than max height...
			$(this).find("#subpanel").css( {
				'height' : panelAdjust
			}); //Adjust subpanel to max height
			$(this).find("ul").css( {
				'height' : ulAdjust
			}); //Adjust subpanel ul to new size
		} else if (panelsub < panelAdjust) { //If subpanel is smaller than max height...
			$(this).find("ul").css( {
				'height' : 'auto'
			}); //Set subpanel ul to auto (default size)
		}
	};

	$("#chatpanel").adjustPanel();
	$(window).resize(function() {
		$("#chatpanel").adjustPanel();

	});

	$("#chatpanel a:first").click(function() {
		if ($(this).next("#subpanel").is(':visible')) {
			$(this).next("#subpanel").hide();
			$("#footpanel li a ").removeClass('active');
		} else {
			$("#subpanel").hide();
			$(this).next("#subpanel").toggle();
			$("#footpanel li a").removeClass('active');
			$(this).toggleClass('active');
		}
		return false;
	});
	$(document).click(function() {
		$("#subpanel").hide(); //hide subpanel
			$("#footpanel li a").removeClass('active'); //remove active class on subpanel trigger
		});
	$('#subpanel ul').click(function(e) {
		e.stopPropagation(); //Prevents the subpanel ul from closing on click
		});

});
