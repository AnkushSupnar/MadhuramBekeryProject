package com.ankush.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.ankush.entities.Employee;
import com.ankush.entities.Login;
import com.ankush.service.service.LoginService;
import com.ankush.service.serviceImpl.EmployeeServiceImpl;
import com.ankush.service.serviceImpl.LoginServiceImpl;

public class CreateUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8622985578177344417L;
	private JPanel panel;
	private JComboBox<String> cmbEmployee;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JLabel label;
	private JPasswordField txtPassword2;
	private EmployeeServiceImpl employeeService;
	private LoginService service;

	public CreateUser() {
		this.employeeService = new EmployeeServiceImpl();
		this.service = new LoginServiceImpl();
		setLocation(300, 200);
		setResizable(false);
		getContentPane().setFont(new Font("Kiran", Font.PLAIN, 16));
		getContentPane().setBackground(new Color(1, 87, 155));
		getContentPane();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Create User");
		setSize(500, 529);
		getContentPane().setLayout(null);

		ImageIcon icon = new ImageIcon("D:\\Madhuram\\images\\user.png", "a pretty but meaningless splat");

		JLabel lblNewLabel_1 = new JLabel("", icon, JLabel.CENTER);
		lblNewLabel_1.setBounds(161, 32, 169, 95);
		getContentPane().add(lblNewLabel_1);

		panel = new JPanel() {
			/**
			* 
			*/
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Dimension arcs = new Dimension(15, 15);
				int width = getWidth();
				int height = getHeight();
				Graphics2D graphics = (Graphics2D) g;
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Draws the rounded opaque panel with borders.
				graphics.setColor(getBackground());
				graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);// paint background
				// graphics.setColor(new Color(255, 255, 255));
				graphics.setColor(getForeground());
				graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);// paint border
			}
		};
		// panel.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		panel.setBackground(new Color(1, 87, 155));
		panel.setBounds(45, 82, 396, 382);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("kamagaaracao naava");
		lblNewLabel.setBounds(167, 64, 103, 24);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));

		cmbEmployee = new JComboBox<>();
		cmbEmployee.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbEmployee.setBounds(66, 100, 268, 35);
		panel.add(cmbEmployee);
		loadEmployeeName();
		cmbEmployee.setSelectedIndex(-1);

		JLabel lblYaujarNaoma = new JLabel("yaujar naoma");
		lblYaujarNaoma.setForeground(Color.WHITE);
		lblYaujarNaoma.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblYaujarNaoma.setBounds(66, 148, 63, 24);
		panel.add(lblYaujarNaoma);

		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtUserName.setBounds(141, 147, 193, 35);
		panel.add(txtUserName);
		txtUserName.setColumns(10);

		JLabel lblPaasavad = new JLabel("paasavaD-");
		lblPaasavad.setForeground(Color.WHITE);
		lblPaasavad.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblPaasavad.setBounds(78, 206, 51, 24);
		panel.add(lblPaasavad);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("SansSerif", Font.BOLD, 16));
		txtPassword.setBounds(141, 205, 193, 35);
		panel.add(txtPassword);

		label = new JLabel("paasavaD-");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Kiran", Font.PLAIN, 25));
		label.setBounds(78, 260, 51, 24);
		panel.add(label);

		txtPassword2 = new JPasswordField();
		txtPassword2.setFont(new Font("SansSerif", Font.BOLD, 16));
		txtPassword2.setBounds(141, 259, 193, 35);
		panel.add(txtPassword2);

		JButton btnSave = new JButton("saovh");
		btnSave.addActionListener(e -> save());
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnSave.setBounds(26, 322, 90, 35);
		panel.add(btnSave);

		JButton btnClear = new JButton("i@laAr");
		btnClear.addActionListener(e -> clear());
		btnClear.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear.setBounds(153, 322, 90, 35);
		panel.add(btnClear);

		JButton btnExit = new JButton("baahor");
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBounds(278, 322, 90, 35);
		panel.add(btnExit);

		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new CreateUser();
	}

	public void loadEmployeeName() {
		try {
			List<Object[]> list = employeeService.getAllEmployeeFullName();
			for (Object[] person : list) {
				String fname = person[0].toString();
				String mname = person[1].toString();
				String lname = person[2].toString();
				cmbEmployee.addItem(fname + " " + mname + " " + lname);
				// System.out.println(fname + " " + mname + " " + lname);
			}
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	public void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private int validateData() {
		try {
			if (cmbEmployee.getSelectedIndex() == -1) {
				showError("Select Employee");
				cmbEmployee.requestFocus();
				cmbEmployee.showPopup();
				return 0;
			}
			if (txtUserName.getText().equals("")) {
				showError("Enter User Name");
				txtUserName.requestFocus();
				return 0;
			}
			if (txtPassword.getPassword().length == 0) {
				showError("Enter Password");
				txtPassword.requestFocus();
				return 0;
			}
			if (txtPassword2.getPassword().length == 0) {
				showError("Enter Password");
				txtPassword2.requestFocus();
				return 0;
			}
			if (!String.valueOf(txtPassword.getPassword()).equals(String.valueOf(txtPassword2.getPassword()))) {
				showError("Password not Match !!!!");
				txtPassword2.requestFocus();
				return 0;
			}
			return 1;
		} catch (Exception e) {
			showError(e.getMessage());
			return 0;
		}
	}

	private void save() {
		try {
			if (validateData() == 0) {
				return;
			}
			Employee emp = employeeService.getEmployeeIdUsingFullName(cmbEmployee.getSelectedItem().toString());
			Login login = new Login();
			login.setEmployee(emp);
			login.setPassword(String.valueOf(txtPassword.getPassword()));
			login.setUserName(txtUserName.getText());
			login.setStatus("inactive");
			if (service.saveUser(login) == 1) {
				JOptionPane.showMessageDialog(this, "Record Save Success", "Success", JOptionPane.INFORMATION_MESSAGE);
				clear();
			} else if (service.saveUser(login) == 2) {
				JOptionPane.showMessageDialog(this, "Already Having Account for this User", "Exist",
						JOptionPane.INFORMATION_MESSAGE);
				// clear();
			} else
				showError("Record Not Saved");

		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private void clear() {
		cmbEmployee.setSelectedIndex(-1);
		txtUserName.setText("");
		txtPassword.setText("");
		txtPassword2.setText("");
	}
}
