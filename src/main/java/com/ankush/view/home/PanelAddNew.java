package com.ankush.view.home;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.ankush.view.AddCounter;
import com.ankush.view.AddEmployee;
import com.ankush.view.AddItem;
import com.ankush.view.AddNewCustomer;
import com.ankush.view.CreateUser;

public class PanelAddNew extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4758705662889896080L;

	public PanelAddNew() {
		setBackground(new Color(1, 87, 155));
		setLayout(null);

		JButton btnAddCounter = new JButton("naivana ka{MTr");
		btnAddCounter.addActionListener(e -> new AddCounter());
		btnAddCounter.setForeground(Color.WHITE);
		btnAddCounter.setBackground(new Color(0, 0, 102));
		btnAddCounter.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnAddCounter.setBounds(54, 102, 186, 74);
		add(btnAddCounter);

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

		JButton btnAddEmployee = new JButton("naivana kamagaar");
		btnAddEmployee.addActionListener(e -> new AddEmployee());
		btnAddEmployee.setForeground(Color.WHITE);
		btnAddEmployee.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnAddEmployee.setBackground(new Color(0, 0, 102));
		btnAddEmployee.setBounds(310, 102, 186, 74);
		add(btnAddEmployee);

		JButton btnAddItem = new JButton("naivana maala");
		btnAddItem.addActionListener(e -> new AddItem());
		btnAddItem.setForeground(Color.WHITE);
		btnAddItem.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnAddItem.setBackground(new Color(0, 0, 102));
		btnAddItem.setBounds(579, 102, 186, 74);
		add(btnAddItem);

		JButton btnAddCustomer = new JButton("naivana ga`ahk");
		btnAddCustomer.addActionListener(e -> new AddNewCustomer());
		btnAddCustomer.setForeground(Color.WHITE);
		btnAddCustomer.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnAddCustomer.setBackground(new Color(0, 0, 102));
		btnAddCustomer.setBounds(54, 273, 186, 74);
		add(btnAddCustomer);

		JButton btnAddUser = new JButton("naivana yaujar");
		btnAddUser.addActionListener(e -> new CreateUser());
		btnAddUser.setForeground(Color.WHITE);
		btnAddUser.setFont(new Font("Kiran", Font.PLAIN, 30));
		btnAddUser.setBackground(new Color(0, 0, 102));
		btnAddUser.setBounds(310, 273, 186, 74);
		add(btnAddUser);
	}
}
