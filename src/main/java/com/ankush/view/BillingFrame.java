package com.ankush.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.ankush.backup.Backup;
import com.ankush.entities.Bill;
import com.ankush.entities.Customer;
import com.ankush.entities.DailyStock;
import com.ankush.entities.Login;
import com.ankush.entities.Transaction;
import com.ankush.service.service.BillService;
import com.ankush.service.service.CustomerService;
import com.ankush.service.service.DailyStockService;
import com.ankush.service.service.ItemService;
import com.ankush.service.service.LoginService;
import com.ankush.service.serviceImpl.BillServiceImpl;
import com.ankush.service.serviceImpl.CounterServiceImpl;
import com.ankush.service.serviceImpl.CustomerServiceImpl;
import com.ankush.service.serviceImpl.DailyStockServiceImpl;
import com.ankush.service.serviceImpl.ItemServiceImpl;
import com.ankush.service.serviceImpl.LoginServiceImpl;

public class BillingFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4440468591072955983L;
//////////////////////////////////
// for Item Search Box
////////////////////////////////////
	private JTextField txtItemName;
	private JPopupMenu iPop;
	private List<String> iList;
	private JList<String> iJList;
	private DefaultListModel<String> iModel;
	private JScrollPane iScroll;
////////////////////////////////////////////
//for CustomerSearch Box
	private JTextField txtCustomerName;
	private JPopupMenu cPop;
	private List<String> cList;
	private JList<String> cJList;
	private DefaultListModel<String> cModel;
	private JScrollPane cScroll;
	private CustomerService customerService;
