package business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.entity.Hotel;
import data.repository.HotelRepository;

@Service
public class HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	public List<Hotel> getAllHotels(){
		
		List<Hotel> hotels = new ArrayList<Hotel>();
		hotelRepository.findAll().forEach(hotels::add);
		
		return hotels;
		
	}
	
	public Hotel getHotel(int hotelId) {
		
		return hotelRepository.findOne(hotelId);
		
	}
	
	public void addHotel(Hotel hotel) {
		
		hotelRepository.save(hotel);
		
	}
	
	public void updateHotel(Hotel hotel) {
		
		hotelRepository.save(hotel);
		
	}
	
	public void deleteHotel(int id) {
		
		hotelRepository.delete(id);
		
	}

}
