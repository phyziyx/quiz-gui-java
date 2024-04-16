package com.company;



/**
 * Question class.
 * This stores the Question and its answers.
 * @author Ali
 */
public class Question {
	// Note to self: Do not change these constants unless database also has been modified!
	/**
	 * Max Answers choice constant
	 */
	public static final int MAX_ANSWERS = 4;

	private String question = "";
	private final int answer;
	private final String[] answers = new String[MAX_ANSWERS];

	/**
	 * The question constructor
	 * @param question the question message
	 * @param answer the answer between 1 and 4
	 * @param answer1 the first choice
	 * @param answer2 the second choice
	 * @param answer3 the third choice
	 * @param answer4 the fourth and last choice
	 */
	Question(String question, int answer, String answer1, String answer2, String answer3, String answer4) {
		this.question = question;
		this.answer = answer;

		this.answers[0] = "" + answer1;
		this.answers[1] = "" + answer2;

		this.answers[2] = "" + answer3;
		this.answers[3] = "" + answer4;
	}

	/**
	 * Get the question string
	 * @return String
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * Get the question's answer
	 * @return int
	 */
	public int getAnswer() {
		return answer;
	}

	/**
	 * Get the question's choices
	 * @return Array of String
	 */
	public String[] getAnswers() {
		return answers;
	}
}
