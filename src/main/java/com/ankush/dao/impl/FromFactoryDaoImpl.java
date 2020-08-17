package com.ankush.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import com.ankush.dao.dao.FromFactoryDao;
import com.ankush.entities.FromFactory;
import com.ankush.entities.FromFactoryTransaction;
import com.ankush.util.HibernateUtil;

public class FromFactoryDaoImpl implements FromFactoryDao {

	@Override
	public int saveFromFactory(FromFactory fromFactory) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			FromFactory f = getFromFactoryById(fromFactory.getId());
			if (fromFactory.getId() == 0 || f == null) {
				session.save(fromFactory);
				session.getTransaction().commit();
				return 1;
			}
			else {
				for (FromFactoryTransaction tr : f.getTransactions()) {
					session.delete(tr);
				}
				f.setDate(fromFactory.getDate());
				f.setStatus(fromFactory.getStatus());
				f.setTransactions(fromFactory.getTransactions());
				f.setCounter(fromFactory.getCounter());
				session.update(f);
				session.getTransaction().commit();
				return 2;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public FromFactory getFromFactoryById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			FromFactory f = session.get(FromFactory.class, id);
			return f;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<FromFactory> getAllFromFactory() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from FromFactory";
			List<FromFactory> list = session.createNativeQuery(hql, FromFactory.class).list();

			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<FromFactory> getDateWiseFromFactory(LocalDate date) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from FromFactory where Date(date)=:d";
			List<FromFactory> list = session.createNativeQuery(hql, FromFactory.class).setParameter("d", date)
					.list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
