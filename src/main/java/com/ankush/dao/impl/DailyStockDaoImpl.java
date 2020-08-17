package com.ankush.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.ankush.dao.dao.DailyStockDao;
import com.ankush.entities.DailyStock;
import com.ankush.util.HibernateUtil;

public class DailyStockDaoImpl implements DailyStockDao {

	@Override
	public int addDailyStock(DailyStock dailyStock) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			DailyStock sd = getDailyStockByName(dailyStock.getItemName());
			if (sd == null) {
				session.save(dailyStock);
				session.getTransaction().commit();
				return 1;
			} else {
				sd.setQty(sd.getQty() + dailyStock.getQty());
				session.update(sd);
				session.getTransaction().commit();
				return 2;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public DailyStock getDailyStockByName(String itemName) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from DailyStock where itemName=:name";
			DailyStock ds = session.createNativeQuery(hql, DailyStock.class).setParameter("name", itemName)
					.uniqueResult();
			return ds;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DailyStock getDailyStockById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			DailyStock ds = session.get(DailyStock.class, id);
			return ds;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<DailyStock> getAllDailyStock() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from DailyStock";
			List<DailyStock> list = session.createNativeQuery(hql, DailyStock.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public float getItemStockByName(String itemName) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {try {
			session.beginTransaction();
			String hql = "select Qty from DailyStock where itemName=:name";
			if (session.createNativeQuery(hql).setParameter("name", itemName).uniqueResult() != null)
				return (Float) session.createNativeQuery(hql).setParameter("name", itemName).uniqueResult();
			else
				return 0.0f;
			// float q = (Float) session.createNativeQuery(hql).setParameter("name",
			// itemName).uniqueResult();
			// return q;
		} catch (Exception e) {
			e.printStackTrace();
			return 0.0f;
		}
	}

}

@Override
public int reduceStock(String itemName, float qty) {
	try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		session.beginTransaction();
		DailyStock dc = getDailyStockByName(itemName);
		// DailyStock dc = session.get(DailyStock.class, id);
		System.out.println("I got Daily Stock To reduce======>" + dc);
		dc.setQty(dc.getQty() - qty);
		session.update(dc);
		session.getTransaction().commit();
		return 1;

	} catch (Exception e) {
		e.printStackTrace();
		return 0;
	}
}
}