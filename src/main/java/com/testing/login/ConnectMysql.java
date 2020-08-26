package com.testing.login;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class ConnectMysql {

	public Connection conn;
	
	public ConnectMysql() {
		try {
		
			FileInputStream fis=new FileInputStream(this.getClass().getResource("").getPath() +"/inter.properties");
			Properties prop=new Properties();
		
			prop.load(fis);
		
			String dburl=prop.getProperty("DBURL");
			String dbuser=prop.getProperty("DBUSER");
			String dbpwd=prop.getProperty("DBPWD");
			
			Class.forName("com.mysql.jdbc.Driver");
		
			conn=DriverManager.getConnection(dburl,dbuser,dbpwd);
	
			DriverManager.setLoginTimeout(10);
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
