package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import business.util.KeptData;

@Controller
public class LogoutController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	public String loginPage(Model model) {
		
		logout();
		return "index";
		
	}
	
	public void logout() {
		
		KeptData.authUser = "";
		
	}

}
