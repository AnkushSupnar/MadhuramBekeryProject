package com.ankush.service.serviceImpl;

import java.util.List;

import org.hibernate.Session;

import com.ankush.dao.dao.ItemDao;
import com.ankush.dao.impl.ItemDaoImpl;
import com.ankush.entities.Item;
import com.ankush.service.service.ItemService;

public class ItemServiceImpl implements ItemService {

	private ItemDao dao;

	public ItemServiceImpl() {
		this.dao = new ItemDaoImpl();
	}
	@Override
	public int saveItem(Item item) {
		return dao.saveItem(item);
	}

	@Override
	public Item findItemById(int id) {
		return dao.findItemById(id);
	}

	@Override
	public List<Item> getAllItem() {
		return dao.getAllItem();
	}

	@Override
	public List<String> getAllItemName() {
		return dao.getAllItemName();
	}

	@Override
	public float getItemRateByName(String name) {
		return dao.getItemRateByName(name);
	}

	@Override
	public Item findItemByName(String name) {
		return dao.findItemByName(name);
	}

	@Override
	public Session getSession() {
		return dao.getSession();
	}

}
