package com.ankush.service.serviceImpl;

import java.util.List;

import com.ankush.dao.dao.AddressDao;
import com.ankush.dao.impl.AddressDaoImpl;
import com.ankush.entities.Address;
import com.ankush.service.service.AddressService;

public class AddressServiceImpl implements AddressService {

	private AddressDao dao;

	public AddressServiceImpl() {
		dao = new AddressDaoImpl();
	}
	@Override
	public List<Address> getAllAddress() {
		return dao.getAllAddress();
	}

}
