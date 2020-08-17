package com.ankush.service.serviceImpl;

import java.util.List;

import com.ankush.dao.dao.CounterDao;
import com.ankush.dao.impl.CounterDaoImpl;
import com.ankush.entities.Counter;
import com.ankush.service.service.CounterService;

public class CounterServiceImpl implements CounterService {

	private CounterDao dao;

	public CounterServiceImpl() {
		this.dao = new CounterDaoImpl();

	}
	@Override
	public int saveCounter(Counter counter) {
		return dao.saveCounter(counter);
	}

	@Override
	public Counter getCounterById(int id) {
		return dao.getCounterById(id);
	}

	@Override
	public List<Counter> getAllCounter() {
		return dao.getAllCounter();
	}

	@Override
	public List<String> getAllCounterName() {
		return dao.getAllCounterName();
	}

	@Override
	public int getCounterIdByName(String name) {
		return dao.getCounterIdByName(name);
	}

	@Override
	public void closeSession() {
		dao.closeSession();

	}

}
