package dailyList;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JLabel;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JFrame mainFrame;
	public AdminPage(JFrame mainFrame) {
		
		this.mainFrame = mainFrame;
		
		addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainFrame.setVisible(true);
            }
        });
		
		setResizable(false);
		setTitle("Admin");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 200, 1075, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();

		panel.setBackground(new Color(46, 40, 40));
		panel.setBounds(165, 0, 894, 444);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 874, 386);
		panel.add(scrollPane);
		
		table = new JTable();

		table.getTableHeader().setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		table.getTableHeader().setBackground(new Color(80, 58, 58));
		table.getTableHeader().setForeground(new Color(244, 254, 253));
		table.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		table.setBackground(new Color(254, 244, 244));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Date", "Customer Name", "Phone No", "Error", "Model", "Solution", "Remark"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(81);
		table.getColumnModel().getColumn(2).setPreferredWidth(92);
		table.getColumnModel().getColumn(4).setPreferredWidth(112);
		table.getColumnModel().getColumn(5).setPreferredWidth(85);
		table.getColumnModel().getColumn(6).setPreferredWidth(123);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();

		panel_1.setBackground(new Color(254, 244, 244));
		panel_1.setBounds(0, 0, 167, 444);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(254, 244, 244));
		menuBar.setBounds(0, 0, 96, 27);
		panel_1.add(menuBar);
		
		JMenu mnTechnician = new JMenu("Setting");
		mnTechnician.setBackground(new Color(254, 244, 244));
		mnTechnician.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		menuBar.add(mnTechnician);
		
		JMenuItem mntmAddTechnician = new JMenuItem("Add Technician");
		mntmAddTechnician.setBackground(new Color(254, 244, 244));
		mntmAddTechnician.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTechnician addTech = new AddTechnician(AdminPage.this);
				addTech.setVisible(true);
				setVisible(false);
			}
		});
		mntmAddTechnician.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		mnTechnician.add(mntmAddTechnician);
		
		JMenuItem mntmUpdateTechnician = new JMenuItem("Update Technician");
		mntmUpdateTechnician.setBackground(new Color(254, 244, 244));
		mntmUpdateTechnician.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindTechnicians findTech = new FindTechnicians(AdminPage.this);
				findTech.setVisible(true);
				setVisible(false);
				
			}
		});
		
		JSeparator separator = new JSeparator();
		mnTechnician.add(separator);
		mnTechnician.add(mntmUpdateTechnician);
		
		JComboBox comboTechnicianList = new JComboBox();
		comboTechnicianList.setMaximumRowCount(15);
		comboTechnicianList.setBackground(new Color(254, 244, 244));
		comboTechnicianList.setBounds(16, 88, 131, 27);
		panel_1.add(comboTechnicianList);
		

		//Add data into combo box.
		comboTechnicianList.addItem("All");
		

		try {
			Connection con = DatabaseConnection.databaseIntitalize();
			String sql = "select name,technician_id from service_technician ";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				comboTechnicianList.addItem(name);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		comboTechnicianList.setSelectedIndex(-1);
		
		comboTechnicianList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tech_id =0;
				try {
					Connection con = DatabaseConnection.databaseIntitalize();
					String sql = "select technician_id from service_technician where name = ? ";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, String.valueOf(comboTechnicianList.getSelectedItem()));
					
					ResultSet rs = stmt.executeQuery();
					while(rs.next()) {
						tech_id =rs.getInt("technician_id");
					}
				}catch(Exception e2) {
					e2.printStackTrace();
				}
				
				//clear default table model;
				((DefaultTableModel)table.getModel()).setRowCount(0);
				
				//Rows from database
				if(comboTechnicianList.getSelectedIndex() == 0) {
					
					try {
						Connection con = DatabaseConnection.databaseIntitalize();
						String sql = "select service_vouncher_no,date,customer_name,customer_phone,error,model,solution,remark from service_detail";
						PreparedStatement stmt = con.prepareStatement(sql);
						
						ResultSet rs = stmt.executeQuery();
						
						int no;
						Date date;
						String customerName, customerPhone, error, model, solution,remark;
						
						while(rs.next()) {
							no = rs.getInt("service_vouncher_no");
							date = rs.getDate("date");
							customerName = rs.getString("customer_name");
							customerPhone = rs.getString("customer_phone");
							error = rs.getString("error");
							model = rs.getString("model");
							solution = rs.getString("solution");
							remark = rs.getString("remark");
							
							((DefaultTableModel)table.getModel()).addRow(new Object[] {no,date,customerName,customerPhone,error,model,solution,remark});
						}
						
						
					}catch(Exception e2) {
						e2.printStackTrace();
					}
				
				}else {
					try {
						Connection con = DatabaseConnection.databaseIntitalize();
						String sql = "select service_vouncher_no,date,customer_name,customer_phone,error,model,solution,remark from service_detail where technician_id = ?";
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setInt(1,tech_id );
						
						ResultSet rs = stmt.executeQuery();
						
						int no;
						Date date;
						String customerName, customerPhone, error, model, solution,remark;
						
						while(rs.next()) {
							no = rs.getInt("service_vouncher_no");
							date = rs.getDate("date");
							customerName = rs.getString("customer_name");
							customerPhone = rs.getString("customer_phone");
							error = rs.getString("error");
							model = rs.getString("model");
							solution = rs.getString("solution");
							remark = rs.getString("remark");
							
							((DefaultTableModel)table.getModel()).addRow(new Object[] {no,date,customerName,customerPhone,error,model,solution,remark});
						}
						
						
					}catch(Exception e2) {
						e2.printStackTrace();
					}
					
				}				
			}
		});
		
		comboTechnicianList.setFont(new Font("Century Schoolbook", Font.PLAIN, 12));
		
		JLabel lblNewLabel = new JLabel("Select Technician");
		lblNewLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		lblNewLabel.setBounds(16, 50, 131, 29);
		panel_1.add(lblNewLabel);
	}
}
