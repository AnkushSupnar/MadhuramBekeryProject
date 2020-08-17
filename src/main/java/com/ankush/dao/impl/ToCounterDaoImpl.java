package com.ankush.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import com.ankush.dao.dao.ToCounterDao;
import com.ankush.entities.ToCounter;
import com.ankush.entities.ToCounterTransaction;
import com.ankush.util.HibernateUtil;

public class ToCounterDaoImpl implements ToCounterDao {

	@Override
	public int saveToCounter(ToCounter toCounter) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			System.out.println("ToCounter Session Created");
			ToCounter tc = getToCounterById(toCounter.getId());
			if (toCounter.getId() == 0 || tc == null) {
				session.save(toCounter);
				session.getTransaction().commit();
				return 1;
			} else {
				for (ToCounterTransaction tr : tc.getTransactions()) {
					session.delete(tr);
				}
				tc.setTransactions(toCounter.getTransactions());
				tc.setCounter(toCounter.getCounter());
				tc.setDate(toCounter.getDate());
				tc.setFromFactoryId(toCounter.getFromFactoryId());
				tc.setStatus(toCounter.getStatus());
				session.update(tc);
				session.getTransaction().commit();
				return 2;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ToCounter getToCounterById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			ToCounter toCounter = session.get(ToCounter.class, id);
			return toCounter;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ToCounter> getAllToCounter() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from ToCounter";
			List<ToCounter> list = session.createNativeQuery(hql, ToCounter.class).list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ToCounter> getDateWiseToCounter(LocalDate date) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			// String hql = "";
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
