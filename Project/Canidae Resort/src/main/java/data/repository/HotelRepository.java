package data.repository;

import org.springframework.data.repository.CrudRepository;

import data.entity.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Integer> {
	
	

}
