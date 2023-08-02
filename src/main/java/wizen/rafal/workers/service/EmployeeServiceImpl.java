package wizen.rafal.workers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wizen.rafal.workers.dao.EmployeeDAO;
import wizen.rafal.workers.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
		employeeDAO = theEmployeeDAO;
	}
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}
	@Override
	@Transactional
	public void save(Employee theEmployee) {
		employeeDAO.save(theEmployee);
	}
	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeDAO.deleteById(theId);
	}
	@Override
	@Transactional
	public Employee getEmployeeById(int theId) {
		return employeeDAO.getEmployeeById(theId);
	}
	@Override
	@Transactional
	public Employee getEmployeeByPID(int personalIdentityNumber) {
		return employeeDAO.getEmployeeByPID(personalIdentityNumber);
	}
}
