package com.ankush.service.serviceImpl;

import java.util.List;

import org.hibernate.Session;

import com.ankush.dao.impl.EmployeeDaoImpl;
import com.ankush.entities.Employee;
import com.ankush.service.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDaoImpl dao;

	public EmployeeServiceImpl() {
		dao = new EmployeeDaoImpl();
	}
	@Override
	public int saveEmployee(Employee employee) {

		return dao.saveEmployee(employee);
	}

	@Override
	public List<Employee> listEmployee() {
		return dao.listEmployee();
	}

	@Override
	public Employee findEmployeeById(int id) {
		return dao.findEmployeeById(id);
	}

	@Override
	public int getNewEmployeeId() {
		return dao.getNewEmployeeId();
	}


	@Override
	public Session getEmployeesession() {
		return dao.getEmployeesession();
	}

	@Override
	public List<Object[]> getAllEmployeeFullName() {
		List<Object[]> list = dao.getAllEmployeeFullName();
		return list;
	}

	public static void main(String[] args) {
		new EmployeeServiceImpl().getAllEmployeeFullName();
	}

	@Override
	public Employee getEmployeeIdUsingFullName(String name) {
		return dao.getEmployeeIdUsingFullName(name);
	}
}
