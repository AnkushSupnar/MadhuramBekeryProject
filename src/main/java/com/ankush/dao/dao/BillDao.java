package com.ankush.dao.dao;

import java.time.LocalDate;
import java.util.List;

import com.ankush.entities.Bill;

public interface BillDao {
	public Bill getBillById(int id);

	public int saveBill(Bill bill);

	public List<Bill> getAllBill();

	public List<Bill> getdateWiseBills(LocalDate date);

	public void cloaseSession();

	public List<Bill> getPeriodBill(LocalDate fromDate, LocalDate toDate);

	public List<Bill> getTenBills(int billno);

	public List<Bill> getBillFromYesterDay(LocalDate toDay);

	public List<Bill> getBillWeekFromToday();

	public List<Bill> getCurrentMonthBills();

	public List<Bill> getCurrentYearBills();

	public List<Bill> getMonthWiseBills(int month);

}
