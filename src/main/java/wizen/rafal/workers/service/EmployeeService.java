package wizen.rafal.workers.service;

import java.util.List;

import wizen.rafal.workers.entity.Employee;
import wizen.rafal.workers.entity.User;

public interface EmployeeService {
	public List<Employee> findAll();
	public List<Employee> findByRole();
	public void save(Employee theEmployee);
	public void deleteById(int theId);
	public Employee getEmployeeById(int theId);
	public Employee getEmployeeByPID(int personalIdentityNumber);
}
