//** jQuery Scroll to Top Control script- (c) Dynamic Drive DHTML code library: http://www.dynamicdrive.com.
//** Available/ usage terms at http://www.dynamicdrive.com (March 30th, 09')



var scrolltotop={
	setting: {startline:300, scrollduration:1000, fadeduration:[500, 100]}, //startline=number of pixels from top of doc scrollbar is at before showing control
	controlHTML: '<div id="toTop">Back to Top</div>', //HTML for control, which is auto wrapped in DIV w/ ID="topcontrol"
	controlattrs: {offsetx:0, offsety:0}, //offset of control relative to right/ bottom of window corner
	anchorkeyword: '#top', //Enter href value of HTML anchors on the page that should also act as "Scroll Up" links

	state: {isvisible:false, shouldvisible:false},

	scrollup:function(){
		if (!this.cssfixedsupport)
			this.$control.css({opacity:0})
		this.$body.animate({scrollTop: 0}, this.setting.scrollduration);
	},

	keepfixed:function(){
		var $window=jQuery(window)
		var controlx=$window.scrollLeft() + $window.width() - this.$control.width() - this.controlattrs.offsetx
		var controly=$window.scrollTop() + $window.height() - this.$control.height() - this.controlattrs.offsety
		this.$control.css({left:controlx+'px', top:controly+'px'})
	},

	togglecontrol:function(){
		var scrolltop=jQuery(window).scrollTop()
		if (!this.cssfixedsupport)
			this.keepfixed()
		this.state.shouldvisible=(scrolltop>=this.setting.startline)? true : false
		if (this.state.shouldvisible && !this.state.isvisible){
			this.$control.stop().animate({opacity:1}, this.setting.fadeduration[0])
			this.state.isvisible=true
		}
		else if (this.state.shouldvisible==false && this.state.isvisible){
			this.$control.stop().animate({opacity:0}, this.setting.fadeduration[1])
			this.state.isvisible=false
		}
	},
	
	init:function(){
		jQuery(document).ready(function($){

			$('div.page').addClass('with-to-top-plugin');

			var mainobj=scrolltotop
			var iebrws=document.all
			mainobj.cssfixedsupport=!iebrws || iebrws && document.compatMode=="CSS1Compat" && window.XMLHttpRequest //not IE or IE7+ browsers in standards mode
			mainobj.$body=$('html,body')
			mainobj.$control=$('<div id="topcontrol" style="width: 100%;z-index:2000">'+mainobj.controlHTML+'</div>')
				.css({position:mainobj.cssfixedsupport? 'fixed' : 'absolute', bottom:mainobj.controlattrs.offsety, right:mainobj.controlattrs.offsetx, opacity:0, cursor:'pointer'})
				.attr({title:'Back to Top'})
				.click(function(){mainobj.scrollup(); return false})
				.appendTo('body')
			if (document.all && !window.XMLHttpRequest && mainobj.$control.text()!='') //loose check for IE6 and below, plus whether control contains any text
				mainobj.$control.css({width:mainobj.$control.width()}) //IE6- seems to require an explicit width on a DIV containing text
			mainobj.togglecontrol()
			$('a[href="' + mainobj.anchorkeyword +'"]').click(function(){
				mainobj.scrollup()
				return false
			})
			$(window).bind('scroll resize', function(e){
				mainobj.togglecontrol()
			})
		})
	}
}

function isSmartPhone(){

	var uagent = navigator.userAgent.toLowerCase();

	return (
		(uagent.indexOf("android") != -1) || 
		(uagent.indexOf("blackberry") != -1) || 
		(uagent.indexOf("ipad") != -1) || 
		(uagent.indexOf("iphone") != -1) || 
		(uagent.indexOf("ipod") != -1) || 
		(uagent.indexOf("mobile") != -1) || 
		(uagent.indexOf("opera mobi") != -1) || 
		(uagent.indexOf("opera mini") != -1) || 
		(uagent.indexOf("phone") != -1) || 
		(uagent.indexOf("symbian") != -1) || 
		(uagent.indexOf("tablet") != -1) || 
		(uagent.indexOf("windows ce") != -1) || 
		(uagent.indexOf("wm5 pie") != -1)
    );
}

if (!isSmartPhone()) scrolltotop.init();

