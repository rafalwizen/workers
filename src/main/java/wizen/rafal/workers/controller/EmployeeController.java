package wizen.rafal.workers.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wizen.rafal.workers.entity.Employee;
import wizen.rafal.workers.entity.WorkTime;
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
	
	@GetMapping("/listEmployees")
	public String showListEmployees(Model theModel) {
		
		theModel.addAttribute("employees", employeeService.findAll());
		
		return "employees";
	}
	
	@GetMapping("/employeeWorkTime")
	public String showEmployeeWorkTime(Model theModel, @RequestParam("employeeId") int theId) {
		theModel.addAttribute("tempEmployee", employeeService.getEmployeeById(theId));
		return "employee-work-time";
	}
	
	@GetMapping("/editEployeeWorkTime")
	public String editEmployeeWorkTime(Model theModel, @RequestParam("workTimeId") int theId) {
		
		theModel.addAttribute("tempWorkTime", workTimeService.getWorkTimeById(theId));

		return "work-time-form";
	}
	
	@GetMapping("/showFormForAddNewEmployee")
	public String showFormForAddNewEmployee(Model theModel) {
		
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employee-form";
	}
	
	@GetMapping("/showFormForAddNewWorkTime")
	public String showFormForAddNewWorkTime(
			Model theModel, @RequestParam("tempEmployeeId") int theId) {
		
		WorkTime tempWorkTime = new WorkTime();
		tempWorkTime.setEmployee(employeeService.getEmployeeById(theId));
		theModel.addAttribute("tempWorkTime", tempWorkTime);
		return "work-time-form";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		employeeService.save(theEmployee);
		return "redirect:/listEmployees";
	}
	
	// this InitBinder is used to solve problems with parsing String to Date in saveWorkTime method.
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(
	    		 Date.class, new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"), true, 19));
	}
	
	@PostMapping("/saveWorkTime")
	public String saveWorkTime(@ModelAttribute("workTime") WorkTime theWorkTime) {
		//this line is used to set Employee by passed id
		theWorkTime.setEmployee(employeeService.getEmployeeById(theWorkTime.getEmployee().getId()));
		workTimeService.save(theWorkTime);
		return "redirect:/employeeWorkTime?employeeId="+theWorkTime.getEmployee().getId();
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {
		employeeService.deleteById(theId);
		return "redirect:/listEmployees";
	}
	
	@GetMapping("/deleteWorkTime")
	public String deleteWorkTime(@RequestParam("workTimeId") int theId) {
		WorkTime tempWorkTime = (WorkTime) workTimeService.getWorkTimeById(theId);
		int tempEmployeeId = tempWorkTime.getEmployee().getId();
		workTimeService.deleteById(theId);
		return "redirect:/employeeWorkTime?employeeId="+tempEmployeeId;
	}
}
