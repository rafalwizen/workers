package wizen.rafal.workers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wizen.rafal.workers.entity.Employee;
import wizen.rafal.workers.rest.EmployeeNotFoundException;
import wizen.rafal.workers.service.EmployeeService;
import wizen.rafal.workers.service.WorkTimeService;

@Controller
public class EmployeeController {

	private WorkTimeService workTimeService;
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController (
			EmployeeService theEmployeeService, WorkTimeService theWorkTimeService) {
		employeeService = theEmployeeService;
		workTimeService = theWorkTimeService;
	}
	
	@RequestMapping("/")
	public String startPage() {
		return "redirect:/listEmployees";
	}
	
	@RequestMapping("/listEmployees")
	public String showListEmployees(Model theModel) {
		
		theModel.addAttribute("employees", employeeService.findAll());
		
		return "employees";
	}
	
	@RequestMapping("/employeeWorkTime")
	public String showEmployeeWorkTime(Model theModel, @RequestParam("employeeId") int theId) {
//		Employee tempEmployee = employeeService.getEmployeeById(theId);
//		
//		if(tempEmployee == null) {
//			throw new EmployeeNotFoundException("Employee id not found - " + theId);
//		}
		
		theModel.addAttribute("tempEmployee", employeeService.getEmployeeById(theId));
		return "employee-work-time";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "add-employee";
	}
	
	@RequestMapping("/save")
	public String save(@ModelAttribute("employee") Employee theEmployee) {
		employeeService.save(theEmployee);
		return "redirect:/form";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {
		employeeService.deleteById(theId);
		return "redirect:/form";
	}
}
