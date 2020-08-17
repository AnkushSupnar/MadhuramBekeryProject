package com.ankush.view.toCounter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
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
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.ankush.entities.Login;
import com.ankush.entities.ToCounter;
import com.ankush.entities.ToCounterTransaction;
import com.ankush.service.service.CounterService;
import com.ankush.service.service.DailyStockService;
import com.ankush.service.service.FromFactoryService;
import com.ankush.service.service.ItemService;
import com.ankush.service.service.LoginService;
import com.ankush.service.service.ToCounterService;
import com.ankush.service.serviceImpl.CounterServiceImpl;
import com.ankush.service.serviceImpl.DailyStockServiceImpl;
import com.ankush.service.serviceImpl.ItemServiceImpl;
import com.ankush.service.serviceImpl.LoginServiceImpl;
import com.ankush.service.serviceImpl.ToCounterServiceImpl;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public class ToCounterFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 154967073893219610L;

//////////////////////////////////
//for Item Search Box
////////////////////////////////////
	private JTextField txtItemName;
	private JPopupMenu iPop;
	private List<String> iList;
	private JList<String> iJList;
	private DefaultListModel<String> iModel;
	private JScrollPane iScroll;

////////////////////////////////////////////
	private ItemService itemService;
	private JTextField txtQty;
	private JTable table;
	private DefaultTableModel model;
	@SuppressWarnings("unused")
	private FromFactoryService factoryService;
	private CounterService counterService;
	private int id;
	private JTable tblOld;
	private DefaultTableModel oldModel;
	private String htmstart = "<html><font face=\"kiran\" size=\"6\">";
	private JTextField txtRecieptId;
	private DatePicker date;
	private DatePickerSettings dateSettings;
	private LoginService loginServce;
	private Login login;
	private ToCounterService toCounterService;
	@SuppressWarnings("unused")
	private DailyStockService dailyStockService;

	public ToCounterFrame(int loginId) {
		this.id = 0;
		this.loginServce = new LoginServiceImpl();
		this.toCounterService = new ToCounterServiceImpl();
		this.counterService = new CounterServiceImpl();
		this.itemService = new ItemServiceImpl();
		this.login = loginServce.getloginById(loginId);
		this.dailyStockService = new DailyStockServiceImpl();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(993, 726);
		setLocation(200, 5);
		setResizable(false);
		setTitle("Goods To Counter");
		getContentPane().setBackground(new Color(1, 87, 155));
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 1, true), "Select Counter",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		panel.setBackground(new Color(1, 87, 155));
		panel.setBounds(10, 5, 540, 127);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("paavataI k`.");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(65, 35, 63, 24);
		panel.add(lblNewLabel);

		txtRecieptId = new JTextField();
		txtRecieptId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if (!txtRecieptId.getText().equals("") && c == KeyEvent.VK_ENTER) {
					date.requestFocus();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		txtRecieptId.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtRecieptId.setBounds(137, 30, 193, 35);
		panel.add(txtRecieptId);

		JLabel lblIdnaamk = new JLabel("idnaaMk ");
		lblIdnaamk.setForeground(Color.WHITE);
		lblIdnaamk.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblIdnaamk.setBounds(77, 77, 49, 24);
		panel.add(lblIdnaamk);

		dateSettings = new DatePickerSettings();
		// Font randomFont = new Font("Monospaced", Font.ITALIC | Font.BOLD, 20);
		// Font smallerFont = new Font("Serif", Font.BOLD, 18);
//		dateSettings.setFontMonthAndYearMenuLabels(randomFont);
//		dateSettings.setFontMonthAndYearNavigationButtons(randomFont);
//		dateSettings.setFontTodayLabel(randomFont);
//		dateSettings.setFontClearLabel(randomFont);
//		dateSettings.setFontCalendarDateLabels(randomFont);
//		dateSettings.setFontCalendarWeekdayLabels(smallerFont);
//		dateSettings.setFontCalendarWeekNumberLabels(smallerFont);
		date = new DatePicker(dateSettings);
		date.setDateToToday();
		date.setBounds(136, 77, 193, 35);
		panel.add(date);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 255, 255), 1, true), "ItemDetails",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		panel_1.setBounds(10, 130, 540, 143);
		panel_1.setBackground(panel.getBackground());
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		txtItemName = new JTextField();
		txtItemName.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtItemName.setColumns(10);
		txtItemName.setBounds(34, 51, 235, 36);
		panel_1.add(txtItemName);

		JLabel lblMaalacaoNaava = new JLabel("maalacao naava");
		lblMaalacaoNaava.setForeground(Color.WHITE);
		lblMaalacaoNaava.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblMaalacaoNaava.setBounds(34, 25, 93, 24);
		panel_1.add(lblMaalacaoNaava);

		JButton btnAdd = new JButton("A^D");
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnAdd.doClick();
				}
			}
		});
		btnAdd.addActionListener(e -> addButton());
		btnAdd.setBackground(new Color(0, 153, 51));
		btnAdd.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnAdd.setBounds(34, 99, 90, 35);
		panel_1.add(btnAdd);

		txtQty = new JTextField();
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
				if (txtItemName.getText().equals("")) {
					txtItemName.requestFocus();
					txtQty.setText("");
				}
			}
		});
		txtQty.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtQty.setBounds(272, 51, 131, 35);
		panel_1.add(txtQty);
		txtQty.setColumns(10);

		JLabel lblNaga = new JLabel("naga");
		lblNaga.setForeground(Color.WHITE);
		lblNaga.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNaga.setBounds(285, 25, 23, 24);
		panel_1.add(lblNaga);

		JButton btnClear = new JButton("i@laAr");
		btnClear.addActionListener(e -> clear());
		btnClear.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear.setBackground(new Color(0, 153, 51));
		btnClear.setBounds(136, 99, 90, 35);
		panel_1.add(btnClear);

		JButton btnEdit = new JButton("eiDT");

		btnEdit.addActionListener(e -> edit());
		btnEdit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnEdit.setBackground(new Color(0, 153, 51));
		btnEdit.setBounds(239, 99, 90, 35);
		panel_1.add(btnEdit);

		JButton btnRemove = new JButton("irmauvh");
		btnRemove.addActionListener(e -> remove());
		btnRemove.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnRemove.setBackground(new Color(0, 153, 51));
		btnRemove.setBounds(341, 99, 90, 35);
		panel_1.add(btnRemove);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 275, 540, 358);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setShowGrid(true);
		table.setFont(new Font("Kiran", Font.PLAIN, 25));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Sr.No.", "ItemName", "Quantity" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(265);
		table.getColumnModel().getColumn(2).setPreferredWidth(131);
		scrollPane.setViewportView(table);

		table.setRowHeight(25);

		JButton btnSave = new JButton("saovh");
		btnSave.addActionListener(e -> save());
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnSave.setBackground(new Color(0, 153, 51));
		btnSave.setBounds(10, 640, 90, 35);
		getContentPane().add(btnSave);

		JButton btnClear2 = new JButton("i@laAr");
		btnClear2.addActionListener(e -> clear2());
		btnClear2.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear2.setBackground(new Color(0, 153, 51));
		btnClear2.setBounds(112, 640, 90, 35);
		getContentPane().add(btnClear2);

		JButton btnEdit2 = new JButton("eiDT");
		btnEdit2.addActionListener(e -> edit2());
		btnEdit2.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnEdit2.setBackground(new Color(0, 153, 51));
		btnEdit2.setBounds(215, 640, 90, 35);
		getContentPane().add(btnEdit2);

		JButton btnExit = new JButton("baahor");
		btnExit.addActionListener(e -> dispose());
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setBounds(317, 640, 90, 35);
		getContentPane().add(btnExit);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(555, 10, 416, 621);
		getContentPane().add(scrollPane_1);

		tblOld = new JTable();
		tblOld.setFont(new Font("SansSerif", Font.PLAIN, 16));
		tblOld.setRowHeight(25);
		tblOld.setShowGrid(true);
		tblOld.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Sr.No", "Date", "Counter Name", "Status" }));
		tblOld.getColumnModel().getColumn(0).setPreferredWidth(94);
		tblOld.getColumnModel().getColumn(1).setPreferredWidth(208);
		tblOld.getColumnModel().getColumn(2).setPreferredWidth(137);
		tblOld.getColumnModel().getColumn(3).setPreferredWidth(76);
		scrollPane_1.setViewportView(tblOld);

		model = (DefaultTableModel) table.getModel();
		generateItemSearchBox();

		oldModel = (DefaultTableModel) tblOld.getModel();

		loadOld();

		setVisible(true);

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new ToCounterFrame(1);
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
							iPop.setVisible(false);
							txtQty.requestFocus();
							// txtRate.setText("" + itemService.getItemRateByName(txtItemName.getText()));
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
						// txtRate.setText("" + itemService.getItemRateByName(txtItemName.getText()));
						iPop.setVisible(false);
						txtQty.requestFocus();

					}
					if (txtItemName.getText().equals("")) {
						// txtRate.setText("" + 0.0f);
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
						// txtRate.setText("" + itemService.getItemRateByName(txtItemName.getText()));
					}

					char c = e.getKeyChar();

					if (c == KeyEvent.VK_ENTER) {

						iJList.setSelectedIndex(0);
						iJList.requestFocus();
						// txtRate.setText("" + 0.0f);
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

	private void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void edit2() {
		try {
			int row = tblOld.getSelectedRow();
			if (row == -1) {
				return;
			}
			model.setRowCount(0);
			ToCounter tc = toCounterService
					.getToCounterById(Integer.parseInt(oldModel.getValueAt(tblOld.getSelectedRow(), 0).toString()));
			txtRecieptId.setText(Integer.toString(tc.getId()));
			date.setDate(tc.getDate().toLocalDate());
			id = tc.getId();
			List<ToCounterTransaction> list = tc.getTransactions();
			int sr = 0;
			for (ToCounterTransaction tr : list) {
				model.addRow(new Object[] { ++sr, tr.getItemName(), tr.getQty() });
			}

		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private void remove() {
		try {
			int row = table.getSelectedRow();
			if (row == -1)
				return;
			model.removeRow(row);
			row = table.getRowCount();
			int sr = 0;
			for (int i = 0; i < row; i++) {
				model.setValueAt(++sr, i, 0);
			}
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private int validateData() {
		try {
			if (txtItemName.getText().equals("")) {
				showError("Enter Item Name");
				txtItemName.requestFocus();
				return 0;
			} else if (txtQty.getText().equals("")) {
				showError("Enter Quantity");
				txtQty.requestFocus();
				return 0;
			}
			return 1;
		} catch (Exception e) {
			showError(e.getMessage());
			return 0;
		}
	}

	private void addButton() {
		try {

			if (validateData() != 1) {
				return;
			}
			int row = model.getRowCount();
			if (row == 0) {
				model.addRow(new Object[] { 1, txtItemName.getText(), txtQty.getText() });
				// clear();

			} else {
				int flag = -1;
				for (int i = 0; i < row; i++) {
					if (model.getValueAt(i, 1).toString().equals(txtItemName.getText())) {
						flag = i;
					}
				}
				if (flag != -1) {
					if (Float.parseFloat(model.getValueAt(flag, 2).toString())
							+ Float.parseFloat(txtQty.getText()) <= 0.0f) {
						showError("Cant Reduce Quantity");
						txtQty.requestFocus();
						return;
					}
					model.setValueAt(
							Float.parseFloat(model.getValueAt(flag, 2).toString()) + Float.parseFloat(txtQty.getText()),
							flag, 2);
					clear();
				} else {
					model.addRow(new Object[] { ++row, txtItemName.getText(), txtQty.getText() });
					clear();
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
		txtItemName.requestFocus();
	}

	private void edit() {
		try {
			int row = table.getSelectedRow();
			if (row == -1) {
				return;
			}
			txtItemName.setText(model.getValueAt(row, 1).toString());
			txtQty.setText(model.getValueAt(row, 2).toString());
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private void save() {
		try {
			int row = table.getRowCount();
			if (row == 0) {
				showError("No data To Save");
				return;
			}
			if (txtRecieptId.getText().equals("")) {
				showError("Enter Reciept No");
				txtRecieptId.requestFocus();
				return;
			}
			ToCounter toCounter = new ToCounter();
			toCounter.setId(id);
			toCounter.setCounter(counterService.getCounterById(counterService.getCounterIdByName(login.getStatus())));
			toCounter.setDate(date.getDate().atTime(LocalTime.now()));
			toCounter.setStatus("submit");
			toCounter.setFromFactoryId(Integer.parseInt(txtRecieptId.getText()));

			List<ToCounterTransaction> trList = new ArrayList<>();
			ToCounterTransaction tr;

			for (int i = 0; i < row; i++) {
				tr = new ToCounterTransaction();
				tr.setItemName(model.getValueAt(i, 1).toString());
				tr.setQty(Float.parseFloat(model.getValueAt(i, 2).toString()));
				tr.setToCounter(toCounter);
				trList.add(tr);
			}
			toCounter.setTransactions(trList);

			int flag = toCounterService.saveToCounter(toCounter);
			if (flag == 1) {

				int sr = tblOld.getRowCount();
				JOptionPane.showMessageDialog(this, "Rcord Save Success", "Success", JOptionPane.INFORMATION_MESSAGE);
				oldModel.addRow(new Object[] { ++sr, toCounter.getDate(),
						htmstart + toCounter.getCounter().getCounterName(), toCounter.getStatus() });
				clear2();
			}
			if (flag == 2) {
				JOptionPane.showMessageDialog(this, "Record Update Success", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				int r = tblOld.getSelectedRow();
				oldModel.setValueAt(toCounter.getDate(), r, 1);
				oldModel.setValueAt(htmstart + toCounter.getCounter().getCounterName(), r, 2);
				oldModel.setValueAt(toCounter.getStatus(), r, 3);
				clear2();
			}
			if (flag == 3) {
				showError("Cant Reduce Sold Quantity");
				return;
			}

		} catch (Exception e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
	}

	private void loadOld() {
		try {
			oldModel.setRowCount(0);
			List<ToCounter> list = toCounterService.getAllToCounter();
			for (ToCounter tc : list) {
				oldModel.addRow(new Object[] { tc.getId(), tc.getDate(), htmstart + tc.getCounter().getCounterName(),
						tc.getStatus() });
			}
		} catch (Exception e) {
			e.printStackTrace();
			showError(e.getMessage());
		}
	}

	private void clear2() {
		txtRecieptId.setText("");
		date.setDateToToday();
		model.setRowCount(0);
		tblOld.clearSelection();
		table.clearSelection();
		id = 0;
		clear();
	}
}
