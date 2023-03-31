package gr.aueb.cf.schoolapp.controllerview;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolapp.Main;
import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.TeacherServiceImpl;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateDeleteForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtLastname;
	private JTextField txtFirstname;
	
	private ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private ITeacherService teacherService = new TeacherServiceImpl(teacherDAO);
	private int listPosition;
	private int listSize;
	List<Teacher> teachers;

	/**
	 * Create the frame.
	 */
	public UpdateDeleteForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				try {
					teachers = teacherService.getTeachersByLastName(Main.getSearchForm().getInputLastname());
					
					listPosition = 0;
					listSize = teachers.size();
					
					if (listSize == 0) return;
					
					txtId.setText(Integer.toString(teachers.get(listPosition).getId()));
					txtLastname.setText(teachers.get(listPosition).getLastname());
					txtFirstname.setText(teachers.get(listPosition).getFirstname());
					
					
				} catch (TeacherDAOException ex) {
					String message = ex.getMessage();
					JOptionPane.showMessageDialog(null, message, "Error in getting teachers", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			@Override
			public void windowClosed(WindowEvent e) {
				txtId.setText("");
				txtLastname.setText("");
				txtFirstname.setText("");
			
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		setTitle("Ενημερωση/Διαγραφη Εκπαιδευτη");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbld = new JLabel("Κωδικος");
		lbld.setForeground(new Color(128, 0, 0));
		lbld.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbld.setHorizontalAlignment(SwingConstants.RIGHT);
		lbld.setBounds(30, 39, 78, 20);
		contentPane.add(lbld);
		
		JLabel lblLastname = new JLabel("Επωνυμο");
		lblLastname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastname.setForeground(new Color(128, 0, 0));
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastname.setBounds(30, 81, 78, 20);
		contentPane.add(lblLastname);
		
		JLabel lblFirstname = new JLabel("Ονομα");
		lblFirstname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstname.setForeground(new Color(128, 0, 0));
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFirstname.setBounds(30, 113, 78, 20);
		contentPane.add(lblFirstname);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(136, 39, 49, 20);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtLastname = new JTextField();
		txtLastname.setBounds(136, 81, 169, 20);
		contentPane.add(txtLastname);
		txtLastname.setColumns(50);
		
		txtFirstname = new JTextField();
		txtFirstname.setBounds(136, 116, 169, 20);
		contentPane.add(txtFirstname);
		txtFirstname.setColumns(50);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(30, 171, 350, 1);
		contentPane.add(separator);
		
		JButton btnFirstEntry = new JButton("");
		btnFirstEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listSize > 0) {
					listPosition = 0;
					
					txtId.setText(Integer.toString(teachers.get(listPosition).getId()));
					txtLastname.setText(teachers.get(listPosition).getLastname());
					txtFirstname.setText(teachers.get(listPosition).getFirstname());
				}
			}
		});
		btnFirstEntry.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("First record.png")));
		btnFirstEntry.setBounds(40, 183, 50, 23);
		contentPane.add(btnFirstEntry);
		
		JButton btnPreviousEntry = new JButton("");
		btnPreviousEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (listPosition > 0) {
					
					listPosition--;
					txtId.setText(Integer.toString(teachers.get(listPosition).getId()));
					txtLastname.setText(teachers.get(listPosition).getLastname());
					txtFirstname.setText(teachers.get(listPosition).getFirstname());
				}
				
				
			}
		});
		btnPreviousEntry.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Previous_record.png")));
		btnPreviousEntry.setBounds(100, 183, 50, 23);
		contentPane.add(btnPreviousEntry);
		
		JButton btnNextEntry = new JButton("");
		btnNextEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (listPosition <= listSize - 2) {
					
					listPosition++;
					txtId.setText(Integer.toString(teachers.get(listPosition).getId()));
					txtLastname.setText(teachers.get(listPosition).getLastname());
					txtFirstname.setText(teachers.get(listPosition).getFirstname());
				}
				
			}
		});
		btnNextEntry.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Next_track.png")));
		btnNextEntry.setBounds(160, 183, 50, 23);
		contentPane.add(btnNextEntry);
		
		JButton btnLastEntry = new JButton("");
		btnLastEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (listSize > 0) {
					listPosition = listSize - 1;
					
					txtId.setText(Integer.toString(teachers.get(listPosition).getId()));
					txtLastname.setText(teachers.get(listPosition).getLastname());
					txtFirstname.setText(teachers.get(listPosition).getFirstname());
				}
			}
		});
		btnLastEntry.setIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource("Last_Record.png")));
		btnLastEntry.setBounds(220, 183, 50, 23);
		contentPane.add(btnLastEntry);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					
					if (txtId.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Not valid input", "Insert Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					int response;
					int id = Integer.parseInt(txtId.getText());
					
					response = JOptionPane.showConfirmDialog(null, "Ειστε σιγουροι;", "Warning", JOptionPane.YES_NO_OPTION);
					
					if (response == JOptionPane.YES_OPTION) {
						teacherService.deleteTeacher(id);
					}
					
					JOptionPane.showMessageDialog(null, "Teacher was deleted", "DELETE", JOptionPane.INFORMATION_MESSAGE);
					
					
				} catch (TeacherNotFoundException | TeacherDAOException ex) {
					String message = ex.getMessage();
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnDelete.setForeground(new Color(0, 0, 255));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(33, 217, 100, 35);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = txtId.getText();
				String inputLastname = txtLastname.getText().trim();
				String inputFirstname = txtFirstname.getText().trim();
				
				if (inputLastname.equals("") || inputFirstname.equals("")) {
					JOptionPane.showMessageDialog(null, "Not valid input", "Insert Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					TeacherDTO teacherDTO = new TeacherDTO();
					teacherDTO.setId(Integer.parseInt(id));
					teacherDTO.setFirstname(inputFirstname);
					teacherDTO.setLastname(inputLastname);
					
					teacherService.updateTeacher(teacherDTO);
					
					JOptionPane.showMessageDialog(null, "Teacher was updated", "UPDATE", JOptionPane.PLAIN_MESSAGE);
				} catch (TeacherDAOException | TeacherNotFoundException ex) {
					String message = ex.getMessage();
					JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnUpdate.setForeground(new Color(0, 0, 255));
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(166, 217, 100, 35);
		contentPane.add(btnUpdate);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchForm().setEnabled(true);
				Main.getUpdateDeleteForm().setVisible(false);
			}
		});
		btnClose.setForeground(new Color(0, 0, 255));
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClose.setBounds(299, 217, 100, 35);
		contentPane.add(btnClose);
	}

}
