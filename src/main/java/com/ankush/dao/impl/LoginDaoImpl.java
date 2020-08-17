package com.ankush.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ankush.dao.dao.LoginDao;
import com.ankush.entities.Login;
import com.ankush.util.HibernateUtil;

public class LoginDaoImpl implements LoginDao {

	private Session session;
	@Override
	public Session createSession() {
		this.session = HibernateUtil.getSessionFactory().openSession();
		return session;
	}

	@Override
	public void closeSession() {
		this.session.close();

	}

	@Override
	public int saveUser(Login login) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			if (getLoginByName(login.getUserName()) == null) {
				session.save(login);
				session.getTransaction().commit();
				return 1;
			}
		else {
				// session.update(login);
				// session.getTransaction().commit();
				return 2;
		}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Login getLoginByName(String userName) {
		try {
			createSession().beginTransaction();
			String hql = "from Login where userName=:name";

			Query<Login> query = session.createQuery(hql, Login.class);
			query.setParameter("name", userName);
			Login l = query.uniqueResult();
			session.getTransaction().commit();
			closeSession();
			return l;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setLoginStatus(int id, String status) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "update Login set status=:stat where id=:id";

			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			query.setParameter("stat", status);
			query.executeUpdate();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<String> getAllUserName() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select userName from Login";
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
	public List<String> getActiveCounter() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select status from Login where status!='inactive'";
			@SuppressWarnings("unchecked")
			List<String> list = session.createNativeQuery(hql).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Login getloginById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Login l = session.get(Login.class, id);
			session.getTransaction().commit();
			return l;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Login> getAllLogin() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from Login";
			List<Login> list = session.createNativeQuery(hql, Login.class).list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
