package wizen.rafal.workers.service;

import java.util.List;

import wizen.rafal.workers.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();
	public void save(Employee theEmployee);
	public void deleteById(int theId);
	public Employee getEmployeeById(int theId);
}
