package data.repository;

import org.springframework.data.repository.CrudRepository;

import data.entity.Review;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
	
	

}
