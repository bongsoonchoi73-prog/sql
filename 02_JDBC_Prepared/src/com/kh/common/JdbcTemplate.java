package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTemplate {
	private static final String rset = null;

	/*
	 * JDBC과정에 사용되는 반복적으로 작성해야하는 코드 메소드로 분리할 클래스
	 */
	public static Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@115.90.212.18:10000/XEPDB1", "HOSPITAL",
					"oracle");
			return conn;
		} catch (SQLException e) {
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