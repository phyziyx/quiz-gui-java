package com.company;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.*;



/**
 * Accessible only by the teacher, displays a GUI
 * with all the quizzes created, with the options
 * to delete, edit and view quizzes as desired.
 *
 * @author Ali
 */
public class ViewQuizzes {
	private int selectedQuizId = 0;
	private int rowId = -1;
	private DefaultTableModel tableModel;

	/**
	 * View Quizzes constructor
	 */
	public ViewQuizzes() {
		initComponents();

		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);

		ListSelectionModel select = table1.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		select.addListSelectionListener(e -> {
			rowId = table1.getSelectedRow();
			if (rowId == -1) return;
			selectedQuizId = (int) table1.getValueAt(rowId, 0);
		});

		loadQuizzes();
	}

	/**
	 * Loads the quizzes list of the user logged in to the quiz viewer table
	 */
	private void loadQuizzes() {
		try {
			PreparedStatement statement = Database.connection.prepareStatement("SELECT id, name FROM quiz WHERE quiz.owner = ?");
			statement.setObject(1, User.getUserId());

			ResultSet rs = statement.executeQuery();

			tableModel = Helper.UneditableTableModel();
			table1.setModel(tableModel);

			tableModel.addColumn("Quiz ID");
			tableModel.addColumn("Name");

			while (rs.next()) {
				tableModel.addRow(new Object[] {
					rs.getInt("id"),
					rs.getString("name")
				});
			}
		} catch (Exception ignored) {
		}
	}

	/**
	 * Shows the attempts by students of the select quiz
	 * @param ignored
	 */
	private void viewAttemptsQuizButton(ActionEvent ignored) {
		if (selectedQuizId == 0) {
			JOptionPane.showMessageDialog(frame, "No quiz is selected!", "Quiz Attempt Viewer", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			new TeacherView(frame, selectedQuizId);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, "Failed to view the quiz!", "Quiz Attempt Viewer", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Shows the details of the selected quiz
	 * @param e
	 */
	private void viewQuizButton(ActionEvent e) {
		if (selectedQuizId == 0) {
			JOptionPane.showMessageDialog(frame, "No quiz is selected!", "Quiz Viewer", JOptionPane.WARNING_MESSAGE);
			return;
		}

		JFrame quizView = null;
		try {
			quizView = new QuizView(frame, selectedQuizId);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(frame, "Failed to view the quiz!", "Quiz Viewers", JOptionPane.ERROR_MESSAGE);
		}
		quizView.setVisible(true);
	}

	/**
	 * Deletes the selected quiz
	 * @param e
	 */
	private void deleteQuizButton(ActionEvent e) {
		if (selectedQuizId == 0) {
			JOptionPane.showMessageDialog(frame, "No quiz is selected!", "Quiz Deletion", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this quiz?", "Quiz Deletion", JOptionPane.YES_NO_OPTION);
		if (response == 0) {
			if (Quiz.delete(selectedQuizId)) {
				tableModel.removeRow(rowId);
				rowId = -1;
				selectedQuizId = 0;
			}
		}
	}

	/**
	 * go back to teacher panel
	 * @param e
	 */
	private void backButton(ActionEvent e) {
		frame.dispose();
	}

	/**
	 * Initiates the components and sets the styling
	 */
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		frame = new JFrame();
		var panel = new JPanel();
		var scrollPane = new JScrollPane();
		table1 = new JTable();
		var viewAttemptButton = new JButton();
		var viewButton = new JButton();
		var deleteButton = new JButton();
		var backButton = new JButton();

		//======== frame ========
		{
			frame.setTitle("Quiz Management System - Teacher Quizzes List");
			var frameContentPane = frame.getContentPane();
			frameContentPane.setLayout(new GridBagLayout());
			((GridBagLayout)frameContentPane.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
			((GridBagLayout)frameContentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			((GridBagLayout)frameContentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)frameContentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

			//======== panel ========
			{
				panel.setLayout(new GridBagLayout());
				((GridBagLayout)panel.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
				((GridBagLayout)panel.getLayout()).rowHeights = new int[] {0, 0};
				((GridBagLayout)panel.getLayout()).columnWeights = new double[] {1.0, 0.0, 1.0, 1.0E-4};
				((GridBagLayout)panel.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

				//======== scrollPane ========
				{

					//---- table1 ----
					table1.setModel(new DefaultTableModel(
						new Object[][] {
							{"Quiz ID", "Name"},
						},
						new String[] {
							null, null
						}
					) {
						Class<?>[] columnTypes = new Class<?>[] {
							String.class, String.class
						};
						@Override
						public Class<?> getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
					});
					scrollPane.setViewportView(table1);
				}
				panel.add(scrollPane, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			frameContentPane.add(panel, new GridBagConstraints(2, 1, 3, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- viewAttemptButton ----
			viewAttemptButton.setText("View Attempts");
			viewAttemptButton.addActionListener(e -> viewAttemptsQuizButton(e));
			frameContentPane.add(viewAttemptButton, new GridBagConstraints(3, 8, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- viewButton ----
			viewButton.setText("View");
			viewButton.addActionListener(e -> viewQuizButton(e));
			frameContentPane.add(viewButton, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- deleteButton ----
			deleteButton.setText("Delete");
			deleteButton.addActionListener(e -> deleteQuizButton(e));
			frameContentPane.add(deleteButton, new GridBagConstraints(3, 9, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- backButton ----
			backButton.setText("Back");
			backButton.addActionListener(e -> backButton(e));
			frameContentPane.add(backButton, new GridBagConstraints(4, 9, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));
			frame.pack();
			frame.setLocationRelativeTo(frame.getOwner());
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	public JFrame frame;
	private JTable table1;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
