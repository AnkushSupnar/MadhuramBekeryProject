package com.ankush.view.report;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.ankush.entities.GodownStock;
import com.ankush.service.service.GodownService;
import com.ankush.service.serviceImpl.GodownServiceImpl;
import com.github.lgooddatepicker.components.DatePicker;

public class GodownStockFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatePicker date;
	private JTable table;
	private DefaultTableModel model;
	private GodownService service;

	public GodownStockFrame() {
		this.service = new GodownServiceImpl();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Godown Stock Report");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(605, 726);
		setResizable(false);
		setLocation(200, 0);
		getContentPane().setBackground(new Color(1, 87, 155));
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("idnaaMk ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(34, 31, 49, 24);
		getContentPane().add(lblNewLabel);

		date = new DatePicker();
		date.setDateToToday();
		date.setBounds(90, 28, 193, 35);
		getContentPane().add(date);

		JButton btnShow = new JButton("paha");
		btnShow.addActionListener(e -> loadData());
		btnShow.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnShow.setBounds(293, 28, 89, 35);
		getContentPane().add(btnShow);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 90, 588, 601);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setFont(new Font("Kiran", Font.PLAIN, 25));
		table.setRowHeight(25);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Sr.No", "Item Name", "Qty" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(364);
		table.getColumnModel().getColumn(2).setPreferredWidth(127);
		model = (DefaultTableModel) table.getModel();
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new GodownStockFrame();
	}

	private void loadData() {
		try {
			model.setRowCount(0);
			List<GodownStock> list = service.getGodownStock();
			int sr = 0;
			for (GodownStock gs : list) {
				model.addRow(new Object[] { ++sr, gs.getItemName(), gs.getQty() });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
