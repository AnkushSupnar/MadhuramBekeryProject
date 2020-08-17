package com.ankush.dao.dao;

import java.time.LocalDate;
import java.util.List;

import com.ankush.entities.FromFactory;

public interface FromFactoryDao {
	public int saveFromFactory(FromFactory fromFactory);

	public FromFactory getFromFactoryById(int id);

	public List<FromFactory> getAllFromFactory();

	public List<FromFactory> getDateWiseFromFactory(LocalDate date);

}
