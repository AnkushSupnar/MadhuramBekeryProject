package com.ankush.service.service;

import java.util.List;

import org.hibernate.Session;

import com.ankush.entities.Item;

public interface ItemService {
	public int saveItem(Item item);

	public Item findItemById(int id);

	public List<Item> getAllItem();

	public List<String> getAllItemName();

	public float getItemRateByName(String name);

	public Item findItemByName(String name);

	public Session getSession();

}
