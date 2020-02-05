package wizen.rafal.workers.dao;

import java.util.List;

import wizen.rafal.workers.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();
	public void save(Employee theEmployee);
	public void deleteById(int theId);
	public Employee getEmployeeById(int theId);
	public Employee getEmployeeByPID(int personalIdentityNumber);
}
