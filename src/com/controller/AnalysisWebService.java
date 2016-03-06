package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.configuration.MysqlDB;

import com.controller.*;

/**
 * Servlet implementation class AnalysisWebService
 */
public class AnalysisWebService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MysqlDB db = new MysqlDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnalysisWebService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		List<AnalysisGraphBean> bean = new ArrayList<AnalysisGraphBean>();
		String sql = "select a.name,avg(b.time_per_request) as time_per_request from web_services a, web_services_time_consumed b where a.id=b.service_id  group by b.service_id;";
		ResultSet results;
		Statement stmt = null;
		try {
			stmt = (Statement) c.createStatement();
			results = stmt.executeQuery(sql);
			while(results.next())
			{
				bean.add(new AnalysisGraphBean(results.getString("name"),results.getInt("time_per_request") ));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("beanGraph",bean );
		request.getRequestDispatcher("/WEB-INF/jsp/AnalysisWebService.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
