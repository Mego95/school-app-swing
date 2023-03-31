package gr.aueb.cf.schoolapp.controllerview;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertForm extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
	
	private JPanel contentPane;
	private JTextField txtFirstname;
	private JTextField txtLastname;
	
	
	/**
	 * Create the frame.
	 */
	public InsertForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtLastname.setText("");
				txtFirstname.setText("");
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		setTitle("Εισαγωγη Εκπαιδευτη");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLastname = new JLabel("Επωνυμο");
		lblLastname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastname.setForeground(new Color(128, 0, 0));
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastname.setBounds(58, 55, 72, 29);
		contentPane.add(lblLastname);
		
		JLabel lblFirstname = new JLabel("Ονομα");
		lblFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstname.setForeground(new Color(128, 0, 0));
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFirstname.setBounds(58, 95, 72, 29);
		contentPane.add(lblFirstname);
		
		txtFirstname = new JTextField();
		txtFirstname.setBounds(140, 101, 150, 20);
		contentPane.add(txtFirstname);
		txtFirstname.setColumns(50);
		
		txtLastname = new JTextField();
		txtLastname.setBounds(140, 61, 150, 20);
		contentPane.add(txtLastname);
		txtLastname.setColumns(50);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputLastname = txtLastname.getText().trim();
				String inputFirstname = txtFirstname.getText().trim();
				
				if (inputLastname.equals("") || inputFirstname.equals("")) {
					JOptionPane.showMessageDialog(null, "Not valid input", "Insert Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					TeacherDTO teacherDTO = new TeacherDTO();
					teacherDTO.setFirstname(inputFirstname);
					teacherDTO.setLastname(inputLastname);
					
					teacherService.insertTeacher(teacherDTO);
					
					JOptionPane.showMessageDialog(null, "Teacher was inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);
				} catch (TeacherDAOException ex) {
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
				Main.getSearchForm().setEnabled(true);
				Main.getInsertForm().setVisible(false);
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
	}
}
