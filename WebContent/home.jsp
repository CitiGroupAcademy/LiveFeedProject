<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="project.dataObjects.Stock ,java.util.List, project.dal.DataAccess, project.dal.GetQuotes"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%
        String run = (String)session.getAttribute("run");
		if(run==null)
		{
   			GetQuotes getQuotesInstance = new GetQuotes();
			getQuotesInstance.start();
			
			session.setAttribute("run", "running");
		}
		
		
		String message = "";
		if(request.getAttribute("message")!=null)
		{
			message = request.getParameter("msg");
		}
%>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<title>Title</title>

	<!--<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico" />
	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
	<link rel="apple-touch-icon" href="favicon.ico" />-->

	<link href="Styles/bootstrap.min.css" rel="stylesheet" type="text/css" media="screen, projection" />
	<link href="Styles/bootstrap-responsive.css" rel="stylesheet" type="text/css" media="screen, projection" />
	<link href="Styles/main.css" rel="stylesheet" type="text/css" media="screen, projection" />
	<link href="Styles/main-responsive.css" rel="stylesheet" type="text/css" media="screen, projection" />
	<link href="Styles/main-uxm.css" rel="stylesheet" type="text/css" media="screen, projection" />

	<script src="js/jquery.min.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>

	<!-- html5 shim for ie8 and ie7 -->
	<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
	<![endif]-->

	<script type="text/javascript">
		
		(function () {
			var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		})();
	</script>
	
	<script type="text/javascript">
	$(document).ready(function () {
		// remove the no-js class
		$('body').removeClass('no-js');
		var message = '<%=message%>';
		if(message!="")
		{ 
			alert(message);
		}
	});
	
	</script>
	
	<script type="text/javascript">
    var request = myCreateXMLHttpRequest();

    function myCreateXMLHttpRequest() {
        try { return new ActiveXObject("Msxml2.XMLHTTP"); } catch (e) { }
        try { return new ActiveXObject("Microsoft.XMLHTTP"); } catch (e) { }
        try { return new XMLHttpRequest(); } catch (e) { }
        return null;
    }
    
    function stratupdate() {

        if (request != null) {
            var url = "search/stratsearch";

            request.open("GET", url, true);
            request.onreadystatechange = stratCallback;
            request.send(null);
        	
        }
    }
    
    function stratCallback() {

        if (request.readyState == 4 && request.status == 200) {
            var outputField = document.getElementById("strattable");
            outputField.innerHTML = request.responseText;
        }
    }
    
    var intervalFunctions = [ top5update, bot5update ];
    var intervalIndex = 0;
    window.setInterval(function(){
      intervalFunctions[intervalIndex++ % intervalFunctions.length]();
    }, 500);
    
    function insertStrat() {

        if (request != null) {
        	var symbol = document.getElementById("sym");
        	var type = document.getElementById("type");
        	var bs = document.getElementById("buysell");
        	var status = document.getElementById("status");
        	alert("A New Strategy has been Created");
        	  	
            var url = "search/insertStrat?sym=" + sym.value + "&sta=" + status.value + "&bs=" + bs.value + "&type=" + type.value;

            request.open("POST", url, true);
            request.send(null);
        }
    }

    function stocksearch() {

        if (request != null) {
        	var textField = document.getElementById("stockInput");
            var url = "search/stocksearch?str=" + textField.value;

            request.open("GET", url, true);
            request.onreadystatechange = stockSearchCallback;
            request.send(null);
        }
    }

    function stockSearchCallback() {

        if (request.readyState == 4 && request.status == 200) {
            var outputField = document.getElementById("searchtable");
            outputField.innerHTML = request.responseText;
        }
    }
    
    function top5update() {

        if (request != null) {
            var url = "search/topsearch";

            request.open("GET", url, true);
            request.onreadystatechange = top5Callback;
            request.send(null);
        	//Logger log = Logger.getLogger("HOME:");
			//log.info("TOP 5 UPDATE");
        }
    }

    function top5Callback() {

        if (request.readyState == 4 && request.status == 200) {
            var outputField = document.getElementById("top5table");
            outputField.innerHTML = request.responseText;
        }
    }

    function delStrat(id) {

        if (request != null) {
        	
        	 var url = "search/deleteStrategy?id=" + id;
             request.open("GET", url, true);
             request.send(null);
        }
        stocksearch();
    }

    function addFav(id) 
    {
        if (request != null) 
        {
            
            var url = "search/addFavourite?sym=" + id;
            request.open("GET", url, true);
            request.send(null);
            alert(id+" has been added to your favourites");
        }
        stocksearch();
    }

    function delFav(id) {

        if (request != null) {
        	 
        	 var url = "search/deleteFavourite?sym=" + id;
             request.open("GET", url, true);
             request.send(null);
             alert(id+" has been removed from your favourites");
        }
        stocksearch();
    }
    
    function bot5update() {

        if (request != null) {
            var url = "search/bottomsearch";

            request.open("GET", url, true);
            request.onreadystatechange = bot5Callback;
            request.send(null);
        }
    }

    function bot5Callback() {

        if (request.readyState == 4 && request.status == 200) {
            var outputField = document.getElementById("bot5table");
            outputField.innerHTML = request.responseText;
        }
    }

    function favupdate() {

        if (request != null) {
            var url = "search/favsearch";

            request.open("GET", url, true);
            request.onreadystatechange = favCallback;
            request.send(null);
        }
    }

    function favCallback() {

        if (request.readyState == 4 && request.status == 200) {
            var outputField = document.getElementById("favtable");
            outputField.innerHTML = request.responseText;
        }
    }

    
    </script>
