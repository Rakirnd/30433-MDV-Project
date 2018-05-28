package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import business.service.OwnerService;
import business.util.KeptData;
import data.entity.Owner;

@Controller
public class AccountController {
	
	@Autowired
	private OwnerService ownerService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/account")
	public String adminPage(Model model) {
		
		Owner logged = ownerService.getUserByUsername(KeptData.authUser);
		
		if(logged.isAdmin())
			return "admin";
		else
			return "account";
		
	}

}
