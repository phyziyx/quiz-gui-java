package com.company;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;

/**
 * This is our Quiz Singleton class
 * @author Ali
 */
public class Quiz {
	private static String topic = "";
	private static int questionIndex = 0;
	private static int quizId = 0;
	private static final ArrayList<Question> questions = new ArrayList<>();
	private static int score = 0;
	private static boolean scoreShow = true;
	private static int passingPercentage = 0;
	private static boolean shuffleOn = true;
	private static int seconds = 0;

	/**
	 * Clears all the data set in the Quiz singleton
	 */
	public static void clear() {
		quizId = 0;
		score  = 0;
		questionIndex = 0;
		topic = "";
		scoreShow = false;
		shuffleOn = false;
		seconds = 0;
		passingPercentage = 0;
		questions.clear();
	}

	/**
	 * Deletes the provided quiz from the database
	 * @param quizId the quiz ID to delete
	 * @return <code>true</code> if successfully deleted, else <code>false</code> if failed
	 */
	public static boolean delete(int quizId) {
		try {
			PreparedStatement statement = Database.connection.prepareStatement("DELETE FROM quiz WHERE quiz.id = ? LIMIT 1");
			statement.setObject(1, quizId);

			return statement.executeUpdate() != 0;
		} catch (Exception ignored) {
			return false;
		}
	}

	/**
	 * Adds the question to the Quiz singleton
	 * @param question the question message
	 * @param answer the answer between 1 and 4
	 * @param answer1 the first choice
	 * @param answer2 the second choice
	 * @param answer3 the third choice
	 * @param answer4 the fourth and last choice
	 */
	public static void addQuestion(String question, int answer, String answer1, String answer2, String answer3, String answer4) {
		questions.add(new Question(question, answer, answer1, answer2, answer3, answer4));
	}

	/**
	 * Loads the provided quiz from the database into the singleton
	 * @param quizId the quiz ID to load
	 * @throws SQLException if an SQL error occurs
	 * @throws InvalidQuizID if the quiz ID is invalid
	 */
	public static void load(int quizId) throws SQLException, InvalidQuizID {
		PreparedStatement statement = Database.connection.prepareStatement("SELECT quiz.*, questions.* FROM quiz INNER JOIN questions ON questions.quizId = quiz.id WHERE quiz.id = ?");
		statement.setObject(1, quizId);

		ResultSet rs = statement.executeQuery();

		if (!rs.isBeforeFirst()) {
			System.out.println("bruh");
			throw new InvalidQuizID();
		}

		Quiz.clear();
		while (rs.next()) {
			Quiz.setTopic(rs.getString("name"));
			Quiz.setQuizId(rs.getInt("quizId"));
			Quiz.setScoreShow(rs.getBoolean("scoreShow"));
			Quiz.setPassingPercentage(rs.getInt("passingPercentage"));
			Quiz.setShuffle(rs.getBoolean("shuffleQuestions"));
			Quiz.setSeconds(rs.getInt("seconds"));
			Quiz.addQuestion(rs.getString("question"), rs.getInt("answer"), rs.getString("answer1"), rs.getString("answer2"), rs.getString("answer3"), rs.getString("answer4"));
		}
	}

	/**
	 * Inserts an attempt into the database with the information provided
	 */
	public static void insertAttempt() {
		try {
			PreparedStatement statement = Database.connection.prepareStatement("INSERT INTO `attempts` (quizId, userId, score, grade) VALUES (?, ?, ?, ?);");
			statement.setInt(1, quizId);
			statement.setInt(2, User.getUserId());
			statement.setInt(3, score);
			statement.setInt(4, (int) Helper.calculatePercentage(score, getTotalQuestions()));
			statement.executeUpdate();
		} catch (SQLException e) {
		}
	}

	/**
	 * Sets the topic.
	 *
	 * @param name The name of the topic.
	 */
	private static void setTopic(String name) {
		topic = name;
	}

	/**
	 * Gets the current topic.
	 *
	 * @return The current topic.
	 */
	public static String getTopic() {
		return topic;
	}

	/**
	 * Gets the total number of questions.
	 *
	 * @return The total number of questions.
	 */
	public static int getTotalQuestions() {
		return questions.size();
	}

	/**
	 * Advances to the next question.
	 */
	public static void nextQuestion() {
		questionIndex ++;
	}

	/**
	 * Compares the answer provided with the question's answer and increases the score if correct
	 * @param answerIndex the answer provided
	 */
	public static void compareAnswer(int answerIndex) {
		if (questions.get(questionIndex).getAnswer() != answerIndex) {
			return;
		}

		score ++;
	}

	/**
	 * Gets the user's score.
	 *
	 * @return The user's score.
	 */
	public static int getScore() {
		return score;
	}

	/**
	 * Gets the current question.
	 *
	 * @return <code>Question</code> class if valid, else <code>null</code>.
	 */
	public static Question getQuestion() {
		if (questionIndex < 0 || questionIndex >= questions.size()) {
			return null;
		}
		return questions.get(questionIndex);
	}

	/**
	 * If there's next question
	 * @return <code>true</code> if there are more questions, else <code>false</code>.
	 */
	public static boolean hasNextQuestion() {
		return (questionIndex + 1 < questions.size());
	}

	/**
	 * Gets the list of all the questions
	 * @return all Questions array
	 */
	public static ArrayList<Question> getQuestions() {
		return questions;
	}

	/**
	 * Set the quiz ID
	 * @param quizId provided Quiz ID
	 */
	public static void setQuizId(int quizId) {
		Quiz.quizId = quizId;
	}

	/**
	 * Is the score visibility on
	 * @return <code>true</code> if on, else <code>false</code>.
	 */
	public static boolean isScoreShow() {
		return scoreShow;
	}

	/**
	 * Set the score visibility status
	 * @param scoreShow <code>boolean</code> status whether the visibility will be on or off
	 */
	public static void setScoreShow(boolean scoreShow) {
		Quiz.scoreShow = scoreShow;
	}

	/**
	 * Get the passing percentage criteria.
	 * @return passing percentage criteria.
	 */
	public static int getPassingPercentage() {
		return passingPercentage;
	}

	/**
	 * Set the passing percentage criteria.
	 * @param passingPercentage the passing percentage criteria.
	 */
	public static void setPassingPercentage(int passingPercentage) {
		Quiz.passingPercentage = passingPercentage;
	}

	public static boolean getShuffle() {
		return shuffleOn;
	}

	public static void setShuffle(boolean shuffleOn) {
		Quiz.shuffleOn = shuffleOn;
	}

	public static void shuffle() {
		if (Quiz.questions.size() == 0) {
			return;
		}
		Collections.shuffle(Quiz.questions);
	}

	public static int getSeconds() {
		return seconds;
	}

	public static void setSeconds(int seconds) {
		Quiz.seconds = seconds;
	}
}
