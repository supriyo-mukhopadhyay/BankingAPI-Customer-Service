package com.dao.layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectivity {
	static Connection conn =null;
	
	public static Connection getConnectionUrl() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		final String url = "jdbc:mysql://localhost:3306/bankingschema";
		final String userName = "root";
		final String password = "AimingSam(5)";
		
		conn = DriverManager.getConnection(url, userName, password);
		return conn;
	}

}
