package dailyList;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class FindTechnicians extends JFrame {
	private JFrame adminFrame;
	private JPanel contentPane;
	private JTextField txtTechnician;

	public FindTechnicians(JFrame adminFrame) {
		
		this.adminFrame = adminFrame;
		
		addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	adminFrame.setVisible(true);
            }
        });
		
		setResizable(false);
		setTitle("Search For Technician");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 464, 280);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Technician Name");
		lblNewLabel.setFont(new Font("Bodoni MT", Font.BOLD, 16));
		lblNewLabel.setBounds(141, 11, 164, 34);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 244, 244));
		panel.setBounds(0, 0, 448, 111);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtTechnician = new JTextField();
		txtTechnician.setBackground(new Color(254, 244, 244));
		txtTechnician.setBounds(141, 51, 162, 31);
		panel.add(txtTechnician);
		txtTechnician.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(46, 40, 40));
		panel_1.setBounds(0, 111, 448, 130);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnEnter = new JButton("Enter");
		
		InputMap inputMap = btnEnter.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = btnEnter.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "enterKey");
        actionMap.put("enterKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	btnEnter.doClick();
            }
        });
		
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String techName = txtTechnician.getText();
				
				try {
					
					Connection con = DatabaseConnection.databaseIntitalize();
					String sql = "select technician_id, name, age, address, phone from service_technician where name = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, techName);
					ResultSet rs = stmt.executeQuery();
					
					boolean tech = false;
					int id =0, age =0;
					String name= null,address= null,phone= null;
					
					while(rs.next()) {
						tech = true;
						id = rs.getInt("technician_id");
						name = rs.getString("name");
						age = rs.getInt("age");
						address = rs.getString("address");
						phone = rs.getString("phone");
					}
					
					if (tech) {
						
						UpdateTechnician updTech = new UpdateTechnician(adminFrame,id,name,age,address,phone);
						updTech.setVisible(true);
						setVisible(false);
						
					}
					else {
						ErrorMessage msg = new ErrorMessage("Technician Not Found");
						msg.setVisible(true);
					}
					
					txtTechnician.setText(null);
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnEnter.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnEnter.setForeground(new Color(254, 244, 244));
		btnEnter.setBackground(new Color(246, 81, 81));
		btnEnter.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		btnEnter.setBounds(175, 44, 99, 33);
		panel_1.add(btnEnter);
	}
}
