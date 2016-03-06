package com.controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.configuration.MysqlDB;
import com.model.WebServiceModel;

/**
 * Servlet implementation class ManageExistingProjectController
 */
public class ManageExistingProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MysqlDB db = new MysqlDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageExistingProjectController() {
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
		ArrayList<WebServiceModel> lst= new ArrayList<WebServiceModel>();
		String sql = "select * from web_services";
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			 while(rs.next())
			 {
				 lst.add(new WebServiceModel(rs.getInt("id"), rs.getString("service_url"), rs.getString("name")));
				 
			 }
			 
			 if(rs!=null)
			 rs.close();
			 if(stmt!=null)
					stmt.close();
			if(c!=null)
				c.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		request.setAttribute("lst", lst);
		request.getRequestDispatcher("/WEB-INF/jsp/manageproject.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operation = request.getParameter("operation");
		int wsdlID = Integer.parseInt(request.getParameter("wsdlID"));
		String message = "";
		String sql ="";
		try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
		Connection c = db.dbConnection();
		
		if(operation.equals("update"))
		{
			String wsdlName = request.getParameter("wsdlName");
			String wsdlUrl = request.getParameter("wsdlUrl");
			
			sql = "UPDATE `cs594`.`web_services` SET`service_url` = '"+wsdlUrl+"',`name` = '"+wsdlName+"' WHERE `id` = "+wsdlID;
			Statement stmt;
			try {
				stmt = c.createStatement();
				stmt.executeUpdate(sql);
				
					 if(stmt!=null)
							stmt.close();
					if(c!=null)
						c.close();
					message = "Updated sucessfully !!";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		if(operation.equals("delete"))
		{
			c = db.dbConnection();
			sql = "{call DeleteWsdl (?)}";
			CallableStatement cstmt;
			
			try {
				cstmt = c.prepareCall(sql);
				cstmt.setInt(1, wsdlID);
				cstmt.executeUpdate();
				 if(cstmt!=null)
						cstmt.close();
				if(c!=null)
					c.close();
				message = "Deleted Successfully";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*sql = "delete from web_services where id="+wsdlID;
			String sql2 = "delete from web_services_time_consumed where service_id="+wsdlID;
			String sql3 = "delete from save_webservice_run where wsdl = "+wsdlID;*/
			
			
			
		}
		request.getSession().setAttribute("message", message);
		response.sendRedirect("ManageExistingProjectController");
	}

}
