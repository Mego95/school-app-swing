package gr.aueb.cf.schoolapp.controllerview;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.IUserDAO;
import gr.aueb.cf.schoolapp.dao.UserDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolapp.dto.UserDTO;
import gr.aueb.cf.schoolapp.service.IUserService;
import gr.aueb.cf.schoolapp.service.UserServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPasswordField;

public class InsertUser extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	private IUserDAO userDAO = new UserDAOImpl();
	private IUserService userService = new UserServiceImpl(userDAO);
	
	
	/**
	 * Create the frame.
	 */
	public InsertUser() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtUsername.setText("");
				txtPassword.setText("");
			}
		});
		setTitle("Εισαγωγη User");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setForeground(new Color(128, 0, 0));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(41, 55, 89, 29);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(new Color(128, 0, 0));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(41, 95, 89, 29);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(140, 61, 150, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(50);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText().trim();
				String password = String.valueOf(txtPassword.getPassword()).trim();
				
				if (username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Not valid input", "Insert Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					UserDTO dto = new UserDTO();
					dto.setUsername(username);
					dto.setPassword(password);
					
					userService.insertUser(dto);
					JOptionPane.showMessageDialog(null, "User was inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);
				} catch (UserDAOException ex) {
					String message = ex.getMessage();
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);			
				}
			}
		});
		btnInsert.setForeground(new Color(0, 0, 255));
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInsert.setBounds(175, 195, 101, 35);
		contentPane.add(btnInsert);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchUser().setEnabled(true);
				Main.getInsertUser().setVisible(false);
			}
		});
		btnClose.setForeground(new Color(0, 0, 255));
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(309, 195, 101, 35);
		contentPane.add(btnClose);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(24, 178, 400, 1);
		contentPane.add(separator);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(24, 35, 309, 116);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(116, 65, 150, 20);
		panel.add(txtPassword);
	}
}
