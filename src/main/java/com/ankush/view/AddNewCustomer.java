package com.ankush.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.ankush.entities.Address;
import com.ankush.entities.Customer;
import com.ankush.service.service.CustomerService;
import com.ankush.service.serviceImpl.CustomerServiceImpl;

public class AddNewCustomer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -868156908118140387L;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtContact;
	private JTextField txtEmail;
	private JTextField txtRoomNo;
	private JTextField txtBuilding;
	private JTextField txtCity;
	private JTextField txtTaluka;
	private JTextField txtDistrict;
	private JTextField txtPin;
	private JTable table;
	private JButton btnSave;
	private CustomerService service;
	@SuppressWarnings("unused")
	private int id;
	private DefaultTableModel model;
	private String htmstart = "<html><font face=\"kiran\" size=\"6\">";

	public AddNewCustomer() {
		this.service = new CustomerServiceImpl();
		this.id = 0;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Add New Customer");
		setSize(1230, 697);
		setResizable(false);
		getContentPane().setBackground(new Color(1, 87, 155));
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Personal Details", TitledBorder.LEADING, TitledBorder.TOP, null, Color.orange));
		panel.setBackground(new Color(1, 87, 155));
		panel.setBounds(20, 17, 500, 183);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("A.k`.");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(18, 25, 34, 24);
		panel.add(lblNewLabel);

		txtId = new JTextField("" + (service.getNewCustomerId() + 1));
		txtId.setEditable(false);
		txtId.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtId.setBounds(18, 50, 150, 35);
		panel.add(txtId);
		txtId.setColumns(10);

		JLabel lblGaahkacaoNaava = new JLabel("ga`ahkacao naava");
		lblGaahkacaoNaava.setForeground(Color.WHITE);
		lblGaahkacaoNaava.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblGaahkacaoNaava.setBounds(176, 25, 88, 24);
		panel.add(lblGaahkacaoNaava);

		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtName.getText().equals("")) {
						txtContact.requestFocus();
					}
				}
			}
		});
		txtName.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtName.setBounds(174, 50, 306, 35);
		panel.add(txtName);
		txtName.setColumns(10);

		JLabel lblMaaobaalaK = new JLabel("maaobaa[la k`.");
		lblMaaobaalaK.setForeground(Color.WHITE);
		lblMaaobaalaK.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblMaaobaalaK.setBounds(18, 104, 88, 24);
		panel.add(lblMaaobaalaK);

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
					txtEmail.requestFocus();
				}
			}
		});
		txtContact.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtContact.setColumns(10);
		txtContact.setBounds(18, 129, 150, 35);
		panel.add(txtContact);

		txtEmail = new JTextField();
		txtEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtEmail.getText().equals("")) {
						txtRoomNo.requestFocus();
					}
				}
			}
		});
		txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(176, 129, 304, 35);
		panel.add(txtEmail);

		JLabel lblmaola = new JLabel("[maola");
		lblmaola.setForeground(Color.WHITE);
		lblmaola.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblmaola.setBounds(178, 104, 35, 24);
		panel.add(lblmaola);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(
				new TitledBorder(null, "Address Details", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		panel_1.setBackground(new Color(1, 87, 155));
		panel_1.setBounds(20, 206, 500, 183);
		getContentPane().add(panel_1);

		JLabel label = new JLabel("r]ma naM.");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Kiran", Font.PLAIN, 25));
		label.setBounds(10, 26, 46, 24);
		panel_1.add(label);

		txtRoomNo = new JTextField();
		txtRoomNo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtRoomNo.getText().equals("")) {
						txtBuilding.requestFocus();
					}
				}
			}
		});
		txtRoomNo.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtRoomNo.setColumns(10);
		txtRoomNo.setBounds(10, 54, 150, 35);
		panel_1.add(txtRoomNo);

		JLabel label_1 = new JLabel("Garacao naava");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Kiran", Font.PLAIN, 25));
		label_1.setBounds(172, 26, 72, 24);
		panel_1.add(label_1);

		txtBuilding = new JTextField();
		txtBuilding.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtBuilding.getText().equals("")) {
						txtCity.requestFocus();
					}
				}
			}
		});
		txtBuilding.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtBuilding.setColumns(10);
		txtBuilding.setBounds(170, 54, 150, 35);
		panel_1.add(txtBuilding);

		JLabel label_2 = new JLabel("gaava");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Kiran", Font.PLAIN, 25));
		label_2.setBounds(359, 26, 72, 24);
		panel_1.add(label_2);

		txtCity = new JTextField();
		txtCity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtCity.getText().equals("")) {
						txtTaluka.requestFocus();
					}
				}
			}
		});
		txtCity.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtCity.setColumns(10);
		txtCity.setBounds(335, 54, 150, 35);
		panel_1.add(txtCity);

		txtTaluka = new JTextField();
		txtTaluka.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtTaluka.getText().equals("")) {
						txtDistrict.requestFocus();
					}
				}
			}
		});
		txtTaluka.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtTaluka.setColumns(10);
		txtTaluka.setBounds(10, 127, 150, 35);
		panel_1.add(txtTaluka);

		JLabel label_3 = new JLabel("taalauka");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Kiran", Font.PLAIN, 25));
		label_3.setBounds(10, 99, 46, 24);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("ijalha");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Kiran", Font.PLAIN, 25));
		label_4.setBounds(172, 101, 46, 24);
		panel_1.add(label_4);

		txtDistrict = new JTextField();
		txtDistrict.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					if (!txtDistrict.getText().equals("")) {
						txtPin.requestFocus();
					}
				}
			}
		});
		txtDistrict.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtDistrict.setColumns(10);
		txtDistrict.setBounds(170, 127, 150, 35);
		panel_1.add(txtDistrict);

		JLabel label_5 = new JLabel("paIna kaoD");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Kiran", Font.PLAIN, 25));
		label_5.setBounds(337, 101, 60, 24);
		panel_1.add(label_5);

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
					btnSave.requestFocus();
				}
			}
		});
		txtPin.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtPin.setColumns(10);
		txtPin.setBounds(335, 127, 150, 35);
		panel_1.add(txtPin);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(520, 17, 688, 621);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "Sr.No", "Customer Name", "Contact No", "Email Id", "Address" }));

		table.setRowHeight(25);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.getColumnModel().getColumn(0).setPreferredWidth(48);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(4).setPreferredWidth(173);
		scrollPane.setViewportView(table);

		model = (DefaultTableModel) table.getModel();
		loadData();
		btnSave = new JButton("saovh");
		btnSave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					btnSave.doClick();
				}
			}
		});
		btnSave.addActionListener(e -> save());
		btnSave.setBackground(Color.GREEN);
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnSave.setBounds(20, 405, 90, 35);
		getContentPane().add(btnSave);

		JButton btnClear = new JButton("i@laAr");
		btnClear.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear.setBackground(Color.GREEN);
		btnClear.setBounds(150, 405, 90, 35);
		getContentPane().add(btnClear);

		JButton btnUpdate = new JButton("ApaDoT");
		btnClear.addActionListener(e -> clear());
		btnUpdate.addActionListener(e -> update());
		btnUpdate.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnUpdate.setBackground(Color.GREEN);
		btnUpdate.setBounds(281, 405, 90, 35);
		getContentPane().add(btnUpdate);

		JButton btnExit = new JButton("baahor");
		btnExit.addActionListener(e -> {
			service.closeSession();
			dispose();
		});
		btnExit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
					service.closeSession();
					dispose();
				}
			}
		});
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(408, 405, 90, 35);
		getContentPane().add(btnExit);

		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new AddNewCustomer();

	}

	private void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private int validateData() {
		try {
			if (txtName.getText().equals("")) {
				showError("Enter Customer Name");
				txtName.requestFocus();
				return 0;
			}
			if (txtContact.getText().equals("")) {
				showError("Enter Mobile No");
				txtContact.requestFocus();
				return 0;
			}
			if (txtRoomNo.getText().equals("")) {
				showError("Enter Room/Flat No");
				txtRoomNo.requestFocus();
				return 0;
			}
			if (txtBuilding.getText().equals("")) {
				showError("Enter Buliding Name");
				txtBuilding.requestFocus();
				return 0;
			}
			if (txtCity.getText().equals("")) {
				showError("Enter City/Village Name");
				txtCity.requestFocus();
				return 0;
			}
			if (txtTaluka.getText().equals("")) {
				showError("Enter Taluka Name");
				txtTaluka.requestFocus();
				return 0;
			}
			if (txtDistrict.getText().equals("")) {
				showError("Enter District Name");
				txtDistrict.requestFocus();
				return 0;
			}
			if (txtPin.getText().equals("")) {
				showError("Enter Pin Code");
				txtPin.requestFocus();
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
			if (validateData() != 1)
				return;
			Customer customer = new Customer();
			customer.setId(Integer.parseInt(txtId.getText()));
			customer.setName(txtName.getText());
			customer.setContact(txtContact.getText());
			customer.setEmail(txtEmail.getText());
			Address address = new Address();
			address.setBuilding(txtBuilding.getText());
			address.setCity(txtCity.getText());
			address.setDistrict(txtDistrict.getText());
			address.setPin(Integer.parseInt(txtPin.getText()));
			address.setRoomNo(txtRoomNo.getText());
			address.setTaluka(txtTaluka.getText());

			customer.setAddress(address);
			System.out.println(customer);
			int flag = service.saveCustomer(customer);
			if (flag == 1) {
				JOptionPane.showMessageDialog(this, "Customer Info Save Success");
				service.closeSession();
				clear();
				loadData();
			} else if (flag == 2) {
				JOptionPane.showMessageDialog(this, "Customer Info Update Success");
				service.closeSession();
				clear();
				loadData();
			} else {
				showError("Record not Saved");
			}

		} catch (Exception e) {
			showError(e.getMessage());

		}
	}

	private void loadData() {
		try {
			model.setRowCount(0);
			service = new CustomerServiceImpl();
			Iterator<Customer> it = service.getAllCustomer().iterator();
			Customer c;
			while (it.hasNext()) {
				c = it.next();
				model.addRow(new Object[] { c.getId(), htmstart + c.getName(), htmstart + c.getContact(), c.getEmail(),
						htmstart + c.getAddress() });

			}
		} catch (Exception e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
	}

	private void update() {
		try {
			int row = table.getSelectedRow();
			if (row == -1) {
				return;
			}
			Customer c = service.findCustomerById(Integer.parseInt(model.getValueAt(row, 0).toString()));
			txtId.setText("" + c.getId());
			txtName.setText(c.getName());
			txtBuilding.setText(c.getAddress().getBuilding());
			txtCity.setText(c.getAddress().getCity());
			txtContact.setText(c.getContact());
			txtDistrict.setText(c.getAddress().getDistrict());
			txtEmail.setText(c.getEmail());
			txtPin.setText("" + c.getAddress().getPin());
			txtRoomNo.setText(c.getAddress().getRoomNo());
			txtTaluka.setText(c.getAddress().getTaluka());

		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private void clear() {
		try {
			txtId.setText("" + (service.getNewCustomerId() + 1));
			txtBuilding.setText("");
			txtCity.setText("");
			txtContact.setText("");
			txtEmail.setText("");
			txtDistrict.setText("");
			txtName.setText("");
			txtPin.setText("");
			txtRoomNo.setText("");
			txtTaluka.setText("");
		} catch (Exception e) {
			showError(e.getMessage());

		}
	}
}
