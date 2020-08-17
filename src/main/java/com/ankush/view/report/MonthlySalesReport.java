package com.ankush.view.report;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.ankush.entities.Bill;
import com.ankush.service.service.BillService;
import com.ankush.service.serviceImpl.BillServiceImpl;

public class MonthlySalesReport extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8255030888216193871L;
	private BillService billService;
	private JTable table;
	private DefaultTableModel model;
	private String htmstart = "<html><font face=\"kiran\" size=\"6\">";
	private JComboBox<String> comboBox;

	public MonthlySalesReport() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Monthly sales Report");
		this.billService = new BillServiceImpl();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(827, 726);
		setResizable(false);
		setLocation(200, 0);
		getContentPane().setBackground(new Color(1, 87, 155));
		getContentPane().setLayout(null);

		JLabel lblMaihnaa = new JLabel("maihnaa");
		lblMaihnaa.setForeground(Color.WHITE);
		lblMaihnaa.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblMaihnaa.setBounds(18, 25, 49, 24);
		getContentPane().add(lblMaihnaa);

		JButton btnShow = new JButton("paha");
		btnShow.addActionListener(e -> loadData());
		btnShow.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnShow.setBounds(254, 20, 90, 35);
		getContentPane().add(btnShow);

		comboBox = new JComboBox<>();
		comboBox.setFont(new Font("Kiran", Font.PLAIN, 25));
		comboBox.setModel(
				new DefaultComboBoxModel<String>(new String[] { "jaanaovaarI", "foba`uvaarI", "maaca-", "eipa`la",
						"mao", "jaUna", "jaUlaO", "Aa^gasT", "sapToMbar", "Aa^@Taobar", "naaovhoMbar", "iDsaoMbar" }));
		comboBox.setBounds(72, 25, 170, 35);
		getContentPane().add(comboBox);

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

	public void showError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);

	}

	public void loadData() {
		try {
			model.setRowCount(0);
			double totalBill = 0f;
			int sr = 0;
			List<Bill> list = billService.getMonthWiseBills(comboBox.getSelectedIndex() + 1);
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
			showError(e.getMessage());
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new MonthlySalesReport();

	}
}
