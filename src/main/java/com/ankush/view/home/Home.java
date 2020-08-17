package com.ankush.view.home;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import com.ankush.backup.Backup;
import com.ankush.entities.Login;
import com.ankush.service.service.LoginService;
import com.ankush.service.serviceImpl.LoginServiceImpl;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -138811794404156647L;
	private JPanel panelDailyTransaction;
	private PanelAddNew paneladdNew;
	private ReportPanel panelReport;
	private LoginService loginService;

	public Home(int id) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				loginService.setLoginStatus(id, "inactive");
				new LoginFrame();
				Backup back = new Backup();
				String path = "D:\\Madhuram\\Backup\\";
				back.writeInBillFile(path);
				back.writeInCounterFile(path);
				back.writeInCustomerFile(path);
				back.writeInDailyFile(path);
				back.writeInEmployeeFile(path);
				back.writeInFromFactoryFile(path);
				back.writeInToCounterFile(path);
				back.writeInItemFile(path);
				back.writeInLoginFile(path);
				back.writeInAddressFile(path);
				dispose();
			}
		});
		this.loginService = new LoginServiceImpl();
		Login login = loginService.getloginById(id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(
				"Home                                                                                Developed By Ankush Supnar(8329394603)");
		setLocation(0, 0);
		setSize(1290, 711);
		getContentPane().setBackground(new Color(1, 87, 155));
		getContentPane().setLayout(null);

		JLabel label = new JLabel(login.getUserName());
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Kiran", Font.PLAIN, 25));
		label.setBounds(904, 12, 131, 24);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(1, 87, 155));
		panel.setBorder(new LineBorder(Color.WHITE, 1, true));
		panel.setBounds(0, 41, 212, 625);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE, 1, true));
		panel_1.setBackground(new Color(1, 87, 155));
		panel_1.setBounds(0, 0, 212, 42);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("DASHBOARD");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 48));
		lblNewLabel.setForeground(new Color(204, 204, 204));
		lblNewLabel.setBounds(1, -6, 209, 52);
		panel_1.add(lblNewLabel);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new LineBorder(Color.WHITE, 1, true));
		panel_1_1.setBackground(new Color(1, 87, 155));
		panel_1_1.setBounds(210, 0, 1064, 42);
		getContentPane().add(panel_1_1);
		panel_1_1.setLayout(null);
		panel_1_1.add(label);

		JLabel lblMadhuramBackers = new JLabel("MADHURAM BAKERY");
		lblMadhuramBackers.setForeground(Color.ORANGE);
		lblMadhuramBackers.setFont(new Font("Algerian", Font.BOLD, 48));
		lblMadhuramBackers.setBounds(105, -6, 520, 52);
		panel_1_1.add(lblMadhuramBackers);

		JLabel lblNewLabel_1 = new JLabel("laa^gaIna : ");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Kiran", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(836, 12, 64, 24);
		panel_1_1.add(lblNewLabel_1);

		JLabel lblDailyTransaction = new MenuLabel("raojacao vyavahar");
		lblDailyTransaction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hidePanel(panelDailyTransaction);
			}
		});
		lblDailyTransaction.setBounds(43, 26, 115, 29);
		panel.add(lblDailyTransaction);

		panelDailyTransaction = new PanelDailyTrnasaction(id);
		panelDailyTransaction.setBounds(212, 42, 1062, 679);
		getContentPane().add(panelDailyTransaction);
		panelDailyTransaction.setVisible(false);

		paneladdNew = new PanelAddNew();
		paneladdNew.setBounds(212, 42, 1062, 679);
		getContentPane().add(paneladdNew);
		paneladdNew.setVisible(false);

		MenuLabel lblAddNew = new MenuLabel("naivana banavaa");
		lblAddNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hidePanel(paneladdNew);
			}
		});
		lblAddNew.setBounds(43, 92, 115, 29);
		panel.add(lblAddNew);

		panelReport = new ReportPanel();
		panelReport.setBounds(212, 42, 1062, 679);
		getContentPane().add(panelReport);
		panelReport.setVisible(false);

		MenuLabel lblReport = new MenuLabel("irpaaoT-");
		lblReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hidePanel(panelReport);
			}
		});
		lblReport.setBounds(43, 172, 115, 29);
		panel.add(lblReport);

		MenuLabel mnlblLaagaaat = new MenuLabel("irpaaoT-");
		mnlblLaagaaat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginService.setLoginStatus(id, "inactive");
				new LoginFrame();
				dispose();
			}
		});
		mnlblLaagaaat.setText("laa^gaAa{T");
		mnlblLaagaaat.setBounds(43, 591, 115, 29);
		panel.add(mnlblLaagaaat);
		if (id != 1) {
			lblAddNew.setVisible(false);
		}

		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		new Home(1);
	}

	private void hidePanel(JPanel panel) {

		panelDailyTransaction.setVisible(false);
		paneladdNew.setVisible(false);
		panelReport.setVisible(false);
		panel.setVisible(true);

	}
}
