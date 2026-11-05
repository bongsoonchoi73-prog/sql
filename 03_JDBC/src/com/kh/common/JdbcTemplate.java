package com.kh.common;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcTemplate {
	private static final String rset = null;

	/*
	 * JDBC과정에 사용되는 반복적으로 작성해야하는 코드 메소드로 분리할 클래스
	 */
	public static Connection getConnection() {
		try {
			Properties prop = new Properties();
			
			prop.load(new FileInpustStream("resotes/connection.properties"));
			
			System.out.println(prop.getProperty("URL"));
			
			Connection conn = DriverManager.getConnection(prop.getProperty("URL")
					                                     ,prop.getProperty("USERNAME")
					                                     ,prop.getProperty("PASSWORD"));
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOExeption e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close(Connection conn) {
		try {
			if (rset != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
}