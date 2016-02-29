package com.dal;

import java.sql.*;

public class DatabaseConnection {
	
	public static Connection conn=null;
	public static ResultSet rs=null;
	public static Statement stmt=null;
	public static PreparedStatement pstmt=null;
	public static CallableStatement cstmt = null;
	public static String query= null; 
	
	 static {
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
	        } catch (Exception e){
	            e.printStackTrace();
	        }
	    }
	
	 
	 public static Connection getConnection() 
	    {
	        try {
	        	String url="jdbc:mysql://localhost/cs594";
	    		String username="root";
	    		String password="abcd";
	    		conn=DriverManager.getConnection(url, username, password);
	        }
	        catch (Exception e) {
	           System.out.println("get Connection : "+e.getMessage());
	        }
	        return conn;
	    }
	 
	 public static void closeConnection() {
		try
		{
			if(pstmt!=null)
				pstmt.close();
			if(stmt!=null)
			stmt.close();
			if(cstmt!=null)
			cstmt.close();
			if(conn!=null)
			conn.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Close Connection "+e.getMessage());
		}
	}

}
