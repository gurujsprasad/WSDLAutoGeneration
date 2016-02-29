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
      <li class="active"><a href="Home">Home</a></li>
      <li><a href="ProjectManagement">Project Management</a></li>
      <li><a href="WSDLServiceController">New WebService Project</a></li>
      <li><a href="Index">Existing WebService Project</a></li>
      <li><a href="AnalysisWebService">Analysis of WebService</a></li>
    </ul>
  </div>
</nav>
    <div class="panel panel-default">
      <div class="panel-heading"> Existing Web Services</div>
      <div class="panel-body">
      	<form action="index" method="post" role="form"  class="form-horizontal">
			<div class="form-group">
			    <label for="text"  class="control-label col-sm-2"> Web Services</label>
			    <div class="col-sm-3"> 
				 	<select name = "wsdl">
						<c:forEach items="${lst}" var="l">
							<option value = "${l}">${l}</option>
						</c:forEach>
					</select>
				 </div>
			 </div>
			  <div class="form-group">        
	     		<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-danger" name = "submit" value = "SelectService">Select Service</button>
			  	</div>
			  </div>
		</form>
      </div>
    </div>
</div>
<footer class="footer">
      <div class="container">
        <p class="text-muted">California State University, Los Angeles. CS594 Web Service Project</p>
      </div>
</footer>
</body>
</html>
