package com.ankush.view.home;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.ankush.view.report.CounterGoodsReport;
import com.ankush.view.report.DailySalesReport;
import com.ankush.view.report.GodownStockFrame;
import com.ankush.view.report.MonthlySalesReport;
import com.ankush.view.report.StockReport;

public class ReportPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5851893177303893884L;

	public ReportPanel() {
		setBackground(new Color(1, 87, 155));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1062, 38);
		panel.setBackground(new Color(0, 0, 102));

		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(1020, -6, 27, 52);
		panel.add(lblNewLabel);

		JButton btnDailySalesReport = new JButton("DolaI saolsa irpaaoT-");
		btnDailySalesReport.addActionListener(e -> new DailySalesReport());
		btnDailySalesReport.setForeground(Color.WHITE);
		btnDailySalesReport.setBackground(new Color(0, 0, 102));
		btnDailySalesReport.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnDailySalesReport.setBounds(54, 102, 186, 74);
		add(btnDailySalesReport);

		JButton btnStockReport = new JButton("sTa^k  irpaaoT-");
		btnStockReport.addActionListener(e -> new StockReport());
		btnStockReport.setForeground(Color.WHITE);
		btnStockReport.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnStockReport.setBackground(new Color(0, 0, 102));
		btnStockReport.setBounds(547, 102, 186, 74);
		add(btnStockReport);

		JButton btnMonthlySalesReport = new JButton("maihnaa saolsa irpaaoT-");
		btnMonthlySalesReport.addActionListener(e -> new MonthlySalesReport());
		btnMonthlySalesReport.setForeground(Color.WHITE);
		btnMonthlySalesReport.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnMonthlySalesReport.setBackground(new Color(0, 0, 102));
		btnMonthlySalesReport.setBounds(788, 102, 186, 74);
		add(btnMonthlySalesReport);

		JButton btnGodownStock = new JButton("iSallak maala");
		btnGodownStock.addActionListener(e -> new GodownStockFrame());
		btnGodownStock.setForeground(Color.WHITE);
		btnGodownStock.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnGodownStock.setBackground(new Color(0, 0, 102));
		btnGodownStock.setBounds(301, 102, 186, 74);
		add(btnGodownStock);

		JButton btnSendReport = new JButton("paazvalaolaa maala irpaaoT-");
		btnSendReport.addActionListener(e -> new CounterGoodsReport());
		btnSendReport.setForeground(Color.WHITE);
		btnSendReport.setFont(new Font("Kiran", Font.PLAIN, 27));
		btnSendReport.setBackground(new Color(0, 0, 102));
		btnSendReport.setBounds(54, 200, 186, 74);
		add(btnSendReport);

	}
}
