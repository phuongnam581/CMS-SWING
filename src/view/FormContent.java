package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import model.DBConnect;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class FormContent extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	JButton btnSubmitButton;
	public FormContent() {
		setBackground(Color.WHITE);
		setBounds(0, 0, 1233, 600);
		setLayout(null);
		
		event();
		
		JLabel lblNewLabel = new JLabel("Add Content");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(35, 51, 1182, 49);
		//lblNewLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#eeeeee")));
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		panel.setBackground(Color.WHITE);
		panel.setBounds(35, 167, 1182, 422);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(10, 11, 46, 14);
		panel.add(lblTitle);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 620, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblBrief = new JLabel("Brief");
		lblBrief.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBrief.setBounds(10, 67, 46, 14);
		panel.add(lblBrief);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 92, 620, 86);
		textArea.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		panel.add(textArea);
		
		JLabel lblContent = new JLabel("Content");
		lblContent.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContent.setBounds(10, 189, 67, 14);
		panel.add(lblContent);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		textArea_1.setBounds(10, 214, 620, 151);
		panel.add(textArea_1);
		
		btnSubmitButton = new JButton("Submit Button");		
		btnSubmitButton.setBounds(10, 376, 99, 23);
		panel.add(btnSubmitButton);
		
		JButton button = new JButton("Reset Button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(119, 376, 99, 23);
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(35, 122, 1182, 49);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("   Content Form Elements");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setBounds(0, 0, 1182, 49);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
	}

	private void event() {
		// TODO Auto-generated method stub
		btnSubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	}
}
