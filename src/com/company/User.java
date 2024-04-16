package com.company;



import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * User singleton class
 * @author Ali
 */
public class User {
	private static int userId = 0;
	private static String userName = null;
	private static String passWord = null;
	private static boolean power   = false;
	private static boolean loggedIn = false;

	/**
	 * Get the user's name
	 * @return Get the user's name
	 */

	public static String getUserName() {
		return userName;
	}

	/**
	 * Is user logged in
	 * @return <code>true</code> if the user is logged in, else <code>false</code>.
	 */
	public static boolean IsLoggedIn() {
		return loggedIn;
	}

	/**
	 * Attempts to log in the user
	 * @return <code>true</code> if successfully logged in, else <code>false</code>.
	 */
	static public boolean attemptLogin() {
		try {
			PreparedStatement statement = Database.connection.prepareStatement("SELECT * FROM users WHERE user = ? AND pass = ?");
			statement.setObject(1, userName);
			statement.setObject(2, passWord);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				loggedIn = true;
				power = rs.getBoolean("power");
				userId = rs.getInt("id");
			} else {
				loggedIn = false;
			}
		} catch (Exception ignored) {
			loggedIn = false;
		}

		return loggedIn;
	}

	/**
	 * Check if the account exists or not
	 * @return <code>true</code> if exists, else <code>false</code>.
	 */
	static public boolean accountExists() {
		try {
			PreparedStatement statement = Database.connection.prepareStatement("SELECT 1 FROM users WHERE user = ? LIMIT 1");
			statement.setObject(1, userName);

			ResultSet rs = statement.executeQuery();
			return rs.next();
		} catch (Exception ignored) {
			return false;
		}
	}

	/**
	 * Attempts to register the user
	 * @return <code>true</code> if successfully registered, else <code>false</code>.
	 */
	static public boolean attemptRegister() {
		try {
			PreparedStatement statement = Database.connection.prepareStatement("INSERT INTO users (user, pass) VALUES (?, ?)");
			statement.setObject(1, userName);
			statement.setObject(2, passWord);

			return statement.executeUpdate() != 0;
		} catch (Exception ignored) {
			return false;
		}
	}

	/**
	 * If the user is Teacher
	 * @return <code>true</code> if is teacher, else <code>false</code>.
	 */
	public static boolean hasPower() {
		return power;
	}

	/**
	 * Sets the user's name
	 * @param text the username to set
	 */
	public static void setUserName(String text) {
		userName = text;
	}

	/**
	 * Sets the user's password
	 * @param text the password to set
	 */
	public static void setPassWord(char[] text) {
		passWord = String.valueOf(text);
	}

	/**
	 * Get the user's password
	 * @return user's password
	 */
	public static String getPassWord() {
		return passWord;
	}

	/**
	 * Get the user's SQL ID
	 * @return user's SQL ID
	 */
	public static int getUserId() {
		return userId;
	}
}