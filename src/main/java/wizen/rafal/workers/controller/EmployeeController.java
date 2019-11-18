package wizen.rafal.workers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wizen.rafal.workers.entity.Employee;
import wizen.rafal.workers.service.EmployeeService;

@Controller
public class EmployeeController {

	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController (EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	@RequestMapping("/")
	public String startPage() {
		return "redirect:/form";
	}
	
	@RequestMapping("/form")
	public String showForm(Model theModel) {
		
		theModel.addAttribute("employees", employeeService.findAll());
		
		return "employees";
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
