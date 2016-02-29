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
    <style>
    html, body {
        height: 100%;
        margin: 0;
        padding: 20px;
      } 
  #map {
        height: 50%;
        width: 50%
  }
.controls {
  margin-top: 10px;
  border: 1px solid transparent;
  border-radius: 2px 0 0 2px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
  height: 32px;
  outline: none;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
}

#pac-input {
  background-color: #fff;
  font-family: Roboto;
  font-size: 15px;
  font-weight: 300;
  margin-left: 12px;
  padding: 0 11px 0 13px;
  text-overflow: ellipsis;
  width: 300px;
}

#pac-input:focus {
  border-color: #4d90fe;
}

.pac-container {
  font-family: Roboto;
}

#type-selector {
  color: #fff;
  background-color: #4d90fe;
  padding: 5px 11px 0px 11px;
}

#type-selector label {
  font-family: Roboto;
  font-size: 13px;
  font-weight: 300;
}

    </style>
  </head>
  
  <body>
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
						<a href="CustomService">Custom Service</a>
					</li>				
					<li>
						<a href="AnalysisController">Analysis</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="body" class="home">
			
			<div class="body">
				<div style = "padding: 31px 10px 0;">
					<h1>Web Services</h1>
					<p> Google Service</p>
					
					<c:if test="${placeDetails.size() > 0}">
						<table style = "color: white;border-style: dotted; border: medium;">
							<tr><th>Place ID</th><th>Name</th><th>Google URL</th><th>Address</th><th>Vicinity</th></tr>	
							<tr>
								
								<c:forEach items="${placeDetails}" var="details">
								<td><c:out value="${details }"></c:out></td>
								</c:forEach>
								<c:set var="res" value=" "></c:set>							
							</tr>
						</table>
						</c:if>	
					<c:if test="${error}">
						<p>  <c:out value="${error }"></c:out></p>
						<c:set var="error" value=" "></c:set>
					</c:if>	
					
					<div class="header">
						
							<form action = "GoogleServiceController" method = "post">
								
									<input type = "text" name = "place" value="Enter Place Name" id = "op1" onblur="this.value=!this.value?'Enter Place Name':this.value;" onfocus="this.select()" onclick="this.value='';"/>
							
									<input type = "submit" value = "Search Place" id="submit" />
								
									<input type = "reset" value ="clear" id="submit"/>
								
							</form>		
				</div>	
			</div>
				
			</div>
			
		<div id="footer">
			<div class="connect">
				<div>
					<h1>CS 594 - Group 11</h1>
					
				</div>
			</div>
		</div>
	</div>
	</div>
	<!--  -kkjkjjjjjjjjjjjjjjjjjffffffdassssssssssssssdddssdsssssssssssssssssss -->

 
  </body>
   <input id="pac-input" name = "pac-input" class="controls" type="text"
        placeholder="Enter a location">
    <div id="type-selector" class="controls">
      <input type="radio" name="type" id="changetype-all" checked="checked">
      <label for="changetype-all">All</label>

      <input type="radio" name="type" id="changetype-establishment">
      <label for="changetype-establishment">Establishments</label>

      <input type="radio" name="type" id="changetype-address">
      <label for="changetype-address">Addresses</label>

      <input type="radio" name="type" id="changetype-geocode">
      <label for="changetype-geocode">Geocodes</label>
    </div>
    <div id="map"></div>
    <script>
// This example requires the Places library. Include the libraries=places
// parameter when you first load the API. For example:
// <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">

function initMap() {
  var map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 34.0667, lng: -118.1678},
    zoom: 16
  });
  var input = /** @type {!HTMLInputElement} */(
      document.getElementById('pac-input'));

  var types = document.getElementById('type-selector');
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(types);

  var autocomplete = new google.maps.places.Autocomplete(input);
  autocomplete.bindTo('bounds', map);

  var infowindow = new google.maps.InfoWindow();
  var marker = new google.maps.Marker({
    map: map,
    anchorPoint: new google.maps.Point(0, -29)
  });

  autocomplete.addListener('place_changed', function() {
    infowindow.close();
    marker.setVisible(false);
    var place = autocomplete.getPlace();
    if (!place.geometry) {
      window.alert("Autocomplete's returned place contains no geometry");
      return;
    }

    // If the place has a geometry, then present it on a map.
    if (place.geometry.viewport) {
      map.fitBounds(place.geometry.viewport);
    } else {
      map.setCenter(place.geometry.location);
      map.setZoom(17);  // Why 17? Because it looks good.
    }
    marker.setIcon(/** @type {google.maps.Icon} */({
      url: place.icon,
      size: new google.maps.Size(20, 20),
      origin: new google.maps.Point(0, 0),
      anchor: new google.maps.Point(17, 34),
      scaledSize: new google.maps.Size(20, 20)
    }));
    marker.setPosition(place.geometry.location);
    marker.setVisible(true);

    var address = '';
    if (place.address_components) {
      address = [
        (place.address_components[0] && place.address_components[0].short_name || ''),
        (place.address_components[1] && place.address_components[1].short_name || ''),
        (place.address_components[2] && place.address_components[2].short_name || '')
      ].join(' ');
    }

    infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
    infowindow.open(map, marker);
  });

  // Sets a listener on a radio button to change the filter type on Places
  // Autocomplete.
  function setupClickListener(id, types) {
    var radioButton = document.getElementById(id);
    radioButton.addEventListener('click', function() {
      autocomplete.setTypes(types);
    });
  }

  setupClickListener('changetype-all', []);
  setupClickListener('changetype-address', ['address']);
  setupClickListener('changetype-establishment', ['establishment']);
  setupClickListener('changetype-geocode', ['geocode']);
}

    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDfydAKTC8fByjtOHWlko5g8MqHtl7OAyA&signed_in=true&libraries=places&callback=initMap"
        async defer></script>
</html>