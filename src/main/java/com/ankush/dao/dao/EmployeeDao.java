package com.ankush.dao.dao;

import java.util.List;

import org.hibernate.Session;

import com.ankush.entities.Employee;

public interface EmployeeDao {

	int saveEmployee(Employee employee);

	List<Employee> listEmployee();

	Employee findEmployeeById(int id);

	int getNewEmployeeId();

	public Session getEmployeesession();

	List<Object[]> getAllEmployeeFullName();

	Employee getEmployeeIdUsingFullName(String name);

}
