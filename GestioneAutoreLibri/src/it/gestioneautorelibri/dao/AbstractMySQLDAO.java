package it.gestioneautorelibri.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractMySQLDAO implements IBaseDAO {
	
	protected Connection connection;
	
	

	//private String driver_name = "com.mysql.jdbc.Driver";
	//private String connect_str = "jdbc:mysql://localhost:3306/corso?user=root&password=";
	
	
	public AbstractMySQLDAO() {
	}
	public AbstractMySQLDAO(Connection connection) {
		this.connection = connection;
	}
	

	
	protected  boolean isNotActive() throws SQLException {
		try {
			return this.connection == null || this.connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	

}
