package com.ankush.view.home;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.ankush.view.BillingFrame;
import com.ankush.view.GodownFrame;
import com.ankush.view.fromFactory.FromFactory;
import com.ankush.view.toCounter.ToCounterFrame;

public class PanelDailyTrnasaction extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5203266888268389326L;

	public PanelDailyTrnasaction(int id) {
		setBackground(new Color(1, 87, 155));
		setLayout(null);

		JButton btnDailyBilling = new JButton("ibailaMga");
		btnDailyBilling.addActionListener(e -> new BillingFrame(id));
		btnDailyBilling.setForeground(Color.WHITE);
		btnDailyBilling.setBackground(new Color(0, 0, 102));
		btnDailyBilling.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnDailyBilling.setBounds(54, 102, 186, 74);
		add(btnDailyBilling);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 102));
		panel.setBounds(0, 0, 1062, 38);

		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(1020, -6, 27, 52);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("raojacao vyavahar");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Kiran", Font.BOLD, 30));
		lblNewLabel_1.setBounds(421, 6, 145, 29);
		panel.add(lblNewLabel_1);

		JButton btnToCounter = new JButton("Aalaolaa maala");
		btnToCounter.addActionListener(e -> new ToCounterFrame(id));
		btnToCounter.setForeground(Color.WHITE);
		btnToCounter.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnToCounter.setBackground(new Color(0, 0, 102));
		btnToCounter.setBounds(579, 102, 186, 74);
		add(btnToCounter);

		JButton btnFromFactory = new JButton("paazvalaolaa maala");
		btnFromFactory.addActionListener(e -> new FromFactory());
		btnFromFactory.setForeground(Color.WHITE);
		btnFromFactory.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnFromFactory.setBackground(new Color(0, 0, 102));
		btnFromFactory.setBounds(848, 102, 186, 74);
		add(btnFromFactory);

		JButton btnGodown = new JButton("tayaar maala");
		btnGodown.addActionListener(e -> new GodownFrame());
		btnGodown.setForeground(Color.WHITE);
		btnGodown.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnGodown.setBackground(new Color(0, 0, 102));
		btnGodown.setBounds(305, 102, 186, 74);
		add(btnGodown);
		if (id != 1) {
			btnFromFactory.setVisible(false);
		}
	}
}
