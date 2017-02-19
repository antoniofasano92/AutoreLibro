package it.gestioneautorelibri.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {

	private static String driver_name = "com.mysql.jdbc.Driver";
	private static String connect_str = "jdbc:mysql://localhost:3306/gestioneautorelibri?user=root&password=root";

	// private static Connection connection;

	public static Connection getConnection() throws Exception {

		Connection c = null;
		try {
			Class.forName(driver_name);
			c = DriverManager.getConnection(connect_str);
			return c;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

	}

	private static Connection createConnection() {

		Connection c = null;
		try {
			Class.forName(driver_name);
			c = DriverManager.getConnection(connect_str);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return c;
	}
}
