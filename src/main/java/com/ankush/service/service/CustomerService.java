package com.ankush.service.service;

import java.util.List;

import com.ankush.entities.Customer;

public interface CustomerService {

	public int saveCustomer(Customer customer);

	public Customer findCustomerById(int id);

	public List<Customer> getAllCustomer();

	public List<String> getAllCustomerName();

	public int getCustomerIdByName(String name);

	public int getNewCustomerId();

	public void closeSession();

	public String getCustomerNameById(int id);
}
