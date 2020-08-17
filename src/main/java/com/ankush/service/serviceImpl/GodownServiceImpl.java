package com.ankush.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import com.ankush.dao.dao.GodownDao;
import com.ankush.dao.impl.GodownDaoImpl;
import com.ankush.entities.Godown;
import com.ankush.entities.GodownStock;
import com.ankush.service.service.GodownService;

public class GodownServiceImpl implements GodownService {

	private GodownDao dao;

	public GodownServiceImpl() {
		this.dao = new GodownDaoImpl();
	}
	@Override
	public int saveGodown(Godown godown) {
		return dao.saveGodown(godown);
	}

	@Override
	public Godown getGodownById(int id) {
		return dao.getGodownById(id);
	}

	@Override
	public List<Godown> getAllGodownList() {
		return dao.getAllGodownList();
	}

	@Override
	public List<Godown> getDateWiseGodown(LocalDate date) {
		return dao.getDateWiseGodown(date);
	}

	@Override
	public List<Godown> getCurrentMonthGodown() {
		return dao.getCurrentMonthGodown();
	}

	@Override
	public int addGodownStock(GodownStock godownStock) {
		return dao.addGodownStock(godownStock);
	}

	@Override
	public int reduceGodownStock(GodownStock godownStock) {
		return dao.reduceGodownStock(godownStock);
	}

	@Override
	public List<GodownStock> getGodownStock() {
		return dao.getGodownStock();
	}

	@Override
	public float getItemStock(String itemName) {
		return dao.getItemStock(itemName);
	}


}
