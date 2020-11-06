package com.ankush.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import com.ankush.dao.dao.EmployeeAttendanceDao;
import com.ankush.dao.impl.EmployeeAttendanceDaoImpl;
import com.ankush.entities.EmployeeAttendance;
import com.ankush.service.service.EmployeeAttendanceService;

public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {

	private EmployeeAttendanceDao dao;

	public EmployeeAttendanceServiceImpl() {
		this.dao = new EmployeeAttendanceDaoImpl();
	}
	@Override
	public int saveEmployeeAttendance(EmployeeAttendance ea) {
		return dao.saveEmployeeAttendance(ea);
	}

	@Override
	public EmployeeAttendance getEmployeeAttendanceById(int id) {
		return dao.getEmployeeAttendanceById(id);
	}

	@Override
	public EmployeeAttendance getEmployeeAttendance(EmployeeAttendance ea) {
		return dao.getEmployeeAttendance(ea);
	}

	@Override
	public List<EmployeeAttendance> getDateWiseEmployeeAttendance(LocalDate date) {
		return dao.getDateWiseEmployeeAttendance(date);
	}

}