</head>
<body class="no-js">
	<div class="page">
		<div class="skipnav"><a href="#skip-dest">Skip to content</a></div>

		<header>
		
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container">
					 
						<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
							<span class="menu">Menu</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</a>
						
						<div class="brand">
							<img src="Images/Banner_Logo2.png" width="800" height="300" />
						</div>

						<div class="nav-collapse collapse">
							<nav class="topnav">
								<ul>
									<li><a href="home.jsp">Home</a></li>
									<li><a href="portfolio.jsp">Portfolio</a></li>
									<li><a href="aboutus.jsp">About Us</a></li>
									
									<!--<li class="dropdown">
										<a data-target="#" href="principles.html" class="dropdown-toggle" data-toggle="dropdown">Principles<b class="caret"></b></a>						
									</li>-->
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>

			<<div class="brandingLogo">
				<img class="logo" src="Images/Banner_Logo2.png" alt="name" width="800" height="200" />

			</div>
		</header>
		
		<div class="content container">
			<h1 id="skip-dest" style="text-align: center;">Stock Information</h1>

			<ul class="tilelinks clearfix">
				<div>
					<div>
				<ul class="tilelinks clearfix">
					<li>
					<div>
							<h2>Stock Search</h2>
							<ul>
								<form>
								<input type="text" id="stockInput" onKeyUp="stocksearch()"/>													
								</form>	
								<p id="searchtable">
								
								</p>
								
							</ul>
						</div>
						<div>
							<h2>Strategies</h2>
							<ul>
								<form action="" method="post">
									<h3>New Strategies</h3>
									<select name="txtSymbol" id="sym" >
            						<option value="symbol" selected>Choose a Symbol</option>
            						<%
            							//Generate the rest of the options from the database
            							List<Stock> stocks = DataAccess.getStocks();
            							for(Stock s: stocks){
            								out.println("<option value=\"" + s.getStockSymbol() + "\">" + s.getStockName() + "</option>");
            							}
           	
            						%>
            						</select>
            						<br><br>
            						<select name="txtStrategy" id="type" >
            						
            						<option value="movingAvg">Moving Average</option>
            						<option value="bollinger">Bollinger Band</option>
            						<option value="movAvgExp">Moving Average Expo</option>    	        						
            						</select>
            						<br><br>
            						<select name="buy/sell" id="buysell" >
            						
            						<option value="buy">Buy</option>
            						<option value="sell">Sell</option>        						
            						</select>  
  									<br><br>
  									<select name="txtstatus" id="status" >
            						
            						<option value="active">Active</option>
            						<option value="inactive">Inactive</option>        						
            						</select>
            						<br><br>
            						<input type="button" value="Create" onClick="insertStrat()"; onclick="return confirm('Are you sure you want to delete?')"/>					
								</form>
							</ul>
						</div>
						<div>
						<ul>
							<p>
								<b>Strategy Update</b>
								<input type="button" id="stratupdate" value="Update" onclick="stratupdate()">
							</p>
							<p id="strattable">
							
							</p>
						</ul>
						
						</div>
					</li>
					<li class="seperater">
						<h2>LiveFeed</h2>
						<ul>
							<p>
								<b>Favourites</b>
								<input type="button" id="favbutton" value="Update" onclick="favupdate()">
							</p>
							
							<p id="favtable">
							 
							</p>
						</ul>
						<ul>
							<p>
								<b>Top 5</b>
							</p>
							<p id="top5table">
							
							</p>
						</ul>
						<ul>
							<p>
								<b>Bottom 5</b>
							</p>						
							<p id="bot5table">
						
							</p>
						</ul>
					</li>
				</ul>
					<a class="twitter-timeline" href="https://twitter.com/FinancialTimes" data-widget-id="651731116228018176">Tweets by @FinancialTimes</a>
	<script>
	!function(d,s,id)
	{
		var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';
		if(!d.getElementById(id))
		{
			js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";
			fjs.parentNode.insertBefore(js,fjs);
		}
	}
	(document,"script","twitter-wjs");
	</script>
	<script language="JavaScript1.2">

	//Specify the marquee's width (in pixels)
	var marqueewidth="100%"
	//Specify the marquee's height
	var marqueeheight="25px"
	//Specify the marquee's marquee speed (larger is faster 1-10)
	var marqueespeed=2
	//configure background color:
	var marqueebgcolor="#DEFDD9"
	//Pause marquee onMousever (0=no. 1=yes)?
	var pauseit=1

	//Specify the marquee's content (don't delete <nobr> tag)
	//Keep all content on ONE line, and backslash any single quotations (ie: that\'s great):

	var marqueecontent='<nobr><font face="Arial">Thank you for visiting <a href="http://www.dynamicdrive.com">Dynamic Drive.</a> If you find this script useful, please consider linking to us by <a href="../link.htm">click here.</a> Enjoy your stay!</font></nobr>'


	////NO NEED TO EDIT BELOW THIS LINE////////////
	marqueespeed=(document.all)? marqueespeed : Math.max(1, marqueespeed-1) //slow speed down by 1 for NS
	var copyspeed=marqueespeed
	var pausespeed=(pauseit==0)? copyspeed: 0
	var iedom=document.all||document.getElementById
	if (iedom)
	document.write('<span id="temp" style="visibility:hidden;position:absolute;top:-100px;left:-9000px">'+marqueecontent+'</span>')
	var actualwidth=''
	var cross_marquee, ns_marquee
	
	function populate()
	{
		if (iedom)
		{
			cross_marquee=document.getElementById? document.getElementById("iemarquee") : document.all.iemarquee
			cross_marquee.style.left=parseInt(marqueewidth)+8+"px"
			cross_marquee.innerHTML=marqueecontent
			actualwidth=document.all? temp.offsetWidth : document.getElementById("temp").offsetWidth
		}
		else if (document.layers)
		{
			ns_marquee=document.ns_marquee.document.ns_marquee2
			ns_marquee.left=parseInt(marqueewidth)+8
			ns_marquee.document.write(marqueecontent)
			ns_marquee.document.close()
			actualwidth=ns_marquee.document.width
		}
		lefttime=setInterval("scrollmarquee()",20)
	}
	window.onload=populate
	
	function scrollmarquee()
	{
		if (iedom)
		{
			if (parseInt(cross_marquee.style.left)>(actualwidth*(-1)+8))
			cross_marquee.style.left=parseInt(cross_marquee.style.left)-copyspeed+"px"
			else
			cross_marquee.style.left=parseInt(marqueewidth)+8+"px"
		}
		else if (document.layers)
		{
			if (ns_marquee.left>(actualwidth*(-1)+8))
			ns_marquee.left-=copyspeed
			else
			ns_marquee.left=parseInt(marqueewidth)+8
		}
	}
	
	if (iedom||document.layers)
	{
		with (document)
		{
			document.write('<table border="0" cellspacing="0" cellpadding="0"><td>')
			if (iedom)
			{
				write('<div style="position:relative;width:'+marqueewidth+';height:'+marqueeheight+';overflow:hidden">')
				write('<div style="position:absolute;width:'+marqueewidth+';height:'+marqueeheight+';background-color:'+marqueebgcolor+'" onMouseover="copyspeed=pausespeed" onMouseout="copyspeed=marqueespeed">')
				write('<div id="iemarquee" style="position:absolute;left:0px;top:0px"></div>')
				write('</div></div>')
			}
			else if (document.layers)
			{
				write('<ilayer width='+marqueewidth+' height='+marqueeheight+' name="ns_marquee" bgColor='+marqueebgcolor+'>')
				write('<layer name="ns_marquee2" left=0 top=0 onMouseover="copyspeed=pausespeed" onMouseout="copyspeed=marqueespeed"></layer>')
				write('</ilayer>')
			}
			document.write('</td></table>')
		}
	}
</script>
			</div>
			
		<footer id="footnote">
			<div class="clearfix">
				<p>
					&#169; Copyright TEAM NAME. All Rights Reserved.
				</p>
		</footer>
	</div>
	<script type="text/javascript" src="js/scrolltopcontrol.js">
	</script>
	
</body>
</html>
    