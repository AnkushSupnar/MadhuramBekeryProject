package com.ankush.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.ankush.entities.Address;
import com.ankush.entities.Employee;
import com.ankush.service.serviceImpl.EmployeeServiceImpl;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public class AddEmployee extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4258512810183033676L;
	private JTextField txtFname;
	private JTextField txtMname;
	private JTextField txtLname;
	private JRadioButton rdbtnMale, rdbtnFemale;
	private JTextField txtContact, txtRoomNo, txtBuilding, txtCity, txtTaluka, txtDistrict, txtPin;
	private ButtonGroup group;
	private JButton btnSave;
	private JTextField txtSalary;
	private JComboBox<String> cmbSalaryType;
	private JComboBox<String> cmbDesignation;
	private JTable table;
	private DefaultTableModel model;
	private DatePicker date;
	private EmployeeServiceImpl service;
	private int id;

	public AddEmployee() {
		this.service = new EmployeeServiceImpl();
		// this.id = service.getNewEmployeeId();
		this.id = 0;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1280, 575);
		setTitle("Add Employee");
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(1, 87, 155));
		panel.setBorder(new TitledBorder(new LineBorder(null, 1, true), "Personal Information", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBounds(10, 5, 500, 183);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("paihlao naava");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(10, 26, 71, 24);
		panel.add(lblNewLabel);

		JLabel lblMaqalaoNaava = new JLabel("maQalao naava");
		lblMaqalaoNaava.setForeground(Color.WHITE);
		lblMaqalaoNaava.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblMaqalaoNaava.setBounds(172, 26, 71, 24);
		panel.add(lblMaqalaoNaava);

		JLabel lblAadnaava = new JLabel("AaDnaava");
		lblAadnaava.setForeground(Color.WHITE);
		lblAadnaava.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblAadnaava.setBounds(337, 26, 71, 24);
		panel.add(lblAadnaava);

		txtFname = new JTextField();
		txtFname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (!txtFname.getText().equals("")) {
					if (ke.getKeyChar() == KeyEvent.VK_ENTER)
						txtMname.requestFocus();
				}
			}
		});
		txtFname.setFont(new Font("Kiran", Font.BOLD, 25));
		txtFname.setBounds(10, 54, 150, 35);
		panel.add(txtFname);
		txtFname.setColumns(10);

		txtMname = new JTextField();
		txtMname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (!txtMname.getText().equals("")) {
					if (ke.getKeyChar() == KeyEvent.VK_ENTER)
						txtLname.requestFocus();
				}
			}
		});
		txtMname.setFont(new Font("Kiran", Font.BOLD, 25));
		txtMname.setColumns(10);
		txtMname.setBounds(170, 54, 150, 35);
		panel.add(txtMname);

		txtLname = new JTextField();
		txtLname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (!txtLname.getText().equals("")) {
					if (ke.getKeyChar() == KeyEvent.VK_ENTER)
						rdbtnMale.requestFocus();
				}
			}
		});
		txtLname.setFont(new Font("Kiran", Font.BOLD, 25));
		txtLname.setColumns(10);
		txtLname.setBounds(335, 54, 150, 35);
		panel.add(txtLname);

		JLabel lblIlamga = new JLabel("ilaMga");
		lblIlamga.setForeground(Color.WHITE);
		lblIlamga.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblIlamga.setBounds(10, 100, 31, 24);
		panel.add(lblIlamga);

		rdbtnMale = new JRadioButton("paur]Ya");
		rdbtnMale.setForeground(Color.WHITE);
		rdbtnMale.setBackground(new Color(51, 153, 102));
		rdbtnMale.setFont(new Font("Kiran", Font.BOLD, 25));
		rdbtnMale.setBounds(6, 126, 65, 33);
		panel.add(rdbtnMale);

		rdbtnFemale = new JRadioButton("s~aI");
		rdbtnFemale.setForeground(Color.WHITE);
		rdbtnFemale.setFont(new Font("Kiran", Font.BOLD, 25));
		rdbtnFemale.setBackground(new Color(51, 153, 102));
		rdbtnFemale.setBounds(86, 126, 55, 33);
		panel.add(rdbtnFemale);

		group = new ButtonGroup();
		group.add(rdbtnMale);
		group.add(rdbtnFemale);
		JLabel lblMaaobaalaNam = new JLabel("maaobaa[la naM.");
		lblMaaobaalaNam.setForeground(Color.WHITE);
		lblMaaobaalaNam.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblMaaobaalaNam.setBounds(172, 101, 77, 24);
		panel.add(lblMaaobaalaNam);

		txtContact = new JTextField();

		txtContact.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if (!txtContact.getText().equals("") && txtContact.getText().length() == 10 && c == KeyEvent.VK_ENTER) {
					txtRoomNo.requestFocus();
				}
			}
		});
		txtContact.setFont(new Font("Kiran", Font.BOLD, 25));
		txtContact.setColumns(10);
		txtContact.setBounds(170, 129, 134, 35);
		panel.add(txtContact);

		DatePickerSettings dateSetting = new DatePickerSettings();
		dateSetting.setFontValidDate(new Font("Times", Font.PLAIN | Font.PLAIN, 12));

		date = new DatePicker(dateSetting);
		date.setDateToToday();
		date.setBounds(316, 129, 170, 35);
		panel.add(date);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Address Details", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel_1.setBackground(new Color(1, 87, 155));
		panel_1.setBounds(10, 195, 500, 183);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblRmaNam = new JLabel("r]ma naM.");
		lblRmaNam.setForeground(Color.WHITE);
		lblRmaNam.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblRmaNam.setBounds(10, 26, 46, 24);
		panel_1.add(lblRmaNam);

		txtRoomNo = new JTextField();
		txtRoomNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!txtRoomNo.getText().equals("") && c == KeyEvent.VK_ENTER) {
					txtBuilding.requestFocus();
				}
			}
		});
		txtRoomNo.setFont(new Font("Kiran", Font.BOLD, 25));
		txtRoomNo.setColumns(10);
		txtRoomNo.setBounds(10, 54, 150, 35);
		panel_1.add(txtRoomNo);

		JLabel lblGaracaoNaava = new JLabel("Garacao naava");
		lblGaracaoNaava.setForeground(Color.WHITE);
		lblGaracaoNaava.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblGaracaoNaava.setBounds(172, 26, 72, 24);
		panel_1.add(lblGaracaoNaava);

		txtBuilding = new JTextField();
		txtBuilding.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (!txtBuilding.getText().equals("")) {
					if (ke.getKeyChar() == KeyEvent.VK_ENTER)
						txtCity.requestFocus();
				}
			}
		});
		txtBuilding.setFont(new Font("Kiran", Font.BOLD, 25));
		txtBuilding.setColumns(10);
		txtBuilding.setBounds(170, 54, 150, 35);
		panel_1.add(txtBuilding);

		JLabel lblGaava = new JLabel("gaava");
		lblGaava.setForeground(Color.WHITE);
		lblGaava.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblGaava.setBounds(359, 26, 72, 24);
		panel_1.add(lblGaava);

		txtCity = new JTextField();
		txtCity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (!txtCity.getText().equals("")) {
					if (ke.getKeyChar() == KeyEvent.VK_ENTER)
						txtTaluka.requestFocus();
				}
			}
		});
		txtCity.setFont(new Font("Kiran", Font.BOLD, 25));
		txtCity.setColumns(10);
		txtCity.setBounds(335, 54, 150, 35);
		panel_1.add(txtCity);

		txtTaluka = new JTextField();
		txtTaluka.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (!txtTaluka.getText().equals("")) {
					if (ke.getKeyChar() == KeyEvent.VK_ENTER)
						txtDistrict.requestFocus();
				}
			}
		});
		txtTaluka.setFont(new Font("Kiran", Font.BOLD, 25));
		txtTaluka.setColumns(10);
		txtTaluka.setBounds(10, 127, 150, 35);
		panel_1.add(txtTaluka);

		JLabel lblTaalauka = new JLabel("taalauka");
		lblTaalauka.setForeground(Color.WHITE);
		lblTaalauka.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblTaalauka.setBounds(10, 99, 46, 24);
		panel_1.add(lblTaalauka);

		JLabel lblIjalha = new JLabel("ijalha");
		lblIjalha.setForeground(Color.WHITE);
		lblIjalha.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblIjalha.setBounds(172, 101, 46, 24);
		panel_1.add(lblIjalha);

		txtDistrict = new JTextField();
		txtDistrict.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (!txtDistrict.getText().equals("")) {
					if (ke.getKeyChar() == KeyEvent.VK_ENTER)
						txtPin.requestFocus();
				}
			}
		});
		txtDistrict.setFont(new Font("Kiran", Font.BOLD, 25));
		txtDistrict.setColumns(10);
		txtDistrict.setBounds(170, 127, 150, 35);
		panel_1.add(txtDistrict);

		JLabel lblPainaKaod = new JLabel("paIna kaoD");
		lblPainaKaod.setForeground(Color.WHITE);
		lblPainaKaod.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblPainaKaod.setBounds(337, 101, 60, 24);
		panel_1.add(lblPainaKaod);

		txtPin = new JTextField();
		txtPin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if (!txtPin.getText().equals("") && c == KeyEvent.VK_ENTER) {
					cmbDesignation.requestFocus();
					cmbDesignation.showPopup();
				}
			}
		});
		txtPin.setFont(new Font("Kiran", Font.BOLD, 25));
		txtPin.setColumns(10);
		txtPin.setBounds(335, 127, 150, 35);
		panel_1.add(txtPin);

		btnSave = new JButton("saovh");
		btnSave.addActionListener(e -> save());
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnSave.setBounds(10, 495, 90, 35);
		getContentPane().add(btnSave);

		JButton btnUpdate = new JButton("ApaDoT");
		btnUpdate.addActionListener(e -> update());
		btnUpdate.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnUpdate.setBounds(122, 495, 90, 35);
		getContentPane().add(btnUpdate);

		JButton btnClear = new JButton("i@laAr");
		btnClear.addActionListener(e -> clear());
		btnClear.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear.setBounds(237, 495, 90, 35);
		getContentPane().add(btnClear);

		JButton btnExit = new JButton("baahor");
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBounds(349, 495, 90, 35);
		getContentPane().add(btnExit);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "Payment Details", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel_2.setBackground(new Color(1, 87, 155));
		panel_2.setBounds(10, 390, 500, 90);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblPagaar = new JLabel("pagaar");
		lblPagaar.setForeground(Color.WHITE);
		lblPagaar.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblPagaar.setBounds(172, 21, 46, 24);
		panel_2.add(lblPagaar);

		txtSalary = new JTextField();
		txtSalary.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if (!txtSalary.getText().equals("") && c == KeyEvent.VK_ENTER) {
					btnSave.requestFocus();
				}
			}
		});
		txtSalary.setFont(new Font("Kiran", Font.BOLD, 25));
		txtSalary.setColumns(10);
		txtSalary.setBounds(170, 49, 150, 35);
		panel_2.add(txtSalary);

		JLabel lblKalavaiqa = new JLabel("pagaar kalavaiQa");
		lblKalavaiqa.setForeground(Color.WHITE);
		lblKalavaiqa.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblKalavaiqa.setBounds(347, 21, 98, 24);
		panel_2.add(lblKalavaiqa);

		cmbSalaryType = new JComboBox<String>();
		cmbSalaryType.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (cmbSalaryType.getSelectedIndex() != -1) {
					if (ke.getKeyChar() == KeyEvent.VK_ENTER)
						btnSave.requestFocus();
				}
			}
		});
		cmbSalaryType.addItem("maihnaa");
		cmbSalaryType.addItem("AzvaDa");
		cmbSalaryType.addItem("raoja");
		cmbSalaryType.setSelectedIndex(-1);
		cmbSalaryType.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbSalaryType.setBounds(335, 49, 150, 35);
		panel_2.add(cmbSalaryType);

		JLabel lblPaaost = new JLabel("paaosT");
		lblPaaost.setForeground(Color.WHITE);
		lblPaaost.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblPaaost.setBounds(10, 21, 35, 24);
		panel_2.add(lblPaaost);

		cmbDesignation = new JComboBox<String>();
		cmbDesignation.addItem("ma^naojar");
		cmbDesignation.addItem("Aka{^MTMT");
		cmbDesignation.addItem("holpar");
		cmbDesignation.addItem("vak-r");
		cmbDesignation.addItem("iSapaa[-");
		cmbDesignation.setSelectedIndex(-1);
		cmbDesignation.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbDesignation.setBounds(10, 49, 150, 35);
		panel_2.add(cmbDesignation);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(522, 5, 736, 475);
		getContentPane().add(scrollPane);

		table = new JTable();

		table.setRowHeight(25);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setFont(new Font("Kiran", Font.PLAIN, 25));
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null }, },
				new String[] { "Sr.No", "Employee Name", "Gender", "Contact", "Address", "Designation", "Salary",
						"Salary Type" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(49);
		table.getColumnModel().getColumn(1).setPreferredWidth(212);
		table.getColumnModel().getColumn(2).setPreferredWidth(53);
		scrollPane.setViewportView(table);

		model = (DefaultTableModel) table.getModel();
		loadAllEmployee();
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new AddEmployee();
	}

	public void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error Message", JOptionPane.ERROR_MESSAGE);
	}

	private int validateData() {
		if (txtFname.getText().equals("")) {
			showError("Enter First Name");
			txtFname.requestFocus();
			return 0;
		} else if (txtMname.getText().equals("")) {
			showError("Enter Middle Name");
			txtMname.requestFocus();
			return 0;
		} else if (txtLname.getText().equals("")) {
			showError("Enter Last Name");
			txtLname.requestFocus();
			return 0;
		} else if (!rdbtnFemale.isSelected() && !rdbtnMale.isSelected()) {
			showError("Select Gender");
			return 0;
		} else if (txtContact.getText().equals("")) {
			showError("Enter Mobile No");
			txtContact.requestFocus();
			return 0;
		} else if (txtRoomNo.getText().equals("")) {

			showError("Enter Room No");
			txtRoomNo.requestFocus();
			return 0;
		} else if (txtBuilding.getText().equals("")) {
			showError("Enter Building Name");
			txtBuilding.requestFocus();
			return 0;
		} else if (txtCity.getText().equals("")) {
			showError("Enter City/Villege Name");
			txtCity.requestFocus();
			return 0;
		} else if (txtTaluka.getText().equals("")) {
			showError("Enter Taluka");
			txtTaluka.requestFocus();
			return 0;
		} else if (txtDistrict.getText().equals("")) {
			showError("Enter District");
			txtDistrict.requestFocus();
			return 0;
		} else if (txtPin.getText().equals("")) {
			showError("Enter Pin Code");
			txtPin.requestFocus();
			return 0;
		} else if (cmbDesignation.getSelectedIndex() == -1) {
			showError("Select Designation");
			cmbDesignation.requestFocus();
			cmbDesignation.showPopup();
			return 0;
		} else if (txtSalary.getText().equals("")) {
			showError("Enter salary");
			txtSalary.requestFocus();
			return 0;
		} else if (cmbSalaryType.getSelectedIndex() == -1) {
			showError("select Salary Type");
			cmbSalaryType.requestFocus();
			cmbSalaryType.showPopup();
			return 0;
		} else {
		}

		return 1;
	}

	private void save() {
		try {
			if (validateData() != 1) {
				return;
			}
			Employee employee = new Employee();
			employee.setId(id);
			employee.setfName(txtFname.getText());
			employee.setmName(txtMname.getText());
			employee.setlName(txtLname.getText());
			employee.setContact(txtContact.getText());
			if (rdbtnMale.isSelected())
				employee.setGender('M');
			else
				employee.setGender('F');
			employee.setJoiningDate(date.getDate());
			employee.setDesignation(cmbDesignation.getSelectedItem().toString());
			employee.setSalary(Double.parseDouble(txtSalary.getText()));
			employee.setSalaryType(cmbSalaryType.getSelectedItem().toString());
			System.out.println(employee);

			Address add = new Address();
			add.setRoomNo(txtRoomNo.getText());
			add.setBuilding(txtBuilding.getText());
			add.setCity(txtCity.getText());
			add.setDistrict(txtDistrict.getText());
			add.setPin(Integer.parseInt(txtPin.getText()));
			add.setTaluka(txtTaluka.getText());

			employee.setAddress(add);
			// System.out.println(employee);

			if (service.saveEmployee(employee) != 0) {
				JOptionPane.showMessageDialog(this, "Employee Information save Success", "Success",
						JOptionPane.INFORMATION_MESSAGE);
				loadAllEmployee();
				clear();
			} else {
				showError("Empoyee Not Saved");
			}

		} catch (Exception e) {
			e.printStackTrace();
			showError(e.getMessage());

		}

	}

	private void clear() {
		txtBuilding.setText("");
		txtCity.setText("");
		txtContact.setText("");
		txtDistrict.setText("");
		txtFname.setText("");
		txtLname.setText("");
		txtMname.setText("");
		txtPin.setText("");
		txtRoomNo.setText("");
		txtSalary.setText("");
		txtTaluka.setText("");
		cmbDesignation.setSelectedIndex(-1);
		cmbSalaryType.setSelectedIndex(-1);
		group.clearSelection();
	}

	private void loadAllEmployee() {
		try {
			model.setRowCount(0);
			Employee emp;
			String gender;
			Iterator<Employee> it = service.listEmployee().iterator();
			while (it.hasNext()) {
				emp = it.next();

				if (emp != null) {
					if (emp.getGender() == 'M')
						gender = "paur]Ya";
					else
						gender = "s~aI";
					model.addRow(new Object[] { emp.getId(),
							emp.getfName() + " " + emp.getmName() + " " + emp.getlName(), gender, emp.getContact(),
							emp.getAddress(), emp.getDesignation(), emp.getSalary(), emp.getSalaryType() });
				}
			}
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private void update() {
		try {
			if (table.getSelectedRow() == -1) {
				return;
			}
			Employee emp = service
					.findEmployeeById(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString()));
			id = emp.getId();
			System.out.println(emp);
			txtFname.setText(emp.getfName());
			txtBuilding.setText(emp.getAddress().getBuilding());
			txtCity.setText(emp.getAddress().getCity());
			txtContact.setText(emp.getContact());
			txtDistrict.setText(emp.getAddress().getDistrict());
			txtLname.setText(emp.getlName());
			txtMname.setText(emp.getmName());
			txtPin.setText("" + emp.getAddress().getPin());
			txtRoomNo.setText("" + emp.getAddress().getRoomNo());
			txtSalary.setText("" + emp.getSalary());
			if (emp.getGender() == 'M')
				rdbtnMale.setSelected(true);
			else
				rdbtnFemale.setSelected(true);
			date.setDate(emp.getJoiningDate());
			txtTaluka.setText(emp.getAddress().getTaluka());
			cmbDesignation.setSelectedItem(emp.getDesignation());
			cmbSalaryType.setSelectedItem(emp.getSalaryType());
			service.getEmployeesession().close();
		} catch (Exception e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
	}
}
