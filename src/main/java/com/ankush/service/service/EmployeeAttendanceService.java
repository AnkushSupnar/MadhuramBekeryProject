package com.ankush.service.service;

import java.time.LocalDate;
import java.util.List;

import com.ankush.entities.EmployeeAttendance;

public interface EmployeeAttendanceService {
	public int saveEmployeeAttendance(EmployeeAttendance ea);

	public EmployeeAttendance getEmployeeAttendanceById(int id);

	public EmployeeAttendance getEmployeeAttendance(EmployeeAttendance ea);

	List<EmployeeAttendance> getDateWiseEmployeeAttendance(LocalDate date);
}
