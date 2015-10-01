<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="project.dataObjects.Stock ,java.util.List, project.dal.DataAccess"%>
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

	<link href="Styles/bootstrap.min.css" rel="stylesheet" type="text/css" media="screen, projection" />
	<link href="Styles/bootstrap-responsive.css" rel="stylesheet" type="text/css" media="screen, projection" />
	<link href="Styles/main.css" rel="stylesheet" type="text/css" media="screen, projection" />
	<link href="Styles/main-responsive.css" rel="stylesheet" type="text/css" media="screen, projection" />
	<link href="Styles/main-uxm.css" rel="stylesheet" type="text/css" media="screen, projection" />

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
							<img src="Images/logo.jpg" alt="name"  width="120" height="28" />
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
						<div id="graph">
							<h2>Graph</h2>
							<ul>
							
	<script type="text/javascript">

  $(function () {
	    $('#graph').highcharts({
	        title: {
	            text: 'Monthly Average Temperature',
	            x: -20 //center
	        },
	        subtitle: {
	            text: 'Source: WorldClimate.com',
	            x: -20
	        },
	        xAxis: {
	            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
	                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
	        },
	        yAxis: {
	            title: {
	                text: 'Temperature (°C)'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: '°C'
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	            name: 'Tokyo',
	            data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
	        }, {
	            name: 'New York',
	            data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
	        }, {
	            name: 'Berlin',
	            data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
	        }, {
	            name: 'London',
	            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
	        }]
	    });
	});

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
							<h2>Buy/Sell</h2>
							<ul>
							<form>
									<h3>Buy</h3>
            						Price: £<input type="text" />
            						<br><br>
            						Number of Stocks: <input type="text" />
            						<br><br><br>
            						<input type="submit" value="Buy" />					
								</form>
							
							</ul>
						</div>
						<div>
							<ul>
							<form>
									<h3>Sell</h3>
            						Price: £<input type="text" />
            						<br><br>
            						Number of Stocks: <input type="text" />
            						<br><br><br>
            						<input type="submit" value="Sell" />					
								</form>
							
							</ul>
						</div>
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
    