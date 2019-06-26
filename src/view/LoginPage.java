package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.ModelMember;
import object.Member;

public class LoginPage extends JFrame implements LogoutListener {
	// File file = new File(System.getProperty("user.home") + "/Desktop/save.txt");
	File file = new File("./file/save.txt");
	private JPanel contentPane;
	private JTextField txtEmail;
	private JLabel lblEmailToo;
	private JLabel lblEmailIst;
	private JPasswordField txtPassword;
	private JLabel lblPasswordToo;
	private JLabel lblPassword;

	private JLabel lblPleaseFillAll;
	private JLabel ClickHere;
	private JCheckBox checkRememberMe;
	private ModelMember modelMember = new ModelMember();

	public static LoginPage frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginPage();
					frame.updateFile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public int check() {
		if (txtPassword.getText().trim().equals("Password") || txtEmail.getText().trim().equals("E-mail")) {
			lblPleaseFillAll.setVisible(true);
			return 1;
		} else {
			lblPleaseFillAll.setVisible(false);
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
		return 0;
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
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
		panel.setBounds(79, 48, 371, 343);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 51, 371, 292);
		panel.add(panel_1);
		panel_1.setLayout(null);

		lblPleaseFillAll = new JLabel("You must enter the full information !");
		lblPleaseFillAll.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPleaseFillAll.setForeground(Color.RED);
		lblPleaseFillAll.setBounds(10, 241, 191, 14);
		panel_1.add(lblPleaseFillAll);
		lblPleaseFillAll.setVisible(false);

		ClickHere = new JLabel("Click here to Register");
		ClickHere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ClickHere.setForeground(Color.RED);
				RegisterPage.frame = new RegisterPage();
				frame.show(false);
				RegisterPage.frame.show(true);
				// transport form in here

			}
		});

		ClickHere.setForeground(Color.BLUE);
		ClickHere.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ClickHere.setBounds(10, 257, 162, 23);
		panel_1.add(ClickHere);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (txtEmail.getText().trim().equals("E-mail")) {
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

//			@Override
//			public void focusLost(FocusEvent e) {
//				if (txtEmail.getText().equals("")) {
//					txtEmail.setText("E-mail");
//					txtEmail.setForeground(Color.BLACK);
//				}
////				txtEmail.setBorder(brdr);
//			}
		});
		txtEmail.setForeground(Color.LIGHT_GRAY);
		txtEmail.setText("E-mail");
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 23, 351, 39);
		txtEmail.setBorder(abstractBorder);
		panel_1.add(txtEmail);

		lblEmailToo = new JLabel("Email must be> = 5 characters and <= 50 characters ! ");
		lblEmailToo.setForeground(Color.RED);
		lblEmailToo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmailToo.setBounds(10, 61, 235, 14);
		lblEmailToo.setVisible(false);
		panel_1.add(lblEmailToo);

		lblEmailIst = new JLabel("E-mail is not correct");
		lblEmailIst.setForeground(Color.RED);
		lblEmailIst.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEmailIst.setBounds(10, 61, 127, 14);
		lblEmailIst.setVisible(false);
		panel_1.add(lblEmailIst);

		txtPassword = new JPasswordField();
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtPassword.getText().equals("Password")) {
					txtPassword.setCaretPosition(0);
				}
//				txtPassword.setBorder(brdr_focus);
			}

