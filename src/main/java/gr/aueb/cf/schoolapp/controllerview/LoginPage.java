package gr.aueb.cf.schoolapp.controllerview;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;
import gr.aueb.cf.schoolapp.service.util.UserAuthUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setTitle("User Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(172, 77, 161, 35);
		contentPane.add(txtPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(128, 0, 0));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(39, 33, 102, 20);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(new Color(128, 0, 0));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(39, 82, 102, 20);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(172, 28, 161, 35);
		contentPane.add(txtUsername);
		txtUsername.setColumns(50);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 140, 414, 1);
		contentPane.add(separator);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = String.valueOf(txtPassword.getPassword());
				
				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Not valid input", "Login Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					UserDTO dto = new UserDTO();
					dto.setUsername(username);
					dto.setPassword(password);
					
					if (UserAuthUtil.isAdmin(dto)) {
						Main.getSearchUser().setVisible(true);
						Main.getLoginPage().setVisible(false);
					} else {
						if (UserAuthUtil.isValidPassword(dto)) {
							Main.getMenu().setVisible(true);
							Main.getLoginPage().setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Not valid input", "Login Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				} catch (UserDAOException | UserNotFoundException ex) {
					String message = ex.getMessage();
					JOptionPane.showMessageDialog(null, message, "Login Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogin.setForeground(new Color(0, 0, 255));
		btnLogin.setBounds(274, 190, 117, 49);
		contentPane.add(btnLogin);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 11, 358, 118);
		contentPane.add(panel);
	}
}