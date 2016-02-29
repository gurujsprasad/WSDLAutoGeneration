<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link href='http://pics.cssbakery.com/pics/css/verticalbargraph.css' rel='stylesheet' type='text/css'/>
</head>

<body>

<div class="bargraph" style= "width: 200px;">
    <ul class="bars">
        <li class="bar1 purplebar" style="height: 100px;">${localTime}</li>
        <li class="bar2 redbar" style="height: 170px;">${googleTime}</li>
        <!-- <li class="bar3 bluebar" style="height: 70px;">35</li>
        <li class="bar4 greenbar" style="height: 14px;"><span>7</span></li>
        <li class="bar5 orangebar" style="height: 160px;">80</li> -->
    </ul>
<ul class="label"><li>Local</li><li>Custom</li><!-- <li>blueberry</li><li>kiwi</li><li>orange</li> --></ul>
<ul class="y-axis"><li>5000ms</li><li>3000ms</li><li>2000ms</li><li>1000ms</li><li>500ms</li><li>0ms</li></ul>
<p class="centered">Web Services Comparison</p> 
</div>

</body>
</html>