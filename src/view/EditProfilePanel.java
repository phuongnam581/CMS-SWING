package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.ModelMember;
import object.Member;

import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class EditProfilePanel extends JPanel {

	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfEmail;
	private JTextField tfPhone;
	private JTextArea taDescription;
	private JButton btnSubmmit;
	public String email;
	private JButton btnReset;
	private static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static String PHONE_PATTERN = "(09|07|03|05|08){1}([0-9]{8})";
	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public EditProfilePanel() {
		setBounds(0, 0, 1233, 595);
		this.setForeground(new Color(0, 0, 0));
		this.setBackground(Color.WHITE);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		email=MainPage.email;
		JLabel lblNewLabel = new JLabel("Edit Profile");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(37, 42, 120, 25);
		this.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(37, 42, 1185, 32);
		panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#eeeeee")));
		this.add(panel);

		JLabel lblNewLabel_1 = new JLabel("Profile Form Elements");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(47, 85, 493, 25);
		this.add(lblNewLabel_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(220, 220, 220));
		panel_1.setBounds(37, 81, 1185, 39);
		panel_1.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#eeeeee")));
		this.add(panel_1);
		JLabel lblNewLabel_2 = new JLabel("First Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(49, 149, 240, 25);
		this.add(lblNewLabel_2);
		tfFirstName = new JTextField();
		tfFirstName.setBounds(49, 175, 650, 32);
		this.add(tfFirstName);
		tfFirstName.setColumns(10);
		tfFirstName.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeee")));
		tfFirstName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (tfFirstName.getText().trim().equals("")) {
					tfFirstName.setText(" Enter the Frist Name");
					tfFirstName.setForeground(new Color(169, 169, 169));
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (tfFirstName.getText().trim().equals("Enter the Frist Name")) {
					tfFirstName.setText("");
					tfFirstName.setForeground(Color.BLACK);
				}

			}
		});
//		LAST NAME
		JLabel lblNewLabel_3 = new JLabel("Last Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(49, 211, 89, 25);
		this.add(lblNewLabel_3);

		tfLastName = new JTextField();
		tfLastName.setForeground(new Color(169, 169, 169));
		tfLastName.setBounds(49, 240, 650, 32);
		tfLastName.setText(" Enter the Last Name");
		tfLastName.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeee")));
		this.add(tfLastName);
		tfLastName.setColumns(10);
		tfLastName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfLastName.getText().trim().equals("Enter the Last Name")) {
					tfLastName.setText("");
					tfLastName.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfLastName.getText().trim().equals("")) {
					tfLastName.setText(" Enter the Last Name");
					tfLastName.setForeground(new Color(169, 169, 169));
				}
			}
		});
//		EMAIL
		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(49, 276, 92, 25);
		this.add(lblNewLabel_4);

		tfEmail = new JTextField();
		tfEmail.setForeground(new Color(169, 169, 169));
		tfEmail.setBounds(49, 296, 650, 32);
		tfEmail.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		this.add(tfEmail);
		tfEmail.setColumns(10);
		tfEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfEmail.getText().trim().equals("your_email@example.com")) {
					tfEmail.setText("");
					tfEmail.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfEmail.getText().trim().equals("")) {
					tfEmail.setText(" your_email@example.com");
					tfEmail.setForeground(new Color(169, 169, 169));
				}
			}
		});
		JLabel lblNewLabel_5 = new JLabel("Phone");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(49, 325, 75, 25);
		this.add(lblNewLabel_5);

		tfPhone = new JTextField();
		tfPhone.setForeground(new Color(169, 169, 169));
		tfPhone.setBounds(49, 348, 650, 32);
		tfPhone.setText(" Enter your phone number");
		tfPhone.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeee")));
		this.add(tfPhone);
		tfPhone.setColumns(10);
		tfPhone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (tfPhone.getText().trim().equals("Enter your phone number")) {
					tfPhone.setText("");
					tfPhone.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (tfPhone.getText().trim().equals("")) {
					tfPhone.setText(" Enter your phone number");
					tfPhone.setForeground(new Color(169, 169, 169));
				}
			}
		});
		JLabel lblNewLabel_6 = new JLabel("Description");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(49, 387, 108, 25);
		this.add(lblNewLabel_6);

		taDescription = new JTextArea();
		taDescription.setBounds(49, 418, 650, 77);
		taDescription.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeee")));
		taDescription.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				taDescription.setForeground(Color.BLACK);
			}
		});
		this.add(taDescription);
		btnSubmmit = new JButton("Submmit Button");
		btnSubmmit.setForeground(new Color(0, 0, 0));
		btnSubmmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSubmmit.setBackground(Color.GREEN);
		btnSubmmit.setBounds(49, 503, 120, 25);
		btnSubmmit.setBackground(Color.WHITE);
		this.add(btnSubmmit);

		btnReset = new JButton("Reset Button");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnReset.setBackground(Color.GREEN);
		btnReset.setBounds(179, 503, 133, 25);
		this.add(btnReset);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(37, 85, 1185, 458);
		panel_2.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeee")));
		this.add(panel_2);
		loadView();
	}

