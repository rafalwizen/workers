package wizen.rafal.workers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

//	@GetMapping("/showMyLoginPage")
//	public String showMyLoginPage() {
//		return "my-login-page";
//	}
	
	@GetMapping("/access-denied")
	public String showAccesDenied() {
		return "access-denied";
	}
}
