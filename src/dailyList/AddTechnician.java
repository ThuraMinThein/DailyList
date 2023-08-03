package dailyList;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddTechnician extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtAddress;
	private JTextField txtPhone;
	private JFrame adminFrame;

	public AddTechnician(JFrame adminFrame) {
		
		this.adminFrame = adminFrame;
		
		addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                adminFrame.setVisible(true);
            }
        });
		
		setResizable(false);
		setTitle("New Technician");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 200, 355, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblNewLabel.setBounds(73, 57, 62, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblAge.setBounds(73, 130, 62, 22);
		contentPane.add(lblAge);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblAddress.setBounds(73, 203, 62, 22);
		contentPane.add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblPhone.setBounds(73, 276, 62, 22);
		contentPane.add(lblPhone);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		txtName.setBounds(73, 90, 192, 29);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtAge = new JTextField();
		txtAge.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		txtAge.setColumns(10);
		txtAge.setBounds(73, 163, 192, 29);
		contentPane.add(txtAge);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		txtAddress.setColumns(10);
		txtAddress.setBounds(73, 236, 192, 29);
		contentPane.add(txtAddress);
		
		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		txtPhone.setColumns(10);
		txtPhone.setBounds(73, 309, 192, 29);
		contentPane.add(txtPhone);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setForeground(new Color(255, 255, 255));
		btnConfirm.setBackground(new Color(255, 21, 21));
		btnConfirm.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		
		InputMap inputMap = btnConfirm.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = btnConfirm.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "enterKey");
        actionMap.put("enterKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	btnConfirm.doClick();
            }
        });
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String address = txtAddress.getText();
				String phone = txtPhone.getText();
				String name = null;
				int age=0;
				
				//check the name from text field is exist or not, if it's exist, cannot add new technician.
						try {
							Connection con = DatabaseConnection.databaseIntitalize();
							String sql="select name from service_technician where name = ?";
							PreparedStatement stmt = con.prepareStatement(sql);
							
							stmt.setString(1, txtName.getText());
							
							ResultSet rs = stmt.executeQuery();
							
							boolean existTechnician = false;
							
							while(rs.next()) {
								existTechnician = true;								
							}
							
							if(!existTechnician) {
								name = txtName.getText();
							}
							else {
								ErrorMessage msg = new ErrorMessage("This name was already Existed");
								msg.setVisible(true);
							}
							
						}catch(Exception e3) {
							e3.printStackTrace();
						}
				
				//check the age from text field is only number or not, if it isn't only number, cann't be able to add.
				try {
				 age = Integer.valueOf(txtAge.getText());
				}catch(Exception e2) {
					ErrorMessage msg = new ErrorMessage("<html>Your need to input all Text Fields <br>(OR)<br>Can't Assign Words in Age, it's should be numbers");
					msg.setVisible(true);
				}
				
				//check the text field is null or not, if it's null, cannot add new Technician.
				if(name.isEmpty() || age == 0|| address.isEmpty() || phone.isEmpty()) {
					if(age!=0) {
						ErrorMessage msg = new ErrorMessage("You need to input all TextFields");
						msg.setVisible(true);
					}
				
				}else {
					try {
						Connection con = DatabaseConnection.databaseIntitalize();
						String sql="insert into service_technician (name,age,address,phone) values(?,?,?,?)";
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setString(1, name);
						stmt.setInt(2, age);
						stmt.setString(3, address);
						stmt.setString(4, phone);
						
						stmt.executeUpdate();
						
						ErrorMessage msg = new ErrorMessage("Successfully added");
						msg.setVisible(true);
						
						txtName.setText(null);
						txtAge.setText(null);
						txtAddress.setText(null);
						txtPhone.setText(null);
						
					}catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnConfirm.setBounds(157, 369, 108, 29);
		contentPane.add(btnConfirm);
		
		JLabel lblNewTechnician = new JLabel("New Technician");
		lblNewTechnician.setForeground(new Color(255, 4, 4));
		lblNewTechnician.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		lblNewTechnician.setBounds(111, 11, 149, 24);
		contentPane.add(lblNewTechnician);
	}
}
