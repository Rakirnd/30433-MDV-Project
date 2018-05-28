package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import business.service.HotelService;

@Controller
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/hotels")
	public String hotelPage(Model model) {
		
		model.addAttribute("hotel_list", hotelService.getAllHotels());
		return "hotels";
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/hotel_search")
	public String hotelListPage(Model model) {
		
		model.addAttribute("hotel_list", hotelService.getAllHotels());
		return "hotel_search";
		
	}

}
