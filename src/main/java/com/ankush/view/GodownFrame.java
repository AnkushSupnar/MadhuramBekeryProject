package com.ankush.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.ankush.backup.Backup;
import com.ankush.entities.Godown;
import com.ankush.entities.GodownStock;
import com.ankush.entities.GodownTransaction;
import com.ankush.service.service.GodownService;
import com.ankush.service.service.ItemService;
import com.ankush.service.serviceImpl.GodownServiceImpl;
import com.ankush.service.serviceImpl.ItemServiceImpl;
import com.github.lgooddatepicker.components.DatePicker;

public class GodownFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1743330947025148375L;

	private DatePicker date;

	// for Item Search Box
////////////////////////////////////
	private JTextField txtItemName;
	private JPopupMenu iPop;
	private List<String> iList;
	private JList<String> iJList;
	private DefaultListModel<String> iModel;
	private JScrollPane iScroll;
////////////////////////////////////////////

	private JTextField txtQty;
	private ItemService itemService;
	private JTable table;
	private DefaultTableModel model, modelOld;
	private int id;
	private GodownService godownService;
	private JTable tblOld;

	public GodownFrame() {
		this.itemService = new ItemServiceImpl();
		this.godownService = new GodownServiceImpl();
		this.id = 0;
		setTitle("Godown Stock Entry");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(981, 721);
		setResizable(false);
		getContentPane().setBackground(new Color(1, 87, 155));
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("idnaaMk ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel.setBounds(34, 31, 49, 24);
		getContentPane().add(lblNewLabel);

		date = new DatePicker();
		date.setDateToToday();
		date.setBounds(90, 28, 193, 35);
		getContentPane().add(date);

		JLabel lblMaalacaoNaava = new JLabel("maalacao naava");
		lblMaalacaoNaava.setForeground(Color.WHITE);
		lblMaalacaoNaava.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblMaalacaoNaava.setBounds(22, 83, 93, 24);
		getContentPane().add(lblMaalacaoNaava);

		txtItemName = new JTextField();
		txtItemName.setFont(new Font("Kiran", Font.PLAIN, 25));
		txtItemName.setColumns(10);
		txtItemName.setBounds(22, 109, 235, 36);
		getContentPane().add(txtItemName);
		generateItemSearchBox();

		JButton btnAdd = new JButton("A^D");
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					btnAdd.doClick();
				}
			}
		});
		btnAdd.addActionListener(e -> addButton());
		btnAdd.setBackground(new Color(0, 153, 51));
		btnAdd.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnAdd.setBounds(22, 157, 90, 35);
		getContentPane().add(btnAdd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 195, 587, 433);
		getContentPane().add(scrollPane);

		txtQty = new JTextField();
		txtQty.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.') || (c == '-') || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				if (!txtQty.getText().equals("") && c == KeyEvent.VK_ENTER) {
					try {
						Float.parseFloat(txtQty.getText());
						btnAdd.requestFocus();
					} catch (Exception e2) {
						showError("Enter Valid Amount Of rate");
						txtQty.requestFocus();
						txtQty.selectAll();
						return;
					}

				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (txtItemName.getText().equals("")) {
					txtItemName.requestFocus();
					txtQty.setText("");
				}
			}
		});
		txtQty.setFont(new Font("SansSerif", Font.PLAIN, 16));
		txtQty.setBounds(260, 109, 131, 35);
		getContentPane().add(txtQty);
		txtQty.setColumns(10);

		table = new JTable();
		table.setShowGrid(true);
		table.setFont(new Font("Kiran", Font.PLAIN, 25));
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Sr.No.", "ItemName", "Quantity" }));
		table.getColumnModel().getColumn(1).setPreferredWidth(265);
		table.getColumnModel().getColumn(2).setPreferredWidth(131);
		scrollPane.setViewportView(table);
		table.setRowHeight(25);
		model = (DefaultTableModel) table.getModel();

		JButton btnClear = new JButton("i@laAr");
		btnClear.addActionListener(e -> clear());
		btnClear.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear.setBackground(new Color(0, 153, 51));
		btnClear.setBounds(124, 157, 90, 35);
		getContentPane().add(btnClear);

		JButton btnEdit = new JButton("eiDT");

		btnEdit.addActionListener(e -> edit());
		btnEdit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnEdit.setBackground(new Color(0, 153, 51));
		btnEdit.setBounds(227, 157, 90, 35);
		getContentPane().add(btnEdit);

		JButton btnRemove = new JButton("irmauvh");
		btnRemove.addActionListener(e -> remove());
		btnRemove.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnRemove.setBackground(new Color(0, 153, 51));
		btnRemove.setBounds(329, 157, 90, 35);
		getContentPane().add(btnRemove);

		JButton btnSave = new JButton("saovh");
		btnSave.addActionListener(e -> save());
		btnSave.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnSave.setBackground(new Color(0, 153, 51));
		btnSave.setBounds(10, 640, 90, 35);
		getContentPane().add(btnSave);

		JButton btnClear2 = new JButton("i@laAr");
		btnClear2.addActionListener(e -> clear2());
		btnClear2.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnClear2.setBackground(new Color(0, 153, 51));
		btnClear2.setBounds(112, 640, 90, 35);
		getContentPane().add(btnClear2);

		JButton btnEdit2 = new JButton("eiDT");
		btnEdit2.addActionListener(e -> edit2());
		btnEdit2.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnEdit2.setBackground(new Color(0, 153, 51));
		btnEdit2.setBounds(215, 640, 90, 35);
		getContentPane().add(btnEdit2);

		JButton btnExit = new JButton("baahor");
		btnExit.addActionListener(e -> dispose());
		btnExit.setFont(new Font("Kiran", Font.PLAIN, 25));
		btnExit.setBackground(new Color(0, 153, 51));
		btnExit.setBounds(409, 640, 90, 35);
		getContentPane().add(btnExit);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(593, 6, 376, 622);
		getContentPane().add(scrollPane_1);

		tblOld = new JTable();
		tblOld.setFont(new Font("SansSerif", Font.PLAIN, 16));
		tblOld.setRowHeight(25);
		tblOld.setShowVerticalLines(true);
		tblOld.setShowHorizontalLines(true);
		tblOld.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "SrNo", "Reciept No", "Date" }));
		tblOld.getColumnModel().getColumn(1).setPreferredWidth(93);
		tblOld.getColumnModel().getColumn(2).setPreferredWidth(177);
		scrollPane_1.setViewportView(tblOld);

		modelOld = (DefaultTableModel) tblOld.getModel();
		loadOld();
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new GodownFrame();
	}

	void generateItemSearchBox() {
		try {
			txtItemName.setFont(new Font("Kiran", Font.PLAIN, 25));
			iPop = new JPopupMenu();
			iList = itemService.getAllItemName();
			iModel = new DefaultListModel<>();
			iJList = new JList<>(iModel);
			iJList.setFont(new Font("Kiran", Font.PLAIN, 25));
			iJList.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent ke) {
					char c = ke.getKeyChar();
					if (c == KeyEvent.VK_ENTER) {

						if (iJList.getSelectedIndex() != -1) {
							txtItemName.setText(iJList.getSelectedValue());
							iPop.setVisible(false);
							txtQty.requestFocus();
							// txtRate.setText("" + itemService.getItemRateByName(txtItemName.getText()));
						}
						if (txtItemName.getText().equals("")) {
							return;
						}
					}

				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
			iJList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if (iJList.getSelectedIndex() != -1) {
						txtItemName.setText(iJList.getSelectedValue());
						// txtRate.setText("" + itemService.getItemRateByName(txtItemName.getText()));
						iPop.setVisible(false);
						txtQty.requestFocus();

					}
					if (txtItemName.getText().equals("")) {
						// txtRate.setText("" + 0.0f);
						return;
					}
				}
			});

			// iJList.setFont(font);

			iScroll = new JScrollPane();
			iScroll.setViewportView(iJList);

			txtItemName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {

					// iList = itemService.getAllItemName();
					findItemFilter(txtItemName.getText());
					if (!iList.isEmpty()) {
						iPop.add(iScroll); // your component
						iPop.setPopupSize(txtItemName.getWidth(), 300);
						iPop.show(txtItemName, 0, txtItemName.getHeight());
						txtItemName.requestFocus();
						// txtRate.setText("" + itemService.getItemRateByName(txtItemName.getText()));
					}

					char c = e.getKeyChar();

					if (c == KeyEvent.VK_ENTER) {

						iJList.setSelectedIndex(0);
						iJList.requestFocus();
						// txtRate.setText("" + 0.0f);
					}
				}
			});

			txtItemName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {

					// iList = itemService.getAllItemName();
					findItemFilter(txtItemName.getText());
					// iJList.requestFocus();
					if (!iList.isEmpty()) {
						iPop.add(iScroll); // your component
						iPop.setPopupSize(txtItemName.getWidth(), 300);
						iPop.show(txtItemName, 0, txtItemName.getHeight());
						txtItemName.requestFocus();
					}
				}
			});

			iPop.setLayout(new BorderLayout());

			// panel.add(txtItemName);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	void findItemFilter(String find) {
		iModel.removeAllElements();
		iModel.clear();
		// System.out.println("i got"+find);
		try {
			for (int i = 0; i < iList.size(); i++) {
				if (iList.get(i).startsWith(find)) {
					// MySortStrings.add(MyStrings.get(i));
					iModel.addElement(iList.get(i));
				}
			}
		} catch (Exception e) {
			System.out.println("Error in findItem " + e.getMessage());
			return;
		}

	}

	private void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void addButton() {
		try {

			if (validateData() != 1) {
				return;
			}
			int row = model.getRowCount();
			if (row == 0) {
				model.addRow(new Object[] { 1, txtItemName.getText(), txtQty.getText() });
				clear();

			} else {
				int flag = -1;
				for (int i = 0; i < row; i++) {
					if (model.getValueAt(i, 1).toString().equals(txtItemName.getText())) {
						flag = i;
					}
				}
				if (flag != -1) {
					if (Float.parseFloat(model.getValueAt(flag, 2).toString())
							+ Float.parseFloat(txtQty.getText()) <= 0.0f) {
						showError("Cant Reduce Quantity");
						txtQty.requestFocus();
						return;
					}
					model.setValueAt(
							Float.parseFloat(model.getValueAt(flag, 2).toString()) + Float.parseFloat(txtQty.getText()),
							flag, 2);
					clear();
				} else {
					model.addRow(new Object[] { ++row, txtItemName.getText(), txtQty.getText() });
					clear();
				}
			}

		} catch (Exception e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
	}

	private int validateData() {
		try {
			if (txtItemName.getText().equals("")) {
				showError("Enter Item Name");
				txtItemName.requestFocus();
				return 0;
			} else if (txtQty.getText().equals("")) {
				showError("Enter Quantity");
				txtQty.requestFocus();
				return 0;
			}
			return 1;
		} catch (Exception e) {
			showError(e.getMessage());
			return 0;
		}
	}

	private void clear() {
		txtItemName.setText("");
		txtQty.setText("");
		txtItemName.requestFocus();
	}

	private void edit() {
		try {
			int row = table.getSelectedRow();
			if (row == -1) {
				return;
			}
			txtItemName.setText(model.getValueAt(row, 1).toString());
			txtQty.setText(model.getValueAt(row, 2).toString());
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private void remove() {
		try {
			int row = table.getSelectedRow();
			if (row == -1)
				return;
			model.removeRow(row);
			row = table.getRowCount();
			int sr = 0;
			for (int i = 0; i < row; i++) {
				model.setValueAt(++sr, i, 0);
			}
		} catch (Exception e) {
			showError(e.getMessage());
		}
	}

	private void save() {
		try {
			if (table.getRowCount() == 0) {
				showError("No Data To Save");
				return;
			}
			Godown godown = new Godown();
			godown.setId(id);
			List<GodownTransaction> oldTransactionList = new ArrayList<>();
			if (id != 0) {
				oldTransactionList = godownService.getGodownById(id).getTransactionList();
			}
			godown.setDate(date.getDate().atTime(LocalTime.now()));

			GodownTransaction tr;
			List<GodownTransaction> trList = new ArrayList<>();
			int row = table.getRowCount();
			for (int i = 0; i < row; i++) {
				tr = new GodownTransaction();
				tr.setGodown(godown);
				tr.setItemName(model.getValueAt(i, 1).toString());
				tr.setQty(Float.parseFloat(model.getValueAt(i, 2).toString()));
				trList.add(tr);
			}
			System.out.println(godown);
			for (GodownTransaction t : trList) {
				System.out.println(t);
			}
			godown.setTransactionList(trList);
			Backup backup = new Backup();
			int flag = godownService.saveGodown(godown);
			if (flag == 1) {
				JOptionPane.showMessageDialog(this, "Record Saved", "Success", JOptionPane.INFORMATION_MESSAGE);
				GodownStock gs = new GodownStock();
				for (GodownTransaction t : trList) {
					gs.setItemName(t.getItemName());
					gs.setQty(t.getQty());
					godownService.addGodownStock(gs);
				}
				// godownService.addGodownStock(godownStock)
				backup.writeInGodownFile("D:\\Madhuram\\Backup\\");
				backup.writeInGodownStock("D:\\Madhuram\\Backup\\");
				loadOld();
				clear2();
			}
			if (flag == 2) {
				JOptionPane.showMessageDialog(this, "Record Update", "Success", JOptionPane.INFORMATION_MESSAGE);
				GodownStock gs = new GodownStock();
				// reduce stock
				for (GodownTransaction t : oldTransactionList) {
					gs.setItemName(t.getItemName());
					gs.setQty(t.getQty());
					godownService.reduceGodownStock(gs);
				}
				// add new stock
				for (GodownTransaction t : trList) {
					gs.setItemName(t.getItemName());
					gs.setQty(t.getQty());
					godownService.addGodownStock(gs);
				}
				backup.writeInGodownFile("D:\\Madhuram\\Backup\\");
				backup.writeInGodownStock("D:\\Madhuram\\Backup\\");
				loadOld();
				clear2();
			}

		} catch (Exception e) {
			showError("Error " + e.getMessage());
		}
	}

	private void clear2() {
		model.setRowCount(0);
		txtItemName.setText("");
		txtQty.setText("");
		tblOld.clearSelection();
		date.setDateToToday();
		id = 0;

	}

	private void loadOld() {
		try {
			modelOld.setRowCount(0);
			int sr = 0;
			List<Godown> list = godownService.getCurrentMonthGodown();
			for (Godown g : list) {
				modelOld.addRow(new Object[] { ++sr, g.getId(), g.getDate() });
			}
		} catch (Exception e) {
			showError("Error In Loading Data " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void edit2() {
		try {
			int row = tblOld.getSelectedRow();
			if (row == -1) {
				return;
			} else {
				int rno = Integer.parseInt(modelOld.getValueAt(row, 1).toString());
				System.out.println("selected=" + rno);
				Godown godown = godownService.getGodownById(rno);
				System.out.println(godown);
				date.setDate(godown.getDate().toLocalDate());
				id = godown.getId();
				System.out.println(godown);
				int sr = 0;
				List<GodownTransaction> list = godown.getTransactionList();
				model.setRowCount(0);
				for (GodownTransaction g : list) {
					model.addRow(new Object[] { ++sr, g.getItemName(), g.getQty() });
					System.out.println("added" + g);
				}

			}
		} catch (Exception e) {
			showError(e.getMessage());
			e.printStackTrace();
		}
	}
}
