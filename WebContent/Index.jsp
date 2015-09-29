<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
    var request = myCreateXMLHttpRequest();

    function myCreateXMLHttpRequest() {
        try { return new ActiveXObject("Msxml2.XMLHTTP"); } catch (e) { }
        try { return new ActiveXObject("Microsoft.XMLHTTP"); } catch (e) { }
        try { return new XMLHttpRequest(); } catch (e) { }
        return null;
    }

    function top5update() {

        if (request != null) {
            var url = "search/topsearch";

            request.open("GET", url, true);
            request.onreadystatechange = top5Callback;
            request.send(null);
        }
    }

    function top5Callback() {

        if (request.readyState == 4 && request.status == 200) {
            var outputField = document.getElementById("top5table");
            outputField.innerHTML = request.responseText;
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
    
    
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" type="text/css" href="Styles/Index.css">

<title>Insert title here</title>
</head>

<body>
<div id="titlebar">
        <h3 id="stocktitle"> Stock Trading System</h3>
</div>

<div id="logo">
	
</div>

<div id="content">
               
            <div id="favourites">
            	<h3> Favourites</h3>
            	
            	<input type="button" id="favbutton" value="Update" onclick="favupdate()">
               	<p id="favtable"></p>
            </div>
            	
            <div id="top5">
            	<h3> Top 5</h3>
                      	
               	<input type="button" id="topbutton" value="Update" onclick="top5update()">
               	<p id="top5table"></p>            	
            </div>
            	               
            <div id="bottom5">
            	<h3> Bottom 5</h3>
            	
            	<input type="button" id="botbutton" value="Update" onclick="bot5update()">
               	<p id="bot5table"></p>
            </div>
            
            <div id="strategies">
            	<h3> Strategies</h3>
            </div>
            
            
    </div>

</body>
</html>