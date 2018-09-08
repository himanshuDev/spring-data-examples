package com.spring.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.learning.entity.Employee;
import com.spring.learning.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.fetchAllEmployee();
	}

	public List<Employee> getEmployeesByName(String employeeName) {
		return employeeRepository.fetchEmployeesByName(employeeName);
	}

	public boolean insertEmployee(Employee emp) {
		return employeeRepository.insertEmployee(emp);
	}

	public boolean testMultipleTransactions(boolean testForSuccess) {
		return employeeRepository.performMultipleTransations(testForSuccess);
	}
}
