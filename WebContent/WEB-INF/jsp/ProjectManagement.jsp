<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <title>Project Management</title>
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
      <div class="panel-heading"> Menu</div>
      <div class="panel-body">
      	<p>Latest Project Runs </p>
      	<table class="table table-hover">
						 <thead>
					      <tr>
					        <th>WSDL Name</th>
					        <th>Method</th>
					        <th>Times Run</th>
					        <th>Time Taken</th>
					        <th>View & Run</th>
					      </tr>
					    </thead>
		    			<tbody>
      	<c:forEach items="${model}" var="m">
      	
      	<tr><td>${m.wsdlName }</td><td>${m.method }</td><td>${m.timesRun }</td><td>${m.timePerRequest }</td><td><a href = "ViewPreRun?id=${m.wsdlID}">View</a></td></tr>
      	
      	</c:forEach>
      	</tbody>
      	</table>
      	<!-- <div class="list-group">
		  <a href="WSDLServiceController" class="list-group-item">New WebService Project</a>
		  <a href="Index" class="list-group-item">Existing WebService Project</a>
		</div> -->
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
