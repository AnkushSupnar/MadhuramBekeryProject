package com.ankush.service.service;

import java.util.List;

import com.ankush.entities.DailyStock;

public interface DailyStockService {

	public int addDailyStock(DailyStock dailyStock);

	public DailyStock getDailyStockByName(String itemName);

	public DailyStock getDailyStockById(int id);

	public List<DailyStock> getAllDailyStock();

	public float getItemStockByName(String itemName);

	public int reduceStock(String itemName, float qty);
}
