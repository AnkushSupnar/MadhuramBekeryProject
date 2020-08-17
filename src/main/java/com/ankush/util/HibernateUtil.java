package com.ankush.util;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.ankush.entities.Address;
import com.ankush.entities.Bill;
import com.ankush.entities.Counter;
import com.ankush.entities.Customer;
import com.ankush.entities.DailyStock;
import com.ankush.entities.Employee;
import com.ankush.entities.FromFactory;
import com.ankush.entities.FromFactoryTransaction;
import com.ankush.entities.Godown;
import com.ankush.entities.GodownStock;
import com.ankush.entities.GodownTransaction;
import com.ankush.entities.Item;
import com.ankush.entities.Login;
import com.ankush.entities.ToCounter;
import com.ankush.entities.ToCounterTransaction;
import com.ankush.entities.Transaction;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	// for Local instance
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				Properties setting = new Properties();
				setting.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				setting.put(Environment.URL, "jdbc:mysql://localhost:3306/madhuram");
				setting.put(Environment.USER, "root");
				setting.put(Environment.PASS, "2355");
				setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				setting.put(Environment.SHOW_SQL, "false");
				setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				setting.put(Environment.HBM2DDL_AUTO, "validate");
				

				configuration.setProperties(setting);
				configuration.addAnnotatedClass(Employee.class);
				configuration.addAnnotatedClass(Address.class);
				configuration.addAnnotatedClass(Login.class);
				configuration.addAnnotatedClass(Item.class);
				configuration.addAnnotatedClass(Customer.class);
				configuration.addAnnotatedClass(Counter.class);
				configuration.addAnnotatedClass(Bill.class);
				configuration.addAnnotatedClass(Transaction.class);
				configuration.addAnnotatedClass(FromFactory.class);
				configuration.addAnnotatedClass(FromFactoryTransaction.class);
				configuration.addAnnotatedClass(ToCounter.class);
				configuration.addAnnotatedClass(ToCounterTransaction.class);
				configuration.addAnnotatedClass(DailyStock.class);
				configuration.addAnnotatedClass(Godown.class);
				configuration.addAnnotatedClass(GodownTransaction.class);
				configuration.addAnnotatedClass(GodownStock.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				System.out.println("java hibernate config service registry created");
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return sessionFactory;
	}

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			System.out.println("Session Created");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
