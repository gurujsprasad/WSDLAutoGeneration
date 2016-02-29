package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.configuration.MysqlDB;

//@WebServlet("/AnalysisController")
public class AnalysisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 MysqlDB db = new MysqlDB();  
    public AnalysisController() {
        super();
      
    }  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
		Connection c = db.dbConnection();
		String localData = "SELECT AVG(time_taken)  as time_taken FROM webservicedetails";
		String googleData = "SELECT AVG(time_taken) as time_taken FROM custom_service";
		long localAverageTime = 0 ;
		long googleAverageTime = 0;
		ResultSet results;
		try {
			Statement stmt = c.createStatement();
			results = stmt.executeQuery(localData);
			while (results.next())
			{
				localAverageTime = results.getLong("time_taken");
			}
			results.close();
			
			results = stmt.executeQuery(googleData);
			while (results.next())
			{
				googleAverageTime = results.getLong("time_taken");
			}
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("localTime", localAverageTime);
		request.setAttribute("googleTime", googleAverageTime);
		request.getRequestDispatcher("/WEB-INF/jsp/Analysis.jsp").forward(request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
