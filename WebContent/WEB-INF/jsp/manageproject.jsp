<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <title>Manage Project</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <!-- <style type="text/css">
  	html{
  	background-image: url('../img/bg-image.jpg');
  	}
  </style> -->
</head>
<body>

<div class="container">

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="Home">CS594</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="ProjectManagement">Home</a></li>
      <li class="dropdown">
      	<a class="dropdown-toggle" data-toggle="dropdown" href="ProjectManagement">Project Management
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
	      <li><a href="WSDLServiceController">New Project</a></li>
	      <li><a href="Index">Run Existing Project</a></li>
	      <li><a href="ManageExistingProjectController">Manage Existing Project</a></li>
	      
	     </ul>
      </li>
      <li><a href="AnalysisWebService">Analysis</a></li>
    </ul>
  </div>
</nav>
  
 
    <div class="panel panel-default">
      <div class="panel-heading"> Manage Project</div>
      <div class="panel-body">
      <c:if test ="${message ne null and message ne ''}">
      	<div class="alert alert-success">
  				<c:out value="${message }"></c:out>
  				<c:set var="message" value="" scope="session"></c:set>
			</div>
      </c:if>
      	<table class="table table-hover">
						 <thead>
					      <tr>
					        <th>WSDL Name</th>
					        <th>WSDL</th>
					        <th>Operation</th>
					      </tr>
					    </thead>
		    			<tbody>
      	<c:forEach items="${lst}" var="l">
			<tr>
			<form method = "post" action = "ManageExistingProjectController" class="form-inline">
			<input type = "hidden" name = "wsdlID" value = "${l.wsdlID}">
				<td><input type = "text" name = "wsdlName" value ="${l.wsdlName}" class="form-control"></td>
				<td><input type = "text" name = "wsdlUrl" value ="${l.wsdl}" class="form-control"></td>
				<td>
					<input type = "submit" name = "operation" value = "update">
					<input type = "submit" name = "operation" value = "delete">
				</td>
			</form>
			</tr> 
		</c:forEach>
		</table>
      </div>
    </div>
</div>
<footer class="footer">
      <div class="container">
        <p class="text-muted">California State University, Los Angeles. California State University, Los Angeles. CS594 Web Service Project</p>
      </div>
</footer>
</body>
</html>
