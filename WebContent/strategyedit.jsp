<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="project.dataObjects.Stock ,java.util.List, project.dal.DataAccess"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>TradeSpotter - Edit Strategy</title>

<!--<link rel="icon" type="image/vnd.microsoft.icon" href="favicon.ico" />
	<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
	<link rel="apple-touch-icon" href="favicon.ico" />-->

<link href="Styles/bootstrap.min.css" rel="stylesheet" type="text/css"
	media="screen, projection" />
<link href="Styles/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" media="screen, projection" />
<link href="Styles/main.css" rel="stylesheet" type="text/css"
	media="screen, projection" />
<link href="Styles/main-responsive.css" rel="stylesheet" type="text/css"
	media="screen, projection" />
<link href="Styles/main-uxm.css" rel="stylesheet" type="text/css"
	media="screen, projection" />

<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/vendor/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>


<!-- html5 shim for ie8 and ie7 -->
<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
	<![endif]-->
<%
int id = 0;
String symbol = "";
String type = "";
String buysell = "";
String active = "";
if(request.getParameter("id")==null)
{
	out.println("Invalid Navigation");
}
else
{
	id = Integer.parseInt(request.getParameter("id"));
	symbol = request.getParameter("sym");
	type = request.getParameter("type");
	buysell = request.getParameter("bs");
	active = request.getParameter("sta");
	
}
%>
<script type="text/javascript">
	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
</script>

<script type="text/javascript">
var request = myCreateXMLHttpRequest();

function myCreateXMLHttpRequest() {
    try { return new ActiveXObject("Msxml2.XMLHTTP"); } catch (e) { }
    try { return new ActiveXObject("Microsoft.XMLHTTP"); } catch (e) { }
    try { return new XMLHttpRequest(); } catch (e) { }
    return null;
}

	$(document).ready(function() {
		// remove the no-js class
		$('body').removeClass('no-js');
	});

	function updateStrat() {

        	var sym = document.getElementById("sym");
        	var type = document.getElementById("type");
        	var bs = document.getElementById("buysell");
        	var status = document.getElementById("status");
        	var id = <%=id%>;
        	alert("The Strategy has been Updated");
        	  	
            var url = "search/updateStrat?sym=" + sym.value + "&sta=" + status.value + "&bs=" + bs.value + "&type=" + type.value + "&id=" + id;

            request.open("GET", url, true);
            request.send(null);
        }
    
</script>



</head>
<body class="no-js">
	<div class="page">
		<div class="skipnav">
			<a href="#skip-dest">Skip to content</a>
		</div>

		<header>

			<div class="navbar">
				<div class="navbar-inner">
					<div class="container">

						<a class="btn btn-navbar" data-toggle="collapse"
							data-target=".nav-collapse"> <span class="menu">Menu</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
						</a>

						<div class="brand">
							<img src="Images/Banner_Logo2.png" alt="name"  width="800" height="200" />
						</div>

						<div class="nav-collapse collapse">
							<nav class="topnav">
								<ul>
									<li><a href="home.jsp">Home</a></li>
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

			<div class="brand">
				<img class="logo" src="Images/Banner_Logo2.png" alt="name" width="800" height="200" />

			</div>
		</header>

		<div class="content container">
			<h1 id="skip-dest" style="text-align: center;">Strategy Edit</h1>

			<ul class="tilelinks clearfix">
				<div>
					<div>
						<ul class="tilelinks clearfix">
							<li>
								<div>
							<h2>Strategy Edit</h2>
							
							<ul>
								<form action="" method="post">
									<h3>New Strategies</h3>
									<select name="txtSymbol" id="sym">
            						<option value="symbol" selected><%=symbol%></option>
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
            						<option><%=type%></option>
            						<option value="movingAvg">Moving Average</option>
            						<option value="bollinger">Bollinger Band</option>   
            						<option value="movAvgExp">Moving Average Expo</option>    						
            						</select>
            						<br><br>
            						<select name="buy/sell" id="buysell" >
            						<option><%=buysell%></option>
            						<option value="buy">Buy</option>
            						<option value="sell">Sell</option>        						
            						</select>  
  									<br><br>
  									<select name="txtstatus" id="status" >
            						<option><%=active%></option>
            						<option value="active">Active</option>
            						<option value="inactive">Inactive</option>        						
            						</select>
            						<br><br>
            						<input type="button" value="Update" onClick="updateStrat()"; onclick="return confirm('Are you sure you want to delete?')"/>					
								</form>
							</ul>
						</div>
							</li>
						</ul>
					</div>

					<footer id="footnote">
						<div class="clearfix">
							<p>&#169; Copyright TEAM NAME. All Rights Reserved.</p>
					</footer>
				</div>
				<script type="text/javascript" src="js/scrolltopcontrol.js">
					
				</script>
</body>
</html>