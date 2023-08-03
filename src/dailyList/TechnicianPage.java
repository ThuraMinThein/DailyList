package dailyList;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TechnicianPage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JTextField txtCusName;
	private JLabel lblCustomerPhone;
	private JTextField txtCusPhone;
	private JLabel lblError;
	private JTextField txtError;
	private JLabel lblModel;
	private JLabel lblSolution;
	private JTextField txtSolution;
	private JButton btnAdd;
	private JLabel lblNewLabel_1;
	private JTextField txtVouncherNo;
	private JTextField txtRemark;
	private JLabel lblRemark;
	private JFrame mainFrame;
	private JPanel panel;
	private JPanel panel_1;

	public TechnicianPage(JFrame mainFrame,int technician_id) {
		
		this.mainFrame = mainFrame;
		addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainFrame.setVisible(true);
            }
        });
		
		setTitle("Technician Service");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 200, 1142, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(276, 33, 840, 493);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.getTableHeader().setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		table.getTableHeader().setBackground(new Color(58, 79, 80));
		table.getTableHeader().setForeground(new Color(244, 254, 253));
		table.setBackground(new Color(254, 244, 244));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No", "Date", "Customer Name", "Phone No", "Error", "Model", "Solution", "Remark"
			}
		));
		
		//clear default table model;
		((DefaultTableModel)table.getModel()).setRowCount(0);
		
		//Rows from database
		try {
			Connection con = DatabaseConnection.databaseIntitalize();
			String sql = "select service_vouncher_no,date,customer_name,customer_phone,error,model,solution,remark from service_detail where technician_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, technician_id);
			
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
		
		table.getColumnModel().getColumn(0).setPreferredWidth(47);
		table.getColumnModel().getColumn(2).setPreferredWidth(109);
		table.getColumnModel().getColumn(4).setPreferredWidth(102);
		table.getColumnModel().getColumn(5).setPreferredWidth(79);
		table.getColumnModel().getColumn(6).setPreferredWidth(117);
		table.getColumnModel().getColumn(7).setPreferredWidth(61);
		table.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.setBackground(new Color(254, 244, 244));
		panel.setBounds(0, 0, 259, 567);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Fill Your Services");
		lblNewLabel_1.setBounds(72, 11, 125, 23);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		
		JLabel lblVouncherNo = new JLabel("Vouncher No");
		lblVouncherNo.setBounds(42, 45, 103, 20);
		panel.add(lblVouncherNo);
		lblVouncherNo.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		
		txtVouncherNo = new JTextField();
		txtVouncherNo.setBackground(new Color(254, 244, 244));
		txtVouncherNo.setBounds(42, 65, 171, 29);
		panel.add(txtVouncherNo);
		txtVouncherNo.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		txtVouncherNo.setColumns(10);
		
		lblNewLabel = new JLabel("Customer Name");
		lblNewLabel.setBounds(42, 105, 103, 20);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		
		txtCusName = new JTextField();
		txtCusName.setBackground(new Color(254, 244, 244));
		txtCusName.setBounds(42, 124, 171, 29);
		panel.add(txtCusName);
		txtCusName.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		txtCusName.setColumns(10);
		
		lblCustomerPhone = new JLabel("Customer Phone");
		lblCustomerPhone.setBounds(42, 164, 103, 20);
		panel.add(lblCustomerPhone);
		lblCustomerPhone.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		
		txtCusPhone = new JTextField();
		txtCusPhone.setBackground(new Color(254, 244, 244));
		txtCusPhone.setBounds(42, 181, 171, 29);
		panel.add(txtCusPhone);
		txtCusPhone.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		txtCusPhone.setColumns(10);
		
		lblError = new JLabel("Error");
		lblError.setBounds(42, 222, 39, 20);
		panel.add(lblError);
		lblError.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		
		txtError = new JTextField();
		txtError.setBackground(new Color(254, 244, 244));
		txtError.setBounds(42, 241, 171, 29);
		panel.add(txtError);
		txtError.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		txtError.setColumns(10);
		
		lblModel = new JLabel("Model");
		lblModel.setBounds(42, 281, 52, 20);
		panel.add(lblModel);
		lblModel.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		
		JTextField txtModel = new JTextField();
		txtModel.setBackground(new Color(254, 244, 244));
		txtModel.setBounds(42, 300, 171, 29);
		panel.add(txtModel);
		txtModel.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		txtModel.setColumns(10);
		
		lblSolution = new JLabel("Solution");
		lblSolution.setBounds(42, 340, 52, 20);
		panel.add(lblSolution);
		lblSolution.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		
		txtSolution = new JTextField();
		txtSolution.setBackground(new Color(254, 244, 244));
		txtSolution.setBounds(42, 359, 171, 29);
		panel.add(txtSolution);
		txtSolution.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		txtSolution.setColumns(10);
		
		lblRemark = new JLabel("Remark");
		lblRemark.setBounds(42, 399, 52, 20);
		panel.add(lblRemark);
		lblRemark.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		
		txtRemark = new JTextField();
		txtRemark.setBackground(new Color(254, 244, 244));
		txtRemark.setBounds(42, 418, 171, 29);
		panel.add(txtRemark);
		txtRemark.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		txtRemark.setColumns(10);
		
		btnAdd = new JButton("Add");
		InputMap inputMap = btnAdd.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = btnAdd.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "enterKey");
        actionMap.put("enterKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	btnAdd.doClick();
            }
        });
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(46, 50, 57));
		btnAdd.setBounds(123, 489, 89, 29);
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int vrNo =0;
				LocalDate date = LocalDate.now();
				String cusName =txtCusName.getText();
				String cusPhone =txtCusPhone.getText();
				String error =txtError.getText();
				String solution =txtSolution.getText();
				String model =txtModel.getText();
				String remark = txtRemark.getText();
				try {
					Connection con = DatabaseConnection.databaseIntitalize();
					String sql="select service_vouncher_no from service_detail where service_vouncher_no = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, txtVouncherNo.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					boolean existVouncherNo = false;
					
					while(rs.next()) {
						existVouncherNo = true;								
					}
					
					if(!existVouncherNo) {
						try {
						 vrNo = Integer.valueOf(txtVouncherNo.getText());
						}catch(Exception e2) {
							ErrorMessage msg = new ErrorMessage("<html>Can't Add New List. Because<br>You need to input All Text Fields <br>(OR) <br>Can't Assign Words in Vouncher Number, it's should be numbers</html>");
							msg.setVisible(true);
						}
					}
					else {
						ErrorMessage msg = new ErrorMessage("<html>This Vouncher Number was <br>already Existed</html>");
						msg.setVisible(true);
						
						txtVouncherNo.setText(null);
					}
					
				}catch(Exception e3) {
					e3.printStackTrace();
				}
				
				if(vrNo==0 || cusName.isEmpty() || cusPhone.isEmpty() || error.isEmpty() || solution.isEmpty() || model.isEmpty()) {
					if(vrNo!=0) {
						ErrorMessage msg = new ErrorMessage ("You need to INPUT all Text Field");
						msg.setVisible(true);
					}
				}
				else {
				
					try {
						Connection con= DatabaseConnection.databaseIntitalize();
						String sql = "insert into service_detail (service_vouncher_no,date,customer_name,customer_phone,error,model,solution,remark,technician_id) values (?,?,?,?,?,?,?,?,?)";
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setInt(1, vrNo);
						stmt.setDate(2, Date.valueOf(date));
						stmt.setString(3, cusName);
						stmt.setString(4, cusPhone);
						stmt.setString(5, error);
						stmt.setString(6, model);
						stmt.setString(7, solution);
						stmt.setString(8, remark);
						stmt.setInt(9, technician_id);
						
						stmt.executeUpdate();
						//JOptionPane.showMessageDialog(null, "Successfully Added");
						
						txtVouncherNo.setText(null);
						txtCusName.setText(null);
						txtCusPhone.setText(null);
						txtError.setText(null);
						txtSolution.setText(null);
						txtModel.setText(null);
						txtRemark.setText(null);
						
					}catch(Exception e1) {
						e1.printStackTrace();
					}
				}

				((DefaultTableModel)table.getModel()).setRowCount(0);
				
				try {
					Connection con = DatabaseConnection.databaseIntitalize();
					String sql = "select service_vouncher_no,date,customer_name,customer_phone,error,model,solution,remark from service_detail where technician_id = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setInt(1, technician_id);
					
					ResultSet rs = stmt.executeQuery();
					
					int no;
					Date date1;
					String customerName, customerPhone, error1, model1, solution1,remark1;
					
					while(rs.next()) {
						no = rs.getInt("service_vouncher_no");
						date1 = rs.getDate("date");
						customerName = rs.getString("customer_name");
						customerPhone = rs.getString("customer_phone");
						error1 = rs.getString("error");
						model1 = rs.getString("model");
						solution1 = rs.getString("solution");
						remark1 = rs.getString("remark");
						
						((DefaultTableModel)table.getModel()).addRow(new Object[] {no,date1,customerName,customerPhone,error1,model1,solution1,remark1});
					}
					
					
				}catch(Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnAdd.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(38, 41, 43));
		panel_1.setBounds(258, 0, 868, 567);
		contentPane.add(panel_1);
	}
}
