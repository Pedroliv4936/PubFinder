package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class JDBC {
	private static final String URL = "jdbc:mysql://remotemysql.com:3306/Inr8NyMJYV";

	private static Connection con;
	private static final String USER = "Inr8NyMJYV";
	private static final String PASS = "Gd1YhhXXL5";

	private JDBC() {
	}

	public static Connection getConnection() {
		try {
			if (con == null)
				con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}