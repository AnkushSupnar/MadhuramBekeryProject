package com.ankush.backup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.ankush.entities.Address;
import com.ankush.entities.Bill;
import com.ankush.entities.Counter;
import com.ankush.entities.Customer;
import com.ankush.entities.DailyStock;
import com.ankush.entities.Employee;
import com.ankush.entities.FromFactoryTransaction;
import com.ankush.entities.Godown;
import com.ankush.entities.GodownStock;
import com.ankush.entities.GodownTransaction;
import com.ankush.entities.Item;
import com.ankush.entities.Login;
import com.ankush.entities.ToCounter;
import com.ankush.entities.ToCounterTransaction;
import com.ankush.entities.Transaction;
import com.ankush.service.service.AddressService;
import com.ankush.service.service.BillService;
import com.ankush.service.service.CounterService;
import com.ankush.service.service.CustomerService;
import com.ankush.service.service.DailyStockService;
import com.ankush.service.service.EmployeeService;
import com.ankush.service.service.FromFactoryService;
import com.ankush.service.service.GodownService;
import com.ankush.service.service.ItemService;
import com.ankush.service.service.LoginService;
import com.ankush.service.service.ToCounterService;
import com.ankush.service.serviceImpl.AddressServiceImpl;
import com.ankush.service.serviceImpl.BillServiceImpl;
import com.ankush.service.serviceImpl.CounterServiceImpl;
import com.ankush.service.serviceImpl.CustomerServiceImpl;
import com.ankush.service.serviceImpl.DailyStockServiceImpl;
import com.ankush.service.serviceImpl.EmployeeServiceImpl;
import com.ankush.service.serviceImpl.FromFactoryServiceImpl;
import com.ankush.service.serviceImpl.GodownServiceImpl;
import com.ankush.service.serviceImpl.ItemServiceImpl;
import com.ankush.service.serviceImpl.LoginServiceImpl;
import com.ankush.service.serviceImpl.ToCounterServiceImpl;

public class Backup {


	public Backup() {

//		writeInBillFile(path);
//		writeInCounterFile(path);
//		writeInCustomerFile(path);
//		writeInDailyFile(path);
//		writeInEmployeeFile(path);
//		writeInFromFactoryFile(path);
//		writeInToCounterFile(path);
//		writeInItemFile(path);
//		writeInLoginFile(path);
//		writeInAddressFile(path);
	}

	public static void main(String[] args) {
		new Backup().writeInBillFile("D:\\Madhuram\\Backup\\");
	}

