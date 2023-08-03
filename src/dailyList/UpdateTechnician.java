package dailyList;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UpdateTechnician extends JFrame {

	private JPanel contentPane;
	private JTextField txtChangeName;
	private JTextField txtChangeAge;
	private JTextField txtChangeAddress;
	private JTextField txtChangePhone;
	private JFrame adminPage;

	public UpdateTechnician(JFrame adminPage,int id,String name,int age,String address,String phone) {
		
		this.adminPage = adminPage;
		addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                adminPage.setVisible(true);
            }
        });
		
		setTitle("UpdateTechnician");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 200, 380, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Technician");
		lblNewLabel.setForeground(new Color(255, 4, 4));
		lblNewLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		lblNewLabel.setBounds(94, 11, 165, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblName.setBounds(94, 79, 63, 24);
		contentPane.add(lblName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblAge.setBounds(94, 155, 63, 24);
		contentPane.add(lblAge);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblAddress.setBounds(94, 231, 63, 24);
		contentPane.add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		lblPhone.setBounds(94, 317, 63, 24);
		contentPane.add(lblPhone);
		
		txtChangeName = new JTextField();
		txtChangeName.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		txtChangeName.setColumns(10);
		txtChangeName.setBounds(94, 114, 177, 30);
		contentPane.add(txtChangeName);
		
		txtChangeAge = new JTextField();
		txtChangeAge.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		txtChangeAge.setColumns(10);
		txtChangeAge.setBounds(94, 190, 177, 30);
		contentPane.add(txtChangeAge);
		
		txtChangeAddress = new JTextField();
		txtChangeAddress.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		txtChangeAddress.setColumns(10);
		txtChangeAddress.setBounds(94, 266, 177, 30);
		contentPane.add(txtChangeAddress);
		
		txtChangePhone = new JTextField();
		txtChangePhone.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		txtChangePhone.setColumns(10);
		txtChangePhone.setBounds(94, 352, 177, 30);
		contentPane.add(txtChangePhone);
	
		
		txtChangeName.setText(name);
		txtChangeAge.setText(String.valueOf(age));
		txtChangeAddress.setText(address);
		txtChangePhone.setText(phone);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setForeground(new Color(255, 255, 255));
		btnConfirm.setBackground(new Color(255, 4, 4));
		
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
				String chname = txtChangeName.getText();
				int chage =0;
				String chaddress = txtChangeAddress.getText();
				String chphone = txtChangePhone.getText();
				
				try {
					chage = Integer.parseInt(txtChangeAge.getText());
				}catch(Exception e1) {
					ErrorMessage msg = new ErrorMessage("You need to input numbers in age");
					msg.setVisible(true);
				}
				
				if(chage!=0)
				try {
					
					Connection con = DatabaseConnection.databaseIntitalize();
					String sql = "update service_technician set name = ?, age = ?, address = ?, phone = ? where technician_id = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, chname);
					stmt.setInt(2, chage);
					stmt.setString(3, chaddress);
					stmt.setString(4, chphone);
					stmt.setInt(5, id);
					
					stmt.executeUpdate();

					ErrorMessage msg = new ErrorMessage("<html>Name : " + chname + "<br>Age : " + chage + "<br>Address : " + chaddress + "<br>Phone : " + chphone + "<html>");
					msg.setVisible(true);
					
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnConfirm.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		btnConfirm.setBounds(168, 425, 103, 30);
		contentPane.add(btnConfirm);
	}
}
