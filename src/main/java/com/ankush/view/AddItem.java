package com.ankush.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.ankush.entities.Item;
import com.ankush.service.service.ItemService;
import com.ankush.service.serviceImpl.ItemServiceImpl;

public class AddItem extends JFrame {

	/**
	 * 
	 */
	private String htmstart = "<html><font face=\"kiran\" size=\"6\">";
	int id;
	private static final long serialVersionUID = 1727515957791574799L;
	private JTextField txtItemName;
	private JTextField txtItemRate;
	private JTable table;
	private JButton btnSave;
	private ItemService service;
	private DefaultTableModel model;

	public AddItem() {
		this.service = new ItemServiceImpl();
		this.id = 0;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Add New Item");
		setResizable(false);
		setSize(500, 697);
		getContentPane().setBackground(new Color(1, 87, 155));
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("naivana maala");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 30));
		lblNewLabel.setBounds(188, 11, 87, 29);
		getContentPane().add(lblNewLabel);

		JLabel label = new JLabel("naivana maala");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Kiran", Font.PLAIN, 25));
		label.setBounds(55, 85, 73, 24);
		getContentPane().add(label);

		txtItemName = new JTextField();
		txtItemName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtItemName.getText().equals("")) {
						txtItemRate.requestFocus();
					}
				}
			}
		});
		label.setLabelFor(txtItemName);
		txtItemName.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtItemName.setBounds(138, 85, 283, 35);
		getContentPane().add(txtItemName);
		txtItemName.setColumns(10);

		JLabel lblMaalaacaaBaava = new JLabel("maalaacaa Baava");
		lblMaalaacaaBaava.setForeground(Color.WHITE);
		lblMaalaacaaBaava.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblMaalaacaaBaava.setBounds(42, 152, 86, 24);
		getContentPane().add(lblMaalaacaaBaava);

		txtItemRate = new JTextField();
		txtItemRate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if (!txtItemRate.getText().equals("") && c == KeyEvent.VK_ENTER) {
					try {
						Float.parseFloat(txtItemRate.getText());
					} catch (Exception e2) {
						showError("Enter Valid Amount Of rate");
						txtItemRate.requestFocus();
						txtItemRate.selectAll();
						return;
					}

					btnSave.requestFocus();
				}
			}
		});
		lblMaalaacaaBaava.setLabelFor(txtItemRate);
		txtItemRate.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtItemRate.setColumns(10);
		txtItemRate.setBounds(138, 152, 283, 35);
		getContentPane().add(txtItemRate);

		btnSave = new JButton("saovh");
		btnSave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					btnSave.doClick();
				}
			}
		});
		btnSave.addActionListener(e -> save());
		btnSave.setBackground(new Color(50, 205, 50));
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnSave.setBounds(50, 232, 90, 35);
		getContentPane().add(btnSave);

		JButton btnClear = new JButton("@laIAr");
		btnClear.addActionListener(e -> {
			clear();
		});
		btnClear.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear.setBackground(new Color(50, 205, 50));
		btnClear.setBounds(152, 232, 90, 35);
		getContentPane().add(btnClear);

		JButton btnUpdate = new JButton("ApaDoT");
		btnUpdate.addActionListener(e -> update());
		btnUpdate.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnUpdate.setBackground(new Color(50, 205, 50));
		btnUpdate.setBounds(260, 232, 90, 35);
		getContentPane().add(btnUpdate);

		JButton btnExit = new JButton("baahor");
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setBounds(368, 232, 90, 35);
		getContentPane().add(btnExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 272, 482, 390);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("SansSerif", Font.PLAIN, 16));
		table.setRowHeight(20);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);

		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Sr.No", "ItemName", "Rate" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(419);
		table.getColumnModel().getColumn(2).setPreferredWidth(109);
		scrollPane.setViewportView(table);

		model = (DefaultTableModel) table.getModel();
		loadData();
		setVisible(true);

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new AddItem();
	}

	private void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private int validateData() {
		try {
			if (txtItemName.getText().equals("")) {
				showError("Enter New Item Name");
				txtItemName.requestFocus();
				return 0;
			}
			if (txtItemRate.getText().equals("")) {
				showError("Enter Rate For New Item");
				txtItemRate.requestFocus();
				return 0;
			}
			return 1;
		} catch (Exception e) {
			showError(e.getMessage());
			return 0;

		}
	}

	private void save() {
		try {
			if (validateData() != 1)
				return;
			Item item = new Item();
			item.setId(id);
			System.out.println("Id=" + id);
			item.setItemName(txtItemName.getText());
			item.setRate(Float.parseFloat(txtItemRate.getText()));
			int flag = service.saveItem(item);
			if (flag == 1) {
				JOptionPane.showMessageDialog(this, "Item Save Success", "Success", JOptionPane.INFORMATION_MESSAGE);
				clear();
				id = 0;
				loadData();

			} else if (flag == 2) {
				JOptionPane.showMessageDialog(this, "Item Update Success", "Success", JOptionPane.INFORMATION_MESSAGE);
				clear();
				id = 0;
				loadData();
			} else
				showError("Item Not Saved");

		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private void clear() {
		txtItemName.setText("");
		txtItemRate.setText("");
		id = 0;
	}

	private void loadData() {
		try {
			model.setRowCount(0);
			Iterator<Item> it = service.getAllItem().iterator();
			Item item;
			while (it.hasNext()) {
				item = it.next();
				model.addRow(new Object[] { item.getId(), htmstart + item.getItemName(), item.getRate() });
			}

		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private void update() {
		try {
			int row = table.getSelectedRow();
			if (row == -1) {
				return;
			}
			Item item = service.findItemById(Integer.parseInt(model.getValueAt(row, 0).toString()));
			txtItemName.setText(item.getItemName());
			txtItemRate.setText(Float.toString(item.getRate()));
			id = item.getId();
			service.getSession().close();
		} catch (Exception e) {
			showError(e.getMessage());
			service.getSession().close();
		}
	}
}
