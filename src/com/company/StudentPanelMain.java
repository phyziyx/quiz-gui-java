/*
 * Created by JFormDesigner on Tue Dec 27 14:25:23 PKT 2022
 */

package com.company;



import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Creates a GUI panel for students
 * @author Ali
 */
public class StudentPanelMain  {

	StudentPanelMain() {
		initComponents();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(600, 200));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * Prompts the user to input a Quiz id
	 * @param message Optional message to display
	 */
	private void askQuizId(String message) {
		String userInput = JOptionPane.showInputDialog(frame, "Insert the quiz ID:" + ((message.length() == 0) ? "" : ("\nNote: " + message)), "Quiz Loader", JOptionPane.QUESTION_MESSAGE);

		// Cancel button is pressed
		if (userInput == null) {
			frame.setVisible(true);
			return;
		}

		try {
			int quizId = Integer.parseInt(userInput);
			Quiz.load(quizId);
			frame.setVisible(false);
			new QuizWindow(this);
		} catch (SQLException ignored) {
			askQuizId("An SQL error occurred!");
		} catch (Exception e) {
			askQuizId("You inserted an invalid quiz ID!");
		}
	}

	/**
	 * Handler function for the 'Attempt a Quiz' button
	 */
	private void attemptQuiz(ActionEvent ignored) {
		askQuizId("");
	}

	/**
	 * Shows the Student their Quiz attempt history
	 */
	private void viewHistory(ActionEvent ignored) {
		frame.setVisible(false);
		new ViewAttempts(this);
	}

	/**
	 * Handles the exit button by... you guessed it,
	 * EXITING the application.
	 */
	private void exitButton(ActionEvent ignored) {
		System.exit(0);
	}

	/**
	 * To show the student panel from the child frame
	 */
	public void show() {
		frame.setVisible(true);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		frame = new JFrame();
		var dialogPane = new JPanel();
		var contentPanel = new JPanel();
		var studentPanelLabel = new JLabel();
		var attemptQuiz = new JButton();
		var viewAttemptHistory = new JButton();
		var exitButton = new JButton();

		//======== frame ========
		{
			frame.setTitle("Quiz Management System - Student Panel");
			var frameContentPane = frame.getContentPane();
			frameContentPane.setLayout(new BorderLayout());

			//======== dialogPane ========
			{
				dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
				dialogPane.setLayout(new BorderLayout());

				//======== contentPanel ========
				{
					contentPanel.setLayout(new GridBagLayout());
					((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0};
					((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
					((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 0.0, 0.0, 1.0E-4};
					((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

					//---- studentPanelLabel ----
					studentPanelLabel.setText("Student Panel");
					studentPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
					studentPanelLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
					contentPanel.add(studentPanelLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

					//---- attemptQuiz ----
					attemptQuiz.setText("Attempt a Quiz");
					attemptQuiz.addActionListener(e -> attemptQuiz(e));
					contentPanel.add(attemptQuiz, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

					//---- viewAttemptHistory ----
					viewAttemptHistory.setText("View your Quiz History");
					viewAttemptHistory.addActionListener(e -> viewHistory(e));
					contentPanel.add(viewAttemptHistory, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

					//---- exitButton ----
					exitButton.setText("Exit");
					exitButton.addActionListener(e -> exitButton(e));
					contentPanel.add(exitButton, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));
				}
				dialogPane.add(contentPanel, BorderLayout.CENTER);
			}
			frameContentPane.add(dialogPane, BorderLayout.CENTER);
			frame.pack();
			frame.setLocationRelativeTo(frame.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	private JFrame frame;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