	public void backup() {
		Process p = null;
		try {
			Runtime runtime = Runtime.getRuntime();
			p = runtime.exec("mysqldump -u root -p 2355 --add-drop-database -B madhuram -r " + "D:\\Madhuram\\"
					+ "backup"
					+ ".sql");
//change the dbpass and dbname with your dbpass and dbname
			int processComplete = p.waitFor();

			System.out.println(processComplete);
			if (processComplete == 0) {

				System.out.println("Backup created successfully!");

			} else {
				System.out.println("Could not create the backup");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void restoreData() {
		String uname = "root";
		String dbPassword = "2355";
		String source = "D:\\Madhuram\\backup.sql";
		String[] restoreCmd = new String[] { "mysql ", "--user=" + uname, "--password=" + dbPassword, "-e",
				"source " + source };

		Process runtimeProcess;
		try {

			runtimeProcess = Runtime.getRuntime().exec(restoreCmd);
			int processComplete = runtimeProcess.waitFor();

			if (processComplete == 0) {
				System.out.println("Restored successfully!");
			} else {
				System.out.println("Could not restore the backup!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public int writeInBillFile(String path) {
		int flag = 0;
		try {

			String file = path + "bill.txt";

			File f = new File(file);

			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}

			// Writing Data in File

			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			BillService billService = new BillServiceImpl();
			List<Bill> bills = billService.getAllBill();
			for (Bill b : bills) {
				System.out.println(b);
				writer.write(b.getBillNo() + "|" + b.getAmount() + "|" + b.getCustomerId() + "|" + b.getPayMode() + "|"
						+ b.getStatus() + "|" + b.getCounter().getId() + "|" + b.getDate() + "|"
						+ b.getLogin().getId());
				writer.newLine();
			}

			writer.newLine();
			writer.close();

			String fileName = path + "transacion.txt";

			File file2 = new File(fileName);
			if (file2.exists() && !file2.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				file2.getParentFile().mkdirs();
				file2.createNewFile();
			}
			writer = new BufferedWriter(new FileWriter(file2));
			for (Bill b : bills) {
				List<Transaction> tr = b.getTransactionList();
				for (Transaction t : tr) {
					writer.write(t.getId() + "|" + t.getItemName() + "|" + t.getQty() + "|" + t.getRate() + "|"
							+ t.getAmount() + "|" + t.getBill().getBillNo());
					writer.newLine();
				}
			}
			writer.close();

			flag = 1;

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
		}
		return flag;

	}

	public int writeInCounterFile(String path) {

		try {
			String file = path + "counter.txt";

			File f = new File(file);

			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			CounterService counterService = new CounterServiceImpl();
			List<Counter> list = counterService.getAllCounter();
			for (Counter c : list) {
				writer.write(c.getId() + "|");
				writer.write(c.getCounterName() + "|");
				writer.write(c.getDescription());
				writer.newLine();
			}
			writer.close();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int writeInCustomerFile(String path) {

		try {
			String file = path + "customer.txt";

			File f = new File(file);

			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			CustomerService customerService = new CustomerServiceImpl();
			List<Customer> list = customerService.getAllCustomer();
			for (Customer c : list) {
				writer.write(c.getId() + "|");
				writer.write(c.getName() + "|");
				writer.write(c.getAddress().getId() + "|");
				writer.write(c.getContact() + "|");
				// System.out.println(c.getContact());
				writer.write(c.getEmail() + "|");
				writer.newLine();
			}
			writer.close();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int writeInDailyFile(String path) {

		try {
			String file = path + "dailystock.txt";

			File f = new File(file);

			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			DailyStockService dailyStockService = new DailyStockServiceImpl();
			List<DailyStock> list = dailyStockService.getAllDailyStock();
			for (DailyStock d : list) {
				System.out.println(d);
				writer.write(d.getId() + "|");
				writer.write(d.getItemName() + "|");
				writer.write(d.getQty() + "|");
				writer.newLine();
			}
			writer.close();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int writeInEmployeeFile(String path) {

		try {
			String file = path + "employee.txt";

			File f = new File(file);

			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			EmployeeService employeeService = new EmployeeServiceImpl();
			List<Employee> list = employeeService.listEmployee();
			for (Employee e : list) {
				writer.write(e.getId() + "|");
				writer.write(e.getfName() + "|");
				writer.write(e.getmName() + "|");
				writer.write(e.getlName() + "|");
				writer.write(e.getGender() + "|");
				writer.write(e.getContact() + "|");
				writer.write(e.getDesignation() + "|");
				writer.write(e.getSalaryType() + "|");
				writer.write(e.getSalary() + "|");
				writer.write(e.getAddress().getId() + "|");
				writer.write(e.getJoiningDate() + "|");

				writer.newLine();
			}
			writer.close();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int writeInFromFactoryFile(String path) {

		try {
			String file = path + "fromfactory.txt";

			File f = new File(file);

			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			FromFactoryService fromFactoryService = new FromFactoryServiceImpl();
			List<com.ankush.entities.FromFactory> list = fromFactoryService.getAllFromFactory();
			System.out.println("Read Total Records From FromFactory" + list.size());
			for (com.ankush.entities.FromFactory e : list) {
				System.out.println(e.getId());
				writer.write(e.getId() + "|");
				writer.write(e.getStatus() + "|");
				writer.write(e.getCounter().getId() + "|");
				writer.write(e.getDate() + "|");
				writer.newLine();
			}
			writer.close();

			file = path + "fromfactorytransaction.txt";

			f = new File(file);

			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			writer = new BufferedWriter(new FileWriter(f));
			for (com.ankush.entities.FromFactory e : list) {
				for (FromFactoryTransaction t : e.getTransactions()) {
					writer.write(t.getId() + "|");
					writer.write(t.getItem() + "|");
					writer.write(t.getQty() + "|");
					writer.write(t.getFactory().getId() + "|");
					writer.newLine();
				}
			}
			writer.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int writeInToCounterFile(String path) {

		try {
			String file = path + "tocounter.txt";

			File f = new File(file);

			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			ToCounterService toCounterService = new ToCounterServiceImpl();
			List<ToCounter> list = toCounterService.getAllToCounter();
			for (ToCounter e : list) {
				writer.write(e.getId() + "|");
				writer.write(e.getDate() + "|");
				writer.write(e.getFromFactoryId() + "|");
				writer.write(e.getStatus() + "|");
				writer.write(e.getCounter().getId() + "|");
				writer.newLine();
			}
			writer.close();

			file = path + "tocountertransaction.txt";

			f = new File(file);

			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			writer = new BufferedWriter(new FileWriter(f));
			for (ToCounter e : list) {
				for (ToCounterTransaction t : e.getTransactions()) {
					writer.write(t.getId() + "|");
					writer.write(t.getItemName() + "|");
					writer.write(t.getQty() + "|");
					writer.write(t.getToCounter().getId() + "|");
					writer.newLine();
				}
			}
			writer.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int writeInItemFile(String path) {

		try {
			String file = path + "item.txt";

			File f = new File(file);

			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			ItemService itemService = new ItemServiceImpl();
			List<Item> list = itemService.getAllItem();
			for (Item i : list) {
				writer.write(i.getId() + "|");
				writer.write(i.getItemName() + "|");
				writer.write(i.getRate() + "|");
				writer.newLine();
			}
			writer.close();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int writeInLoginFile(String path) {

		try {
			String file = path + "login.txt";

			File f = new File(file);

			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			LoginService logiService = new LoginServiceImpl();
			List<Login> list = logiService.getAllLogin();
			for (Login i : list) {
				writer.write(i.getId() + "|");
				writer.write(i.getUserName() + "|");
				writer.write(i.getPassword() + "|");
				writer.write(i.getStatus() + "|");
				writer.write(i.getEmployee().getId() + "|");

				writer.newLine();
			}
			writer.close();

			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int writeInAddressFile(String path) {
		try {
			String file = path + "address.txt";
			File f = new File(file);
			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			AddressService addService = new AddressServiceImpl();
			List<Address> list = addService.getAllAddress();
			for (Address i : list) {
				writer.write(i.getId() + "|");
				writer.write(i.getBuilding() + "|");
				writer.write(i.getRoomNo() + "|");
				writer.write(i.getCity() + "|");
				writer.write(i.getTaluka() + "|");
				writer.write(i.getDistrict() + "|");
				writer.write(i.getPin() + "|");

				writer.newLine();
			}
			writer.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int writeInGodownFile(String path) {
		try {
			String file = path + "godown.txt";
			File f = new File(file);
			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			GodownService gs = new GodownServiceImpl();
			List<Godown> list = gs.getAllGodownList();
			for (Godown g : list) {
				writer.write(g.getId() + "|");
				writer.write("" + g.getDate() + "|");
				writer.newLine();
			}
			writer.close();

			String fileName = path + "godownTransacion.txt";

			File file2 = new File(fileName);
			if (file2.exists() && !file2.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				file2.getParentFile().mkdirs();
				file2.createNewFile();
			}
			writer = new BufferedWriter(new FileWriter(file2));
			for (Godown g : list) {
				List<GodownTransaction> trList = g.getTransactionList();
				for (GodownTransaction tr : trList) {
					writer.write(tr.getId() + "|");
					writer.write(tr.getItemName() + "|");
					writer.write(tr.getGodown().getId() + "|");
					writer.write("" + tr.getQty());
					writer.newLine();
				}
			}
			writer.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int writeInGodownStock(String path) {
		try {
			String file = path + "godownStock.txt";
			File f = new File(file);
			if (f.exists() && !f.isDirectory()) {
				System.out.println("Exist");
				// do something
			} else {
				System.out.println("Not found. Creating new File");
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			GodownService gs = new GodownServiceImpl();
			List<GodownStock> list = gs.getGodownStock();
			for (GodownStock i : list) {
				writer.write(i.getId() + "|");
				writer.write(i.getItemName() + "|");
				writer.write("" + i.getQty());
				writer.newLine();
			}
			writer.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
