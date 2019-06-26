package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.DBConnect;
import model.ModelMember;
import object.Member;

public class RegisterPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtEmail;

	private JLabel lblPleaseFillAll;
	private JLabel lblUserName;
	private JLabel lblEmailToo;
	private JLabel lblEmailIst;
	private JLabel lblEmailAlready;
	private JLabel lblPasswordToo;
	private JLabel lblRePassword;
	private JLabel lblUserName_1;
	private JLabel ClickHere;
	private JPasswordField txtPassword;
	private JPasswordField txtRepassword;
	private ModelMember modelMember = new ModelMember();
	public static RegisterPage frame;
	public static LoginPage frame_login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RegisterPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public int checkInfo() {
		if (txtUserName.getText().trim().equals("Username") || txtPassword.getText().trim().equals("Password")
				|| txtEmail.getText().trim().equals("E-mail") || txtRepassword.getText().trim().equals("Re Password")) {
			lblPleaseFillAll.setVisible(true);
			return 1;
		} else {
			lblPleaseFillAll.setVisible(false);
		}
		if (txtUserName.getText().trim().length() >= 50) {
			lblUserName.setVisible(true);
			return 1;
		} else {
			lblUserName.setVisible(false);
		}
		if (txtEmail.getText().trim().length() >= 50) {
			lblEmailToo.setVisible(true);
			return 1;
		} else {
			lblEmailToo.setVisible(false);
		}
		String emailPattern = "^[\\w!#$%&锟�*+/=?`{|}~^-]+(?:\\.[\\w!#$%&锟�*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern regex = Pattern.compile(emailPattern);
		Matcher matcher = regex.matcher(txtEmail.getText().trim());
		if (matcher.find()) {
			lblEmailIst.setVisible(false);
		} else {
			lblEmailIst.setVisible(true);
			return 1;
		}
		if (txtPassword.getText().trim().length() > 50 || txtPassword.getText().trim().length() < 8) {
			lblPasswordToo.setVisible(true);
			return 1;
		} else {
			lblPasswordToo.setVisible(false);
		}
		if (txtRepassword.getText().trim().equals(txtPassword.getText().trim())
				|| txtRepassword.getText().trim().equals("Re Password")) {
			lblRePassword.setVisible(false);
		} else {
			lblRePassword.setVisible(true);
			return 1;
		}
		return 0;
	}



	/**
	 * Create the frame.
	 */
	public RegisterPage() {
		AbstractBorder abstractBorder = new TextBubbleBorder(Color.DARK_GRAY, 1, 12, 0);

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.LIGHT_GRAY);
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setBounds(74, 11, 371, 431);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 50, 371, 381);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txtUserName = new JTextField();
		
		txtUserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(txtUserName.getText().trim().equals("Username")) {
					txtUserName.setText("");
					txtUserName.setForeground(Color.BLACK);
				}				
			}			
		});				
		txtUserName.setForeground(Color.LIGHT_GRAY);
		txtUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtUserName.getText().equals("Username")) {
					txtUserName.setCaretPosition(0);
				}	
//				txtUserName.setBorder(brdr_focus);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtUserName.getText().equals("")) {
					txtUserName.setText("Username");
					txtUserName.setForeground(Color.LIGHT_GRAY);
				}
//				txtUserName.setBorder(brdr);
			}
		});
		
		lblPleaseFillAll = new JLabel("(*) You must enter the full information !");
		lblPleaseFillAll.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPleaseFillAll.setForeground(Color.RED);
		lblPleaseFillAll.setBounds(10, 320, 150, 14);
		panel_1.add(lblPleaseFillAll);
		lblPleaseFillAll.setVisible(false);
		
		ClickHere = new JLabel("Click here to Login");
		ClickHere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ClickHere.setForeground(Color.RED);
				// transport form in here
				frame_login = new LoginPage();
				frame.show(false);
				frame_login.show(true);
			}
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//				ClickHere.setForeground(Color.GREEN);
//			}
		});
		
		ClickHere.setForeground(Color.BLUE);
		ClickHere.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ClickHere.setBounds(10, 335, 162, 23);
		panel_1.add(ClickHere);
		
		lblUserName = new JLabel("(*) Username must be <50 characters ! ");
		lblUserName.setForeground(Color.RED);
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUserName.setBounds(10, 60, 235, 14);
		lblUserName.setVisible(false);
		panel_1.add(lblUserName);
		
		lblEmailToo = new JLabel("(*) Email must be> = 5 characters and <= 50 characters ! ");
		lblEmailToo.setForeground(Color.RED);
		lblEmailToo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmailToo.setBounds(10, 120, 235, 14);
		lblEmailToo.setVisible(false);
		panel_1.add(lblEmailToo);
		
		lblEmailAlready = new JLabel("(*) Email already exists !");
		lblEmailAlready.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmailAlready.setForeground(Color.RED);
		lblEmailAlready.setBounds(10, 120, 127, 14);
		lblEmailAlready.setVisible(false);
		panel_1.add(lblEmailAlready);
		
		lblPasswordToo = new JLabel("(*) Password must be >=8 characters and <=50 characters! ");
		lblPasswordToo.setForeground(Color.RED);
		lblPasswordToo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPasswordToo.setBounds(10, 180, 235, 14);
		lblPasswordToo.setVisible(false);
		panel_1.add(lblPasswordToo);
		
		lblRePassword = new JLabel("(*) Re Password is not corrcet  ! ");
		lblRePassword.setForeground(Color.RED);
		lblRePassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblRePassword.setBounds(10, 240, 235, 14);
		lblRePassword.setVisible(false);
		panel_1.add(lblRePassword);
		
		lblEmailIst = new JLabel("(*) E-mail is not correct !");
		lblEmailIst.setForeground(Color.RED);
		lblEmailIst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmailIst.setBounds(10, 120, 127, 14);
		lblEmailIst.setVisible(false);
		panel_1.add(lblEmailIst);
		
		lblUserName_1 = new JLabel("(*) Username already exists !");
		lblUserName_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUserName_1.setForeground(Color.RED);
		lblUserName_1.setBounds(10, 60, 162, 14);
		lblUserName_1.setVisible(false);
		panel_1.add(lblUserName_1);
		
		txtUserName.setText("Username");
		txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUserName.setColumns(10);
		txtUserName.setBounds(10, 20, 351, 39);
		txtUserName.setBorder(abstractBorder);
		panel_1.add(txtUserName);
		
		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(txtEmail.getText().trim().equals("E-mail")) {
					txtEmail.setText("");
					txtEmail.setForeground(Color.BLACK);
				}				
			}			
		});	
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtEmail.getText().equals("E-mail")) {
					txtEmail.setCaretPosition(0);
				}	
