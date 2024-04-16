package com.company;



/**
 * Exception that is thrown when a quiz ID is inserted that does not exist.
 * @author Ali
 */
public class InvalidQuizID extends Exception {
	/**
	 * Constructor with the default message
	 * "You inserted a quiz ID that does not exist"
	 */
	public InvalidQuizID() {
		super("You inserted a quiz ID that does not exist");
	}
}
