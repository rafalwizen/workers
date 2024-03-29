package wizen.rafal.workers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wizen.rafal.workers.entity.Employee;
import wizen.rafal.workers.entity.WorkTime;
import wizen.rafal.workers.service.EmployeeService;
import wizen.rafal.workers.service.WorkTimeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private WorkTimeService workTimeService;
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(
			EmployeeService theEmployeeService, WorkTimeService theWorkTimeService) {
		employeeService = theEmployeeService;
		workTimeService = theWorkTimeService;
	}

	@GetMapping("/currentEmployee")
	public Object getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@GetMapping("/workTimes")
	public List<WorkTime> getWorkTimeList() {
		return workTimeService.findAll();
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
	
	@PostMapping("/workTimes")
	public WorkTime addNewWorkTime(@RequestBody HelperTransferObject tempHelperTransferObject) {
		Employee tempEmployee = employeeService.getEmployeeByPID(
				tempHelperTransferObject.getPersonalIdentityNumber());
		WorkTime tempWorkTime = new WorkTime (tempHelperTransferObject.isStart());
		tempWorkTime.setId(0);
		tempWorkTime.setEmployee(tempEmployee);
		workTimeService.save(tempWorkTime);
		return tempWorkTime;
	}
}
