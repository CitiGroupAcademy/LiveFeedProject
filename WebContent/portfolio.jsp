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
<script src="js/vendor/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>


<!-- html5 shim for ie8 and ie7 -->
<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
	<![endif]-->

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
	$(document).ready(function() {
		// remove the no-js class
		$('body').removeClass('no-js');
	});
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
							<img src="Images/logo.jpg" alt="name" width="120" height="28" />
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
				<img class="logo" src="Images/logo.jpg" alt="name" width="173"
					height="57" />
				<div class="service-name">
					Stock Meet</br>
					</br>
					<span>Stocking you with Information</span>
				</div>
			</div>
		</header>

		<div class="content container">
			<h1 id="skip-dest" style="text-align: center;">Portfolio</h1>

			<ul class="tilelinks clearfix">
				<div>
					<div>
						<ul class="tilelinks clearfix">
							<li>
								
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