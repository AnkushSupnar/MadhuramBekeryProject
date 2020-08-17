package com.ankush.view.home;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

import com.ankush.entities.Login;
import com.ankush.service.service.LoginService;
import com.ankush.service.serviceImpl.LoginServiceImpl;

public class LoginFrame extends JFrame {
	private static final long serialVersionUID = -1795510944410888881L;
	private JPanel panel;
	private JComboBox<String> cmbUserName;
	private JPasswordField txtPassword;
	private LoginService service;
	private JComboBox<String> cmbCounter;

	public LoginFrame() {
		this.service = (LoginService) new LoginServiceImpl();
		this.setTitle("Login Frame");
		this.setLocation(200, 100);
		this.getContentPane().setBackground(new Color(1, 87, 155));
		this.setDefaultCloseOperation(2);
		this.setSize(449, 427);
		this.getContentPane().setLayout(null);

		final ImageIcon icon = new ImageIcon("D:\\Madhuram\\images\\user.png", "a pretty but meaningless splat");
		final JLabel lblNewLabel_1 = new JLabel("", icon, 0);
		lblNewLabel_1.setBounds(132, 20, 169, 95);
		this.getContentPane().add(lblNewLabel_1);

		panel = new JPanel();
		panel.setBackground(new Color(1, 87, 155));
		this.panel.setBounds(43, 82, 345, 300);
		this.getContentPane().add(this.panel);
		this.panel.setLayout(null);
		final JLabel lblYaujarNaoma = new JLabel("yaujar naoma");
		lblYaujarNaoma.setForeground(Color.WHITE);
		lblYaujarNaoma.setFont(new Font("Kiran", 0, 25));
		lblYaujarNaoma.setBounds(40, 107, 63, 24);
		this.panel.add(lblYaujarNaoma);
		(this.cmbUserName = new JComboBox<String>(new Vector<String>(this.service.getAllUserName())))
				.setFont(new Font("Kiran", 0, 25));
		this.cmbUserName.setBounds(115, 102, 193, 35);
		this.panel.add(this.cmbUserName);
		this.cmbUserName.setSelectedIndex(-1);
		final JLabel lblPaasavad = new JLabel("paasavaD-");
		lblPaasavad.setForeground(Color.WHITE);
		lblPaasavad.setFont(new Font("Kiran", 0, 25));
		lblPaasavad.setBounds(52, 168, 51, 24);
		this.panel.add(lblPaasavad);
		(this.txtPassword = new JPasswordField()).setFont(new Font("SansSerif", 1, 16));
		this.txtPassword.setBounds(115, 162, 193, 35);
		this.panel.add(this.txtPassword);
		final JButton btnLogin = new JButton("laa^gaIna");
		btnLogin.addActionListener(e -> this.login());
		btnLogin.setBackground(new Color(50, 205, 50));
		btnLogin.setFont(new Font("Kiran", 0, 25));
		btnLogin.setBounds(40, 235, 90, 35);
		this.panel.add(btnLogin);
		final JButton btnExit = new JButton("baahor ");
		btnExit.addActionListener(e -> System.exit(0));
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setFont(new Font("Kiran", 0, 25));
		btnExit.setBounds(218, 239, 90, 35);
		this.panel.add(btnExit);
		final JLabel lblKamtr = new JLabel("ka{MTr");
		lblKamtr.setForeground(Color.WHITE);
		lblKamtr.setFont(new Font("Kiran", 0, 25));
		lblKamtr.setBounds(50, 49, 48, 24);
		this.panel.add(lblKamtr);
		(this.cmbCounter = new JComboBox<String>(new Vector<String>(this.service.getActiveCounter())))
				.setSelectedIndex(-1);
		this.cmbCounter.setFont(new Font("Kiran", 0, 25));
		this.cmbCounter.setBounds(115, 44, 193, 35);
		this.panel.add(this.cmbCounter);
		// this.setVisible(true);
	}

	public static void main(final String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new LoginFrame();
	}

	private void showError(final String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", 0);
	}

	private int validateData() {
		try {
			if (this.cmbUserName.getSelectedIndex() == -1) {
				this.showError("Please select User name");
				this.cmbUserName.requestFocus();
				this.cmbUserName.showPopup();
				return 0;
			}
			if (this.txtPassword.getPassword().length == 0) {
				this.showError("Enter Your Password");
				this.txtPassword.requestFocus();
				return 0;
			}
			if (this.cmbCounter.getSelectedIndex() == -1) {
				this.showError("Select YOur Counter");
				this.cmbCounter.requestFocus();
				this.cmbCounter.showPopup();
				return 0;
			}
			return 1;
		} catch (Exception e) {
			this.showError(e.getMessage());
			return 0;
		}
	}

	private void login() {
		try {
			if (this.validateData() != 1) {
				return;
			}
			final Login l = this.service.getLoginByName(this.cmbUserName.getSelectedItem().toString());
			if (l == null) {
				this.showError("Enter correct User Name");
			}
			if (l.getPassword().equals(String.valueOf(this.txtPassword.getPassword()))) {
				JOptionPane.showMessageDialog(this, "Welcome", "Success", 1);
				this.service.setLoginStatus(l.getId(), this.cmbCounter.getSelectedItem().toString());
				new Home(l.getId());
				this.dispose();
			} else {
				this.showError("Enter Correct Password");
			}
		} catch (Exception e) {
			this.showError(e.getMessage());
		}
	}
}