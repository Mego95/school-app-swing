package gr.aueb.cf.schoolapp.controllerview;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SearchUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private String inputUsername;
	
	/**
	 * Create the frame.
	 */
	public SearchUser() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtUsername.setText("");
			}
		});
		setTitle("Αναζητηση User");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 339);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(128, 0, 0));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUsername.setBounds(178, 27, 99, 32);
		contentPane.add(lblUsername);
		
		JButton btnSearch = new JButton("Αναζητηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputUsername = txtUsername.getText().trim();
				Main.getUpdateDeleteUsers().setVisible(true);
				Main.getSearchUser().setEnabled(false);
			}
		});
		btnSearch.setForeground(new Color(0, 0, 255));
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(165, 98, 117, 50);
		contentPane.add(btnSearch);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(148, 65, 150, 25);
		contentPane.add(txtUsername);
		txtUsername.setColumns(50);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setForeground(new Color(0, 0, 255));
		btnClose.setBounds(381, 257, 89, 32);
		contentPane.add(btnClose);
		
		JButton btnInsert = new JButton("Εισαγωγη");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchUser().setEnabled(false);
				Main.getInsertUser().setVisible(true);
			}
		});
		btnInsert.setForeground(Color.BLUE);
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInsert.setBounds(165, 191, 117, 50);
		contentPane.add(btnInsert);
		
		JPanel panel = new JPanel();
		panel.setBounds(87, 24, 284, 141);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(87, 176, 284, 83);
		contentPane.add(panel_1);
	}

	public String getInputUsername() {
		return inputUsername;
	}
	
	

}
