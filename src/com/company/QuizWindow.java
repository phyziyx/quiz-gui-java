/*
 * Created by JFormDesigner on Tue Dec 20 23:01:33 PKT 2022
 */

package com.company;

import java.awt.*;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Quiz Window
 * @author Ali
 */
public class QuizWindow  {

	StudentPanelMain window;
	Timer quizTimer = null;

	/**
	 * Creates the Quiz Window
	 * @param window
	 */
	public QuizWindow(StudentPanelMain window) {
		initComponents();

		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
		frame1.setResizable(false);
		frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.window = window;

		if (Quiz.getShuffle()) {
			Quiz.shuffle();
		}

		usernameLabel.setText(User.getUserName());
		updateQuestion();

		// Timer is not set!
		int seconds = Quiz.getSeconds();
		if (seconds == 0) {
			quizInfoLabel.setVisible(false);
		} else {
			// Start timer
			quizTimer = new Timer("Timer");
			quizTimer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					int seconds = Quiz.getSeconds() - 1;
					Quiz.setSeconds(seconds);
					int min = seconds / 60;
					int sec = seconds % 60;
					quizInfoLabel.setText(String.format(Locale.ENGLISH, "%02d:%02d left!", min, sec));

					if (seconds == 0) {
						submitQuiz();
					}
				}
			}, 0, 1000);
		}
	}

	/**
	 * Updates the question on the Quiz Window
	 */
	private void updateQuestion() {
		Question question = Quiz.getQuestion();
		String[] answers  = question.getAnswers();

		questionLabel.setText("<html><center>" + question.getQuestion() + "</center></html>");

		for (int i = 0; i < Question.MAX_ANSWERS; i ++) {
			answerButtons[i].setText(answers[i]);
		}
		answerGroup.clearSelection();

		if (Quiz.hasNextQuestion()) {
			nextSubmitButton.setText("Next");
		} else {
			nextSubmitButton.setText("Submit");
		}
	}

	/**
	 * Checks the user's answer and advances the quiz
	 */
	private void quizNext() {

		int answer = 0;
		for (int i = 0; i < Question.MAX_ANSWERS; i ++) {
			if (answerButtons[i].isSelected()) {
				answer = i + 1;
				break;
			}
		}

		if (answer == 0) {
			JOptionPane.showMessageDialog(frame1, "You have not selected any answer!", "Quiz", JOptionPane.WARNING_MESSAGE);
			return;
		}

		Quiz.compareAnswer(answer);

		if (Quiz.hasNextQuestion()) {
			Quiz.nextQuestion();
			updateQuestion();
			return;
		}

		submitQuiz();
	}

	/**
	 * Function to call when the quiz has been completed,
	 * either by the user or by the timer function
	 */
	private void submitQuiz() {
		if (quizTimer != null) {
			quizTimer.cancel();
		}

		StringBuilder message = new StringBuilder();

		if (Quiz.isScoreShow()) {
			message.append("You scored ").
					append(Quiz.getScore()).
					append(" out of ").
					append(Quiz.getTotalQuestions()).
					append(".");

			int passingPercentage = Quiz.getPassingPercentage();
			if (passingPercentage != 0) {
				int percentage = (int) Helper.calculatePercentage(Quiz.getScore(), Quiz.getTotalQuestions());
				message.append("\n").append((passingPercentage > percentage) ? "You have failed the quiz." : "You have passed the quiz.");
			}
		} else {
			message.append("You have submitted the quiz!");
		}

		Quiz.insertAttempt();

		message.append("\nPress 'Yes' if you wish to go back to the Student Panel.").
				append("\nPress 'No' if you wish to exit the application.");

		int response = JOptionPane.showConfirmDialog(frame1, message, "Quiz Completed", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (response == 0) {
			frame1.setVisible(false);
			this.window.show();
		} else {
			System.exit(0);
		}
	}

	/**
	 * Exits the quiz application
	 */
	private void quizExit() {
		System.exit(0);
	}

	/**
	 * Initialises the components and sets the styling
	 */
	private void initComponents() {
		frame1 = new JFrame();
		mainPanel = new JPanel();
		var splitPane1 = new JSplitPane();
		sidePanel = new JPanel();
		exitButton = new JButton();
		usernameLabel = new JLabel();
		var separator1 = new JSeparator();
		quizInfoLabel = new JLabel();
		var separator2 = new JSeparator();
		viewPanel = new JPanel();
		answerGroup = new ButtonGroup();
		questionLabel = new JLabel("<html>");
		var separator3 = new JSeparator();
		vSpacer1 = new JPanel(null);
		nextSubmitButton = new JButton();
		label1 = new JLabel();

		//======== frame1 ========
		{
			frame1.setMinimumSize(new Dimension(0, 450));
			frame1.setMaximizedBounds(new Rectangle(0, 0, 600, 800));
			frame1.setTitle("Quiz Management System - Quiz View");
			var frame1ContentPane = frame1.getContentPane();
			frame1ContentPane.setLayout(null);

			//======== mainPanel ========
			{
				mainPanel.setBorder(new TitledBorder(""));
				mainPanel.setLayout(new BorderLayout());

				//======== splitPane1 ========
				{
					splitPane1.setFocusable(false);
					splitPane1.setOpaque(false);
					splitPane1.setEnabled(false);
					splitPane1.setMaximumSize(null);
					splitPane1.setMinimumSize(null);
					splitPane1.setPreferredSize(null);
					splitPane1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

					//======== sidePanel ========
					{
						sidePanel.setAlignmentY(1.0F);
						sidePanel.setBorder(new TitledBorder(""));
						sidePanel.setLayout(new GridBagLayout());

						//---- exitButton ----
						exitButton.setText("Exit");
						exitButton.addActionListener(e -> quizExit());
						sidePanel.add(exitButton, new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
							new Insets(0, 0, 0, 0), 0, 0));

						//---- usernameLabel ----
						usernameLabel.setText("Username");
						sidePanel.add(usernameLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
							new Insets(0, 0, 0, 0), 0, 0));
						sidePanel.add(separator1, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));

						//---- quizInfoLabel ----
						quizInfoLabel.setText("00:00 s");
						sidePanel.add(quizInfoLabel, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.NONE,
							new Insets(0, 0, 0, 0), 0, 0));
						sidePanel.add(separator2, new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0,
							GridBagConstraints.CENTER, GridBagConstraints.BOTH,
							new Insets(0, 0, 0, 0), 0, 0));
					}
					splitPane1.setLeftComponent(sidePanel);

					//======== viewPanel ========
					{
						viewPanel.setBorder(null);
						viewPanel.setPreferredSize(new Dimension(60, 200));
						viewPanel.setFocusable(false);
						viewPanel.setMinimumSize(new Dimension(60, 200));
						viewPanel.setMaximumSize(null);
						viewPanel.setLayout(new GridBagLayout());
						((GridBagLayout)viewPanel.getLayout()).columnWidths = new int[] {0, 500, 0, 0};
						((GridBagLayout)viewPanel.getLayout()).rowHeights = new int[] {200, 0, 0, 0, 0, 0, 0, 0, 0};
						((GridBagLayout)viewPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0E-4};
						((GridBagLayout)viewPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0E-4};

						//---- questionLabel ----
						questionLabel.setFont(questionLabel.getFont().deriveFont(Font.BOLD, 16f));
						questionLabel.setText("<html><center>a very long question to test whether it will go out of the window or not because it should not my dear friend</center></html>");
						questionLabel.setMaximumSize(new Dimension(300, 22));
						questionLabel.setMinimumSize(null);
						questionLabel.setHorizontalAlignment(JLabel.CENTER);
						questionLabel.setPreferredSize(new Dimension(500, 44));
						viewPanel.add(questionLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.CENTER,
							new Insets(0, 0, 0, 0), 0, 0));
						viewPanel.add(separator3, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
							new Insets(0, 0, 0, 0), 0, 0));

						//---- answerButtons ----
						for (int i = 0; i < answerButtons.length; i ++) {
							answerButtons[i] = new JRadioButton();
							answerGroup.add(answerButtons[i]);

							answerButtons[i].setFont(answerButtons[i].getFont().deriveFont(14f));
							answerButtons[i].setText("answer" + i + "Button");

							viewPanel.add(answerButtons[i], new GridBagConstraints(1, 2+i, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
									new Insets(0, 0, 0, 0), 0, 0));
						}

						viewPanel.add(vSpacer1, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
							new Insets(0, 0, 0, 0), 0, 0));

						//---- nextSubmitButton ----
						nextSubmitButton.setText("Next/Submit");
						nextSubmitButton.addActionListener(e -> quizNext());
						viewPanel.add(nextSubmitButton, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
							GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
							new Insets(0, 0, 0, 0), 0, 0));
					}
					splitPane1.setRightComponent(viewPanel);
				}
				mainPanel.add(splitPane1, BorderLayout.CENTER);
			}
			frame1ContentPane.add(mainPanel);
			mainPanel.setBounds(0, 0, 1012, 402);

			//---- label1 ----
			label1.setText("text");
			frame1ContentPane.add(label1);
			label1.setBounds(new Rectangle(new Point(170, 0), label1.getPreferredSize()));

			frame1ContentPane.setPreferredSize(new Dimension(635, 435));
			frame1.pack();
			frame1.setLocationRelativeTo(frame1.getOwner());
		}
	}

	private JFrame frame1;
	private JPanel mainPanel;
	private JPanel sidePanel;
	private JButton exitButton;
	private JLabel usernameLabel;
	private JLabel quizInfoLabel;
	private JPanel viewPanel;
	private JLabel questionLabel;
	private JRadioButton[] answerButtons = new JRadioButton[4];
	private ButtonGroup answerGroup;
	private JPanel vSpacer1;
	private JButton nextSubmitButton;
	private JLabel label1;
}
