package dailyList;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;
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
import java.awt.event.ActionEvent;

public class CollectAdminPassword extends JFrame {
	private JFrame mainFrame;
	private JPanel contentPane;
	private JPasswordField pwdAdmin;

	public CollectAdminPassword(JFrame mainFrame) {
		
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
		setBounds(500, 200, 464, 280);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Password");
		lblNewLabel.setFont(new Font("Bodoni MT", Font.BOLD, 16));
		lblNewLabel.setBounds(172, 11, 115, 34);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 244, 244));
		panel.setBounds(0, 0, 448, 111);
		contentPane.add(panel);
		panel.setLayout(null);
		
		pwdAdmin = new JPasswordField();
		pwdAdmin.setBackground(new Color(254, 244, 244));
		pwdAdmin.setBounds(140, 62, 165, 34);
		panel.add(pwdAdmin);
		
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
				
				String password = String.valueOf(pwdAdmin.getPassword());
				
					if(password.equals("admin")) {
						
						AdminPage admin = new AdminPage(mainFrame);
						admin.setVisible(true);
						setVisible(false);
						
					}
					else {
						ErrorMessage msg = new ErrorMessage("Incorrect Password");
						msg.setVisible(true);
						pwdAdmin.setText(null);
					}
				  
			}
		});
		btnEnter.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnEnter.setForeground(new Color(254, 244, 244));
		btnEnter.setBackground(new Color(246, 81, 81));
		btnEnter.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		btnEnter.setBounds(176, 44, 99, 33);
		panel_1.add(btnEnter);
	}
}