//////////////////////////////////////////////
	private String htmstart = "<html><font face=\"kiran\" size=\"6\">";
	private JTextField txtContact;
	private JTextField txtQty;
	private JTextField txtRate;
	private JTextField txtAmount;
	private JTable tblBilling;
	private DefaultTableModel modelBilling, oldBillModel;
	private JTextField txtTotal;
	private JTable table;
	private ItemService itemService;
	private JButton btnAdd;
	private LoginService loginService;
	private Login login;
	private BillService billService;
	private DailyStockService stockService;
	private int billNumber;
	private DefaultListModel<String> itemListModel;

	public BillingFrame(int id) {
		this.loginService = new LoginServiceImpl();
		this.login = loginService.getloginById(id);
		this.itemService = new ItemServiceImpl();
		this.billService = new BillServiceImpl();
		this.customerService = new CustomerServiceImpl();
		this.stockService = new DailyStockServiceImpl();
		this.billNumber = 0;
		setType(Type.POPUP);
		setLocale(new Locale("hi", "IN"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(
				"Billing...                                                                                                               Developed By Ankush Supnar(8329394603)");

		// setSize(1240, 758);
		setSize(1240, 732);
		getContentPane().setBackground(new Color(1, 87, 155));
		getContentPane().setLayout(null);

		JPanel CounterPanel = new JPanel();
		CounterPanel.setBorder(new LineBorder(Color.ORANGE, 1, true));
		CounterPanel.setBackground(new Color(1, 87, 155));
		CounterPanel.setBounds(0, 0, 241, 484);
		getContentPane().add(CounterPanel);
		CounterPanel.setLayout(null);

		ImageIcon icon = new ImageIcon("D:\\Madhuram\\images\\Counter.png", "a pretty but meaningless splat");

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(65, 10, 121, 117);
		CounterPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(login.getStatus());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Kiran", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(5, 139, 235, 30);
		CounterPanel.add(lblNewLabel_1);

		JLabel lblYaujar = new JLabel("yaujar :");
		lblYaujar.setForeground(Color.WHITE);
		lblYaujar.setFont(new Font("Kiran", Font.PLAIN, 30));
		lblYaujar.setBounds(19, 181, 54, 29);
		CounterPanel.add(lblYaujar);

		JLabel lblUserName = new JLabel(login.getUserName());
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Kiran", Font.PLAIN, 30));
		lblUserName.setBounds(79, 181, 161, 29);
		CounterPanel.add(lblUserName);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.ORANGE, 1, true));
		panel.setBackground(new Color(1, 87, 155));
		panel.setBounds(241, 0, 602, 73);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblGaahkacaoNaava = new JLabel("ga`ahkacao naava");
		lblGaahkacaoNaava.setForeground(Color.WHITE);
		lblGaahkacaoNaava.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblGaahkacaoNaava.setBounds(15, 6, 88, 24);
		panel.add(lblGaahkacaoNaava);

		txtCustomerName = new JTextField();
		txtCustomerName.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtCustomerName.setBounds(6, 28, 217, 35);
		panel.add(txtCustomerName);
		txtCustomerName.setColumns(10);
		generateCustomerSearchBox();

		JLabel lblMaaobaalaK = new JLabel("maaobaa[la k`.");
		lblMaaobaalaK.setForeground(Color.WHITE);
		lblMaaobaalaK.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblMaaobaalaK.setBounds(234, 6, 88, 24);
		panel.add(lblMaaobaalaK);

		txtContact = new JTextField();
		txtContact.setEditable(false);
		txtContact.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtContact.setColumns(10);
		txtContact.setBounds(225, 28, 167, 35);
		panel.add(txtContact);

		JButton btnClearCustomer = new JButton("@laIAr");
		btnClearCustomer.addActionListener(e -> {
			txtCustomerName.setText("");
			txtContact.setText("");
		});
		btnClearCustomer.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClearCustomer.setBounds(404, 29, 90, 35);
		panel.add(btnClearCustomer);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.ORANGE, 1, true));
		panel_1.setBackground(new Color(1, 87, 155));
		panel_1.setBounds(241, 73, 602, 120);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblMaalacaoNaava = new JLabel("maalacao naava");
		lblMaalacaoNaava.setForeground(Color.WHITE);
		lblMaalacaoNaava.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblMaalacaoNaava.setBounds(15, 6, 88, 24);
		panel_1.add(lblMaalacaoNaava);

		txtItemName = new JTextField();
		txtItemName.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtItemName.setColumns(10);
		txtItemName.setBounds(6, 28, 256, 35);
		panel_1.add(txtItemName);
		generateItemSearchBox();

		JLabel lblNaga = new JLabel("naga");
		lblNaga.setForeground(Color.WHITE);
		lblNaga.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNaga.setBounds(265, 6, 23, 24);
		panel_1.add(lblNaga);

		txtQty = new JTextField();
		txtQty.setFont(new Font("SansSerif", Font.PLAIN, 18));
		txtQty.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == '-') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if (!txtQty.getText().equals("") && c == KeyEvent.VK_ENTER) {
					try {
						Float.parseFloat(txtQty.getText());
						txtAmount.setText(
								"" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));
						btnAdd.requestFocus();
					} catch (Exception e2) {
						showError("Enter Valid Amount Of rate");
						txtQty.requestFocus();
						txtQty.selectAll();
						return;
					}

				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (txtItemName.getText().equals("") || txtRate.getText().equals("")
						|| txtRate.getText().equals("0.0")) {
					txtItemName.requestFocus();
					txtQty.setText("");
				}
			}
		});
		// txtQty.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtQty.setColumns(10);
		txtQty.setBounds(263, 28, 110, 35);
		panel_1.add(txtQty);

		JLabel lblBaava = new JLabel("Baava");
		lblBaava.setForeground(Color.WHITE);
		lblBaava.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblBaava.setBounds(375, 6, 27, 24);
		panel_1.add(lblBaava);

		txtRate = new JTextField();
		txtRate.setEditable(false);
		txtRate.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtRate.setColumns(10);
		txtRate.setBounds(373, 28, 110, 35);
		panel_1.add(txtRate);

		JLabel lblRkma = new JLabel("r@kma");
		lblRkma.setForeground(Color.WHITE);
		lblRkma.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblRkma.setBounds(485, 6, 41, 24);
		panel_1.add(lblRkma);

		txtAmount = new JTextField();
		txtAmount.setEditable(false);
		txtAmount.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtAmount.setColumns(10);
		txtAmount.setBounds(483, 28, 110, 35);
		panel_1.add(txtAmount);

		btnAdd = new JButton("A^D");
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					btnAdd.doClick();
				}
			}
		});
		btnAdd.addActionListener(e -> addButton());
		btnAdd.setBackground(new Color(0, 153, 51));
		btnAdd.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnAdd.setBounds(6, 75, 90, 35);
		panel_1.add(btnAdd);

		JButton btnClear = new JButton("@laIAr");
		btnClear.addActionListener(e -> clear());
		btnClear.setBackground(new Color(0, 153, 51));
		btnClear.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear.setBounds(122, 75, 90, 35);
		panel_1.add(btnClear);

		JButton btnEdit = new JButton("eiDT");
		btnEdit.addActionListener(e -> edit());
		btnEdit.setBackground(new Color(0, 153, 51));
		btnEdit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnEdit.setBounds(236, 75, 90, 35);
		panel_1.add(btnEdit);

		JButton btnRemove = new JButton("irmauvh");
		btnRemove.addActionListener(e -> remove());
		btnRemove.setBackground(new Color(220, 20, 60));
		btnRemove.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnRemove.setBounds(357, 75, 90, 35);
		panel_1.add(btnRemove);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(241, 190, 605, 258);
		getContentPane().add(scrollPane);

		tblBilling = new JTable();
		tblBilling.setShowVerticalLines(true);
		tblBilling.setShowHorizontalLines(true);
		tblBilling.setFont(new Font("Kiran", Font.PLAIN, 25));
		tblBilling.setRowHeight(25);
		tblBilling.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Sr.No", "Item Name", "Qty", "Rate", "Amount" }));
		tblBilling.getColumnModel().getColumn(1).setPreferredWidth(258);
		tblBilling.getColumnModel().getColumn(2).setPreferredWidth(95);
		tblBilling.getColumnModel().getColumn(3).setPreferredWidth(94);
		tblBilling.getColumnModel().getColumn(4).setPreferredWidth(100);
		scrollPane.setViewportView(tblBilling);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Kiran", Font.BOLD, 25));
		txtTotal.setBackground(new Color(255, 215, 0));
		txtTotal.setBounds(721, 449, 122, 35);
		getContentPane().add(txtTotal);
		txtTotal.setColumns(10);

		JButton btnSave = new JButton("saovh");
		btnSave.addActionListener(e -> save());
		btnSave.setBackground(new Color(0, 153, 51));
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnSave.setBounds(241, 449, 90, 35);
		getContentPane().add(btnSave);

		JButton btnClear2 = new JButton("@laIAr");
		btnClear2.addActionListener(e -> clear2());
		btnClear2.setBackground(new Color(0, 153, 51));
		btnClear2.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear2.setBounds(350, 449, 90, 35);
		getContentPane().add(btnClear2);

		JButton btnEditBill = new JButton("eiDT");
		btnEditBill.addActionListener(e -> editBill());
		btnEditBill.setBackground(new Color(0, 153, 51));
		btnEditBill.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnEditBill.setBounds(466, 449, 90, 35);
		getContentPane().add(btnEditBill);

		JButton btnExit = new JButton("baahor");
		btnExit.setBackground(new Color(220, 20, 60));
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBounds(576, 449, 90, 35);
		getContentPane().add(btnExit);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.ORANGE, 1, true));
		panel_2.setBackground(new Color(1, 87, 155));
		panel_2.setBounds(845, 0, 111, 484);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JButton btn1 = new JButton("1");
		btn1.setForeground(new Color(0, 0, 102));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtQty.getText().equals("")) {
					txtQty.setText(btn1.getText());
					return;
				}
				txtQty.setText("" + txtQty.getText() + btn1.getText());
				if (!txtItemName.getText().equals("") || !txtRate.getText().equals("")) {
					txtAmount.setText("" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));
				}
			}
		});
		btn1.setBackground(new Color(0, 153, 51));
		btn1.setFont(new Font("Kiran", Font.BOLD, 40));
		btn1.setBounds(0, 0, 111, 51);
		panel_2.add(btn1);

		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// txtQty.setText(btn2.getText());
				if (txtQty.getText().equals("")) {
					txtQty.setText(btn2.getText());
					return;
				}
				txtQty.setText("" + txtQty.getText() + btn2.getText());
				if (!txtItemName.getText().equals("") || !txtRate.getText().equals("")) {
					txtAmount.setText("" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));
				}
			}
		});
		btn2.setBackground(new Color(0, 153, 51));
		btn2.setFont(new Font("Kiran", Font.BOLD, 40));
		btn2.setBounds(0, 48, 111, 51);
		panel_2.add(btn2);

		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtQty.getText().equals("")) {
					txtQty.setText(btn3.getText());
					return;
				}
				txtQty.setText("" + txtQty.getText() + btn3.getText());
				if (!txtItemName.getText().equals("") || !txtRate.getText().equals("")) {
					txtAmount.setText("" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));
				}
			}
		});
		btn3.setBackground(new Color(0, 153, 51));
		btn3.setBounds(0, 96, 111, 51);
		btn3.setFont(new Font("Kiran", Font.BOLD, 40));
		panel_2.add(btn3);

		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtQty.getText().equals("")) {
					txtQty.setText(btn4.getText());
					return;
				}
				txtQty.setText("" + txtQty.getText() + btn4.getText());
				if (!txtItemName.getText().equals("") || !txtRate.getText().equals("")) {
					txtAmount.setText("" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));
				}
			}
		});
		btn4.setBackground(new Color(0, 153, 51));
		btn4.setFont(new Font("Kiran", Font.BOLD, 40));
		btn4.setBounds(0, 144, 111, 51);
		panel_2.add(btn4);

		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtQty.getText().equals("")) {
					txtQty.setText(btn5.getText());
					return;
				}
				txtQty.setText("" + txtQty.getText() + btn5.getText());
				if (!txtItemName.getText().equals("") || !txtRate.getText().equals("")) {
					txtAmount.setText("" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));
				}
			}
		});
		btn5.setBackground(new Color(0, 153, 51));
		btn5.setFont(new Font("Kiran", Font.BOLD, 40));
		btn5.setBounds(0, 192, 111, 51);
		panel_2.add(btn5);

		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtQty.getText().equals("")) {
					txtQty.setText(btn6.getText());
					return;
				}
				txtQty.setText("" + txtQty.getText() + btn6.getText());
				if (!txtItemName.getText().equals("") || !txtRate.getText().equals("")) {
					txtAmount.setText("" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));
				}
			}
		});
		btn6.setBackground(new Color(0, 153, 51));
		btn6.setFont(new Font("Kiran", Font.BOLD, 40));
		btn6.setBounds(0, 240, 111, 51);
		panel_2.add(btn6);

		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtQty.getText().equals("")) {
					txtQty.setText(btn7.getText());
					return;
				}
				txtQty.setText("" + txtQty.getText() + btn7.getText());
				if (!txtItemName.getText().equals("") || !txtRate.getText().equals("")) {
					txtAmount.setText("" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));
				}
			}
		});
		btn7.setBackground(new Color(0, 153, 51));
		btn7.setFont(new Font("Kiran", Font.BOLD, 40));
		btn7.setBounds(0, 288, 111, 51);
		panel_2.add(btn7);

		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtQty.getText().equals("")) {
					txtQty.setText(btn8.getText());
					return;
				}
				txtQty.setText("" + txtQty.getText() + btn8.getText());
				if (!txtItemName.getText().equals("") || !txtRate.getText().equals("")) {
					txtAmount.setText("" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));
				}
			}
		});
		btn8.setBackground(new Color(0, 153, 51));
		btn8.setFont(new Font("Kiran", Font.BOLD, 40));
		btn8.setBounds(0, 336, 111, 51);
		panel_2.add(btn8);

		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtQty.getText().equals("")) {
					txtQty.setText(btn9.getText());
					return;
				}
				txtQty.setText("" + txtQty.getText() + btn9.getText());
				if (!txtItemName.getText().equals("") || !txtRate.getText().equals("")) {
					txtAmount.setText("" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));
				}
			}
		});
		btn9.setBackground(new Color(0, 153, 51));
		btn9.setFont(new Font("Kiran", Font.BOLD, 40));
		btn9.setBounds(0, 384, 111, 51);
		panel_2.add(btn9);

		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtQty.getText().equals("")) {
					txtQty.setText(btn0.getText());
					return;
				}
				txtQty.setText("" + txtQty.getText() + btn0.getText());
				if (!txtItemName.getText().equals("") || !txtRate.getText().equals("")) {
					txtAmount.setText("" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));
				}
			}
		});
		btn0.setBackground(new Color(0, 153, 51));
		btn0.setFont(new Font("Kiran", Font.BOLD, 40));
		btn0.setBounds(0, 432, 111, 51);
		panel_2.add(btn0);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(0, 484, 956, 178);
		getContentPane().add(scrollPane_2);

		table = new JTable();
		table.setRowHeight(25);
		table.setShowGrid(true);
		table.setFont(new Font("SansSerif", Font.PLAIN, 16));
		scrollPane_2.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "BillNo", "Bill Amount", "Bill Date", "LoginUser", "Status", "Customer" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(87);
		table.getColumnModel().getColumn(2).setPreferredWidth(129);
		table.getColumnModel().getColumn(3).setPreferredWidth(132);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(175);

		oldBillModel = (DefaultTableModel) table.getModel();
		loadOldBills(LocalDate.now());
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(959, 33, 265, 633);
		getContentPane().add(scrollPane_1);

		itemListModel = new DefaultListModel<>();
		JList<String> list = new JList<>(itemListModel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (list.getSelectedIndex() != -1) {
					txtItemName.setText(list.getSelectedValue());
					txtRate.setText("" + itemService.getItemRateByName(txtItemName.getText()));
				}
			}
		});
		list.setForeground(Color.WHITE);
		list.setBackground(new Color(0, 51, 102));
		list.setFont(new Font("Kiran", Font.PLAIN, 30));
		scrollPane_1.setViewportView(list);

		JLabel lblNewLabel_2 = new JLabel("maalaacaI yaadI");
		lblNewLabel_2.setForeground(new Color(255, 153, 51));
		lblNewLabel_2.setFont(new Font("Kiran", Font.BOLD, 30));
		lblNewLabel_2.setBounds(1003, 5, 139, 29);
		getContentPane().add(lblNewLabel_2);

		JButton btnYesterdayBills = new JButton("kalacaI ibalao");
		btnYesterdayBills.addActionListener(e -> loadYesterDayBill());
		btnYesterdayBills.addActionListener(e -> loadTenBill());
		btnYesterdayBills.setFont(new Font("Kiran", Font.PLAIN, 20));
		btnYesterdayBills.setBounds(134, 659, 122, 28);
		getContentPane().add(btnYesterdayBills);

		JButton btnWeek = new JButton("caalau AazvaDa");
		btnWeek.addActionListener(e -> loadWeekBills());
		btnWeek.setFont(new Font("Kiran", Font.PLAIN, 20));
		btnWeek.setBounds(268, 659, 122, 28);
		getContentPane().add(btnWeek);

		JButton btnMonth = new JButton("caalau maihnaa");
		btnMonth.addActionListener(e -> loadMonthBills());
		btnMonth.setFont(new Font("Kiran", Font.PLAIN, 20));
		btnMonth.setBounds(402, 659, 122, 28);
		getContentPane().add(btnMonth);

		JButton btnYear = new JButton("caalau vaYa-");
		btnYear.addActionListener(e -> loadYearBills());
		btnYear.setFont(new Font("Kiran", Font.PLAIN, 20));
		btnYear.setBounds(536, 659, 122, 28);
		getContentPane().add(btnYear);

		JButton btnAllBills = new JButton("sava- ibalao");
		btnAllBills.addActionListener(e -> loadAllBills());
		btnAllBills.setFont(new Font("Kiran", Font.PLAIN, 20));
		btnAllBills.setBounds(670, 659, 122, 28);
		getContentPane().add(btnAllBills);

		JButton btnTodayBill = new JButton("AajacaI ibalao");
		btnTodayBill.addActionListener(e -> loadTodayBills());
		btnTodayBill.setFont(new Font("Kiran", Font.PLAIN, 20));
		btnTodayBill.setBounds(5, 659, 122, 28);
		getContentPane().add(btnTodayBill);
		loadItemList();

		modelBilling = (DefaultTableModel) tblBilling.getModel();
		setVisible(true);

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		new BillingFrame(1);

	}

	void generateItemSearchBox() {
		try {
			txtItemName.setFont(new Font("Kiran", Font.PLAIN, 25));
			iPop = new JPopupMenu();
			iList = itemService.getAllItemName();
			iModel = new DefaultListModel<>();
			iJList = new JList<>(iModel);
			iJList.setFont(new Font("Kiran", Font.PLAIN, 25));
			iJList.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent ke) {
					char c = ke.getKeyChar();
					if (c == KeyEvent.VK_ENTER) {

						if (iJList.getSelectedIndex() != -1) {
							txtItemName.setText(iJList.getSelectedValue());
							// float rate = CommonLogic.getRateUsingName(txtItemName.getText());
							// txtRate.setText("" + rate);
							// txtCode.setText("" + CommonLogic.getCodeFromName(txtItemName.getText()));
							iPop.setVisible(false);
							txtQty.requestFocus();
							txtRate.setText("" + itemService.getItemRateByName(txtItemName.getText()));
						}
						if (txtItemName.getText().equals("")) {
							return;
						}
					}

				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
			iJList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (iJList.getSelectedIndex() != -1) {
						txtItemName.setText(iJList.getSelectedValue());
						txtRate.setText("" + itemService.getItemRateByName(txtItemName.getText()));
						iPop.setVisible(false);
						txtQty.requestFocus();

					}
					if (txtItemName.getText().equals("")) {
						txtRate.setText("" + 0.0f);
						return;
					}
				}
			});

			// iJList.setFont(font);

			iScroll = new JScrollPane();
			iScroll.setViewportView(iJList);

			txtItemName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {

					// iList = itemService.getAllItemName();
					findItemFilter(txtItemName.getText());
					if (!iList.isEmpty()) {
						iPop.add(iScroll); // your component
						iPop.setPopupSize(txtItemName.getWidth(), 300);
						iPop.show(txtItemName, 0, txtItemName.getHeight());
						txtItemName.requestFocus();
						txtRate.setText("" + itemService.getItemRateByName(txtItemName.getText()));
					}

					char c = e.getKeyChar();

					if (c == KeyEvent.VK_ENTER) {

						iJList.setSelectedIndex(0);
						iJList.requestFocus();
						txtRate.setText("" + 0.0f);
					}
				}
			});

			txtItemName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {

					// iList = itemService.getAllItemName();
					findItemFilter(txtItemName.getText());
					// iJList.requestFocus();
					if (!iList.isEmpty()) {
						iPop.add(iScroll); // your component
						iPop.setPopupSize(txtItemName.getWidth(), 300);
						iPop.show(txtItemName, 0, txtItemName.getHeight());
						txtItemName.requestFocus();
					}
				}
			});

			iPop.setLayout(new BorderLayout());

			// panel.add(txtItemName);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void generateCustomerSearchBox() {
		try {
			txtCustomerName.setFont(new Font("Kiran", Font.PLAIN, 25));
			cPop = new JPopupMenu();
			cList = customerService.getAllCustomerName();
			cModel = new DefaultListModel<>();
			cJList = new JList<>(cModel);
			cJList.setFont(new Font("Kiran", Font.PLAIN, 25));
			cJList.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent ke) {
					char c = ke.getKeyChar();
					if (c == KeyEvent.VK_ENTER) {
						if (cJList.getSelectedIndex() != -1) {
							txtCustomerName.setText(cJList.getSelectedValue());
							cPop.setVisible(false);
							txtContact.requestFocus();
							Customer cu = customerService
									.findCustomerById(customerService.getCustomerIdByName(txtCustomerName.getText()));
							txtContact.setText(cu.getContact());
							// txtQty.requestFocus();
							// txtRate.setText("" + itemService.getItemRateByName(txtItemName.getText()));
						}
						if (txtCustomerName.getText().equals("")) {
							return;
						}
					}

				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
			cJList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (cJList.getSelectedIndex() != -1) {
						txtCustomerName.setText(cJList.getSelectedValue());
						cPop.setVisible(false);
						txtContact.requestFocus();
						Customer c = customerService
								.findCustomerById(customerService.getCustomerIdByName(txtCustomerName.getText()));
						txtContact.setText(c.getContact());

					}
					if (txtCustomerName.getText().equals("")) {

						return;
					}
				}
			});

			// iJList.setFont(font);

			cScroll = new JScrollPane();
			cScroll.setViewportView(cJList);

			txtCustomerName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {

					// iList = itemService.getAllItemName();
					findCustomerFilter(txtCustomerName.getText());

					if (!cList.isEmpty()) {
						cPop.add(cScroll);
						cPop.setPopupSize(txtCustomerName.getWidth(), 300);
						cPop.show(txtCustomerName, 0, txtCustomerName.getHeight());
						txtCustomerName.requestFocus();
					}

					char c = e.getKeyChar();

					if (c == KeyEvent.VK_ENTER) {

						cJList.setSelectedIndex(0);
						cJList.requestFocus();
					}
				}
			});

			txtCustomerName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {

					// iList = itemService.getAllItemName();
					findCustomerFilter(txtItemName.getText());
					// iJList.requestFocus();
					if (!cList.isEmpty()) {
						cPop.add(cScroll); // your component
						cPop.setPopupSize(txtCustomerName.getWidth(), 300);
						cPop.show(txtCustomerName, 0, txtCustomerName.getHeight());
						txtCustomerName.requestFocus();
					}
				}
			});

			cPop.setLayout(new BorderLayout());

			// panel.add(txtItemName);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void findItemFilter(String find) {
		iModel.removeAllElements();
		iModel.clear();

		// System.out.println("i got"+find);
		try {
			for (int i = 0; i < iList.size(); i++) {
				if (iList.get(i).startsWith(find)) {
					// MySortStrings.add(MyStrings.get(i));
					iModel.addElement(iList.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("Error in findItem " + e.getMessage());
			return;
		}

	}

	void findCustomerFilter(String find) {
		cModel.removeAllElements();
		cModel.clear();
		// System.out.println("i got"+find);
		try {
			for (int i = 0; i < cList.size(); i++) {
				if (cList.get(i).startsWith(find)) {
					// MySortStrings.add(MyStrings.get(i));
					cModel.addElement(cList.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("Error in findItem " + e.getMessage());
			return;
		}

	}

	private void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void addButton() {
		try {
			if (txtQty.getText().equals("") || txtQty.getText().equals("0") || txtQty.getText().equals("0.0")) {
				showError("Enter Quantity >0");
				txtQty.requestFocus();
				return;
			}
			if (txtRate.getText().equals("") || txtRate.getText().equals("0.0")) {
				showError("Select Item Again");
				txtItemName.requestFocus();
				return;
			}
			if (txtItemName.getText().equals("")) {
				showError("Enter Item Name");
				txtItemName.requestFocus();
				return;
			}
			if (billNumber == 0
					&& (stockService.getItemStockByName(txtItemName.getText()) < Float.parseFloat(txtQty.getText()))) {
				showError("Not Enough Stock Available");
				txtQty.requestFocus();
				return;
			}
			if (billNumber != 0) {
				float oldqty = Float.parseFloat(modelBilling.getValueAt(tblBilling.getSelectedRow(), 2).toString());
				float newQty = Float.parseFloat(txtQty.getText()) + oldqty;
				float stock = stockService.getItemStockByName(txtItemName.getText());
				if ((stock + oldqty) < newQty) {
					showError("Not Enough Stock Available");
					txtQty.requestFocus();
					return;
				}

				// return;
			}

			txtAmount.setText("" + (Float.parseFloat(txtQty.getText()) * Float.parseFloat(txtRate.getText())));

			// add Data in Jtable
			int row = tblBilling.getRowCount();
			System.out.println(row);
			int flag = -1;
			if (row == 0) {
				System.out.println("no data");
				if (Integer.parseInt(txtQty.getText()) < 0) {
					showError("Cant Add Less Quantity");
					txtQty.requestFocus();
					return;
				}
				modelBilling.addRow(new Object[] { 1, txtItemName.getText(), txtQty.getText(), txtRate.getText(),
						txtAmount.getText() });
				txtTotal.setText("" + txtAmount.getText());
				clear();
				txtItemName.requestFocus();
			} else {
				for (int i = 0; i < row; i++) {
					String name = modelBilling.getValueAt(i, 1).toString();
					if (name.equals(txtItemName.getText())
							&& txtRate.getText().equals(modelBilling.getValueAt(i, 3).toString())) {
						flag = i;
					}
				}
				if (flag == -1) {
					// add New
					if (Integer.parseInt(txtQty.getText()) < 0) {
						showError("Cant Add Less Quantity");
						txtQty.requestFocus();
						return;
					}
					modelBilling.addRow(new Object[] { ++row, txtItemName.getText(), txtQty.getText(),
							txtRate.getText(), txtAmount.getText() });
					txtTotal.setText(
							"" + (Float.parseFloat(txtAmount.getText()) + Float.parseFloat(txtTotal.getText())));
					clear();
					txtItemName.requestFocus();
				} else {
					// update Existing

					float newQty = Float.parseFloat(txtQty.getText());
					float oldQty = Float.parseFloat(modelBilling.getValueAt(flag, 2).toString());
					if (oldQty + newQty <= 0) {
						showError("Cant Add Less Quantity");
						txtQty.requestFocus();
						return;
					}
					if (billNumber == 0 && stockService.getItemStockByName(txtItemName.getText()) < (newQty + oldQty)) {
						showError("Not Enough Stock Available");
						txtQty.requestFocus();
						return;
					}
					modelBilling.setValueAt(Float.parseFloat(modelBilling.getValueAt(flag, 2).toString())
							+ Float.parseFloat(txtQty.getText()), flag, 2);
					modelBilling.setValueAt(Float.parseFloat(modelBilling.getValueAt(flag, 4).toString())
							+ Float.parseFloat(txtAmount.getText()), flag, 4);
					txtTotal.setText(
							"" + (Float.parseFloat(txtAmount.getText()) + Float.parseFloat(txtTotal.getText())));
					clear();
					txtItemName.requestFocus();
				}
			}
		} catch (Exception e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
	}

	private void clear() {
		txtItemName.setText("");
		txtQty.setText("");
		txtRate.setText("");
		txtAmount.setText("");
		tblBilling.clearSelection();
	}

	private void loadItemList() {
		try {
			Iterator<String> it = itemService.getAllItemName().iterator();
			while (it.hasNext()) {
				itemListModel.addElement(it.next());
			}
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private void edit() {
		try {
			int row = tblBilling.getSelectedRow();
			if (row == -1) {
				return;
			}
			txtItemName.setText(modelBilling.getValueAt(row, 1).toString());
			txtRate.setText(modelBilling.getValueAt(row, 3).toString());
			txtAmount.setText(modelBilling.getValueAt(row, 4).toString());

		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private void remove() {

		if (tblBilling.getSelectedRow() == -1) {
			return;
		}
		// float oldTotal = Float.parseFloat(txtTotal.getText());
		// float amt = Float.parseFloat(s)
		txtTotal.setText("" + (Float.parseFloat(txtTotal.getText())
				- Float.parseFloat(modelBilling.getValueAt(tblBilling.getSelectedRow(), 4).toString())));
		modelBilling.removeRow(tblBilling.getSelectedRow());
	}

	private void save() {
		try {
			int row = tblBilling.getRowCount();
			if (row == 0) {
				showError("No Data To Save");
				return;
			}
			Backup back = new Backup();
			Bill bill = new Bill();
			bill.setAmount(Double.parseDouble(txtTotal.getText()));
			bill.setCounter(new CounterServiceImpl()
					.getCounterById(new CounterServiceImpl().getCounterIdByName(login.getStatus())));
			if (!txtCustomerName.getText().equals("")) {
				if (txtContact.getText().equals("")) {
					showError("Plese select Customer Again");
					return;
				}
				bill.setCustomerId(customerService.getCustomerIdByName(txtCustomerName.getText()));
				bill.setPayMode("credit");
				bill.setStatus("unpaid");
			} else {
				bill.setCustomerId(0);
				bill.setPayMode("cash");
				bill.setStatus("paid");
			}

			// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			bill.setDate(LocalDateTime.now());
			bill.setLogin(login);

			Transaction tr;
			List<Transaction> list = new ArrayList<>();
			for (int i = 0; i < row; i++) {
				tr = new Transaction();
				tr.setItemName(modelBilling.getValueAt(i, 1).toString());
				tr.setAmount(Float.parseFloat(modelBilling.getValueAt(i, 4).toString()));
				tr.setQty(Float.parseFloat(modelBilling.getValueAt(i, 2).toString()));
				tr.setRate(Float.parseFloat(modelBilling.getValueAt(i, 3).toString()));
				tr.setBill(bill);
				list.add(tr);

				System.out.println(tr);
			}
			bill.setTransactionList(list);
			bill.setBillNo(billNumber);
			reduceStock(bill);
			int flag = billService.saveBill(bill);
			if (flag == 1) {
				JOptionPane.showMessageDialog(this, "Bill Saved", "Success", JOptionPane.INFORMATION_MESSAGE);
				// reduce Stock

				oldBillModel.addRow(new Object[] { bill.getBillNo(), bill.getAmount(), bill.getDate(),
						htmstart + bill.getLogin().getUserName(), bill.getStatus(),
						htmstart + customerService.getCustomerNameById(bill.getCustomerId()) });
				clear2();
				scrollTable();
				back.writeInBillFile("D:\\Madhuram\\Backup\\");
				back.writeInDailyFile("D:\\Madhuram\\Backup\\");

			}
			if (flag == 2) {

				JOptionPane.showMessageDialog(this, "Bill Update", "Success", JOptionPane.INFORMATION_MESSAGE);
				oldBillModel.setValueAt(bill.getBillNo(), table.getSelectedRow(), 0);
				oldBillModel.setValueAt(bill.getAmount(), table.getSelectedRow(), 1);
				oldBillModel.setValueAt(bill.getDate(), table.getSelectedRow(), 2);
				oldBillModel.setValueAt(htmstart + bill.getLogin().getUserName(), table.getSelectedRow(), 3);
				oldBillModel.setValueAt(bill.getStatus(), table.getSelectedRow(), 4);
				oldBillModel.setValueAt(htmstart + customerService.getCustomerNameById(bill.getCustomerId()),
						table.getSelectedRow(), 5);
				clear2();
				back.writeInBillFile("D:\\Madhuram\\Backup\\");
				back.writeInDailyFile("D:\\Madhuram\\Backup\\");
			}
//			oldBillModel.addRow(new Object[] { bill.getBillNo(), bill.getAmount(), bill.getDate(),
//					htmstart + bill.getLogin().getUserName(), bill.getStatus(),
//					htmstart + customerService.getCustomerNameById(bill.getCustomerId()) });
			// scrollTable();
			// System.out.println(bill);

		} catch (Exception e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
	}

	private void loadOldBills(LocalDate date) {
		try {
			oldBillModel.setRowCount(0);
			Iterator<Bill> it = billService.getdateWiseBills(date).iterator();
			Bill bill;
			while (it.hasNext()) {
				bill = it.next();
				// System.out.println(bill);
				oldBillModel.addRow(new Object[] { bill.getBillNo(), bill.getAmount(), bill.getDate(),
						htmstart + bill.getLogin().getUserName(), bill.getStatus(),
						htmstart + customerService.getCustomerNameById(bill.getCustomerId()) });
				scrollTable();
			}
		} catch (Exception e) {
			showError("Errro Loading Old Bill");
		}
	}

	private void clear2() {
		try {
			txtItemName.setText("");
			modelBilling.setRowCount(0);
			txtTotal.setText("" + 0.0f);
			txtCustomerName.setText("");
			txtContact.setText("");
			table.clearSelection();
			billNumber = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollTable() {
		table.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				table.scrollRectToVisible(table.getCellRect(table.getRowCount() - 1, 0, true));
			}
		});
	}

	private void editBill() {
		try {
			int row = table.getSelectedRow();
			if (row == -1) {
				return;
			}
			modelBilling.setRowCount(0);
			txtTotal.setText("" + 0.0f);
			int billNo = Integer.parseInt(oldBillModel.getValueAt(row, 0).toString());
			Bill bill = billService.getBillById(billNo);
			// Iterator<Transaction> tr = bill.getTransactionList().iterator();
			List<Transaction> tr = bill.getTransactionList();
			int sr = 0;
			double total = 0.0f;
			for (Transaction t : tr) {
				modelBilling.addRow(new Object[] { ++sr, t.getItemName(), t.getQty(), t.getRate(), t.getAmount() });
				total = total + t.getAmount();

			}
			txtTotal.setText("" + total);
			billNumber = bill.getBillNo();
			if (bill.getCustomerId() == 0) {

			} else {
				Customer c = customerService.findCustomerById(bill.getCustomerId());
				txtCustomerName.setText(c.getName());
				txtContact.setText(c.getContact());
			}

		} catch (Exception e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
	}

	private int reduceStock(Bill bill) {
		try {
			if (bill.getBillNo() == 0) {
				for (Transaction t : bill.getTransactionList()) {
					stockService.reduceStock(t.getItemName(), t.getQty());
				}
				return 1;
			} else {
				int row = tblBilling.getRowCount();
				DailyStock ds;
				for (Transaction t : billService.getBillById(bill.getBillNo()).getTransactionList()) {
					ds = new DailyStock();
					ds.setItemName(t.getItemName());
					ds.setQty(t.getQty());
					stockService.addDailyStock(ds);
					System.out.println("Stock added=>" + ds);
				}
				for (int i = 0; i < row; i++) {
					stockService.reduceStock(modelBilling.getValueAt(i, 1).toString(),
							Float.parseFloat(modelBilling.getValueAt(i, 2).toString()));
					System.out.println("Stock Reduced ");
				}
				return 1;
			}
		} catch (Exception e) {
			showError(e.getMessage());
			return 0;
		}
	}

	public void loadTenBill() {
		try {

			LocalDate fromDate, toDate;
			if (table.getRowCount() == 0) {
				toDate = LocalDate.now();
				fromDate = toDate.minusDays(1);

			} else {

				String d = oldBillModel.getValueAt(0, 2).toString();
				fromDate = LocalDate.parse(d.subSequence(0, 10)).minusDays(1);
				String t = oldBillModel.getValueAt(table.getRowCount() - 1, 2).toString();
				toDate = LocalDate.parse(t.subSequence(0, 10));
			}

			toDate = fromDate.plusDays(1);
			System.out.println("fromDate " + fromDate + "\n toDate" + toDate);
			oldBillModel.setRowCount(0);

			List<Bill> list = billService.getPeriodBill(fromDate, toDate);
			for (Bill bill : list) {
				oldBillModel.addRow(new Object[] { bill.getBillNo(), bill.getAmount(), bill.getDate(),
						htmstart + bill.getLogin().getUserName(), bill.getStatus(),
						htmstart + customerService.getCustomerNameById(bill.getCustomerId()) });
				// scrollTable();
			}

		} catch (Exception e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void loadYesterDayBill() {
		try {
			oldBillModel.setRowCount(0);
			List<Bill> list = billService.getBillFromYesterDay(LocalDate.now());
			for (Bill bill : list) {
				oldBillModel.addRow(new Object[] { bill.getBillNo(), bill.getAmount(), bill.getDate(),
						htmstart + bill.getLogin().getUserName(), bill.getStatus(),
						htmstart + customerService.getCustomerNameById(bill.getCustomerId()) });
				scrollTable();
			}
		} catch (Exception e) {
			showError("Error " + e.getMessage());
		}
	}

	public void loadWeekBills() {
		try {
			oldBillModel.setRowCount(0);
			List<Bill> list = billService.getBillWeekFromToday();
			for (Bill bill : list) {
				oldBillModel.addRow(new Object[] { bill.getBillNo(), bill.getAmount(), bill.getDate(),
						htmstart + bill.getLogin().getUserName(), bill.getStatus(),
						htmstart + customerService.getCustomerNameById(bill.getCustomerId()) });
				scrollTable();
			}
		} catch (Exception e) {
			showError("Error " + e.getMessage());
		}
	}

	public void loadMonthBills() {
		try {
			oldBillModel.setRowCount(0);
			List<Bill> list = billService.getCurrentMonthBills();
			for (Bill bill : list) {
				oldBillModel.addRow(new Object[] { bill.getBillNo(), bill.getAmount(), bill.getDate(),
						htmstart + bill.getLogin().getUserName(), bill.getStatus(),
						htmstart + customerService.getCustomerNameById(bill.getCustomerId()) });
				scrollTable();
			}
		} catch (Exception e) {
			showError("Error " + e.getMessage());
		}
	}

	public void loadYearBills() {
		try {
			oldBillModel.setRowCount(0);
			List<Bill> list = billService.getCurrentYearBills();
			for (Bill bill : list) {
				oldBillModel.addRow(new Object[] { bill.getBillNo(), bill.getAmount(), bill.getDate(),
						htmstart + bill.getLogin().getUserName(), bill.getStatus(),
						htmstart + customerService.getCustomerNameById(bill.getCustomerId()) });
				scrollTable();
			}
		} catch (Exception e) {
			showError("Error " + e.getMessage());
		}
	}

	public void loadTodayBills() {
		try {
			oldBillModel.setRowCount(0);
			List<Bill> list = billService.getdateWiseBills(LocalDate.now());
			for (Bill bill : list) {
				oldBillModel.addRow(new Object[] { bill.getBillNo(), bill.getAmount(), bill.getDate(),
						htmstart + bill.getLogin().getUserName(), bill.getStatus(),
						htmstart + customerService.getCustomerNameById(bill.getCustomerId()) });
				scrollTable();
			}
		} catch (Exception e) {
			showError("Error " + e.getMessage());
		}
	}

	public void loadAllBills() {
		try {
			oldBillModel.setRowCount(0);
			List<Bill> list = billService.getAllBill();
			for (Bill bill : list) {
				oldBillModel.addRow(new Object[] { bill.getBillNo(), bill.getAmount(), bill.getDate(),
						htmstart + bill.getLogin().getUserName(), bill.getStatus(),
						htmstart + customerService.getCustomerNameById(bill.getCustomerId()) });
				scrollTable();
			}
		} catch (Exception e) {
			showError("Error " + e.getMessage());
		}
	}
}
