package com.ankush.view.report;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.ankush.entities.FromFactory;
import com.ankush.entities.FromFactoryTransaction;
import com.ankush.service.service.CounterService;
import com.ankush.service.service.FromFactoryService;
import com.ankush.service.serviceImpl.CounterServiceImpl;
import com.ankush.service.serviceImpl.FromFactoryServiceImpl;
import com.github.lgooddatepicker.components.DatePicker;

public class CounterGoodsReport extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cmbCounter;
	private DatePicker date;
	private CounterService counterService;
	private FromFactoryService fromFactoryService;
	private JTable table;
	private DefaultTableModel model;

	public CounterGoodsReport() {
		this.counterService = new CounterServiceImpl();
		this.fromFactoryService = new FromFactoryServiceImpl();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Godown Stock Report");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(656, 726);
		setResizable(false);
		setLocation(200, 0);
		getContentPane().setBackground(new Color(1, 87, 155));
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("ka{MTrcao naava");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(34, 31, 93, 24);
		getContentPane().add(lblNewLabel);

		cmbCounter = new JComboBox<>(new Vector<String>(counterService.getAllCounterName()));
		cmbCounter.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbCounter.setBounds(34, 62, 225, 35);
		getContentPane().add(cmbCounter);

		JLabel lblNewLabel1 = new JLabel("idnaaMk ");
		lblNewLabel1.setForeground(Color.WHITE);
		lblNewLabel1.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel1.setBounds(269, 31, 49, 24);
		getContentPane().add(lblNewLabel1);

		date = new DatePicker();
		date.setDateToToday();
		date.setBounds(269, 62, 193, 35);
		getContentPane().add(date);

		JButton btnShow = new JButton("paha");
		btnShow.setBackground(new Color(0, 153, 51));
		btnShow.addActionListener(e -> showData());
		btnShow.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnShow.setBounds(37, 124, 90, 35);
		getContentPane().add(btnShow);

		JButton btnClear = new JButton("@laIAr");
		btnClear.setBackground(new Color(0, 153, 51));
		btnClear.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear.setBounds(169, 124, 90, 35);
		getContentPane().add(btnClear);

		JButton btnExit = new JButton("baahor");
		btnExit.setBackground(new Color(255, 0, 51));
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBounds(285, 124, 90, 35);
		getContentPane().add(btnExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 171, 627, 520);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Kiran", Font.PLAIN, 25));
		table.setRowHeight(25);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Sr.No", "Item Name", "Qty" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(63);
		table.getColumnModel().getColumn(1).setPreferredWidth(331);
		table.getColumnModel().getColumn(2).setPreferredWidth(103);
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
		new CounterGoodsReport();

	}

	void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void showData() {
		try {
			if (cmbCounter.getSelectedIndex() == -1) {
				showError("Select Counter Name");
				cmbCounter.requestFocus();

				return;
			}
			if (date.getDate() == null) {
				showError("Select Date");
				date.requestFocus();
				return;
			}
			model.setRowCount(0);
			int sr = 0;
			int counterId = counterService.getCounterIdByName(cmbCounter.getSelectedItem().toString());
			LocalDate date1 = date.getDate();
			List<FromFactory> list = fromFactoryService.getDateWiseFromFactory(date1);
			for (FromFactory f : list) {
				if (f.getCounter().getId() == counterId) {
					for (FromFactoryTransaction tr : f.getTransactions()) {
						model.addRow(new Object[] { ++sr, tr.getItem(), tr.getQty() });
					}
				}
			}
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}
}
