package com.ankush.service.service;

import java.util.List;

import com.ankush.entities.Counter;

public interface CounterService {
	public int saveCounter(Counter counter);

	public Counter getCounterById(int id);

	public List<Counter> getAllCounter();

	public List<String> getAllCounterName();

	public int getCounterIdByName(String name);

	public void closeSession();

}
