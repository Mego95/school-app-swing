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

public class SearchForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLastname;
	private String inputLastname;
	
	/**
	 * Create the frame.
	 */
	public SearchForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtLastname.setText("");
			}
		});
		setTitle("Αναζητηση Εκπαιδευτων");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Thread.currentThread().getContextClassLoader().getResource("eduv2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 339);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLastname = new JLabel("Επωνυμο");
		lblLastname.setForeground(new Color(128, 0, 0));
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblLastname.setBounds(183, 27, 81, 32);
		contentPane.add(lblLastname);
		
		JButton btnSearch = new JButton("Αναζητηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLastname = txtLastname.getText().trim();
				Main.getSearchForm().setEnabled(false);
				Main.getUpdateDeleteForm().setVisible(true);
			}
		});
		btnSearch.setForeground(new Color(0, 0, 255));
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(165, 98, 117, 50);
		contentPane.add(btnSearch);
		
		txtLastname = new JTextField();
		txtLastname.setBounds(148, 65, 150, 25);
		contentPane.add(txtLastname);
		txtLastname.setColumns(50);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(true);
				Main.getSearchForm().setVisible(false);
			}
		});
		btnClose.setForeground(new Color(0, 0, 255));
		btnClose.setBounds(381, 257, 89, 32);
		contentPane.add(btnClose);
		
		JButton btnInsert = new JButton("Εισαγωγη");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchForm().setEnabled(false);
				Main.getInsertForm().setVisible(true);
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

	public String getInputLastname() {
		return inputLastname;
	}
	
	

}