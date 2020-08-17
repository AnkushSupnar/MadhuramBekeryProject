package com.ankush.service.service;

import java.time.LocalDate;
import java.util.List;

import com.ankush.entities.Godown;
import com.ankush.entities.GodownStock;

public interface GodownService {
	public int saveGodown(Godown godown);

	public Godown getGodownById(int id);

	public List<Godown> getAllGodownList();

	public List<Godown> getDateWiseGodown(LocalDate date);

	public List<Godown> getCurrentMonthGodown();

	public int addGodownStock(GodownStock godownStock);

	public int reduceGodownStock(GodownStock godownStock);

	public List<GodownStock> getGodownStock();

	public float getItemStock(String itemName);
}
