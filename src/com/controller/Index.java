package com.controller;

import java.io.IOException;
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
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 MysqlDB db = new MysqlDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
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
		request.getRequestDispatcher("/WEB-INF/jsp/indx.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String existing = request.getParameter("existing");
		request.getSession().setAttribute("existing", existing);
		getServletContext().setAttribute("wsdl", request.getParameter("wsdl").toString());
		//System.out.println(request.getParameter("name"));
		request.getSession().setAttribute("wsdlName", request.getParameter("name"));
		//System.out.println(getServletContext().getAttribute("wsdl"));
		response.sendRedirect("WSDLServiceController");
		
	}

}

