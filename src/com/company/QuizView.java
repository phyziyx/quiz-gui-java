/*
 * Created by JFormDesigner on Sun Dec 25 15:47:02 PKT 2022
 */

package com.company;



import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

/**
 * Quiz Viewer class
 * @author Ali
 */
public class QuizView extends JFrame {
	private JFrame parent = null;

	/**
	 * Loads the provided quiz ID to view
	 *
	 * @param selectedQuizId the Quiz ID to load
	 * @throws Exception if Quiz viewer fails to load
	 */
	public QuizView(JFrame parent, int selectedQuizId) throws Exception {
		initComponents();

		Quiz.load(selectedQuizId);

		this.parent = parent;
		parent.setVisible(false);
		topicLabel.setText(Quiz.getTopic());

		DefaultTableModel tableModel = Helper.UneditableTableModel();
		questionsList.setModel(tableModel);

		tableModel.addColumn("Question #");
		tableModel.addColumn("Title / Answer");

		int number = 0;
		for (Question question : Quiz.getQuestions()) {
			number ++;

			int answer = (question.getAnswer() - 1);
			String[] answers = question.getAnswers();

			tableModel.addRow(new Object[] {
					number,
					question.getQuestion()
			});

			for (int i = 0; i < Question.MAX_ANSWERS; i ++) {
				tableModel.addRow(new Object[] {
						(answer == i ? "ANSWER" : "CHOICE"),
						answers[i]
				});
			}
		}
	}

	/**
	 * Go back to the parent frame
	 */
	private void goBack() {
		parent.setVisible(true);
		this.dispose();
	}

	/**
	 * Initialises the components and sets the styling
	 */
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		var dialogPane = new JPanel();
		var contentPanel = new JPanel();
		topicLabel = new JLabel();
		var scrollPane1 = new JScrollPane();
		questionsList = new JTable();
		var backButton = new JButton();

		//======== this ========
		setFont(new Font(Font.DIALOG, Font.BOLD, 12));
		var contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new GridBagLayout());
				((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
				((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
				((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 1.0, 0.0, 1.0E-4};
				((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0, 0.0, 1.0E-4};

				//---- topicLabel ----
				topicLabel.setText("Quiz Topic Name");
				topicLabel.setHorizontalAlignment(SwingConstants.CENTER);
				topicLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				topicLabel.setLabelFor(questionsList);
				contentPanel.add(topicLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
					new Insets(0, 0, 5, 5), 0, 0));

				//======== scrollPane1 ========
				{

					//---- questionsList ----
					questionsList.setFont(questionsList.getFont().deriveFont(questionsList.getFont().getStyle() | Font.BOLD));
					questionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					questionsList.setFillsViewportHeight(true);
					questionsList.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					scrollPane1.setViewportView(questionsList);
				}
				contentPanel.add(scrollPane1, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 5, 5), 0, 0));

				//---- backButton ----
				backButton.setText("Back");
				backButton.addActionListener(e -> goBack());
				contentPanel.add(backButton, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	/**
	 * Topic name label, this is updated when a Quiz is loaded
	 */
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	private JLabel topicLabel;
	public JTable questionsList;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}