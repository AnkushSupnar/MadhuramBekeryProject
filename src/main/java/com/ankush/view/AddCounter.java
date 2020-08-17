package com.ankush.view;

import java.awt.Color;
import java.awt.Font;
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

import com.ankush.entities.Counter;
import com.ankush.service.service.CounterService;
import com.ankush.service.serviceImpl.CounterServiceImpl;

public class AddCounter extends JFrame {
	/**
	 * 
	 */

	private static final long serialVersionUID = -1405032805576901453L;

	private JTextField txtCounterName;
	private CounterService service;
	private JTextField txtDescription;
	private JTable table;
	private DefaultTableModel model;
	private String htmstart = "<html><font face=\"kiran\" size=\"6\">";
	private int id;

	public AddCounter() {
		this.service = new CounterServiceImpl();
		this.id = 0;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(450, 514);
		setTitle("Add Counter");
		setResizable(false);
		getContentPane().setBackground(new Color(1, 87, 155));
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ka{MTrcao naava");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(74, 57, 93, 24);
		getContentPane().add(lblNewLabel);

		txtCounterName = new JTextField();
		txtCounterName.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtCounterName.setBounds(172, 55, 191, 35);
		getContentPane().add(txtCounterName);
		txtCounterName.setColumns(10);

		JLabel lblKamtrcaiMaaihtai = new JLabel("ka{MTrcaI maaihtaI");
		lblKamtrcaiMaaihtai.setForeground(Color.WHITE);
		lblKamtrcaiMaaihtai.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblKamtrcaiMaaihtai.setBounds(48, 120, 119, 24);
		getContentPane().add(lblKamtrcaiMaaihtai);

		txtDescription = new JTextField();
		txtDescription.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtDescription.setColumns(10);
		txtDescription.setBounds(172, 115, 191, 35);
		getContentPane().add(txtDescription);

		JButton btnSave = new JButton("saovh");
		btnSave.addActionListener(e -> save());
		btnSave.setBackground(new Color(0, 153, 51));
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnSave.setBounds(25, 186, 90, 35);
		getContentPane().add(btnSave);

		JButton btnExit = new JButton("baahor");
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBackground(new Color(204, 0, 51));
		btnExit.setBounds(330, 186, 90, 35);
		getContentPane().add(btnExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1, 233, 441, 246);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setRowHeight(25);
		table.setShowGrid(true);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Sr.No", "Counter Name", "Description" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(241);
		scrollPane.setViewportView(table);

		JButton btnClear = new JButton("i@laAr");
		btnClear.addActionListener(e -> clear());
		btnClear.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear.setBackground(new Color(0, 153, 51));
		btnClear.setBounds(127, 186, 90, 35);
		getContentPane().add(btnClear);

		JButton btnUpdate = new JButton("ApaDoT");
		btnUpdate.addActionListener(e -> update());
		btnUpdate.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnUpdate.setBackground(new Color(0, 153, 51));
		btnUpdate.setBounds(229, 186, 90, 35);
		getContentPane().add(btnUpdate);

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
		new AddCounter();
	}

	private void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private int validateData() {
		try {
			if (txtCounterName.getText().equals("")) {
				showError("Enter Counter Name");
				txtCounterName.requestFocus();
				return 0;
			} else if (txtDescription.getText().equals("")) {
				showError("Enter Counter Description");
				txtDescription.requestFocus();
				return 0;
			} else
				return 1;
		} catch (Exception e) {
			showError(e.getMessage());
			return 0;
		}
	}

	private void save() {
		try {
			if (validateData() == 0)
				return;

			Counter counter = new Counter();
			counter.setId(id);
			counter.setCounterName(txtCounterName.getText());
			counter.setDescription(txtDescription.getText());
			int flag = service.saveCounter(counter);
			if (flag == 1) {
				JOptionPane.showMessageDialog(this, "Counter Save Success", "Success", JOptionPane.INFORMATION_MESSAGE);
				loadData();
				clear();
			}
			if (flag == 2) {
				JOptionPane.showMessageDialog(this, "Counter update Success", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				loadData();
				clear();
			}

		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private void loadData() {
		try {
			model.setRowCount(0);
			Iterator<Counter> it = service.getAllCounter().iterator();
			Counter c;
			while (it.hasNext()) {
				c = it.next();
				model.addRow(new Object[] { c.getId(), htmstart + c.getCounterName(), htmstart + c.getDescription() });
			}
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private void clear() {
		txtCounterName.setText("");
		txtDescription.setText("");

	}

	private void update() {
		try {
			int row = table.getSelectedRow();
			if (row == -1) {
				return;
			}
			Counter c = service.getCounterById(Integer.parseInt(model.getValueAt(row, 0).toString()));
			System.out.println(c);
			id = c.getId();
			txtCounterName.setText(c.getCounterName());
			txtDescription.setText(c.getDescription());
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}
}
