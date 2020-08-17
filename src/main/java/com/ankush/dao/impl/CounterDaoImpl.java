package com.ankush.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.ankush.dao.dao.CounterDao;
import com.ankush.entities.Counter;
import com.ankush.util.HibernateUtil;

public class CounterDaoImpl implements CounterDao {
	private Session session;

	public CounterDaoImpl() {
		this.session = HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public void closeSession() {
		this.session.close();
	}

	@Override
	public int saveCounter(Counter counter) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Counter c = getCounterById(counter.getId());
			System.out.println("form Dao=>" + c);
			if (counter.getId() == 0 || c == null) {
				session.save(counter);
				session.getTransaction().commit();
				return 1;
			} else {
				c.setCounterName(counter.getCounterName());
				c.setDescription(counter.getDescription());
				session.update(c);
				session.getTransaction().commit();
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Counter getCounterById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Counter counter = session.get(Counter.class, id);
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Counter> getAllCounter() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from Counter";
			List<Counter> list = session.createNativeQuery(hql, Counter.class).list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getAllCounterName() {
		try {
			session.beginTransaction();
			String hql = "select counterName from Counter";
			@SuppressWarnings("unchecked")
			List<String> list = session.createNativeQuery(hql).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public int getCounterIdByName(String name) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select id from Counter where counterName=:name";
			int id = (int) session.createNativeQuery(hql).setParameter("name", name).getSingleResult();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
	}
	}

}