//load view
	public void loadView() {
		ModelMember modelMember = new ModelMember();
		try {
			Member member = modelMember.getUser(email);
			System.out.print(email);
			tfFirstName.setText(member.getFirstname().toString());
			tfLastName.setText(member.getLastname().toString());
			tfEmail.setText(member.getEmail().toString());
			tfPhone.setText(member.getPhone().toString());
			taDescription.setText(member.getDescription().toString());
			tfFirstName.setForeground(Color.BLACK);
			tfLastName.setForeground(Color.BLACK);
			tfEmail.setForeground(Color.BLACK);
			tfPhone.setForeground(Color.BLACK);
			taDescription.setForeground(Color.BLACK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setEvent();
	}

	public void setEvent() {
		this.btnSubmmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Member mem = new Member();
				ModelMember modelMem = new ModelMember();
				boolean check = Validate();
				try {
					mem = modelMem.getUser("nam@gmail.com");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (check) {
					mem.setFirstname(tfFirstName.getText().toString());
					mem.setLastname(tfLastName.getText().toString());
					mem.setEmail(tfEmail.getText().toString());
					mem.setPhone(tfPhone.getText().toString());
					mem.setDescription(taDescription.getText().toString());
					try {
						boolean rs = modelMem.updateMember(mem, mem.getId());
						if (rs) {
							JOptionPane.showMessageDialog(null, "Edit success");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

// Button Reset
		this.btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tfFirstName.setText(" Enter the Frist Name");
				tfLastName.setText(" Enter the Last Name");
				tfEmail.setText(" your_email@example.com");
				tfPhone.setText(" Enter your phone number");
				taDescription.setText("");
				tfFirstName.setForeground(new Color(169, 169, 169));
				tfLastName.setForeground(new Color(169, 169, 169));
				tfEmail.setForeground(new Color(169, 169, 169));
				tfPhone.setForeground(new Color(169, 169, 169));
				taDescription.setForeground(new Color(169, 169, 169));
			}
		});

	}

//	Validate
	public boolean Validate() {
		if (tfFirstName.getText().equals("Enter the Frist Name") || tfLastName.getText().equals("Enter the Last Name")
				|| tfEmail.getText().equals("your_email@example.com")
				|| tfPhone.getText().equals("Enter your phone number") || taDescription.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please fill all infor!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (tfFirstName.getText().length() == 0 || tfLastName.getText().length() == 0
				|| tfEmail.getText().length() == 0 || tfPhone.getText().length() == 0
				|| taDescription.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Please fill all infor!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!tfEmail.getText().toString().matches(EMAIL_PATTERN)) {
			JOptionPane.showMessageDialog(null, "Email is not corret.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!tfPhone.getText().toString().matches(PHONE_PATTERN)) {
			JOptionPane.showMessageDialog(null, "Phone is not corret.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
