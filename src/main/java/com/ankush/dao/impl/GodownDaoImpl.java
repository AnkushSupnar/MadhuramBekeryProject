package com.ankush.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import com.ankush.dao.dao.GodownDao;
import com.ankush.entities.Godown;
import com.ankush.entities.GodownStock;
import com.ankush.entities.GodownTransaction;
import com.ankush.util.HibernateUtil;

public class GodownDaoImpl implements GodownDao {

	@Override
	public int saveGodown(Godown godown) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Godown g = getGodownById(godown.getId());
			System.out.println(g);
			if (godown.getId() == 0 || g == null) {
				session.save(godown);

				session.getTransaction().commit();
				return 1;
			}
			else {
				g.setDate(godown.getDate());
				List<GodownTransaction> tr = g.getTransactionList();
				for (GodownTransaction t : tr) {

					session.createNativeQuery("delete from GodownTransaction where id=:i").setParameter("i", t.getId())
							.executeUpdate();
				}
				g.setTransactionList(godown.getTransactionList());
				session.update(g);
				session.getTransaction().commit();
				return 2;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Godown getGodownById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Godown godown = session.get(Godown.class, id);
			return godown;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Godown> getAllGodownList() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from Godown";
			List<Godown> list = session.createNativeQuery(hql, Godown.class).list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Godown> getDateWiseGodown(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Godown> getCurrentMonthGodown() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from Godown where  `date` >= DATE_SUB(CURDATE(), INTERVAL 0 MONTH)";
			List<Godown> list = session.createNativeQuery(hql, Godown.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int addGodownStock(GodownStock godownStock) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from GodownStock where ItemName=:name";
			GodownStock gs = session.createNativeQuery(hql, GodownStock.class)
					.setParameter("name", godownStock.getItemName()).uniqueResult();
			if (gs == null) {
				// not found
				session.save(godownStock);
				session.getTransaction().commit();
				return 1;
			} else {
				// found
				gs.setQty(gs.getQty() + godownStock.getQty());
				session.update(gs);
				session.getTransaction().commit();
				return 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;

		}
	}

	@Override
	public int reduceGodownStock(GodownStock godownStock) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "Select * from GodownStock where ItemName=:name";
			GodownStock gs = session.createNativeQuery(hql, GodownStock.class)
					.setParameter("name", godownStock.getItemName()).uniqueResult();
			if (gs != null) {
				gs.setQty(gs.getQty() - godownStock.getQty());
				session.update(gs);
				session.getTransaction().commit();
				return 1;
			}
			else {
				session.save(godownStock);
				session.getTransaction().commit();
			}
			return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<GodownStock> getGodownStock() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from GodownStock";
			List<GodownStock> list = session.createNativeQuery(hql, GodownStock.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		return null;
		}
	}

	@Override
	public float getItemStock(String itemName) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from GodownStock where ItemName=:name";
			GodownStock gs = session.createNativeQuery(hql, GodownStock.class).setParameter("name", itemName)
					.uniqueResult();
			return gs.getQty();

		} catch (Exception e) {
			e.printStackTrace();
			return 0.0f;
		}
	}

}
