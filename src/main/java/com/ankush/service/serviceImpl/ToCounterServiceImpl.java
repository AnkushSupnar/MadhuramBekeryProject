package com.ankush.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import com.ankush.dao.dao.ToCounterDao;
import com.ankush.dao.impl.ToCounterDaoImpl;
import com.ankush.entities.DailyStock;
import com.ankush.entities.ToCounter;
import com.ankush.entities.ToCounterTransaction;
import com.ankush.service.service.DailyStockService;
import com.ankush.service.service.ToCounterService;

public class ToCounterServiceImpl implements ToCounterService {

	private ToCounterDao dao;

	public ToCounterServiceImpl() {
		dao = new ToCounterDaoImpl();
	}
	@Override
	public int saveToCounter(ToCounter toCounter) {
		try {
			DailyStockService dailyStock = new DailyStockServiceImpl();

			if (toCounter.getId() != 0) {// only for update

				// Get Old Transaction
				ToCounter toC = getToCounterById(toCounter.getId());
				for (ToCounterTransaction t : toC.getTransactions()) {

					dailyStock.reduceStock(t.getItemName(), t.getQty());
				}
				DailyStock stock;
				for (ToCounterTransaction ct : toCounter.getTransactions()) {
					stock = new DailyStock();
					stock.setItemName(ct.getItemName());
					stock.setQty(ct.getQty());
					// dailyStock.addDailyStock(stock);
				}


			}
			DailyStock stock;
			for (ToCounterTransaction ct : toCounter.getTransactions()) {
				stock = new DailyStock();
				stock.setItemName(ct.getItemName());
				stock.setQty(ct.getQty());
				dailyStock.addDailyStock(stock);
			}

			return dao.saveToCounter(toCounter);// for new
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	@Override
	public ToCounter getToCounterById(int id) {
		return dao.getToCounterById(id);
	}

	@Override
	public List<ToCounter> getAllToCounter() {
		return dao.getAllToCounter();
	}

	@Override
	public List<ToCounter> getDateWiseToCounter(LocalDate date) {
		return dao.getDateWiseToCounter(date);
	}

}
