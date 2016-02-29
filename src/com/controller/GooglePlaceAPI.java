package com.controller;

import java.util.ArrayList;
import java.util.List;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;

public class GooglePlaceAPI {
	
	public String GooglePlacesWebService (String placeForm)
	{
		

		GooglePlaces client = new GooglePlaces("AIzaSyDfydAKTC8fByjtOHWlko5g8MqHtl7OAyA");
		String ID = "";
		String location = placeForm;
		 List<String> placeDetails = new ArrayList<String>();
		List<Place> places = client.getPlacesByQuery(location, GooglePlaces.MAXIMUM_RESULTS);
		Place empireStateBuilding = null;
		for (Place place : places) {
		    if (place.getName().equals(location)) {
		        empireStateBuilding = place;
		        break;
		    }
		}

		if (empireStateBuilding != null) {
		    Place detailedEmpireStateBuilding = empireStateBuilding.getDetails(); // sends a GET request for more details
		    // Just an example of the amount of information at your disposal:
		  
		     ID = detailedEmpireStateBuilding.getPlaceId();
		    /*String name = detailedEmpireStateBuilding.getName();
		    String googlePlaceURL = detailedEmpireStateBuilding.getGoogleUrl();
		    String address = detailedEmpireStateBuilding.getAddress();
		    String vicinity = detailedEmpireStateBuilding.getVicinity();
		    */
		   
		    /*placeDetails.add(ID);
		    placeDetails.add(name);
		    placeDetails.add(googlePlaceURL);
		    placeDetails.add(address);
		    placeDetails.add(vicinity);*/
		    
		   
		    
		    
		   /*System.out.println("ID: " + ID);
		    System.out.println("Name: " + detailedEmpireStateBuilding.getName());
		    System.out.println("Phone: " + detailedEmpireStateBuilding.getPhoneNumber());
		    System.out.println("International Phone: " + empireStateBuilding.getInternationalPhoneNumber());
		    System.out.println("Website: " + detailedEmpireStateBuilding.getWebsite());
		    System.out.println("Always Opened: " + detailedEmpireStateBuilding.isAlwaysOpened());
		    System.out.println("Status: " + detailedEmpireStateBuilding.getStatus());
		    System.out.println("Google Place URL: " + detailedEmpireStateBuilding.getGoogleUrl());
		    System.out.println("Price: " + detailedEmpireStateBuilding.getPrice());
		    System.out.println("Address: " + detailedEmpireStateBuilding.getAddress());
		    System.out.println("Vicinity: " + detailedEmpireStateBuilding.getVicinity());
		    System.out.println("Reviews: " + detailedEmpireStateBuilding.getReviews().size());
		    System.out.println("Hours:\n " + detailedEmpireStateBuilding.getHours());*/
		}
		if (empireStateBuilding == null) {
			
			
			 String error = "Sorry place ID is still not generated for this place !!";
			 placeDetails.add(error);
			
		}
		return ID;
	}

	/*
	public static void main(String [] args){
	

	GooglePlaces client = new GooglePlaces("AIzaSyDfydAKTC8fByjtOHWlko5g8MqHtl7OAyA");
	
	String location = "Walmart";
	
	List<Place> places = client.getPlacesByQuery(location, GooglePlaces.MAXIMUM_RESULTS);
	Place empireStateBuilding = null;
	for (Place place : places) {
	    if (place.getName().equals(location)) {
	        empireStateBuilding = place;
	        break;
	    }
	}

	if (empireStateBuilding != null) {
	    Place detailedEmpireStateBuilding = empireStateBuilding.getDetails(); // sends a GET request for more details
	    // Just an example of the amount of information at your disposal:
	  
	    String ID = detailedEmpireStateBuilding.getPlaceId();
	    String name = detailedEmpireStateBuilding.getName();
	    String googlePlaceURL = detailedEmpireStateBuilding.getGoogleUrl();
	    String address = detailedEmpireStateBuilding.getAddress();
	    String vicinity = detailedEmpireStateBuilding.getVicinity();
	    
	    List<String> placeDetails = new ArrayList<String>();
	    placeDetails.add(ID);
	    placeDetails.add(name);
	    placeDetails.add(googlePlaceURL);
	    placeDetails.add(address);
	    placeDetails.add(vicinity);
	    
	    
	    
	   System.out.println("ID: " + ID);
	    System.out.println("Name: " + detailedEmpireStateBuilding.getName());
	    System.out.println("Phone: " + detailedEmpireStateBuilding.getPhoneNumber());
	    System.out.println("International Phone: " + empireStateBuilding.getInternationalPhoneNumber());
	    System.out.println("Website: " + detailedEmpireStateBuilding.getWebsite());
	    System.out.println("Always Opened: " + detailedEmpireStateBuilding.isAlwaysOpened());
	    System.out.println("Status: " + detailedEmpireStateBuilding.getStatus());
	    System.out.println("Google Place URL: " + detailedEmpireStateBuilding.getGoogleUrl());
	    System.out.println("Price: " + detailedEmpireStateBuilding.getPrice());
	    System.out.println("Address: " + detailedEmpireStateBuilding.getAddress());
	    System.out.println("Vicinity: " + detailedEmpireStateBuilding.getVicinity());
	    System.out.println("Reviews: " + detailedEmpireStateBuilding.getReviews().size());
	    System.out.println("Hours:\n " + detailedEmpireStateBuilding.getHours());
	}
	if (empireStateBuilding == null) {
		System.out.println("nothing found");
	}
}*/
}
	

