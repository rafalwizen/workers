package wizen.rafal.workers.service;

import wizen.rafal.workers.entity.Employee;
import wizen.rafal.workers.entity.User;

import java.util.List;

public interface UserService {

    public List<Employee> findAll();
    public void save(Employee theEmployee);
    public void deleteById(int theId);
    public Employee getEmployeeById(int theId);
    public Employee getEmployeeByPID(int personalIdentityNumber);
    public List<User> findAllUsers();
}
