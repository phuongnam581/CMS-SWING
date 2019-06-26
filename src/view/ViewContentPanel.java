package view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.LabelUI;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import model.ModelContent;
import object.Content;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ViewContentPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ModelContent modelContent = new ModelContent();
	private JTable table;
	private List<Content> list;
	private DefaultTableModel tableModel;
	private TableButton buttonEditorEdit;
	private TableButton buttonEditorDel;
	private JButton btnSearch;
	private JLabel lb;
	private JTextPane tpKey;

	/**
	 * Create the panel.
	 */
	public ViewContentPanel() {
		// JPanel panelView = new JPanel();

		this.setBounds(0, 0, 1233, 517);
		this.setBackground(Color.decode("#ffffff"));
		this.setLayout(null);

		JLabel lbViewContent = new JLabel("View Content");
		lbViewContent.setVerticalAlignment(SwingConstants.TOP);
		lbViewContent.setHorizontalAlignment(SwingConstants.LEFT);
		lbViewContent.setFont(new Font("Arial", Font.PLAIN, 40));
		lbViewContent.setForeground(Color.decode("#273540"));
		lbViewContent.setBounds(35, 51, 1185, 54);
		lbViewContent.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#eeeeeee")));
		this.add(lbViewContent);

		tpKey = new JTextPane();
		tpKey.setBounds(817, 55, 204, 39);
		tpKey.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeeee")));
		this.add(tpKey);

		btnSearch = new JButton();

		btnSearch.setUI(new MetalButtonUI());
		btnSearch.setBackground(Color.decode("#ffffff"));
		// btnSearch.setForeground(Color.white);
		btnSearch.setBounds(1019, 55, 44, 39);
		btnSearch.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeeee")));
		btnSearch.setIcon(new ImageIcon(MainPage.iconSearch));
		this.add(btnSearch);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(34, 122, 1185, 534);
		panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeeee")));
		this.add(panel);
		panel.setLayout(null);

		JLabel lbViewContentList = new JLabel("   View Content List");
		lbViewContentList.setOpaque(true);// thêm dòng này để đổi dc background
		lbViewContentList.setBackground(Color.decode("#f5f5f5"));
		lbViewContentList.setFont(new Font("Arial", Font.PLAIN, 15));
		lbViewContentList.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#eeeeeee")));
		lbViewContentList.setBounds(0, 0, 1185, 50);
		panel.add(lbViewContentList);

		list = new ArrayList<Content>();
		try {
			list = modelContent.getListContent();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table = new JTable(buildTable(list)) {
			// Returning the Class of each column will allow different
			// renderers to be used based on Class
			@Override
			// khong cho chon cot 1
			public boolean isCellEditable(int row, int column) {
				if (column == 0)
					return false;
				return true;
			};

			public Class getColumnClass(int column) {
				return String.class;
				// return getValueAt(0, column).getClass();
			}

			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				JComponent jc = (JComponent) c;

				// Alternate row color

				if (!isRowSelected(row) && column != 4 && column != 5)
					c.setBackground(row % 2 == 0 ? getBackground() : Color.decode("#e6e6fa"));
				return c;
			}
		};

//		for(int i =0;i<table.getColumnCount();i++){
//		    table.getColumnModel().getColumn(i).setCellEditor(getCellEditor());
//		}
		// table.setBounds(109, 155, 673, 433);
		// table.setBackground(Color.WHITE);
		// JOptionPane.showMessageDialog(null, new JScrollPane(table));
		JScrollPane scrollPane = new JScrollPane();
		// scrollPane.setBackground(Color.white);
		scrollPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeeee")));
		table.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeeee")));
		scrollPane.setBounds(24, 61, 1000, 433);
		scrollPane.setViewportView(table);
		// panel.add(new JScrollPane(table));
		panel.add(scrollPane);

		// table.setEnabled(false);
		// table.getColumnModel().getColumn(0).setCellRenderer(r);
		initTable();
		event();
	}

	private void initTable() {
		// TODO Auto-generated method stub
		DefaultTableCellRenderer r = new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				setBackground(Color.GREEN);
				setText("Edit");
				setFont(new Font("SansSerif", Font.BOLD, 12));
				setHorizontalAlignment(SwingConstants.CENTER);
				setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.decode("#eeeeeee")));
				return this;
			}

		};
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 17));
		// table.getTableHeader().setHorizontalAlignment(SwingConstants.LEFT);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(53);
		table.getColumnModel().getColumn(1).setPreferredWidth(235);
		table.getColumnModel().getColumn(2).setPreferredWidth(413);
		// table.getColumnModel().getColumn(4).setPreferredWidth(80);
		// table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setMinWidth(135);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		// table.getColumnModel().getColumn(0).setWidth(10);
		table.getTableHeader().setBackground(Color.WHITE);
		buttonEditorEdit = new TableButton("Edit");
		// buttonEditorDel.setBackground(Color.GREEN);
		buttonEditorDel = new TableButton("Delete");
		if (list.size() > 0) {
			TableColumn colEdit = new TableColumn(1, 80);
			colEdit.setCellRenderer(buttonEditorEdit);
			colEdit.setCellEditor(buttonEditorEdit);

			TableColumn colDel = new TableColumn(1, 80);
			colDel.setCellRenderer(buttonEditorDel);
			colDel.setCellEditor(buttonEditorDel);

			table.addColumn(colDel);
			table.addColumn(colEdit);
			table.getColumnModel().getColumn(4).setHeaderValue("");
			table.getColumnModel().getColumn(5).setCellRenderer(r);
			table.getColumnModel().getColumn(5).setHeaderValue("");
			table.setRowHeight(30);
//			table.getColumnModel().getColumn(4).setMinWidth(0);
//			   table.getColumnModel().getColumn(4).setMaxWidth(0);
//			   table.getColumnModel().getColumn(4).setWidth(0);
//			
		}
		
	}

	int check = 0;

	private void event() {
		// TODO Auto-generated method stub

		buttonEditorEdit.addTableButtonListener(new TableButtonListener() {

			@Override
			public void tableButtonClicked(int row, int col) {
				buttonEditorEdit.setFocusable(true);
				System.out.println(row + table.getValueAt(row, 2).toString());
				try {
					Content content = list.get(row);

					// Date date = new Date("dd-MM-yyyy HH:mm:ss");
					// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					content.setUpdateTime(new Date());
					content.setBrief(table.getValueAt(row, 2).toString());
					content.setTitle(table.getValueAt(row, 1).toString());

					modelContent.editContent(content);
					check = 1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		buttonEditorDel.addTableButtonListener(new TableButtonListener() {

			@Override
			public void tableButtonClicked(int row, int col) {
				 System.out.println("gggg");

				try {
					// System.out.println(row);

					modelContent.deleteContent(list.get(row));
					table.setModel(buildTable(modelContent.getListContent()));
					initTable();
					// tableModel.fireTableDataChanged();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Content> list = new ArrayList<Content>();

				try {
					list = modelContent.getListcontentByTitle(tpKey.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				table.setModel(buildTable(list));
				initTable();
				// tableModel=(DefaultTableModel) table.getModel();
				// tableModel.fireTableDataChanged();
				// table.repaint();
				
			}
		});
//		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//	        public void valueChanged(ListSelectionEvent event) {
//	            // do some actions here, for example
//	            // print first column value from selected row
//	            System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
//	            //table.getColumnModel().getColumnIndexAtX(4).
//	          //  TableCellRenderer.getTableCellRendererComponent(table, aaTextInfo, autoscrolls, autoscrolls, check, check...)
//	           
//	        }
//	    });
	}

	public static DefaultTableModel buildTable(List<Content> list) {
		Vector<String> listColName = new Vector<String>();
		listColName.add("#");
		listColName.add("Title");
		listColName.add("Brief");
		listColName.add("Created Date");
		// listColName.add("");

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		int size = list.size();
		for (int i = 0; i < size; i++)// data
		{
			Vector<Object> vector = new Vector<Object>();
			vector.add(i + 1);
			vector.add(list.get(i).getTitle());
			// System.out.println(list.get(i).getTitle()+"ttt");
			vector.add(list.get(i).getBrief());
			vector.add(list.get(i).getCreatedDate());

			data.add(vector);
		}
		return new DefaultTableModel(data, listColName);
	}

	private TableCellEditor getCellEditor() {
		JTextField f = new JTextField();
		f.setBorder(BorderFactory.createLineBorder(Color.RED));
		return new DefaultCellEditor(f);
	}
	
}
