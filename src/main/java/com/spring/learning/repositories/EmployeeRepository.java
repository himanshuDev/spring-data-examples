package com.spring.learning.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.learning.entity.Employee;

@Repository
public class EmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Employee> fetchAllEmployee() {
		// This does not uses prepared statement
		return jdbcTemplate.query("select * from employee", (rs, rowNum) -> {
			Employee employee = new Employee();
			employee.setId(rs.getInt("id"));
			employee.setName(rs.getString("name"));
			employee.setSalary(rs.getInt("salary"));
			employee.setDept(rs.getString("dept"));
			return employee;
		});
	}

	public List<Employee> fetchEmployeesByName(String name) {
		return jdbcTemplate.queryForList("select * from employee where name like ?", "%" + name + "%").stream()
				.map(dataMap -> {
					Employee employee = new Employee();
					employee.setId((Integer) dataMap.get("id"));
					employee.setName((String) dataMap.get("name"));
					employee.setSalary((Integer) dataMap.get("salary"));
					employee.setDept((String) dataMap.get("dept"));
					return employee;
				}).collect(Collectors.toList());
	}

	public boolean insertEmployee(Employee employee) {
		int insretedRecords = jdbcTemplate.update("insert into employee(name, salary, dept) values (?,?,?)",
				employee.getName(), employee.getSalary(), employee.getDept());
		return insretedRecords == 1;
	}

	@Transactional
	public boolean performMultipleTransations(boolean testForSuccess) {
		jdbcTemplate.update("delete from employee where id = ?", 12);
		if (!testForSuccess) {
			throw new NullPointerException("this transaction should fail");
		}
		jdbcTemplate.update("delete from employee where id = ?", 13);
		return true;
	}
}
