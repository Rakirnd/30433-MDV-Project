package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import business.service.OwnerService;
import business.util.KeptData;
import business.util.Login;
import data.entity.Owner;

@Controller
public class LoginController {
	
	@Autowired
	private OwnerService ownerService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String loginPage(Model model) {
		
		model.addAttribute("login", new Login());
		return "login";
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public String authenticate(@ModelAttribute Login login) {
		
		Owner u = null;
		String username = login.getUsername();
		String password = login.getPassword();
		
		try {
			
			u = ownerService.getUserByUsername(username);
			
		}
		catch (NullPointerException e) {
			
		}
		
		if(u != null)
			if(password.equals(u.getPassword())) {
				
				KeptData.authUser = u.getName();
				
				if(u.isAdmin())
					return "admin";
				else
					return "logged";
				
			}
		
		return "login";
		
	}
	
}
