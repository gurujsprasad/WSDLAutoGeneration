<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Web Services</title>
	<link href="css/speedometer.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="css/mobile.css">
	<script src="js/mobile.js" type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
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
<body style = "padding: 20px">
	<div id="page">
		<div id="header">
			<div>
				<!-- <a href="index.html" class="logo"></a> -->
				<ul id="navigation">
					<li class="selected">
						<a href="Home">Home</a>
					</li>
					<li>
						<a href="ProjectManagement">Project Management</a>
					</li>
					<li>
						<a href="GoogleServiceController">Google Service</a>
					</li>
					<li>
						<a href="CustomService">New Service</a>
					</li>
					<li>
						<a href="AnalysisController">Analysis</a>
					</li>
					
					<!-- <li class="menu">
						<a href=""> Yahoo Service</a>
						<ul class="primary">
							<li>
								<a href="proj1.html">proj 1</a>
							</li>
						</ul>
					</li> -->
					
				</ul>
			</div>
		</div>
		<div id="body" class="home">
			
			<div class="body">
				<div style = "padding: 31px 10px 0;">
					<h1>Web Services</h1>
					<p> Arithmetic service to add two operands</p>
					<c:if test="${res != null and res != ''}">
						<p> Addition of Two numbers is : ${res}, time taken to complete process is : ${time } milliseconds</p>
						<c:set var="res" value=" "></c:set>
					</c:if>	
					
					<div class="header">
						
							<form action = "Home" method = "post">
								
									<input type = "text" name = "op1" value="Enter 1st Operand" id = "op1" onblur="this.value=!this.value?'Enter 1st Operand':this.value;" onfocus="this.select()" onclick="this.value='';"/>
								
								
									<input type = "text" name = "op2" value="Enter 2nd Operand" id = "op2" onblur="this.value=!this.value?'Enter 2nd Operand':this.value;" onfocus="this.select()" onclick="this.value='';"/>
								
									<input type = "submit" value = "ADD" id="submit" />
									<input type = "reset" value ="clear" id="submit"/>
								
							</form>
							
							
						
					</div>
					<div id = "speed">
						<input name = "speed"type='text' id="myValues" value = "${time}"/>
					</div>

					<button id="hide">Hide</button>
					<button id="show">Show</button>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script src="js/speedometer.js"></script>
<script type="text/javascript">
$("#myValues").myfunc({divFact:10});

</script>
					</div>	

					
				</div>
				
			</div>
			
		<div id="footer">
			<div class="connect">
				<div>
					<h1>CS 594 - Group 11</h1>
					
				</div>
			</div>
			<!-- <div class="footnote">
				<div>
					<p>&copy; 2023 BY SPACE PROSPECTION | ALL RIGHTS RESERVED</p>
				</div>
			</div> -->
		</div>
	</div>
	
</body>
</html>