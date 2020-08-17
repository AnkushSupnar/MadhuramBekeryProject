package com.ankush.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import com.ankush.dao.dao.BillDao;
import com.ankush.dao.impl.BillDaoImpl;
import com.ankush.entities.Bill;
import com.ankush.service.service.BillService;

public class BillServiceImpl implements BillService {

	private BillDao dao;

	public BillServiceImpl() {
		dao = new BillDaoImpl();
	}
	@Override
	public Bill getBillById(int id) {
		return dao.getBillById(id);
	}

	@Override
	public int saveBill(Bill bill) {
		return dao.saveBill(bill);
	}

	@Override
	public List<Bill> getAllBill() {
		return dao.getAllBill();
	}

	@Override
	public void closeSession() {
		dao.cloaseSession();
	}

	@Override
	public List<Bill> getdateWiseBills(LocalDate date) {
		return dao.getdateWiseBills(date);
	}

	@Override
	public List<Bill> getPeriodBill(LocalDate fromDate, LocalDate toDate) {
		return dao.getPeriodBill(fromDate, toDate);
	}

	@Override
	public List<Bill> getTenBills(int billno) {
		// TODO Auto-generated method stub
		return dao.getTenBills(billno);
	}

	@Override
	public List<Bill> getBillFromYesterDay(LocalDate toDay) {
		return dao.getBillFromYesterDay(toDay);
	}

	@Override
	public List<Bill> getBillWeekFromToday() {
		return dao.getBillWeekFromToday();
	}

	@Override
	public List<Bill> getCurrentMonthBills() {
		return dao.getCurrentMonthBills();
	}

	@Override
	public List<Bill> getCurrentYearBills() {
		return dao.getCurrentYearBills();
	}

	@Override
	public List<Bill> getMonthWiseBills(int month) {
		return dao.getMonthWiseBills(month);
	}

}