//			@Override
//			public void focusLost(FocusEvent e) {
//				if (txtPassword.getText().equals("")) {
//					txtPassword.setText("Password");
//					txtPassword.setForeground(Color.LIGHT_GRAY);
//				}
////				txtPassword.setBorder(brdr);
//			}
		});
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (txtPassword.getText().equals("Password")) {
					txtPassword.setText("");
					txtPassword.setForeground(Color.BLACK);
					txtPassword.setEchoChar('*');
				}
			}
		});
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setForeground(Color.LIGHT_GRAY);
		txtPassword.setBounds(10, 86, 351, 39);
		txtPassword.setBorder(abstractBorder);
		txtPassword.setEchoChar((char) 0);
		txtPassword.setText("Password");
		panel_1.add(txtPassword);
		txtPassword.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				
			hint();
			}
			private void hint() {
                Runnable dohint = new Runnable() {
                    @Override
                    public void run() {
                       
                    	if (txtPassword.getText().equals("")) {
        					txtPassword.setText("Password");
        					txtPassword.setEchoChar('\u0000');
        					txtPassword.setForeground(Color.LIGHT_GRAY);
        				} else {
        					txtPassword.setEchoChar('*');
        					txtPassword.setForeground(Color.BLACK);
        				}
                    }
                };
                SwingUtilities.invokeLater(dohint );
            }
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				//hint();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
//				if (txtPassword.getText().equals("")) {
//					txtPassword.setText("Password");
//				} else {
//					txtPassword.setEchoChar('*');
//				}

			}
		});
		txtEmail.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				
			hint();
			}
			private void hint() {
                Runnable dohint = new Runnable() {
                    @Override
                    public void run() {
                       
                    	if (txtEmail.getText().equals("")) {
                    		txtEmail.setText("E-mail");
                    		txtEmail.setForeground(Color.LIGHT_GRAY);
                    		
        				} else {
        					txtEmail.setForeground(Color.BLACK);
        				}
                    }
                };
                SwingUtilities.invokeLater(dohint );
            }
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
//				if (txtPassword.getText().equals("")) {
//					txtPassword.setText("Password");
//				} else {
//					txtPassword.setEchoChar('*');
//				}

			}
		});

		lblPasswordToo = new JLabel("Password must be >= 8 characters and <= 50 characters ! ");
		lblPasswordToo.setForeground(Color.RED);
		lblPasswordToo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPasswordToo.setBounds(10, 125, 235, 14);
		lblPasswordToo.setVisible(false);
		panel_1.add(lblPasswordToo);

		lblPassword = new JLabel("Password is not correct! ");
		lblPassword.setForeground(Color.RED);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPassword.setBounds(10, 125, 235, 14);
		lblPassword.setVisible(false);
		panel_1.add(lblPassword);

		// txtEmail.setText("nam1@gmail.com");
		// txtPassword.setText("40064444");
		checkRememberMe = new JCheckBox("Remember Me");
		checkRememberMe.setBackground(Color.WHITE);
		checkRememberMe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {

			}
		});
		checkRememberMe.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				txtEmail.setText(txtEmail.getText().trim());
				txtPassword.setText(txtPassword.getText().trim());

			}
		});
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Member member = new Member(txtPassword.getText().trim(), txtEmail.getText().trim());
//				System.out.println(member.getEmail());

				if (check() == 0) {

					lblEmailIst.setVisible(false);
					lblPassword.setVisible(false);
					if (modelMember.checkExistEmail(member.getEmail().trim())) {

						if (modelMember.checkLogin(member.getEmail().trim(), member.getPassword().trim())) {
							JOptionPane.showMessageDialog(panel, "Login success ! ");
							if (checkRememberMe.isSelected()) {
								SAVE(); // Save This UserName and his PassWord

							} else {
								if (!file.exists()) {
									file.delete();
								}
							}
							System.out.print("333" + member.getEmail());
							MainPage window = new MainPage(member.getEmail().trim(), LoginPage.this);
							window.frame.setVisible(true);
							LoginPage.this.setVisible(false);

						} else {
							lblPassword.setVisible(true);
						}
					} else {
						lblEmailIst.setVisible(true);
					}
				}
			}

		});

		btnLogin.setBackground(new Color(84, 192, 84));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setBounds(10, 186, 351, 56);
		btnLogin.setBorder(abstractBorder);
		panel_1.add(btnLogin);

//		
		checkRememberMe.setBackground(Color.WHITE);
		checkRememberMe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		checkRememberMe.setBounds(10, 146, 191, 23);
		panel_1.add(checkRememberMe);

		JLabel lblSigIn = new JLabel("Please Sig In");
		lblSigIn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSigIn.setBounds(10, 11, 169, 29);
		panel.add(lblSigIn);
	}

	public void SAVE() {
		try {
			if (!file.exists())
				file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			bw.write(txtEmail.getText().trim());
			bw.newLine();
			bw.write(txtPassword.getPassword());
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void updateFile() {
		try {
			if (file.exists()) {
				Scanner scan = new Scanner(file);
				txtEmail.setText(scan.nextLine().toString());
				txtPassword.setText(scan.nextLine().toString());
				txtPassword.setEchoChar('*');
				checkRememberMe.setSelected(true);
				scan.close();
			}
			else
			{
				txtPassword.setText("");
				txtEmail.setText("");
			}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void readFile(Member member) {
		try {
			if (file.exists()) {
				Scanner scan = new Scanner(file);
				member.setEmail(scan.nextLine().toString());
				member.setPassword(scan.nextLine().toString());
				scan.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void logout() {
		if (file.exists() && !checkRememberMe.isSelected()) {
			file.delete();
		}
		if (!file.exists()) {
			txtEmail.setText("E-mail");
			txtEmail.setForeground(Color.LIGHT_GRAY);
			txtPassword.setText("Password");
			txtPassword.setForeground(Color.LIGHT_GRAY);
			txtPassword.setEchoChar('\u0000');
		}

	}

}
