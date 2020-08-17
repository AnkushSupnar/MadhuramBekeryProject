package com.ankush.view.home;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class Splash extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1641361024041553952L;

	public JProgressBar progressBar;

	public Splash() {
		setUndecorated(true);
		// setLocationRelativeTo(null);
		setSize(463, 335);
		setLocation(350, 200);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(0, 0, 566, 337);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Madhuram");
		lblNewLabel.setFont(new Font("Vivaldi", Font.BOLD, 60));
		lblNewLabel.setForeground(new Color(0, 255, 255));
		lblNewLabel.setBounds(20, 27, 281, 41);
		panel.add(lblNewLabel);

		JLabel lblBekers = new JLabel("Bakery");
		lblBekers.setBackground(new Color(255, 255, 255));
		lblBekers.setForeground(new Color(192, 192, 192));
		lblBekers.setFont(new Font("Sitka Banner", Font.ITALIC, 35));
		lblBekers.setBounds(190, 59, 111, 51);
		panel.add(lblBekers);

		JLabel lblManagementSystem = new JLabel("Management System");
		lblManagementSystem.setForeground(Color.LIGHT_GRAY);
		lblManagementSystem.setFont(new Font("Sitka Banner", Font.ITALIC, 35));
		lblManagementSystem.setBackground(Color.WHITE);
		lblManagementSystem.setBounds(75, 107, 389, 51);
		panel.add(lblManagementSystem);

		JLabel lblDevelopedBy = new JLabel("Developed By:");
		lblDevelopedBy.setForeground(new Color(128, 128, 0));
		lblDevelopedBy.setFont(new Font("Sitka Banner", Font.ITALIC, 20));
		lblDevelopedBy.setBackground(Color.WHITE);
		lblDevelopedBy.setBounds(10, 236, 110, 26);
		panel.add(lblDevelopedBy);

		JLabel lblAnkushSupnar = new JLabel("Ankush Supnar");
		lblAnkushSupnar.setForeground(Color.LIGHT_GRAY);
		lblAnkushSupnar.setFont(new Font("Sitka Banner", Font.ITALIC, 20));
		lblAnkushSupnar.setBackground(Color.WHITE);
		lblAnkushSupnar.setBounds(20, 260, 268, 26);
		panel.add(lblAnkushSupnar);

		JLabel lblContact = new JLabel("Contact: 8329394603 / 9960855742");
		lblContact.setForeground(Color.LIGHT_GRAY);
		lblContact.setFont(new Font("Sitka Banner", Font.ITALIC, 20));
		lblContact.setBackground(Color.WHITE);
		lblContact.setBounds(20, 278, 349, 26);
		panel.add(lblContact);

		JLabel lblEmailAnkushsupnargmailcom = new JLabel("Email: ankushsupnar@gmail.com");
		lblEmailAnkushsupnargmailcom.setForeground(Color.LIGHT_GRAY);
		lblEmailAnkushsupnargmailcom.setFont(new Font("Sitka Banner", Font.ITALIC, 20));
		lblEmailAnkushsupnargmailcom.setBackground(Color.WHITE);
		lblEmailAnkushsupnargmailcom.setBounds(20, 297, 349, 26);
		panel.add(lblEmailAnkushsupnargmailcom);

		progressBar = new JProgressBar();

		progressBar.setBackground(new Color(0, 255, 0));
		progressBar.setBounds(0, 318, 464, 20);
		panel.add(progressBar);

		setVisible(true);

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			Splash sp = new Splash();
			LoginFrame l = null;
			for (int i = 0; i <= 100; i++) {
				Thread.sleep(20);
				sp.progressBar.setValue(i);
				sp.progressBar.setString(Integer.toString(i));
				if (i == 50) {
					l = new LoginFrame();
				}

				if (i == 100) {
					// new LoginFrame();
					l.setVisible(true);
					sp.dispose();
				}
			}
		} catch (Exception e) {

		}

	}
}
