package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.configuration.MysqlDB;

//import java.net.URL;
//import java.net.URLConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Servlet implementation class CustomService
 */

public class CustomService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 MysqlDB db = new MysqlDB();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/CustomService.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int repeation=Integer.parseInt(request.getParameter("op2"));
		long start = System.currentTimeMillis();
		
		
		
		for(int i=0;i<repeation;i++){
		//URL urldemo = new URL(request.getParameter("op1") );
        //URLConnection yc = urldemo.openConnection();
        
int ii=100;
ii*=ii;
       
        }
		long end = System.currentTimeMillis();
		long timeTaken = end - start;
		
		
	System.out.println(timeTaken);	 
	/*INSERT INTO `cs594`.`custom_service`(`url`,`time_of_execution`,`time_taken`)
	VALUES (<{url: }>,<{time_of_execution: }>,<{time_taken: }>);*/
	try
    {
        Class.forName( "com.mysql.jdbc.Driver" );
    }
    catch( ClassNotFoundException e )
    {
        throw new ServletException( e );
    }
	Connection c = db.dbConnection();
	String insertSql = "INSERT INTO `cs594`.`custom_service` (`url`,`time_of_execution`,`time_taken`,`no_of_times_executed`) "
			+ "VALUES ('"+request.getParameter("op1")+"',CURRENT_TIMESTAMP(),"+timeTaken+","+Integer.parseInt(request.getParameter("op2"))+");";
	try {
		Statement stmt = c.createStatement();
		stmt.executeUpdate(insertSql);
		stmt.close();
		c.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		request.setAttribute("time", timeTaken);
		request.getRequestDispatcher("/WEB-INF/jsp/CustomService.jsp").forward(request, response);
	}

}

/*
DatabaseConnection.conn = DatabaseConnection.getConnection();

DatabaseConnection.query = "INSERT INTO time_consumed(time_consumed,Date)VALUES(?,?)";
DatabaseConnection.pstmt = DatabaseConnection.conn.prepareStatement(DatabaseConnection.query);
DatabaseConnection.pstmt.setString(1," "+totalTime+"".trim());
DatabaseConnection.pstmt.setString(2,new java.util.Date().toString());
DatabaseConnection.pstmt.executeUpdate();

} catch (Exception e) {
System.out.println(e);
}finally
{
DatabaseConnection.closeConnection();
}*/

