package com.ankush.service.serviceImpl;

import java.util.List;

import com.ankush.dao.dao.CustomerDao;
import com.ankush.dao.impl.CustomerDaoImpl;
import com.ankush.entities.Customer;
import com.ankush.service.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDao dao;

	public CustomerServiceImpl() {
		this.dao = new CustomerDaoImpl();
	}
	@Override
	public int saveCustomer(Customer customer) {
		return dao.saveCustomer(customer);
	}

	@Override
	public Customer findCustomerById(int id) {
		return dao.findCustomerById(id);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return dao.getAllCustomer();
	}

	@Override
	public List<String> getAllCustomerName() {
		return dao.getAllCustomerName();
	}

	@Override
	public int getCustomerIdByName(String name) {
		return dao.getCustomerIdByName(name);
	}

	@Override
	public int getNewCustomerId() {
		return dao.getNewCustomerId();
	}

	@Override
	public void closeSession() {
		dao.closeSession();

	}

	@Override
	public String getCustomerNameById(int id) {
		return dao.getCustomerNameById(id);
	}

}
