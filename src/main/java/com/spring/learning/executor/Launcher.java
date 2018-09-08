package com.spring.learning.executor;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.learning.entity.Employee;
import com.spring.learning.service.EmployeeService;

public class Launcher {
	public static void main(String... strings) {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.spring");
		context.refresh();
		EmployeeService employeeService = context.getBean(EmployeeService.class);

		Employee emp = new Employee();
		emp.setDept("IT");
		emp.setName("Test Name");
		emp.setSalary(100000);
		employeeService.insertEmployee(emp);

		List<Employee> empList = employeeService.getAllEmployees();
		System.out.println(empList);

		empList = employeeService.getEmployeesByName("i");
		System.out.println(empList);

		employeeService.testMultipleTransactions(false);
	}
}
