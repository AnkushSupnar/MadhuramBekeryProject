package com.ankush.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ankush.dao.dao.EmployeeDao;
import com.ankush.entities.Employee;
import com.ankush.util.HibernateUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	private Session session;

	public EmployeeDaoImpl() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void closeSession() {
		getEmployeesession().close();
	}

	public Session getEmployeesession() {
		return this.session;
	}

	@Override
	public int saveEmployee(Employee employee) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			int flag = 0;
			Employee e = findEmployeeById(employee.getId());
			if (employee.getId() == 0 || e == null) {
				flag = (int) session.save(employee);
				session.getTransaction().commit();
				return flag;
			} else {
				session.createNativeQuery("delete from Address where id=:i").setParameter("i", e.getAddress().getId())
						.executeUpdate();
				session.update(employee);
				session.getTransaction().commit();
				return 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Employee> listEmployee() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
			Root<Employee> root = query.from(Employee.class);
			query.select(root);
			Query<Employee> q = session.createQuery(query);
			List<Employee> list = q.getResultList();
			session.getTransaction().commit();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Employee findEmployeeById(int id) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			// Query<Employee> query =
			Employee emp = session.get(Employee.class, id);
			session.getTransaction().commit();
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int getNewEmployeeId() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select MAX(id) from Employee";
			@SuppressWarnings("unchecked")
			Query<Integer> query = session.createQuery(hql);
			List<Integer> list = query.list();
			int id = (int) list.get(0);
			session.getTransaction().commit();
			return id + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public List<Object[]> getAllEmployeeFullName()
		{
			try(Session session = HibernateUtil.getSessionFactory().openSession()) {
				session.beginTransaction();
				String hql="Select fName,mName,lName from Employee";
				@SuppressWarnings("unchecked")
				List<Object[]> list = session.createNativeQuery(hql).list();
				session.getTransaction().commit();
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		@Override
		public Employee getEmployeeIdUsingFullName(String name) {
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				session.beginTransaction();
				String fname[] = name.split(" ");
				System.out.println("FName===" + fname[0]);
				String hql = "Select * from Employee where fName=:first AND mName=:middle AND lName=:last";

				List<Employee> list = session.createNativeQuery(hql, Employee.class).setParameter(
						"first",
						fname[0])
						.setParameter("middle", fname[1]).setParameter("last", fname[2]).getResultList();
				return list.get(0);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		}

}
