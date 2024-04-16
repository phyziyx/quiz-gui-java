package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * Database singleton class
 * for connecting and disconnecting to the SQL database
 * using JDBC driver
 * @author Ali
 */
public class Database {
	// Singleton
	static final private String SQL_USER = "root";
	static final private String SQL_PASS = "phyziyx";
	static final private String SQL_DATA = "quiz";
	static final private String SQL_PORT = "3306";
	static final private String SQL_IP   = "127.0.0.1";
	static final private String SQL_URL  = "jdbc:mysql://" + SQL_IP + ":" + SQL_PORT + "/" + SQL_DATA;

	/**
	 * Stores the Connection handle
	 */
	static public Connection connection = null;

	/**
	 * Connects to the database
	 * @return Connection
	 * @throws SQLException if SQL exception occurs
	 * @throws ClassNotFoundException if SQL driver is not found
	 */
	static public Connection connect() throws SQLException, ClassNotFoundException {
		if (connection == null || connection.isClosed()) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(SQL_URL, SQL_USER, SQL_PASS);
		}
		return connection;
	}

	/**
	 * Disconnects from the database
	 * @throws SQLException if SQL exception occurs
	 */
	static public void disconnect() throws SQLException {
		if (connection == null || connection.isClosed()) {
			return;
		}

		connection.close();
	}
}
