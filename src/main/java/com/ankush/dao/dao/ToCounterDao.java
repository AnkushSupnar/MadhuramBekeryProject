package com.ankush.dao.dao;

import java.time.LocalDate;
import java.util.List;

import com.ankush.entities.ToCounter;

public interface ToCounterDao {

	public int saveToCounter(ToCounter toCounter);

	public ToCounter getToCounterById(int id);

	public List<ToCounter> getAllToCounter();

	public List<ToCounter> getDateWiseToCounter(LocalDate date);
}
