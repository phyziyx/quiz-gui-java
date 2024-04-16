/*
 * Created by JFormDesigner on Sun Dec 25 23:58:34 PKT 2022
 */



package com.company;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * QuestionAdder class - creates a Question Adder GUI
 * @author Ali
 */
public class QuestionAdder {
	JFrame parentFrame;
	QuizCreator creator;

	/**
	 * Creates the Question Adder class
	 * @param creator The class that calls this constructor
	 * @param parentFrame The frame that Question   Adder will be bind to
	 */
	public QuestionAdder(QuizCreator creator, JFrame parentFrame) {
		initComponents();

		this.creator = creator;
		this.parentFrame = parentFrame;
		// parentFrame.setVisible(false);

		mainDialog.setLocationRelativeTo(null);
		mainDialog.setVisible(true);
		mainDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/**
	 * Adds the question into the list
	 */
	private void addQuestion() {
		if (questionField.getText().isBlank() ||
			answerField1.getText().isBlank() ||
			answerField2.getText().isBlank() ||
			answerField3.getText().isBlank() ||
			answerField4.getText().isBlank()) {
			JOptionPane.showMessageDialog(mainDialog, "One of the fields is empty!", "Add Question", JOptionPane.ERROR_MESSAGE);
			return;
		}

		int answer = 0;
		for (int i = 0, j = checkBox.length; i < j; i ++) {
			if (!checkBox[i].isSelected()) {
				continue;
			}

			answer = i + 1;
			break;
		}

		if (answer == 0) {
			JOptionPane.showMessageDialog(mainDialog, "You have not assigned any of the choices as an answer!", "Add Question", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Quiz.addQuestion(questionField.getText(), answer, answerField1.getText(), answerField2.getText(), answerField3.getText(), answerField4.getText());
		JOptionPane.showMessageDialog(mainDialog, "Successfully Added!", "Add Question", JOptionPane.INFORMATION_MESSAGE);

		questionField.setText("");

		answerField1.setText("");
		answerField2.setText("");
		answerField3.setText("");
		answerField4.setText("");

		answerButtonGroup.clearSelection();
		creator.updateQuestionLabel();
	}

	/**
	 * Cancel question adder menu
	 */
	private void cancelAdd() {
		parentFrame.setVisible(true);
		mainDialog.dispose();
	}

	/**
	 * Initiates the components and sets styling
	 */
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		mainDialog = new JDialog(parentFrame);
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		var questionLabel = new JLabel();
		questionField = new JTextField();
		var choice1 = new JLabel();
		answerField1 = new JTextField();
		var choice2 = new JLabel();
		answerField2 = new JTextField();
		var choice3 = new JLabel();
		answerField3 = new JTextField();
		var choice4 = new JLabel();
		answerField4 = new JTextField();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();
		answerButtonGroup = new ButtonGroup();

		for (int index = 0; index < checkBox.length; index ++) {
			checkBox[index] = new JCheckBox();
			answerButtonGroup.add(checkBox[index]);
		}

		//======== mainDialog ========
		{
			mainDialog.setTitle("Add Question");
			var mainDialogContentPane = mainDialog.getContentPane();
			mainDialogContentPane.setLayout(new BorderLayout());

			//======== dialogPane ========
			{
				dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
				dialogPane.setLayout(new BorderLayout());

				//======== contentPanel ========
				{
					contentPanel.setLayout(new GridBagLayout());
					((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0};
					((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0};
					((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 0.0, 1.0E-4};
					((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0, 1.0E-4};

					//---- questionLabel ----
					questionLabel.setText("Question");
					questionLabel.setFont(questionLabel.getFont().deriveFont(questionLabel.getFont().getStyle() | Font.BOLD));
					contentPanel.add(questionLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));
					contentPanel.add(questionField, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

					//---- choice1 ----
					choice1.setText("Choice 1");
					choice1.setFont(choice1.getFont().deriveFont(choice1.getFont().getStyle() | Font.BOLD));
					contentPanel.add(choice1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));
					contentPanel.add(answerField1, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

					//---- choice2 ----
					choice2.setText("Choice 2");
					choice2.setFont(choice2.getFont().deriveFont(choice2.getFont().getStyle() | Font.BOLD));
					contentPanel.add(choice2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));
					contentPanel.add(answerField2, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

					//---- choice3 ----
					choice3.setText("Choice 3");
					choice3.setFont(choice3.getFont().deriveFont(choice3.getFont().getStyle() | Font.BOLD));
					contentPanel.add(choice3, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));
					contentPanel.add(answerField3, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

					//---- choice4 ----
					choice4.setText("Choice 4");
					choice4.setFont(choice4.getFont().deriveFont(choice4.getFont().getStyle() | Font.BOLD));
					contentPanel.add(choice4, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));
					contentPanel.add(answerField4, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

					//---- checkBox ----
					for (int index = 0; index < checkBox.length; index ++) {
						checkBox[index].setText("Answer?");
						contentPanel.add(checkBox[index], new GridBagConstraints(3, 1+index, 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH,
								new Insets(0, 0, 5, 0), 0, 0));
					}
				}
				dialogPane.add(contentPanel, BorderLayout.CENTER);

				//======== buttonBar ========
				{
					buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
					buttonBar.setLayout(new GridBagLayout());
					((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
					((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0};

					//---- okButton ----
					okButton.setText("Add");
					okButton.addActionListener(e -> addQuestion());
					buttonBar.add(okButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 5), 0, 0));

					//---- cancelButton ----
					cancelButton.setText("Cancel");
					cancelButton.addActionListener(e -> cancelAdd());
					buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 0), 0, 0));
				}
				dialogPane.add(buttonBar, BorderLayout.SOUTH);
			}
			mainDialogContentPane.add(dialogPane, BorderLayout.CENTER);
			mainDialog.pack();
			mainDialog.setLocationRelativeTo(mainDialog.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	private JDialog mainDialog;
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JTextField questionField;
	private JTextField answerField1;
	private JCheckBox[] checkBox = new JCheckBox[4];
	private JTextField answerField2;
	private JTextField answerField3;
	private JTextField answerField4;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	private ButtonGroup answerButtonGroup;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
