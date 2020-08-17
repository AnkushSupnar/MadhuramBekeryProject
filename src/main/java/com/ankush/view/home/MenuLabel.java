package com.ankush.view.home;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class MenuLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9064940109114768467L;

	public MenuLabel(String name) {
		setForeground(Color.WHITE);
		setText(name);
		setFont(new Font("Kiran", Font.PLAIN, 30));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setForeground(Color.WHITE);
			}
		});

	}
}