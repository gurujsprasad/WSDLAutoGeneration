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
import com.model.WebServiceModel;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class ProjectManagement
 */
public class ProjectManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 MysqlDB db = new MysqlDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectManagement() {
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
		
		
		String sql = "SELECT a.*,b.name FROM cs594.save_webservice_run a, web_services b where b.id = a.wsdl;";
		List<WebServiceModel> models= new ArrayList<WebServiceModel>();
		try {
			PreparedStatement pstmt = (PreparedStatement) c.prepareStatement(sql);
			 ResultSet rs=pstmt.executeQuery();
			// System.out.println(exist);
			 while(rs.next())
			 {
				 	models.add(new WebServiceModel(rs.getInt("id"), rs.getString("name"), rs.getString("method"), rs.getDouble("time_taken"), Integer.parseInt(rs.getString("times_run"))));
			 }
			
			 if(rs!=null)
			 rs.close();
			 if(pstmt!=null)
					pstmt.close();
			
			 if(c!=null)
				 c.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		request.setAttribute("model", models);
		request.getSession().setAttribute("wsdlName", "");
		getServletContext().setAttribute("wsdlName", "");
		getServletContext().setAttribute("wsdl", "");
		request.getRequestDispatcher("/WEB-INF/jsp/ProjectManagement.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
