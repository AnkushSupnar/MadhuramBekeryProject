package com.ankush.dao.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.ankush.dao.dao.BillDao;
import com.ankush.entities.Bill;
import com.ankush.entities.Transaction;
import com.ankush.util.HibernateUtil;

public class BillDaoImpl implements BillDao {

	private Session session;

	public BillDaoImpl() {
		this.session = HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public void cloaseSession() {
		this.session.close();
	}

	@Override
	public Bill getBillById(int id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Bill b = session.get(Bill.class, id);
			return b;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int saveBill(Bill bill) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Bill b = getBillById(bill.getBillNo());
			if (bill.getBillNo() == 0 || b == null) {
				session.save(bill);
				session.getTransaction().commit();
				return 1;
			}
			else {
				b.setAmount(bill.getAmount());
				b.setCounter(bill.getCounter());
				b.setCustomerId(bill.getCustomerId());
				b.setDate(bill.getDate());
				b.setLogin(bill.getLogin());
				b.setPayMode(bill.getPayMode());
				b.setStatus(bill.getStatus());
				List<Transaction> tr = b.getTransactionList();
				for (Transaction t : tr) {
					session.createNativeQuery("delete from Transaction where id=:i").setParameter("i", t.getId())
							.executeUpdate();
					// session.getTransaction().commit();
				}

				b.setTransactionList(bill.getTransactionList());
				session.update(b);
				session.getTransaction().commit();
				return 2;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Bill> getAllBill() {
		try (Session session = HibernateUtil.getSessionFactory().openSession())
		{
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Bill> query = builder.createQuery(Bill.class);
			Root<Bill> root = query.from(Bill.class);
			query.select(root);
			Query<Bill> q = session.createQuery(query);
			List<Bill> list = q.getResultList();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Bill> getdateWiseBills(LocalDate date) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			System.out.println("I Got " + date);
			String hql = "select * from Bill where Date(date)=:d";

			List<Bill> list = session.createNativeQuery(hql, Bill.class).setParameter("d", date).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Bill> getPeriodBill(LocalDate fromDate, LocalDate toDate) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from Bill where Date(date) between :fromDate and :todate";
			List<Bill> list = session.createNativeQuery(hql, Bill.class).setParameter("fromDate", fromDate)
					.setParameter("todate", toDate).list();

			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Bill> getTenBills(int billno) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			int ten = billno + 10;
			String hql = "select * from Bill where billNo>=:billno and billNo<=:ten";
			List<Bill> list = session.createNativeQuery(hql, Bill.class).setParameter("ten", ten)
					.setParameter("billno", billno).list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public List<Bill> getBillFromYesterDay(LocalDate toDay) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			LocalDate yesterday = toDay.minusDays(1);
			System.out.println("Today= " + toDay);
			System.out.println("YesterDay= " + yesterday);
			// String hql = "select * from Bill where date=:yesterday and date=:today";
			String hql = "select * from Bill where Date(date) between :yesterday and :today";
			List<Bill> list = session.createNativeQuery(hql, Bill.class).setParameter("yesterday", yesterday)
					.setParameter("today", toDay).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Bill> getBillWeekFromToday() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from bill where  `date` >= DATE_SUB(CURDATE(), INTERVAL 8 DAY) ";
			List<Bill> list = session.createNativeQuery(hql, Bill.class).list();
			return list;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}

	}

	@Override
	public List<Bill> getCurrentMonthBills() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "select * from bill where  `date` >= DATE_SUB(CURDATE(), INTERVAL 0 MONTH)";
			List<Bill> list = session.createNativeQuery(hql, Bill.class).list();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<Bill> getCurrentYearBills() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "SELECT * FROM bill WHERE  date between  DATE_FORMAT(CURDATE() ,'%Y-01-01') AND CURDATE()";
			List<Bill> list = session.createNativeQuery(hql, Bill.class).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		BillDao bd = new BillDaoImpl();
		List<Bill> list = bd.getBillWeekFromToday();
		for (Bill bill : list) {
			System.out.println(bill);
		}
	}

	@Override
	public List<Bill> getMonthWiseBills(int month) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			String hql = "SELECT * FROM bill where MONTH(date)=:m";
			List<Bill> list = session.createNativeQuery(hql, Bill.class).setParameter("m", month).list();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}