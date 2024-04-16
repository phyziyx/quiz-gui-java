/*
 * Created by JFormDesigner on Tue Dec 27 15:15:58 PKT 2022
 */

package com.company;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Creates a GUI with all the student's quiz attempts
 * @author Ali
 */
public class ViewAttempts  {
	StudentPanelMain owner;

	/**
	 * viewAttempts constructor
	 * We pass in the StudentPanelMain because we want to make it un/accessible
	 * @param owner the StudentPanelMain instance
	 */
	ViewAttempts(StudentPanelMain owner) {
		this.owner = owner;
		initComponents();
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		populateAttempts();
	}

	/**
	 * Populates the attempts table
	 */
	private void populateAttempts() {
		DefaultTableModel tableModel = Helper.UneditableTableModel();
		table1.setModel(tableModel);

		tableModel.addColumn("Topic Name");
		tableModel.addColumn("Teacher");
		tableModel.addColumn("Questions");
		tableModel.addColumn("Score");
		tableModel.addColumn("Grade");

		try {
			PreparedStatement statement = Database.connection.prepareStatement("SELECT" +
				" quiz.name, quiz.scoreShow, quiz.passingPercentage," +
				" attempts.score, attempts.grade," +
				" (SELECT COUNT(*) FROM questions WHERE questions.quizId = attempts.quizId) AS questions," +
				" (SELECT users.user FROM users WHERE users.id = quiz.owner) AS ownerName" +
				" FROM " +
				" attempts " +
				"INNER JOIN " +
				" quiz " +
				"ON " +
				" attempts.quizId = quiz.id " +
				"WHERE " +
				" attempts.userId = ?");
			statement.setObject(1, User.getUserId());

			ResultSet rs = statement.executeQuery();
			boolean hasHistory = false;
			int     scoreShow  = 0;
			int     percentage = 0;

			while (rs.next()) {
				hasHistory = true;
				StringBuilder scoreMsg = new StringBuilder();
				StringBuilder gradeMsg = new StringBuilder();
				gradeMsg.setLength(0);

				scoreShow = rs.getInt("scoreShow");
				percentage = rs.getInt("passingPercentage");

				if (scoreShow == 0) {
					scoreMsg.append("Hidden");
					gradeMsg.append("Hidden");
				} else {
					scoreMsg.append(rs.getInt("score"));

					if (percentage == 0) {
						gradeMsg.append("N/A");
					} else {
						if (rs.getInt("grade") >= percentage) {
							gradeMsg.append("PASSED");
						} else {
							gradeMsg.append("FAILED");
						}

						gradeMsg.append(" ")
								.append(rs.getInt("grade"))
								.append("%")
						;
					}
				}

				tableModel.addRow(new Object[] {
						rs.getString("name"),
						rs.getString("ownerName"),
						rs.getInt("questions"),
						scoreMsg,
						gradeMsg
				});
			}

			if (!hasHistory) {
				JOptionPane.showMessageDialog(frame, "You have no attempts history!", "Attempts History", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception ignored) {
			System.out.println(ignored.getMessage());
		}
	}

	/**
	 * Back button event handler
	 * @param ignored
	 */
	private void goBack(ActionEvent ignored) {
		this.owner.show();
		frame.dispose();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		frame = new JFrame();
		var panel1 = new JPanel();
		var scrollPane1 = new JScrollPane();
		table1 = new JTable();
		var backButton = new JButton();

		//======== frame ========
		{
			frame.setTitle("Quiz Management System - Student Attempt History");
			var frameContentPane = frame.getContentPane();
			frameContentPane.setLayout(new GridBagLayout());
			((GridBagLayout)frameContentPane.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
			((GridBagLayout)frameContentPane.getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			((GridBagLayout)frameContentPane.getLayout()).columnWeights = new double[] {0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0E-4};
			((GridBagLayout)frameContentPane.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};

			//======== panel1 ========
			{
				panel1.setLayout(new GridBagLayout());
				((GridBagLayout)panel1.getLayout()).columnWidths = new int[] {0, 0, 0, 0};
				((GridBagLayout)panel1.getLayout()).rowHeights = new int[] {0, 0};
				((GridBagLayout)panel1.getLayout()).columnWeights = new double[] {1.0, 0.0, 1.0, 1.0E-4};
				((GridBagLayout)panel1.getLayout()).rowWeights = new double[] {0.0, 1.0E-4};

				//======== scrollPane1 ========
				{

					//---- table1 ----
					table1.setModel(new DefaultTableModel(
						new Object[][] {
							{"Topic Name", "Teacher", "Total Questions", "Score", "Grade"},
						},
						new String[] {
							null, null, null, null, null
						}
					) {
						Class<?>[] columnTypes = new Class<?>[] {
							String.class, Object.class, String.class, Object.class, Object.class
						};
						boolean[] columnEditable = new boolean[] {
							false, true, true, true, true
						};
						@Override
						public Class<?> getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
						@Override
						public boolean isCellEditable(int rowIndex, int columnIndex) {
							return columnEditable[columnIndex];
						}
					});
					scrollPane1.setViewportView(table1);
				}
				panel1.add(scrollPane1, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			frameContentPane.add(panel1, new GridBagConstraints(2, 1, 3, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5), 0, 0));

			//---- backButton ----
			backButton.setText("Back");
			backButton.addActionListener(e -> goBack(e));
			frameContentPane.add(backButton, new GridBagConstraints(3, 9, 1, 1, 0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 5), 0, 0));
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


