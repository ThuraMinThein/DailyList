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
import javax.swing.SwingConstants;

public class ErrorMessage extends JFrame {
	private JPanel contentPane;

	public ErrorMessage(String message) {
		
		setResizable(false);
		setTitle("Message");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 401, 246);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(254, 244, 244));
		panel.setBounds(0, 0, 385, 207);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMessage = new JLabel(message);
		lblMessage.setForeground(new Color(255, 0, 0));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Century Schoolbook", Font.BOLD, 14));
		lblMessage.setBounds(60, 11, 259, 124);
		panel.add(lblMessage);
		
		JButton btnOk = new JButton("OK");
		btnOk.setForeground(new Color(254, 244, 244));
		btnOk.setBackground(new Color(246, 81, 81));
		btnOk.setFont(new Font("Century Schoolbook", Font.BOLD, 13));
		btnOk.setBounds(146, 157, 89, 25);
		panel.add(btnOk);
		
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		InputMap inputMap = btnOk.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = btnOk.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "enterKey");
        actionMap.put("enterKey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	btnOk.doClick();
            }
        });
	}
}
