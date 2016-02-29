package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.configuration.MysqlDB;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;


@WebServlet("/GoogleServiceController")
public class GoogleServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 MysqlDB db = new MysqlDB();
	//GooglePlaceAPI googleAPI;
	
	public List<String> GooglePlacesWebService (String placeForm)
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
		    String name = detailedEmpireStateBuilding.getName();
		    String googlePlaceURL = detailedEmpireStateBuilding.getGoogleUrl();
		    String address = detailedEmpireStateBuilding.getAddress();
		    String vicinity = detailedEmpireStateBuilding.getVicinity();
		    
		   
		    placeDetails.add(ID);
		    placeDetails.add(name);
		    placeDetails.add(googlePlaceURL);
		    placeDetails.add(address);
		    placeDetails.add(vicinity);
		    
		}
		if (empireStateBuilding == null) {
			
			
			 String error = "Sorry place ID is still not generated for this place !!";
			 placeDetails.add(error);
			
		}
		return placeDetails;
	}
	
    public GoogleServiceController() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/jsp/GoogleService.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String place = request.getParameter("place");
		
		long start = System.currentTimeMillis();
		List<String> placeDetails = GooglePlacesWebService(place);
		long end = System.currentTimeMillis();
		
		long timeTaken = end - start;
		
		try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
		Connection c = db.dbConnection();
		String insertSql = "INSERT INTO `cs594`.`googleservice` (`location_name`,`time_of_execution`,`time_taken`) "
				+ "VALUES ('"+place+"',CURRENT_TIMESTAMP(),"+timeTaken+");";
		try {
			Statement stmt = c.createStatement();
			stmt.executeUpdate(insertSql);
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(placeDetails.size() > 0)
		{
			request.setAttribute("placeDetails", placeDetails);
		}
		else
		{
			request.setAttribute("error", "Place ID is not yet generated for entered Place !!");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/GoogleService.jsp").forward(request, response);
		
	}

}
