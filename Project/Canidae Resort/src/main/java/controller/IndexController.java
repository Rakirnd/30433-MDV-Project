package controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import business.service.HotelService;
import business.service.OwnerService;
import business.util.KeptData;
import data.entity.Hotel;
import data.entity.Owner;

@Controller
public class IndexController {
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Autowired
	private OwnerService userService;
	
	@Autowired
	private HotelService hotelService;
	
	@RequestMapping("/index")
	public String indexPage(Model model) {
		
		initalizeDatabase();
		System.out.print(KeptData.authUser);
		
		//model.addAttribute("appName", appName);
		return "index";
		
	}
	
	@RequestMapping("/homepage")
	public String homepage(Model model) {
		
		return "homepage";
		
	}
	
	private void initalizeDatabase() {
		
		initializeOwners();
		initializeHotels();
		
	}
	
	private void initializeOwners() {
		
		List<Owner> ownerList = null;
		
		try {
			
			ownerList = userService.getAllUsers();
			
		}
		catch (NullPointerException e) {
			
		}
		
		if(ownerList.isEmpty()) {
			
			List<Owner> ol = Arrays.asList(
							
							new Owner(1, "admin", "admin", "IBAN_0000", true),
							new Owner(2, "dan", "mdv", "IBAN_1111", false),
							new Owner(3, "aky", "dog", "IBAN_0001", false)
							
							);
					
			for(Owner o: ol) {
				
				userService.addUser(o);
				
			}
			
			System.out.println("I added the users!");
			
		}
		else
			System.out.println("Users already registered!");
		
	}
	
	private void initializeHotels() {
		
		List<Hotel> hotelList = null;
		
		try {
			
			hotelList = hotelService.getAllHotels();
			
		}
		catch (NullPointerException e) {
			
		}
		
		if(hotelList.isEmpty()) {
			
			List<Hotel> hl = Arrays.asList(
							new Hotel(1, "Dog Heaven", "31 Acacia Avenue", 25, 35, "IBAN_0110"),
							new Hotel(2, "CR Headquarters", "4 Privet Drive", 15, 25, "IBAN_1101"),
							new Hotel(3, "Dog's Ark", "9^3/4 King's Cross", 40, 30, "IBAN_1110")
							);
			
			for(Hotel h: hl) {
				
				hotelService.addHotel(h);
				
			}
			
			System.out.println("I added the hotels!");
			
		}
		else
			System.out.println("Hotels already exist!");
		
	}
	
	
}
