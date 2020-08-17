package com.ankush.service.serviceImpl;

import java.util.List;

import com.ankush.dao.dao.DailyStockDao;
import com.ankush.dao.impl.DailyStockDaoImpl;
import com.ankush.entities.DailyStock;
import com.ankush.service.service.DailyStockService;

public class DailyStockServiceImpl implements DailyStockService {
	private DailyStockDao dao;

	public DailyStockServiceImpl() {
		this.dao = new DailyStockDaoImpl();
	}

	@Override
	public int addDailyStock(DailyStock dailyStock) {
		return dao.addDailyStock(dailyStock);
	}

	@Override
	public DailyStock getDailyStockByName(String itemName) {
		return dao.getDailyStockByName(itemName);
	}

	@Override
	public DailyStock getDailyStockById(int id) {
		return dao.getDailyStockById(id);
	}

	@Override
	public List<DailyStock> getAllDailyStock() {
		return dao.getAllDailyStock();
	}

	@Override
	public float getItemStockByName(String itemName) {
		return dao.getItemStockByName(itemName);
	}

	@Override
	public int reduceStock(String itemName, float qty) {
		return dao.reduceStock(itemName, qty);
	}

}
