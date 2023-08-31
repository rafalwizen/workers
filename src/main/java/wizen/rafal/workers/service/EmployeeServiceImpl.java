package wizen.rafal.workers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wizen.rafal.workers.dao.EmployeeDAO;
import wizen.rafal.workers.dao.UserDAO;
import wizen.rafal.workers.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;
	private UserDAO userDAO;

	@Autowired
	public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO, UserDAO theUserDAO) {
		employeeDAO = theEmployeeDAO;
		userDAO = theUserDAO;
	}
	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}
	@Override
	@Transactional
	public List<Employee> findByRole() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return employeeDAO.findAll();
		}
		List<Employee> list = new ArrayList<>();
		String currentUserName = user.getUsername();
		int id = userDAO.getUserByName(currentUserName).getEmployee().getId();
		if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
			list.addAll(employeeDAO.getEmployeesByManagerId(id));
		}
		list.add(employeeDAO.getEmployeeById(id));

		return list;
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
