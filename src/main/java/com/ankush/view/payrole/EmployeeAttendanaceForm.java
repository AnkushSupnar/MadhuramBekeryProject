package com.ankush.view.payrole;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.ankush.entities.Employee;
import com.ankush.entities.EmployeeAttendance;
import com.ankush.service.service.EmployeeAttendanceService;
import com.ankush.service.service.EmployeeService;
import com.ankush.service.serviceImpl.EmployeeAttendanceServiceImpl;
import com.ankush.service.serviceImpl.EmployeeServiceImpl;
import com.github.lgooddatepicker.components.DatePicker;

public class EmployeeAttendanaceForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Object> cmbEmployee;
	private JComboBox<String> cmbMonth;
	private EmployeeService employeeService;
	private JTable table;
	private DefaultTableModel model, modelOld;
	private DatePicker date;

	private EmployeeAttendanceService eaService;
	private JTable tblOld;
	private JComboBox<String> cmbYear;

	public EmployeeAttendanaceForm() {

		this.eaService = new EmployeeAttendanceServiceImpl();
		this.employeeService = new EmployeeServiceImpl();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(805, 629);
		setTitle("Employee Attendance");
		setResizable(false);
		getContentPane().setBackground(new Color(1, 87, 155));
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("kamagaaracao naava");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(18, 18, 103, 24);
		getContentPane().add(lblNewLabel);

		cmbEmployee = new JComboBox<>();
		cmbEmployee.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbEmployee.setBounds(18, 40, 264, 35);
		getContentPane().add(cmbEmployee);
		loadEmployeeName();

		date = new DatePicker();
		date.setDateToToday();
		date.setBounds(284, 40, 184, 35);
		getContentPane().add(date);

		JButton btnPresent = new JButton("hjar");
		btnPresent.addActionListener(e -> add("hjar"));
		btnPresent.setBackground(Color.GREEN);
		btnPresent.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnPresent.setBounds(18, 87, 90, 35);
		getContentPane().add(btnPresent);

		JButton btnAbsent = new JButton("gaOrhjar");

		btnAbsent.addActionListener(e -> add("gaOrhjar"));
		btnAbsent.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnAbsent.setBackground(Color.GREEN);
		btnAbsent.setBounds(150, 87, 90, 35);
		getContentPane().add(btnAbsent);

		JButton btnExit = new JButton("baahor");
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(260, 551, 90, 35);
		getContentPane().add(btnExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1, 134, 467, 405);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Kiran", Font.PLAIN, 25));
		table.setRowHeight(25);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Sr.No", "Employee Name", "Present/Absent" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(287);
		table.getColumnModel().getColumn(2).setPreferredWidth(129);
		scrollPane.setViewportView(table);

		model = (DefaultTableModel) table.getModel();

		JButton btnSave = new JButton("saovh");
		btnSave.addActionListener(e -> save());
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnSave.setBackground(Color.GREEN);
		btnSave.setBounds(18, 551, 90, 35);
		getContentPane().add(btnSave);

		JLabel lblIdnaamk = new JLabel("idnaaMk ");
		lblIdnaamk.setForeground(Color.WHITE);
		lblIdnaamk.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblIdnaamk.setBounds(283, 18, 103, 24);
		getContentPane().add(lblIdnaamk);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(472, 134, 321, 405);
		getContentPane().add(scrollPane_1);

		tblOld = new JTable();
		tblOld.setFont(new Font("SansSerif", Font.PLAIN, 16));
		tblOld.setRowHeight(25);
		tblOld.setShowVerticalLines(true);
		tblOld.setShowHorizontalLines(true);
		tblOld.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Sr.No", "Date" }));
		tblOld.getColumnModel().getColumn(0).setPreferredWidth(94);
		tblOld.getColumnModel().getColumn(1).setPreferredWidth(204);
		scrollPane_1.setViewportView(tblOld);
		modelOld = (DefaultTableModel) tblOld.getModel();
		loadData(date.getDate());
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.WHITE, 1, true));
		panel.setBackground(new Color(1, 87, 155));
		panel.setBounds(475, 6, 318, 123);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblMaihnaa = new JLabel("maihnaa");
		lblMaihnaa.setForeground(Color.WHITE);
		lblMaihnaa.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblMaihnaa.setBounds(22, 30, 42, 24);
		panel.add(lblMaihnaa);

		cmbMonth = new JComboBox<>();
		cmbMonth.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbMonth.setModel(
				new DefaultComboBoxModel<String>(new String[] { "jaanaovaarI", "foba`uvaarI", "maaca-", "eipa`la",
						"mao", "jaUna", "jaUlaO", "Aa^gasT", "sapToMbar", "Aa^@Taobar", "naaovhoMbar", "iDsaoMbar" }));
		cmbMonth.setBounds(72, 25, 141, 35);
		panel.add(cmbMonth);

		JLabel lblVaya = new JLabel("vaYa-");
		lblVaya.setForeground(Color.WHITE);
		lblVaya.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblVaya.setBounds(22, 77, 42, 24);
		panel.add(lblVaya);

		cmbYear = new JComboBox<String>();
		cmbYear.setModel(new DefaultComboBoxModel<String>(new String[] { "2020", "2021" }));
		cmbYear.setFont(new Font("Kiran", Font.PLAIN, 25));
		cmbYear.setBounds(72, 77, 141, 35);
		panel.add(cmbYear);

		JButton btnNewButton = new JButton("paha");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnNewButton.setBounds(222, 77, 79, 35);
		panel.add(btnNewButton);

		JButton btnUpdate = new JButton("ApaDoT");
		btnUpdate.addActionListener(e -> update());
		btnUpdate.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnUpdate.setBackground(Color.GREEN);
		btnUpdate.setBounds(129, 551, 90, 35);
		getContentPane().add(btnUpdate);

		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new EmployeeAttendanaceForm();

	}

	private void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
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

	private void add(String status) {
		try {
			int row = model.getRowCount();
			System.out.println("Row= " + row);
			if (row == 0) {
				model.addRow(new Object[] { 1, cmbEmployee.getSelectedItem().toString(), status });
			} else {
				int flag = -1;
				for (int i = 0; i < row; i++) {
					if (model.getValueAt(i, 1).toString().equals(cmbEmployee.getSelectedItem().toString())) {
						flag = i;
					}
				}

				if (flag == -1) {
					model.addRow(new Object[] { ++row, cmbEmployee.getSelectedItem().toString(), status });
				} else {
					model.setValueAt(status, flag, 2);
				}
			}
		} catch (Exception e) {
			showError(e.getMessage());

		}
	}

	private void save() {
		try {
			int row = model.getRowCount();
			if (row == 0) {
				showError("No Data To Save");
				return;
			}
			EmployeeAttendance ea = new EmployeeAttendance();
			int flag = 0;
			for (int i = 0; i < row; i++) {
				ea.setDate(date.getDate());
				ea.setEmployee(employeeService.findEmployeeById(
						employeeService.getEmployeeIdUsingFullName(model.getValueAt(i, 1).toString()).getId()));
				ea.setStatus(model.getValueAt(i, 2).toString());
				flag = eaService.saveEmployeeAttendance(ea);
			}

			if (flag != 0) {
				JOptionPane.showMessageDialog(this, "Attendance Save Success", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			showError("Error" + e.getMessage());
		}
	}

	private void loadData(LocalDate today) {
		try {
			// LocalDate today = LocalDate.now();
			int monthdays = today.lengthOfMonth();
			int month = today.getMonthValue();
			int year = today.getYear();
			String m;
			System.out.print("Day in Month" + month + " Month" + monthdays);
			modelOld.setRowCount(0);
			for (int day = 1; day < monthdays; day++) {
				if (month >= 10)
					m = Integer.toString(month);
				else
					m = "0" + month;

				modelOld.addRow(new Object[] { day, day + "-" + m + "-" + year });
			}

		} catch (Exception e) {
			showError(e.getMessage());
		}

	}

	private void update() {
		try {
			int row = tblOld.getSelectedRow();
			model.setRowCount(0);

			if (row == -1) {
				return;
			}
			String d = modelOld.getValueAt(row, 1).toString();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
			LocalDate selected = LocalDate.parse(d, formatter);
			showError(selected.toString());

			List<EmployeeAttendance> list = eaService.getDateWiseEmployeeAttendance(selected);
			if (list.size() != 0)
				date.setDate(selected);
			int sr = 0;
			for (EmployeeAttendance ae : list) {
				Employee emp = ae.getEmployee();
				model.addRow(new Object[] { ++sr, emp.getfName() + " " + emp.getmName() + " " + emp.getlName(),
						ae.getStatus() });
			}

			tblOld.clearSelection();
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}
}
