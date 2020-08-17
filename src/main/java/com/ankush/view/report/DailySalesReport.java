package com.ankush.view.report;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.ankush.entities.Bill;
import com.ankush.service.service.BillService;
import com.ankush.service.serviceImpl.BillServiceImpl;
import com.github.lgooddatepicker.components.DatePicker;

public class DailySalesReport extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatePicker date;
	private JTable table;
	private DefaultTableModel model;
	private BillService billService;
	private String htmstart = "<html><font face=\"kiran\" size=\"6\">";

	public DailySalesReport() {

		this.billService = new BillServiceImpl();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(827, 726);
		setResizable(false);
		setLocation(200, 0);
		setTitle("Daily sales Report");
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(1, 87, 155));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 1, true));
		panel.setBackground(new Color(1, 87, 155));
		panel.setBounds(0, 0, 810, 69);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("idnaaMk ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(10, 24, 49, 24);
		panel.add(lblNewLabel);

		date = new DatePicker();
		date.setDateToToday();
		date.setBounds(60, 15, 175, 35);
		panel.add(date);

		JButton btnShow = new JButton("paha");
		btnShow.addActionListener(e -> loadReport());
		btnShow.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnShow.setBounds(246, 15, 90, 35);
		panel.add(btnShow);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 81, 810, 610);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("SansSerif", Font.PLAIN, 16));
		table.setShowGrid(true);
		table.setRowHeight(25);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "Sr.No", "BillNo", "Amount", "Paymode", "User Name", "Counter" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		table.getColumnModel().getColumn(2).setPreferredWidth(93);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(129);
		table.getColumnModel().getColumn(5).setPreferredWidth(113);
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
		new DailySalesReport();
	}

	private void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void loadReport() {
		try {
			model.setRowCount(0);
			LocalDate d1 = date.getDate();
			List<Bill> list = billService.getdateWiseBills(d1);
			int sr = 0;
			double totalBill = 0f;
			for (Bill b : list) {
				totalBill = totalBill + b.getAmount();
				model.addRow(new Object[] { ++sr, b.getBillNo(), b.getAmount(), b.getPayMode(),
						htmstart + b.getLogin().getUserName(), htmstart + b.getCounter().getCounterName() });
			}
			model.addRow(new Object[] {});
			model.addRow(new Object[] {});
			model.addRow(new Object[] {});
			model.addRow(new Object[] { "", "Total", totalBill });

		} catch (Exception e) {
			e.printStackTrace();
			showError(e.getMessage());
		}
	}
}
