package com.ankush.service.service;

import java.time.LocalDate;
import java.util.List;

import com.ankush.entities.FromFactory;

public interface FromFactoryService {

	public int saveFromFactory(FromFactory fromFactory);

	public FromFactory getFromFactoryById(int id);

	public List<FromFactory> getAllFromFactory();

	public List<FromFactory> getDateWiseFromFactory(LocalDate date);
}
