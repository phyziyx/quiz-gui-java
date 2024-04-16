package com.company;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main Window
 * @author Ali
 */
public class MainWindow {

	private JWindow window;

	// START - LOGIN PAGE
	private JFrame loginFrame;

	private JTextField loginUsernameField;
	private JPasswordField loginPasswordField;

	// END - LOGIN PAGE

	// START - REGISTER PAGE
	private JFrame registerFrame;
	private JTextField registerUsernameField;
	private JPasswordField registerPasswordField;
	// END   - REGISTER PAGE


	/**
	 * Creates the login box and initialise functions related to it
	 */
	private void loginInitialise() {
		loginFrame = new JFrame("Quiz Login Frame");
		JLabel loginH1 = new JLabel("LOGIN", SwingConstants.CENTER);

		loginUsernameField = new JTextField();
		loginPasswordField = new JPasswordField();

		JLabel loginUsernameLabel = new JLabel("Username: ");
		JLabel loginPasswordLabel = new JLabel("Password: ");

		JButton loginLoginButton = new JButton("Login!");
		JButton loginRegisterButton = new JButton("Register?");

		loginH1.setBounds(0,0,500,40);
		loginH1.setFont(new Font("", Font.BOLD, 20));

		loginUsernameLabel.setBounds(70,70,100,30);

		loginUsernameField.setBounds(170,70,250,30);

		loginPasswordLabel.setBounds(70,120,100,30);

		loginPasswordField.setBounds(170,120,250,30);

		loginLoginButton.setBounds(320,170,100,40);

		loginRegisterButton.setBounds(70,170,100,40);

		loginFrame.add(loginH1);

		loginFrame.add(loginUsernameLabel);
		loginFrame.add(loginUsernameField);

		loginFrame.add(loginPasswordLabel);
		loginFrame.add(loginPasswordField);

		loginFrame.add(loginLoginButton);
		loginFrame.add(loginRegisterButton);

		loginLoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User.setUserName(loginUsernameField.getText());
				User.setPassWord(loginPasswordField.getPassword());

				if (User.getUserName().length() == 0 || User.getPassWord().length() == 0) {
					return;
				}

				if (!User.attemptLogin()) {
					JOptionPane.showMessageDialog(null, "Invalid credentials entered.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Logged in!", "Success", JOptionPane.INFORMATION_MESSAGE);
					hideLoginBox();
					showMainBox();
				}
			}
		});

		loginRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hideLoginBox();
				showRegisterBox();
			}
		});
	}

	/**
	 * Creates the register box and initialises functions related to it
	 */
	private void registerInitialise() {
		// START - REGISTER PAGE
		registerFrame = new JFrame("Quiz Register Frame");
		JLabel registerHeading = new JLabel("Register", SwingConstants.CENTER);

		registerUsernameField = new JTextField();
		registerPasswordField = new JPasswordField();

		JLabel registerUsernameLabel = new JLabel("Username: ");
		JLabel registerPasswordLabel = new JLabel("Password: ");

		JButton registerRegisterButton = new JButton("Register!");
		JButton registerLoginButton = new JButton("Login?");

		registerHeading.setBounds(0,0,500,40);
		registerHeading.setFont(new Font("", Font.BOLD, 20));

		registerUsernameLabel.setBounds(70,70,100,30);

		registerUsernameField.setBounds(170,70,250,30);

		registerPasswordLabel.setBounds(70,120,100,30);

		registerPasswordField.setBounds(170,120,250,30);

		registerRegisterButton.setBounds(320,170,100,40);

		registerLoginButton.setBounds(70,170,100,40);

		registerFrame.add(registerHeading);

		registerFrame.add(registerUsernameLabel);
		registerFrame.add(registerUsernameField);

		registerFrame.add(registerPasswordLabel);
		registerFrame.add(registerPasswordField);

		registerFrame.add(registerRegisterButton);
		registerFrame.add(registerLoginButton);
		// END   - REGISTER PAGE

		registerLoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				hideRegisterBox();
				showLoginBox();
			}
		});

		registerRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User.setUserName(registerUsernameField.getText());
				User.setPassWord(registerPasswordField.getPassword());

				if (User.getUserName().length() < 3 || User.getPassWord().length() < 3) {
					JOptionPane.showMessageDialog(null, "Your username/password must be at least three characters long!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (User.accountExists()) {
					JOptionPane.showMessageDialog(null, "An account with this name already exists!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!User.attemptRegister()) {
					JOptionPane.showMessageDialog(null, "This user already exists!", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "This user is now registered!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	MainWindow() {
		window = new JWindow();
		window.setName("Quiz App");
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		loginInitialise();
		registerInitialise();

		showLoginBox();
	}

	// REGISTER

	/**
	 * Hides the register box
	 */
	private void hideRegisterBox() {
		registerFrame.setVisible(false);
	}

	/**
	 * Shows the register box
	 */
	private void showRegisterBox() {
		registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		registerFrame.setSize(500,300);
		registerFrame.setLayout(null);
		registerFrame.setResizable(false);
		registerFrame.setLocationRelativeTo(null);

		registerFrame.setVisible(true);
	}

	/**
	 * Hides the login box
	 */
	private void hideLoginBox() {
		loginFrame.setVisible(false);
	}

	/**
	 * Shows the login box
	 */
	private void showLoginBox() {
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		loginFrame.setSize(500,300);
		loginFrame.setLayout(null);
		loginFrame.setResizable(false);
		loginFrame.setLocationRelativeTo(null);

		loginFrame.setVisible(true);
	}

	/**
	 * Shows the main box, which displays the user's designated panels
	 */
	public void showMainBox() {
		if (User.hasPower()) {
			// User is logged in as Teacher
			new TeacherPanelMain();
		} else {
			// User is logged in as Student
			new StudentPanelMain();
		}
	}
}
