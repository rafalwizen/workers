package wizen.rafal.workers.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wizen.rafal.workers.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOImpl (EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Employee> theQuery 
				= currentSession.createQuery("from Employee", Employee.class);
		
		List<Employee> employees = theQuery.getResultList();
		
		return employees;
	}

	@Override
	public void save(Employee theEmployee) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Employee tempEmp = currentSession.get(Employee.class, theId);
		currentSession.delete(tempEmp);
	}

	@Override
	public Employee getEmployeeById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Employee tempEmp = currentSession.get(Employee.class, theId);
		
		return tempEmp;
	}

	@Override
	public Employee getEmployeeByPID(int personalIdentityNumber) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query<Employee> theQuery = currentSession.createQuery(
				"from Employee E WHERE E.personalIdentityNumber = "+personalIdentityNumber, Employee.class);
		Employee tempEmp = theQuery.getSingleResult();
		return tempEmp;
	}

	
	
	
}
