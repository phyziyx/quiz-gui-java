/*
 * Created by JFormDesigner on Sat Dec 24 20:16:29 PKT 2022
 */

package com.company;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Creates a GUI panel for teachers
 * @author phyziyx
 */
public class TeacherPanelMain {
	/**
	 * Teacher Panel constructor
	 */
	public TeacherPanelMain() {
		initComponents();

		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setSize(new Dimension(500, 200));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	}

	/**
	 * Launches the Quiz Creator
	 */
	private void createQuiz(ActionEvent e) {
		new QuizCreator(frame);
	}

	/**
	 * Launches the Quiz viewer
	 */
	private void viewQuizzes(ActionEvent e) {
		new ViewQuizzes();
	}

	/**
	 * Exit the application
	 */
	private void exitButton(ActionEvent e) {
		System.exit(0);
	}

	/**
	 * Initiates the components and sets the styling
	 */
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		frame = new JFrame();
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		var headerLabel = new JLabel();
		var createQuizButton = new JButton();
		var viewQuizziesButton = new JButton();
		var exitButton = new JButton();

		//======== frame ========
		{
			frame.setTitle("Quiz Management System - Teacher Panel");
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

					//---- headerLabel ----
					headerLabel.setText("Teacher Panel");
					headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
					headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
					contentPanel.add(headerLabel, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

					//---- createQuizButton ----
					createQuizButton.setText("Create a Quiz");
					createQuizButton.addActionListener(e -> createQuiz(e));
					contentPanel.add(createQuizButton, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 5, 5), 0, 0));

					//---- viewQuizziesButton ----
					viewQuizziesButton.setText("View your Quizzes");
					viewQuizziesButton.addActionListener(e -> viewQuizzes(e));
					contentPanel.add(viewQuizziesButton, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0,
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
	private JPanel dialogPane;
	private JPanel contentPanel;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
