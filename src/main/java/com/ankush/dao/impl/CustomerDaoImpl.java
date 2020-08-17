package com.ankush.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ankush.dao.dao.CustomerDao;
import com.ankush.entities.Customer;
import com.ankush.util.HibernateUtil;

public class CustomerDaoImpl implements CustomerDao {

	private Session session;

	public CustomerDaoImpl() {
		this.session = HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public void closeSession() {
		this.session.close();
	}

	@Override
	public int saveCustomer(Customer customer) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Customer c = findCustomerById(customer.getId());
			if (c == null) {
				session.save(customer);
				session.getTransaction().commit();
				return 1;
			}
			else {
				session.createNativeQuery("delete from Address where id=:i").setParameter("i", c.getAddress().getId())
						.executeUpdate();

				c.setAddress(customer.getAddress());
				c.setContact(customer.getContact());
				c.setEmail(customer.getEmail());
				c.setName(customer.getName());

				session.update(c);
				session.getTransaction().commit();
				System.out.println("for update =====>" + c);
				return 2;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Customer findCustomerById(int id) {
		try {
			// ;
			this.session = HibernateUtil.getSessionFactory().openSession();
			this.session.beginTransaction();
			// Customer c = session.byId(Customer.class).getReference(id);
			Customer c = this.session.get(Customer.class, id);
			System.out.println("Customer Loaded");
			this.session.getTransaction().commit();
			return c;
		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Customer> getAllCustomer() {
		try {
			this.session.beginTransaction();
			String hql = "select * from Customer";
			List<Customer> list = session.createNativeQuery(hql, Customer.class).list();
			this.session.getTransaction().commit();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getAllCustomerName() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select name from Customer";
			@SuppressWarnings("unchecked")
			List<String> list = session.createNativeQuery(hql).list();
			session.getTransaction().commit();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public int getCustomerIdByName(String name) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			int id = session.createQuery("select id from Customer where name=:name", Integer.class)
					.setParameter("name", name).getSingleResult();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getNewCustomerId() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select MAX(id) from Customer";
			Query<Integer> query = session.createQuery(hql, Integer.class);
			List<Integer> list = query.list();
			return (int) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static void main(String[] args) {
		System.out.println(new CustomerDaoImpl().getCustomerIdByName("Ankush"));
	}

	@Override
	public String getCustomerNameById(int id) {
		if (id == 0) {
			return "";
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String name = session.createQuery("select name from Customer where id=:id", String.class)
					.setParameter("id", id).getSingleResult();
			return name;

		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
