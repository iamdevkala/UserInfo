package com.vastika.userinfo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static final String URL = "jdbc:mysql://localhost:3306/demo_db";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "oasis2010";

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

}