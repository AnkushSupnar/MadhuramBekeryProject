package com.ankush.view.report;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.table.DefaultTableModel;

import com.ankush.entities.DailyStock;
import com.ankush.service.service.DailyStockService;
import com.ankush.service.service.ItemService;
import com.ankush.service.serviceImpl.DailyStockServiceImpl;
import com.ankush.service.serviceImpl.ItemServiceImpl;

public class StockReport extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -461431507015481801L;
	// for Item Search Box
////////////////////////////////////
	private JTextField txtItemName;
	private JPopupMenu iPop;
	private List<String> iList;
	private JList<String> iJList;
	private DefaultListModel<String> iModel;
	private JScrollPane iScroll;
	private ItemService itemService;
////////////////////////////////////////////
	private JTable table;
	private DefaultTableModel model;
	@SuppressWarnings("unused")
	private String htmstart = "<html><font face=\"kiran\" size=\"6\">";
	private DailyStockService stockService;

	public StockReport() {
		this.itemService = new ItemServiceImpl();
		this.stockService = new DailyStockServiceImpl();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Stock Report");
		setSize(827, 726);
		setResizable(false);
		setLocation(200, 5);
		getContentPane().setBackground(new Color(1, 87, 155));
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 1, true));
		panel.setBackground(new Color(1, 87, 155));
		panel.setBounds(0, 0, 810, 138);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("maalacao naava");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(30, 32, 75, 24);
		panel.add(lblNewLabel);

		txtItemName = new JTextField();
		txtItemName.setBounds(110, 30, 276, 35);
		panel.add(txtItemName);
		txtItemName.setColumns(10);

		generateItemSearchBox();

		JButton btnFind = new JButton("paha");
		btnFind.setForeground(Color.WHITE);
		btnFind.setBackground(new Color(0, 153, 0));
		btnFind.addActionListener(e -> find());
		btnFind.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnFind.setBounds(27, 88, 90, 35);
		panel.add(btnFind);

		JButton btnFindAll = new JButton("sava- paha");
		btnFindAll.addActionListener(e -> findAll());
		btnFindAll.setForeground(Color.WHITE);
		btnFindAll.setBackground(new Color(0, 153, 0));
		btnFindAll.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnFindAll.setBounds(149, 88, 90, 35);
		panel.add(btnFindAll);

		JButton btnClear = new JButton("@laIAr");
		btnClear.setForeground(Color.WHITE);
		btnClear.setBackground(new Color(0, 153, 0));
		btnClear.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear.setBounds(267, 88, 88, 35);
		panel.add(btnClear);

		JButton btnExit = new JButton("baahor");
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setBounds(382, 88, 88, 35);
		panel.add(btnExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 140, 810, 551);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Kiran", Font.PLAIN, 25));
		table.setShowGrid(true);
		table.setRowHeight(25);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Sr.No", "Item Name", "Stock" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(396);
		scrollPane.setViewportView(table);
		model = (DefaultTableModel) table.getModel();

		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new StockReport();
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
							// txtQty.requestFocus();
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
						// txtQty.requestFocus();

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

	private void find() {
		try {
			if (txtItemName.getText().equals("")) {
				showError("Enter Item Name");
				txtItemName.requestFocus();
				return;
			}
			model.setRowCount(0);
			DailyStock stock = stockService.getDailyStockByName(txtItemName.getText());
			model.addRow(new Object[] { 1, stock.getItemName(), stock.getQty() });
		} catch (Exception e) {
			showError(e.getMessage());
		}

	}

	private void findAll() {
		try {
			model.setRowCount(0);
			List<DailyStock> list = stockService.getAllDailyStock();
			int sr = 0;
			for (DailyStock stock : list) {
				model.addRow(new Object[] { ++sr, stock.getItemName(), stock.getQty() });
			}
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}
}
