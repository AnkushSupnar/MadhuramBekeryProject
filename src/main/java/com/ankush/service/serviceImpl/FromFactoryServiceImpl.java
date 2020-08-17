package com.ankush.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import com.ankush.dao.dao.FromFactoryDao;
import com.ankush.dao.impl.FromFactoryDaoImpl;
import com.ankush.entities.FromFactory;
import com.ankush.service.service.FromFactoryService;

public class FromFactoryServiceImpl implements FromFactoryService {

	private FromFactoryDao dao;

	public FromFactoryServiceImpl() {
		this.dao = new FromFactoryDaoImpl();
	}
	@Override
	public int saveFromFactory(FromFactory fromFactory) {
		return dao.saveFromFactory(fromFactory);
	}

	@Override
	public FromFactory getFromFactoryById(int id) {
		return dao.getFromFactoryById(id);
	}

	@Override
	public List<FromFactory> getAllFromFactory() {
		return dao.getAllFromFactory();
	}

	@Override
	public List<FromFactory> getDateWiseFromFactory(LocalDate date) {
		return dao.getDateWiseFromFactory(date);
	}

}
