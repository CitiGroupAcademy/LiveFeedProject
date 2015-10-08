<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="project.dataObjects.Stock ,java.util.List, project.dal.DataAccess"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>Title</title>

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
<script src="js/jquery-ui.min.js"></script>
<script src="js/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>


<!-- html5 shim for ie8 and ie7 -->
<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
	<![endif]-->
<%
String symbol = "";
if(request.getParameter("sym")==null)
{
	out.println("Invalid Navigation");
}
else
{
	symbol = request.getParameter("sym");
}
%>
<script type="text/javascript">
var request = myCreateXMLHttpRequest();

function myCreateXMLHttpRequest() 
{
    try { return new ActiveXObject("Msxml2.XMLHTTP"); } catch (e) { }
    try { return new ActiveXObject("Microsoft.XMLHTTP"); } catch (e) { }
    try { return new XMLHttpRequest(); } catch (e) { }
    return null;
}

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
	$(document).ready(function() {
		// remove the no-js class
		$('body').removeClass('no-js');
	});

	function stockdata() {

        if (request != null) {
            var sym = '<%=symbol%>';
            var url = "search/stockdata?sym="+sym;

            request.open("GET", url, true);
            request.onreadystatechange = stockdataCallback;
            request.send(null);
        	
        }
    }

    function stockdataCallback() 
    {
        if (request.readyState == 4 && request.status == 200) 
        {
            var outputField = document.getElementById("datatable");
            outputField.innerHTML = request.responseText;
        }
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

			<div class="brandingLogo">
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
								<div id="graph">
									<h2>Graph</h2>
									<ul>

										<script type="text/javascript">
										window.onload = updategraph;
										setInterval(updategraph, 15000);
										function updategraph() 
											{

												var percentChange = [];
												var timeStamp = [];
												var change = true;
												var symbol = '<%=symbol%>';
												var updateChart = $.get("search/getgraphdata?sym="+symbol,
												function(temp) 
												{
													temp = temp.split(',');

													for ( var i in temp) 
													{
														if (i == 0) 
														{
															var goodPC = temp[i].replace("[","");
															percentChange.push(parseFloat(goodPC));
															change = false;
														} 
														else if (i == (temp.length - 1)) 
														{
															var goodTS = temp[i].replace("]","");
															goodTS = goodTS.replace(".0","");
															timeStamp.push(goodTS);
														} 
														else 
														{
															if (change == true) 
															{
																percentChange.push(parseFloat(temp[i]));
																change = false;
															} 
															else 
																{
																	var goodTS = temp[i].replace(".0","");
																	timeStamp.push(goodTS);
																	change = true;
																}
														}
													}

													$('#graph').highcharts(
													{
														title : 
														{
															text : 'Stock Table',
															x : -20 //center
														},
														subtitle : 
														{
															text : symbol+': Stock Information',
															x : -20
														},
														xAxis : 
														{
															title : 
															{
																text : 'Time'
															},
															categories : timeStamp
														},
														yAxis : 
														{
															title : 
															{
																text : 'Change(£)'
															},
															plotLines : 
															[ {
																value : 0,
																width : 1,
																color : '#808080'
															} ]
														},
														tooltip : 
														{
															valuePrefix : '£'
														},
														legend : 
														{
															layout : 'vertical',
															align : 'right',
															verticalAlign : 'middle',
															borderWidth : 0
														},
														series : 
														[ {
															name : symbol,
															data : percentChange
														} ]
													});
												});
											};
										</script>
									</ul>
								</div>
							</li>
						</ul>
					</div>

					<div>
						<ul class="tilelinks clearfix">
							<li>
								<div>
									<h2>Stock Data Table:</h2>
									<ul>
										<ul>
											<p>
												<input type="button" id="stockupdate" value="Update" onclick="stockdata()">
											</p>
											
											<p id="datatable">
											 
											</p>
										</ul>
									</ul>
								</div>
								<div>
									<h2>Buy/Sell</h2>
									<ul>
										<form action="BuyServlet" method="post">
											<h3>Buy</h3>
											<input type="hidden" name="stockSymbol" value="<%=symbol%>"/>
											<br> Number of Stocks: <input type="number" min="1" max="999999" name="stockAmount"/> <br>
											<br>
											<br> <input type="submit" value="Buy" />
										</form>

									</ul>
								</div>
								<div>
									<ul>
										<form action="SellServlet" method="post">
											<h3>Sell</h3>
											<input type="hidden" name="stockSymbol" value="<%=symbol%>"/>
											<br> Number of Stocks: <input type="number" min="1" max="999999" name="stockAmount"/> <br>
											<br>
											<br> <input type="submit" value="Sell" />
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
