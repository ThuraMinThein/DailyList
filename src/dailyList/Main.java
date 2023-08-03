package dailyList;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.Font;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearchTechnician;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setResizable(false);
		setTitle("Daily List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 590, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(282, 0, 292, 342);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTechnician = new JLabel("Search For Technicians");
		lblTechnician.setBounds(47, 66, 205, 29);
		panel.add(lblTechnician);
		lblTechnician.setFont(new Font("Bodoni MT Black", Font.PLAIN, 16));
		
		txtSearchTechnician = new JTextField();
		txtSearchTechnician.setBounds(47, 106, 193, 34);
		panel.add(txtSearchTechnician);
		txtSearchTechnician.setFont(new Font("Century Schoolbook", Font.PLAIN, 14));
		txtSearchTechnician.setColumns(10);
		
		JButton btnSerachTechnician = new JButton("Go");
		btnSerachTechnician.setForeground(new Color(255, 255, 255));
		btnSerachTechnician.setBackground(new Color(255, 4, 4));
		btnSerachTechnician.setBounds(89, 208, 113, 34);
		panel.add(btnSerachTechnician);
		
		InputMap inputMap = btnSerachTechnician.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = btnSerachTechnician.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "enterKey");
        actionMap.put("enterKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                btnSerachTechnician.doClick();
            }
        });
		
		btnSerachTechnician.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchTechnician = txtSearchTechnician.getText();
				int tech_id=0;
				try {
					Connection con = DatabaseConnection.databaseIntitalize();
					String sql="select name,technician_id from service_technician where name = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, searchTechnician);
					
					ResultSet rs = stmt.executeQuery();
					boolean existTechnician = false;
					
					while(rs.next()) {
						existTechnician = true;		
						tech_id = rs.getInt("technician_id");
					}
					
					if(existTechnician) {
						TechnicianPage tech = new TechnicianPage(Main.this,tech_id);
						tech.setVisible(true);
					}
					else {
						ErrorMessage msg = new ErrorMessage("Technician Not Found");
						msg.setVisible(true);
					}
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				txtSearchTechnician.setText(null);
			}
		});
		btnSerachTechnician.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				CollectAdminPassword colPwd = new CollectAdminPassword(Main.this);
				colPwd.setVisible(true);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {

				panel_1.setBackground(new Color(40, 40, 40));
			}
			public void mouseExited(MouseEvent e1) {
				panel_1.setBackground(new Color(111, 111, 111));
			}
		});
		panel_1.setBackground(new Color(111, 111, 111));
		panel_1.setBounds(0, 0, 285, 342);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblA = new JLabel("A D M I N");
		lblA.setForeground(new Color(0, 0, 0));
		lblA.setFont(new Font("Bodoni MT Black", Font.BOLD, 25));
		lblA.setBounds(59, 145, 148, 46);
		panel_1.add(lblA);
	}
}
