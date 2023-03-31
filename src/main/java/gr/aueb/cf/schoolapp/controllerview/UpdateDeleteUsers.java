package gr.aueb.cf.schoolapp.controllerview;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.UserNotFoundException;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UpdateDeleteUsers extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	private IUserDAO userDAO = new UserDAOImpl();
	private IUserService userService = new UserServiceImpl(userDAO);
	/**
	 * Create the frame.
	 */
	public UpdateDeleteUsers() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtPassword.setText("");
				try {
					User user = userService.getUserByUsername(Main.getSearchUser().getInputUsername());
					
					if (user == null) {
						txtId.setText("");
						txtUsername.setText("");
					} else {
						txtId.setText(String.valueOf(user.getId()));
						txtUsername.setText(user.getUsername());
					}
				} catch (UserDAOException ex) {
					String message = ex.getMessage();
					JOptionPane.showMessageDialog(null, message, "Error in getting user", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		setTitle("Ενημερωση - Διαγραφη Χρηστη");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(160, 56, 53, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setEditable(false);
		txtUsername.setBounds(160, 87, 132, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(250);
		
		JLabel lblId = new JLabel("Κωδικος");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setForeground(new Color(128, 0, 0));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(59, 59, 78, 17);
		contentPane.add(lblId);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setForeground(new Color(128, 0, 0));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(46, 87, 91, 17);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(new Color(128, 0, 0));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(59, 115, 78, 17);
		contentPane.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(160, 115, 132, 20);
		contentPane.add(txtPassword);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 157, 401, 1);
		contentPane.add(separator);
		
		JButton btnUpdatePass = new JButton("Update Pass");
		btnUpdatePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (txtId.getText().equals("")) {
					return;
				}
				
				int inputId = Integer.parseInt(txtId.getText());
				String inputUsername = txtUsername.getText().trim();
				String inputPassword = String.valueOf(txtPassword.getPassword()).trim();
				
				if (inputUsername.equals("") || inputPassword.equals("")) {
					JOptionPane.showMessageDialog(null, "Not valid input", "Insert Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					UserDTO dto = new UserDTO(inputId, inputUsername, inputPassword);
					
					userService.updateUser(dto);
					
					
					JOptionPane.showMessageDialog(null, "User was updated", "UPDATE", JOptionPane.PLAIN_MESSAGE);
				} catch (UserDAOException | UserNotFoundException ex) {
					String message = ex.getMessage();
					JOptionPane.showMessageDialog(null, message, "Error in updating user", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnUpdatePass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdatePass.setForeground(new Color(0, 0, 255));
		btnUpdatePass.setBounds(11, 213, 130, 35);
		contentPane.add(btnUpdatePass);
		
		JButton btnDelete = new JButton("Delete User");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if (txtId.getText().equals("")) {
					return;
				}
				
				int inputId = Integer.parseInt(txtId.getText());
				int response;
				
				if (String.valueOf(inputId).equals("")) {
					JOptionPane.showMessageDialog(null, "Not valid input", "Insert Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				response = JOptionPane.showConfirmDialog(null, "Ειστε σιγουροι;", "Warning", JOptionPane.YES_NO_OPTION);
				
				if (response == JOptionPane.YES_OPTION) {
					try {
						userService.deleteUser(inputId);
						JOptionPane.showMessageDialog(null, "User was deleted", "DELETE", JOptionPane.PLAIN_MESSAGE);
					} catch (UserDAOException | UserNotFoundException ex) {
						String message = ex.getMessage();
						JOptionPane.showMessageDialog(null, message, "Error in deleting user", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				
				
			}
		});
		btnDelete.setForeground(Color.BLUE);
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(152, 213, 130, 35);
		contentPane.add(btnDelete);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchUser().setEnabled(true);
				Main.getUpdateDeleteUsers().setVisible(false);
			}
		});
		btnClose.setForeground(Color.BLUE);
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(293, 213, 130, 35);
		contentPane.add(btnClose);
	}
}