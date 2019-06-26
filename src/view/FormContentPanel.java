package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import model.ModelContent;
import object.Content;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FormContentPanel extends JPanel {
	JLabel lblNewLabel;
	JPanel panel;
	JPanel panel_1;
	JLabel lblNewLabel_1;
	JLabel lblTitle;
	JTextField textFieldTitle;
	JLabel lblBrief;
	JTextArea textAreaBrief;
	JLabel lblContent;
	JTextArea textAreaContent;
	private JButton btnSubmitButton;
	private JButton btnResetButton;
	private ReloadTable reloadTable;
	ModelContent modelContent = new  ModelContent();
	
	public FormContentPanel() {
		//this.reloadTable = reloadTable;
		this.setBounds(0, 0, 1040, 610);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		lblNewLabel = new JLabel("Add Content");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(35, 51, 982, 49);
		//lblNewLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#eeeeee")));
		lblNewLabel.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.LIGHT_GRAY));
		this.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		panel.setBackground(Color.WHITE);
		panel.setBounds(35, 167, 982, 424);
		this.add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(35, 122, 982, 49);
		this.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_1 = new JLabel("   Content Form Elements");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setBounds(0, 0, 982, 49);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(10, 11, 46, 14);
		panel.add(lblTitle);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setText("Enter the title");
		textFieldTitle.setForeground(new Color(169, 169, 169));
		textFieldTitle.setBounds(10, 36, 620, 20);
		panel.add(textFieldTitle);
		textFieldTitle.setColumns(10);
		
		lblBrief = new JLabel("Brief");
		lblBrief.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBrief.setBounds(10, 67, 46, 14);
		panel.add(lblBrief);
		
		textAreaBrief = new JTextArea();
		textAreaBrief.setBounds(10, 92, 620, 86);
		textAreaBrief.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		panel.add(textAreaBrief);
		
		lblContent = new JLabel("Content");
		lblContent.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContent.setBounds(10, 189, 67, 14);
		panel.add(lblContent);
		
		textAreaContent = new JTextArea();
		textAreaContent.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		textAreaContent.setBounds(10, 214, 620, 160);
		panel.add(textAreaContent);
		
		btnSubmitButton = new JButton("Submit Button");		
		btnSubmitButton.setBackground(Color.GREEN);
		btnSubmitButton.setBounds(10, 385, 118, 23);
		panel.add(btnSubmitButton);
		
		btnResetButton = new JButton("Reset Button");
		btnResetButton.setBackground(Color.GREEN);
		btnResetButton.setBounds(149, 385, 118, 23);
		panel.add(btnResetButton);
		
		event();
	}
	
	public void event() {
		btnSubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Content content = new Content();
				content.setTitle(textFieldTitle.getText());
				content.setBrief(textAreaBrief.getText());
				content.setContent(textAreaContent.getText());
				content.setAuthorId(MainPage.member.getId()); 		
				java.util.Date uDate = new java.util.Date();
				java.sql.Date sDate = convertUtilToSql(uDate);
				content.setCreatedDate(sDate);
				content.setUpdateTime(sDate);
				
				
				if(modelContent.addContent(content))
				{
					JOptionPane.showMessageDialog(null, "Add content success");	
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Add content fail");
				}
			}

			private java.sql.Date convertUtilToSql(Date uDate) {
				// TODO Auto-generated method stub
				java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		        return sDate;				
			}
		});
		
		btnResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				textFieldTitle.setText("Enter the title");
				textAreaBrief.setText("");
				textAreaContent.setText("");
				textFieldTitle.setForeground(new Color(169, 169, 169));
				textAreaBrief.setForeground(new Color(169, 169, 169));
				textAreaContent.setForeground(new Color(169, 169, 169));
			}
		});
		
		textFieldTitle.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (textFieldTitle.getText().trim().equals("Enter the title")) {
					textFieldTitle.setText("");
					textFieldTitle.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textFieldTitle.getText().trim().equals("")) {					
					textFieldTitle.setText("Enter the title");
					textFieldTitle.setForeground(new Color(169, 169, 169));					
				}
			}
		});
	}

}
