<!DOCTYPE html>
<html lang="en">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
 <link href="css/speedometer.css" rel="stylesheet" type="text/css" />
 <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Our WebService</title>

<script>
function call()
{
	alert ("jhkjk");
	$("#myValues").myfunc({divFact:10,eventListenerType:'keyup'});
	}
$(document).ready(function(){
    $("#hide").click(function(){
        $("#speed").hide();
    });
    $("#show").click(function(){
        $("#speed").show();
    });
    
    $("#myvalues").focus().bind('blur', function() {
        $(this).focus();  
    });
});

</script>

</head>
<body style = " background:#C1D1D6"> 
<div id = "body" style = "padding-left: 400px">
<h2>Web Client for Arithmetic Operation</h2>
<c:if test="${res != null and res != ''}">
	<p> Addition of Two numbers is : ${res}, time taken to complete process is : ${time } milliseconds</p>
	<c:set var="res" value=" "></c:set>
</c:if>	
<table>
<form action = "DisplayServlet" method = "post">
	<tr>
		<td>Enter 1st operand:</td><td><input type = "text" name = "op1" id = "op1"/></td>
	</tr><br/>
	<tr>
		<td>Enter 2nd operand:</td><td><input type = "text" name = "op2" id = "op2"/></td>
	</tr>
	<tr>
		<td><input type = "submit" value = "ADD"/></td>
		<td><input type = "reset" value ="clear"/></td>
	</tr>
</form>
</table>
<br/>
<div id = 'speed'><input type='text' id="myValues" value = "${time}"/></div>
<br/>
<button id="hide">Hide</button>
<button id="show">Show</button>
</div>	


</body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script src="js/speedometer.js"></script>
<script type="text/javascript">
$("#myValues").myfunc({divFact:10});

</script>
