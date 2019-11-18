package wizen.rafal.workers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wizen.rafal.workers.entity.Employee;
import wizen.rafal.workers.service.EmployeeService;

@RestController
@RequestMapping("/")
public class EmployeeRestController {

	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> getEmployeesList() {
		
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployeeById (@PathVariable int employeeId) {
		Employee tempEmployee = employeeService.getEmployeeById(employeeId);
		
		if(tempEmployee == null) {
			throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
		}
		
		return tempEmployee;
	}
	
	@PostMapping("/employees")
	public Employee addNewEmployee(@RequestBody Employee theEmployee) {
		
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
}
