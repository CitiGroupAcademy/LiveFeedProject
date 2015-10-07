<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="project.dataObjects.Stock ,java.util.List, project.dal.DataAccess, project.dal.GetQuotes"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%
   		GetQuotes getQuotesInstance = new GetQuotes();
		getQuotesInstance.start();
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
    }

    function addFav(id) 
    {
        if (request != null) 
        {
            
            var url = "search/addFavourite?sym=" + id;
            request.open("GET", url, true);
            request.send(null);
        }
    }

    function delFav(id) {

        if (request != null) {
        	 
        	 var url = "search/deleteFavourite?sym=" + id;
             request.open("GET", url, true);
             request.send(null);
        }
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
<body class="no-js" onload="bot5update();top5update();favupdate();">
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
							<img src="Images/logo.jpg" alt="name"  width="120" height="28" />
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

			<div class="brandingLogo">
				<img class="logo" src="Images/logo.jpg" alt="name" width="173" height="57" />
				<div class="service-name">Stock Meet</br></br><span>Stocking you with Information</span></div>
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
								<input type="button" id="topbutton" value="Update" onclick="top5update()">
							</p>
							<p id="top5table">
							
							</p>
						</ul>
						<ul>
							<p>
								<b>Bottom 5</b>
								<input type="button" id="botbutton" value="Update" onclick="bot5update()">
							</p>						
							<p id="bot5table">
						
							</p>
						</ul>
					</li>
				</ul>
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
    