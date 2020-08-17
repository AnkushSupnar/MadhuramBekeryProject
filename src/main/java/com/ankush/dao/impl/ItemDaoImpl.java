package com.ankush.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.ankush.dao.dao.ItemDao;
import com.ankush.entities.Item;
import com.ankush.util.HibernateUtil;

public class ItemDaoImpl implements ItemDao {

	private Session session;

	public ItemDaoImpl() {
		this.session = HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public Session getSession() {
		return this.session;
	}

	@Override
	public int saveItem(Item item) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			System.out.println("Itemm Id " + item.getId());
			Item i = findItemById(item.getId());

			if (item.getId() == 0 || i == null) {
				session.save(item);
				session.getTransaction().commit();
				return 1;
			} else {
				System.out.println("Item Found =" + item);
				i = session.load(Item.class, i.getId());
				i.setItemName(item.getItemName());
				i.setRate(item.getRate());
				session.update(i);
				session.getTransaction().commit();
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Item findItemById(int id) {
		try {
			this.session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Item i = session.byId(Item.class).getReference(id);
			session.getTransaction().commit();
			return i;

		} catch (Exception e) {
			e.printStackTrace();
			getSession().close();
			return null;
		}
	}

	@Override
	public List<Item> getAllItem() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from Item";
			List<Item> list = session.createNativeQuery(hql, Item.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getAllItemName() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select itemName from Item ORDER BY itemName";
			@SuppressWarnings("unchecked")
			List<String> list = session.createNativeQuery(hql).list();
			session.getTransaction().commit();
			return list;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public float getItemRateByName(String name) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select rate from Item where itemName=:name";

			float rate = (Float) session.createNativeQuery(hql).setParameter("name", name).getSingleResult();
			return rate;

		} catch (Exception e) {
			// e.printStackTrace();
			return 0.0f;
		}
	}

	@Override
	public Item findItemByName(String name) {
		System.out.println(name);
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "Select * from Item where itemName=:name";
			List<Item> list = session.createNativeQuery(hql, Item.class).setParameter("name", name).list();
			if (list.size() != 0)
				return list.get(0);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/// public static void main(String[] args) {
	// ItemDao dao = new ItemDaoImpl();
//		List<Item> ilist = dao.getAllItem();
//		for (Item i : ilist) {
//			System.out.println(i);
//		}
//		Item item = new Item();
//
//		item.setItemName("Item1");
//		item.setRate(100.0f);
//		System.out.println("Save Item= " + dao.saveItem(item));
//
//		item.setRate(200.0f);
//		System.out.println("update Item= " + dao.saveItem(item));

	// }
}
