package gr.aueb.cf.schoolapp.controllerview;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public Menu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		setTitle("Διαχειριση Εκπαιδευτικου Συστηματος");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQuality = new JLabel("Ποιοτητα στην Εκπαιδευση");
		lblQuality.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuality.setBounds(59, 9, 254, 38);
		contentPane.add(lblQuality);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(30, 60, 375, 1);
		contentPane.add(separator);
		
		JButton btnTeachers = new JButton("");
		btnTeachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(false);
				Main.getSearchForm().setVisible(true);
			}
		});
		btnTeachers.setBounds(26, 92, 36, 36);
		contentPane.add(btnTeachers);
		
		JButton btnStudents = new JButton("");
		btnStudents.setBounds(26, 139, 36, 36);
		contentPane.add(btnStudents);
		
		JLabel lblStudents = new JLabel("Εκπαιδευομενοι");
		lblStudents.setForeground(new Color(128, 0, 0));
		lblStudents.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStudents.setBounds(72, 145, 137, 23);
		contentPane.add(lblStudents);
		
		JLabel lblTeachers = new JLabel("Εκπαιδευτες");
		lblTeachers.setForeground(new Color(128, 0, 0));
		lblTeachers.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTeachers.setBounds(72, 102, 111, 22);
		contentPane.add(lblTeachers);
		
		JLabel lblQuality2 = new JLabel("Ποιοτητα στην Εκπαιδευση");
		lblQuality2.setForeground(new Color(0, 128, 0));
		lblQuality2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuality2.setBounds(61, 11, 254, 38);
		contentPane.add(lblQuality2);
	}
}
