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
import com.ankush.entities.Employee;
import com.ankush.entities.Item;
import com.ankush.entities.Login;
import com.ankush.entities.Transaction;
///for Clever cloude Instance
public class HibernateUtil2 {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				Properties setting = new Properties();
				setting.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				setting.put(Environment.URL,
						"jdbc:mysql://ujiiv6cikxd7wtoo:aTSdFQNukeNJueBEiDn0@brv4yfibcuaxk2jthjql-mysql.services.clever-cloud.com:3306/brv4yfibcuaxk2jthjql");
				setting.put(Environment.USER, "ujiiv6cikxd7wtoo");
				setting.put(Environment.PASS, "aTSdFQNukeNJueBEiDn0");
				setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
				setting.put(Environment.SHOW_SQL, "true");
				setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				setting.put(Environment.HBM2DDL_AUTO, "update");

				configuration.setProperties(setting);
				configuration.addAnnotatedClass(Employee.class);
				configuration.addAnnotatedClass(Address.class);
				configuration.addAnnotatedClass(Login.class);
				configuration.addAnnotatedClass(Item.class);
				configuration.addAnnotatedClass(Customer.class);
				configuration.addAnnotatedClass(Counter.class);
				configuration.addAnnotatedClass(Bill.class);
				configuration.addAnnotatedClass(Transaction.class);

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