//				txtEmail.setBorder(brdr_focus);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtEmail.getText().equals("")) {
					txtEmail.setText("E-mail");
					txtEmail.setForeground(Color.LIGHT_GRAY);
				}
//				txtEmail.setBorder(brdr);
			}
		});
		txtEmail.setForeground(Color.LIGHT_GRAY);
		txtEmail.setText("E-mail");
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 80, 351, 39);
		txtEmail.setBorder(abstractBorder);
		panel_1.add(txtEmail);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				long millis=System.currentTimeMillis();  
				java.sql.Date date=new java.sql.Date(millis);
				
				Member member=new Member(txtUserName.getText().trim(), txtPassword.getText().trim(), txtEmail.getText().trim(), date);
				
				if(checkInfo() == 0) {
					// connect database
					
						lblUserName_1.setVisible(false);
						lblEmailAlready.setVisible(false);
						
						if(modelMember.checkExistUsername(member.getUsername().trim())) {
							lblUserName_1.setVisible(true);
						} 
						if(modelMember.checkExistEmail(member.getEmail().trim())) {
							lblEmailAlready.setVisible(true);
						}
						else {
							lblUserName_1.setVisible(false);
							lblEmailAlready.setVisible(false);
							
							int result_insert = modelMember.insertMember(member);
							if(result_insert >= 0) {
								JOptionPane.showMessageDialog(panel, "Registration success ! ");
							} else {
								JOptionPane.showMessageDialog(panel, "Registration failed ! ");
							}							
						}
					
				}		
			}
		});
		
		btnRegister.setBackground(new Color(84, 192, 84));
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRegister.setBounds(10, 260, 351, 56);
		btnRegister.setBorder(abstractBorder);
		panel_1.add(btnRegister);
		
		txtPassword = new JPasswordField();
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtPassword.getText().equals("Password")) {
					txtPassword.setCaretPosition(0);
				}	
//				txtPassword.setBorder(brdr_focus);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtPassword.getText().equals("")) {
					txtPassword.setText("Password");
					txtPassword.setForeground(Color.LIGHT_GRAY);
				}
//				txtPassword.setBorder(brdr);
			}
		});
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(txtPassword.getText().equals("Password")) {
					txtPassword.setText("");
					txtPassword.setForeground(Color.BLACK);
					txtPassword.setEchoChar('*');					
				}
			}
		});
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setForeground(Color.LIGHT_GRAY);
		txtPassword.setBounds(10, 140, 351, 39);
		txtPassword.setBorder(abstractBorder);
		txtPassword.setEchoChar((char)0);
		txtPassword.setText("Password");
		panel_1.add(txtPassword);
		
		txtRepassword = new JPasswordField();
		txtRepassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtRepassword.getText().equals("Re Password")) {
					txtRepassword.setCaretPosition(0);
				}	
//				txtRepassword.setBorder(brdr_focus);
			}			
			@Override
			public void focusLost(FocusEvent e) {
				if(txtRepassword.getText().equals("")) {
					txtRepassword.setText("Re Password");
					txtRepassword.setForeground(Color.LIGHT_GRAY);
				}
//				txtRepassword.setBorder(brdr);
			}
		});
		txtRepassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(txtRepassword.getText().equals("Re Password")) {
					txtRepassword.setText("");
					txtRepassword.setForeground(Color.BLACK);
					txtRepassword.setEchoChar('*');					
				}
			}
		});
		txtRepassword.setForeground(Color.LIGHT_GRAY);
		txtRepassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRepassword.setBounds(10, 200, 351, 39);
		txtRepassword.setBorder(abstractBorder);
		txtRepassword.setEchoChar((char)0);
		txtRepassword.setText("Re Password");
		panel_1.add(txtRepassword);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRegister.setBounds(10, 11, 81, 29);
		panel.add(lblRegister);
	}

}
