<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <title>Web Service</title>
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
      <div class="panel-heading">Web Services</div>
      <div class="panel-body">
      <c:if test ="${existing ne null and existing ne ''}">
      	<div class="alert alert-success">
  				<c:out value="Web Service Loaded, now get methods !!"></c:out>
  				<c:set var="existing" value="" scope="session"></c:set>
			</div>
      </c:if>
     
      	<form action = "WSDLServiceController" method="post" role="form"  class="form-horizontal">
      	<div class="form-group">
			    <label for="text"  class="control-label col-sm-2"> Name </label>
			    <div class="col-sm-3"> 
				 	<c:if test="${wsdlName ne '' and wsdlName ne null }">
						<input class="form-control" type = "text" name = "wsdlName" value = "${wsdlName }" id ="wsdlName" required></input>
					</c:if>
					<c:if test="${wsdlName eq '' or wsdlName eq null }">
						<input class="form-control" type = "text" name = "wsdlName" id ="wsdlName" required></input>
					</c:if>
				 </div>
			 </div>
			<div class="form-group">
			    <label for="text"  class="control-label col-sm-2"> Enter WSDL URL </label>
			    <div class="col-sm-6"> 
				 	<c:if test="${wsdl ne '' and wsdl ne null }">
						<input type = "text" name ="wsdl"  class="form-control" id = "wsdl" required value = "${wsdl}" size="50"></input>
					</c:if>
					<c:if test="${wsdl eq '' or wsdl eq null }">
						
						<input type = "text" name ="wsdl" class="form-control" id = "wsdl" required></input>
					</c:if>
				 </div>
			 </div>
			  <div class="form-group">        
	     		<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-danger" name = "operation" value ="GetMethods">Get WSDL Methods</button>
			  	</div>
			  </div>
		</form>
		
		
		<hr />
	
	<c:if test="${allOps.size() > 0}">
		<form action = "WSDLServiceController" method= "post" role="form"  class="form-horizontal">
			<div class="form-group">
			    <label for="text"  class="control-label col-sm-2"> WSDL Methods </label>
			    <div class="col-sm-6"> 
				 	<select name = "methods" id = "methods" class="form-control">
						<c:forEach items="${allOps}" var="allOp">	
							<option value = "${allOp.getName() }" <c:if test = "${method eq allOp.getName()}">selected</c:if>><c:out value="${allOp.getName() }"></c:out></option>
						</c:forEach>
					</select>
				 </div>
			</div>
			 <div class="form-group">        
	     		<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-danger" name = "operation" value ="GetParameters">Get Method Parameters</button>
			  	</div>
			  </div>
		</form>
		<hr />
	</c:if>
	
	
	<c:if test = "${allInputs != null}">
		<form action = "WSDLServiceController" method= "post" role="form"  class="form-horizontal">
			<c:forEach items="${allInputs}" var="allInput">
			<div class="form-group">
			    <label for="text"  class="control-label col-sm-2"> <c:out value="${allInput.getName() }"></c:out> </label>
			    <div class="col-sm-6"> 
				 	<input type = "text"  name = "inputs[]" class="form-control"></input>
				 </div>
			 </div>
			 </c:forEach>
			 <div class="form-group">
			    <label for="text"  class="control-label col-sm-2"> No Of time to Run </label>
			    <div class="col-sm-6"> 
				 	<input type = "text" class="form-control" name = "timesToRun" id = "timesToRun" required></input>
				 </div>
			 </div>
			 
			 <div class="form-group">        
	     		<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary" name = "operation" value ="SetParameters">Run WSDL & Save Run</button>
			  	</div>
			  </div>
		</form>	
		<hr/>
	</c:if>	
	
	<c:if test = "${outs.size() > 0 }">
		<c:out value="${message}"></c:out> <br/>
		<c:forEach items = "${outs}" var = "out">
	    	<c:out value="${out.getName()}"></c:out>:
	    	<c:out value="${out.getValue()}"></c:out>
		</c:forEach> 
	</c:if>
	
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
