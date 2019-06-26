package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import javax.swing.OverlayLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalButtonUI;

import model.ModelMember;
import object.Member;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.MenuItem;

import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MainPage {

	public JFrame frame;
	public static String email="" ;
	private String iconDN = "./image/iconNguoi.jpg";
	public static String iconSearch = "./image/iconSearch.jpg";
	private String iconTable = "./image/iconTable.jpg";
	private String iconPen = "./image/iconPen.jpg";
	private String iconLogOut="./image/iconLogout.jpg";
	private String iconUserProfile="./image/iconUserProfile.jpg";
	private JButton btnFormContent, btnViewContent;
	private JPanel panelView;
	private FormContentPanel panelFormContent;
	private ViewContentPanel panelViewContent;
	private JButton btnDangNhap;
	private ModelMember modelMember;
	private JPopupMenu menu;
	private EditProfilePanel panelEditProfile;
	private LogoutListener logout;
	private ReloadTable reloadTable;
	public static Member member;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}
	public MainPage(String email,LogoutListener logout) {
		this.email = email;
		
		this.logout = logout;
		modelMember= new ModelMember();
		try {
			member= modelMember.getUser(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.getContentPane().setLayout(null);

		JPanel panelCMS = new JPanel();
		panelCMS.setBounds(0, 0, 1526, 52);
		panelCMS.setBackground(Color.decode("#F8F8F8"));
		panelCMS.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#eeeeeee")));
		frame.getContentPane().add(panelCMS);
		panelCMS.setLayout(null);

		JLabel lbCMS = new JLabel("CMS");
		lbCMS.setFont(new Font("Arial", Font.BOLD, 21));
		// lblNewLabel.setSize(new Dimension(45, 19));
		lbCMS.setBounds(13, 13, 46, 20);
		// lblNewLabel.setBorder(BorderFactory.createEmptyBorder(16,13,22,0));
		// lblNewLabel.setBorder(new EmptyBorder(16,13,22,0));
		lbCMS.setForeground(Color.decode("#8b8e95"));
		panelCMS.add(lbCMS);
		
		btnDangNhap = new JButton("");
		btnDangNhap.setBounds(1307, 21, 31, 20);
		btnDangNhap.setIcon(new ImageIcon(iconDN));
		btnDangNhap.setMargin(new Insets(0, 0, 0, 0));
		btnDangNhap.setUI(new MetalButtonUI());
		btnDangNhap.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.decode("#eeeeeee")));
		panelCMS.add(btnDangNhap);

		menu = new JPopupMenu("Menu");

		JMenuItem itemEdit = new JMenuItem("User Profile");
		itemEdit.setIcon(new ImageIcon(iconUserProfile));
		itemEdit.setBackground(Color.white);
		itemEdit.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#eeeeeee")));
		itemEdit.setPreferredSize(new Dimension(177,47));
		JMenuItem itemLogOut = new JMenuItem("Logout");
		itemLogOut.setIcon(new ImageIcon(iconLogOut));
		itemLogOut.setBackground(Color.WHITE);
		itemLogOut.setPreferredSize(new Dimension(177,47));
		//menu.setBounds(1209, 17, 177, 87);
		//menu.setBackground(Color.white);
		
		menu.add(itemEdit);
		menu.add(itemLogOut);
		menu.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeeee")));
		ActionListener menuListenerEditProfile = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (panelEditProfile.getParent() == null) {
					panelEditProfile = new EditProfilePanel();
				}
				changePanel(panelEditProfile, panelViewContent);
				changePanel(panelEditProfile, panelFormContent);
			}
		};
		ActionListener menuListenerLogout = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println("logout");
				LoginPage window = new LoginPage();
				window.frame.setVisible(true);
				MainPage.this.frame.setVisible(false);
				logout.logout();
			}
		};
		itemEdit.addActionListener(menuListenerEditProfile);
		itemLogOut.addActionListener(menuListenerLogout);
		JPanel panelSearch = new JPanel();
		panelSearch.setBounds(0, 51, 277, 700);
		panelSearch.setBackground(Color.decode("#F8F8F8"));
		panelSearch.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.decode("#eeeeeee")));
		frame.getContentPane().add(panelSearch);
		panelSearch.setLayout(null);

//		JButton btnSearch = new JButton();
//		
//		btnSearch.setUI(new MetalButtonUI());
//		btnSearch.setBackground(Color.decode("#ffffff"));
//		//btnSearch.setForeground(Color.white);
//		btnSearch.setBounds(212, 21, 44, 39);
//		btnSearch.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeeee")));
//		panelSearch.add(btnSearch);
//		btnSearch.setIcon(new ImageIcon(iconSearch));
//		
		btnViewContent = new JButton("View contents");

		btnViewContent.setFont(new Font("Calibri", Font.BOLD, 17));
		btnViewContent.setUI(new MetalButtonUI());
		btnViewContent.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeeee")));
		btnViewContent.setBounds(0, 0, 277, 48);
		btnViewContent.setIcon(new ImageIcon(iconTable));
		btnViewContent.setForeground(Color.decode("#588ea6"));
		btnViewContent.setBackground(Color.WHITE);
		btnViewContent.setHorizontalAlignment(SwingConstants.LEFT);
		panelSearch.add(btnViewContent);

		btnFormContent = new JButton("Form content");

		btnFormContent.setUI(new MetalButtonUI());
		btnFormContent.setBounds(0, 47, 277, 48);
		btnFormContent.setBackground(Color.WHITE);
		btnFormContent.setIcon(new ImageIcon(iconPen));
		btnFormContent.setForeground(Color.decode("#588ea6"));
		btnFormContent.setFont(new Font("Calibri", Font.BOLD, 17));
		btnFormContent.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeeee")));
		btnFormContent.setHorizontalAlignment(SwingConstants.LEFT);
		panelSearch.add(btnFormContent);

		panelView = new JPanel();
		panelView.setBounds(277, 51, 1085, 700);

		// ViewContentPanel panelViewContent= new ViewContentPanel();
		panelViewContent = new ViewContentPanel();
		panelViewContent.setSize(1233, 700);

		panelView.add(panelViewContent);
		frame.getContentPane().add(panelView);
		// panelViewContent.setLayout(null);
		panelView.setLayout(null);
		panelView.setBackground(Color.WHITE);

		frame.setBounds(0, 0, 1526, 700);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		panelFormContent = new FormContentPanel();
		event();
		panelEditProfile = new EditProfilePanel();
	}

	private void event() {
		// TODO Auto-generated method stub
		btnFormContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (panelFormContent.getParent() == null) {
					panelFormContent = new FormContentPanel();
				}
				changePanel(panelFormContent, panelViewContent);
				changePanel(panelFormContent, panelEditProfile);
				// table.setModel(buildTable(list));
				// initTable();
				panelView.repaint();
			}
		});
		btnViewContent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (panelViewContent.getParent() == null) {
					panelViewContent = new ViewContentPanel();
				}

				changePanel(panelViewContent, panelFormContent);
				changePanel(panelViewContent, panelEditProfile);
			}
		});
		btnDangNhap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				menu.show(btnDangNhap, -150, 28);

			}
		});
		
	}

	private void changePanel(JPanel panelAdd, JPanel panelRemove) {
		if (panelRemove.getParent() != null && panelAdd.getParent() == null) {
			panelView.remove(panelRemove);
			panelAdd.setSize(1233, 700);
			panelView.add(panelAdd);

			panelView.repaint();
		}

	}
}
