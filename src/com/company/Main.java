package com.company;



import java.sql.*;

/**
 * Main driver
 * @author Ali
 */
public class Main {
	/**
	 * Programme entry point
	 * @param args commandline arguments
	 */
	public static void main(String[] args) {
		try {
			Database.connect();
		} catch (SQLException e) {
			System.out.println("SQL connection failed!  Programme exiting...");
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("SQL driver not found!  Programme exiting...");
			return;
		}

		// Database connected!
		new MainWindow();
	}
}