/*
 * Created by JFormDesigner on Sun Dec 25 21:08:49 PKT 2022
 */



package com.company;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

/**
 * @author Ali
 */
public class QuizCreator {
	JFrame ownerFrame = null;
	JFrame selfFrame  = null;

	/**
	 * QuizCreator constructor
	 * @param ownerFrame the owner frame Quiz Creator will bind to.
	 */
	public QuizCreator(JFrame ownerFrame) {
		this.ownerFrame = ownerFrame;
		initComponents();

		selfFrame = new JFrame();
		selfFrame.add(mainPanel);
		selfFrame.setVisible(true);
		selfFrame.setLocationRelativeTo(null);
		selfFrame.setMinimumSize(new Dimension(300, 200));
		selfFrame.setSize(mainPanel.getSize());
		selfFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		Quiz.clear();

		this.ownerFrame.setVisible(false);
	}

	/**
	 * Updates the question label
	 */
	public void updateQuestionLabel() {
		questionAddedLabel.setText("Questions Added: " + Quiz.getTotalQuestions());
	}

	/**
	 * Displays the question Adder menu
	 */
	private void addQuestion() {
		new QuestionAdder(this, selfFrame);
	}

	/**
	 * Attempts to create the quiz
	 */
	private void createQuiz() {
		int length = topicNameField.getText().length();
		if (length < 3 || length > 32 ) {
			JOptionPane.showMessageDialog(selfFrame, "Invalid topic name (must be 3 or 32 characters long)", "Quiz Creator", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (Quiz.getTotalQuestions() == 0) {
			JOptionPane.showMessageDialog(selfFrame, "You have not added any questions!", "Quiz Creator", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			int quizId = 0;

			PreparedStatement statement = Database.connection.prepareStatement("INSERT INTO quiz.quiz (name, owner, scoreShow, passingPercentage, shuffleQuestions, seconds) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			statement.setObject(1, topicNameField.getText());
			statement.setObject(2, User.getUserId());
			statement.setObject(3, scoreVisibilityCheckbox.isSelected() ? 1 : 0);
			statement.setObject(4, passingPercentageSlider.getValue());
			statement.setObject(5, shuffleQuestionsCheckbox.isSelected() ? 1 : 0);
			statement.setObject(6, minuteSlider.getValue() * 60);

			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				JOptionPane.showMessageDialog(selfFrame, "An error occurred while creating the quiz (1)!", "Quiz Creator", JOptionPane.ERROR_MESSAGE);
				return;
			}

			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				quizId = generatedKeys.getInt(1);
			}

			if (quizId == 0) {
				JOptionPane.showMessageDialog(selfFrame, "An error occurred while creating the quiz (2)!", "Quiz Creator", JOptionPane.ERROR_MESSAGE);
				return;
			}

			statement = Database.connection.prepareStatement("INSERT INTO quiz.questions (quizId, question, answer, answer1, answer2, answer3, answer4) VALUES (?, ?, ?, ?, ?, ?, ?)");
			statement.setObject(1, quizId);

			for (Question question : Quiz.getQuestions()) {
				statement.setObject(2, question.getQuestion());
				statement.setObject(3, question.getAnswer());

				String[] answers = question.getAnswers();
				for (int i = 0; i < answers.length; i ++) {
					statement.setObject(4 + i, answers[i]);
				}

				statement.execute();
			}

			JOptionPane.showMessageDialog(selfFrame, "Successfully created the quiz!", "Quiz Creator", JOptionPane.INFORMATION_MESSAGE);
			backFromCreator();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(selfFrame, "An error occurred while creating the quiz (3)!", "Quiz Creator", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Exit the Quiz Creator and go back to Teacher panel
	 */
	private void backFromCreator() {
		selfFrame.dispose();
		ownerFrame.setVisible(true);
		ownerFrame.setLocationRelativeTo(null);
	}

	/**
	 * Slider change watcher
	 */
	private void sliderChange() {
		passingSliderText.setText(passingPercentageSlider.getValue() + "%");
	}

	private void minuteSliderStateChange() {
		minuteSliderText.setText(String.valueOf(minuteSlider.getValue()));
	}

	/**
	 * Initialises the components and sets the styling
	 */
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		mainPanel = new JPanel();
		var quizCreatorLabel = new JLabel();
		var topicNameLabel = new JLabel();
		topicNameField = new JTextField();
		var scoreVisibilityLabel = new JLabel();
		scoreVisibilityCheckbox = new JCheckBox();
		var passingPercentageLabel = new JLabel();
		passingPercentageSlider = new JSlider();
		passingSliderText = new JLabel();
		var shuffleQuestionsLabel = new JLabel();
		shuffleQuestionsCheckbox = new JCheckBox();
		var minuteLabel = new JLabel();
		minuteSlider = new JSlider();
		minuteSliderText = new JLabel();
		questionAddedLabel = new JLabel();
		var addButton = new JButton();
		var createButton = new JButton();
		var backButton = new JButton();

		//======== mainPanel ========
		{
			mainPanel.setLayout(new GridBagLayout());
			((GridBagLayout)mainPanel.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0};
			((GridBagLayout)mainPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			((GridBagLayout)mainPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)mainPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

			//---- quizCreatorLabel ----
			quizCreatorLabel.setText("Quiz Creator");
			quizCreatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
			quizCreatorLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
			mainPanel.add(quizCreatorLabel, new GridBagConstraints(2, 0, 2, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- topicNameLabel ----
			topicNameLabel.setText("Topic Name");
			topicNameLabel.setLabelFor(topicNameField);
			topicNameLabel.setFont(topicNameLabel.getFont().deriveFont(topicNameLabel.getFont().getStyle() | Font.BOLD));
			mainPanel.add(topicNameLabel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 5, 5), 0, 0));
			mainPanel.add(topicNameField, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- scoreVisibilityLabel ----
			scoreVisibilityLabel.setText("Score Visibility");
			scoreVisibilityLabel.setLabelFor(topicNameField);
			scoreVisibilityLabel.setFont(scoreVisibilityLabel.getFont().deriveFont(scoreVisibilityLabel.getFont().getStyle() | Font.BOLD));
			mainPanel.add(scoreVisibilityLabel, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 5, 5), 0, 0));
			mainPanel.add(scoreVisibilityCheckbox, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- passingPercentageLabel ----
			passingPercentageLabel.setText("Passing Percentage");
			passingPercentageLabel.setLabelFor(topicNameField);
			passingPercentageLabel.setFont(passingPercentageLabel.getFont().deriveFont(passingPercentageLabel.getFont().getStyle() | Font.BOLD));
			mainPanel.add(passingPercentageLabel, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- passingPercentageSlider ----
			passingPercentageSlider.setValue(0);
			passingPercentageSlider.setPaintLabels(true);
			passingPercentageSlider.setToolTipText("% of marks Student must obtain to pass the quiz");
			passingPercentageSlider.addChangeListener(e -> sliderChange());
			mainPanel.add(passingPercentageSlider, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- passingSliderText ----
			passingSliderText.setText("0%");
			passingSliderText.setLabelFor(passingPercentageSlider);
			mainPanel.add(passingSliderText, new GridBagConstraints(4, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- shuffleQuestionsLabel ----
			shuffleQuestionsLabel.setText("Shuffle Questions");
			shuffleQuestionsLabel.setLabelFor(topicNameField);
			shuffleQuestionsLabel.setFont(shuffleQuestionsLabel.getFont().deriveFont(shuffleQuestionsLabel.getFont().getStyle() | Font.BOLD));
			mainPanel.add(shuffleQuestionsLabel, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 5, 5), 0, 0));
			mainPanel.add(shuffleQuestionsCheckbox, new GridBagConstraints(3, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- minuteLabel ----
			minuteLabel.setText("Time (Minutes)");
			minuteLabel.setLabelFor(topicNameField);
			minuteLabel.setFont(minuteLabel.getFont().deriveFont(minuteLabel.getFont().getStyle() | Font.BOLD));
			mainPanel.add(minuteLabel, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- minuteSlider ----
			minuteSlider.setValue(0);
			minuteSlider.setMaximum(10);
			minuteSlider.addChangeListener(e -> minuteSliderStateChange());
			mainPanel.add(minuteSlider, new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- minuteSliderText ----
			minuteSliderText.setText("0");
			minuteSliderText.setLabelFor(passingPercentageSlider);
			mainPanel.add(minuteSliderText, new GridBagConstraints(4, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- questionAddedLabel ----
			questionAddedLabel.setText("Questions Added: 0");
			mainPanel.add(questionAddedLabel, new GridBagConstraints(2, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- addButton ----
			addButton.setText("Add Question");
			addButton.addActionListener(e -> addQuestion());
			mainPanel.add(addButton, new GridBagConstraints(3, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- createButton ----
			createButton.setText("Create");
			createButton.addActionListener(e -> createQuiz());
			mainPanel.add(createButton, new GridBagConstraints(2, 8, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- backButton ----
			backButton.setText("Back");
			backButton.addActionListener(e -> backFromCreator());
			mainPanel.add(backButton, new GridBagConstraints(3, 8, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	private JPanel mainPanel;
	private JTextField topicNameField;
	private JCheckBox scoreVisibilityCheckbox;
	private JSlider passingPercentageSlider;
	private JLabel passingSliderText;
	private JCheckBox shuffleQuestionsCheckbox;
	private JSlider minuteSlider;
	private JLabel minuteSliderText;
	private JLabel questionAddedLabel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
