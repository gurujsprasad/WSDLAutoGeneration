package com.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.configuration.*;

import com.mysql.jdbc.ResultSet;

public class MysqlDB {
	
	ClientConfiguration conf = new ClientConfiguration();
	public Connection dbConnection()
	{
		Connection connection = null;
		try {
			connection = (Connection) DriverManager.getConnection( conf.url, conf.username, conf.password );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
		
	}
	
	public ResultSet selectStatement(String query, Connection connection)
	{
		Statement stmt;
		ResultSet rs = null;
		try {
			stmt = (Statement) connection.createStatement();
			rs = (ResultSet) stmt.executeQuery( query );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	
	public void insertUpdateStatement(String query, Connection connection)
	{
		Statement stmt;
		try {
			stmt = (Statement) connection.createStatement();
			stmt.executeUpdate( query );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
