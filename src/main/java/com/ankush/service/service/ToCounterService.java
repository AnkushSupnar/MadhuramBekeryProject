package com.ankush.service.service;

import java.time.LocalDate;
import java.util.List;

import com.ankush.entities.ToCounter;

public interface ToCounterService {
	public int saveToCounter(ToCounter toCounter);

	public ToCounter getToCounterById(int id);

	public List<ToCounter> getAllToCounter();

	public List<ToCounter> getDateWiseToCounter(LocalDate date);
}
