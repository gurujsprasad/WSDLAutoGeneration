package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.configuration.MysqlDB;
import com.service.*;
import com.service.ArithmeticOperatorWebServiceStub.ArithmeticOperation;
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    MysqlDB db = new MysqlDB();
	
    public Home() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		double op1 = Double.parseDouble(request.getParameter("op1"));
		double op2 = Double.parseDouble(request.getParameter("op2"));
		
		ArithmeticOperatorWebServiceStub stub = new ArithmeticOperatorWebServiceStub();
		ArithmeticOperation ope = new ArithmeticOperation();
		ope.setOp1(op1);
		ope.setOp2(op2);
		
		long start = System.currentTimeMillis();
		double res = stub.arithmeticOperation(ope).get_return();
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
		
		
		String insertSql = "insert into webservicedetails (`time_taken`) values ('"+timeTaken+"')";
		try {
			Statement stmt = c.createStatement();
			stmt.executeUpdate(insertSql);
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().println(c);
		request.setAttribute("res", res);
		request.setAttribute("time", timeTaken);
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		
	}

}
