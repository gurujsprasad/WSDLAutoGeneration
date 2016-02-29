<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
  <title>Analysis WebService</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script>
$(function () {
    $('#container').highcharts({
        data: {
            table: 'datatable'
        },
        chart: {
            type: 'column'
        },
        title: {
            text: 'Data extracted from DataBase for WSDL Services'
        },
        yAxis: {
            allowDecimals: false,
            title: {
                text: 'MilliSeconds'
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    this.point.y + ' ' + this.point.name.toLowerCase();
            }
        }
    });
});
</script>
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
      <div class="panel-heading"> Bar Graph Analysis</div>
      <div class="panel-body">
      	<div class="list-group">
		  <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		   <div style = "visibility: hidden">
		   <table id="datatable">
		    <thead> 
		    <tr> 
	            <c:forEach items="${beanGraph}" var="bean">
	            	
		            <th><br/></th>
			    </c:forEach>
			     </tr>
		    </thead>
		    <tbody>
			    <c:forEach items="${beanGraph}" var="bean">
			    	<tr>
			           <th>${bean.wsdlName }</th>
			            <td>${bean.averageTime }</td>
			        </tr>
			    </c:forEach>
		    </tbody>
		</table>
		</div>
		</div>
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
