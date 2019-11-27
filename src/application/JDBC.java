package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class JDBC {
	private static final String URL = "jdbc:mysql://localhost:3306/pub_finder";

	private static Connection con;
	private static final String USER = "piriurna";
	private static final String PASS = "Arm210526";

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