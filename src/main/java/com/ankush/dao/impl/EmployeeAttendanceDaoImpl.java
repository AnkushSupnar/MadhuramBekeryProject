package com.ankush.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import com.ankush.dao.dao.EmployeeAttendanceDao;
import com.ankush.entities.EmployeeAttendance;
import com.ankush.util.HibernateUtil;

public class EmployeeAttendanceDaoImpl implements EmployeeAttendanceDao {

	@Override
	public int saveEmployeeAttendance(EmployeeAttendance ea) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			EmployeeAttendance e = getEmployeeAttendance(ea);
			int flag = 0;
			if (e == null) {
				flag = (int) session.save(ea);
				session.getTransaction().commit();
				return flag;
			} else {
				e.setDate(ea.getDate());
				e.setEmployee(ea.getEmployee());
				e.setStatus(ea.getStatus());
				session.update(e);
				session.getTransaction().commit();
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public EmployeeAttendance getEmployeeAttendanceById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			EmployeeAttendance ea = session.get(EmployeeAttendance.class, id);
			session.getTransaction().commit();
			return ea;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public EmployeeAttendance getEmployeeAttendance(EmployeeAttendance ea) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from EmployeeAttendance where EmployeeId=:id and Date(date)=:d";
			EmployeeAttendance e = session.createNativeQuery(hql, EmployeeAttendance.class)
					.setParameter("id", ea.getEmployee().getId()).setParameter("d", ea.getDate()).uniqueResult();
			return e;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<EmployeeAttendance> getDateWiseEmployeeAttendance(LocalDate date) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "Select * from EmployeeAttendance where Date(date)=:d";
			List<EmployeeAttendance> list = session.createNativeQuery(hql, EmployeeAttendance.class)
					.setParameter("d", date).list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
