package business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.entity.Review;
import data.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public Review getReview(int reviewId) {
		
		return reviewRepository.findOne(reviewId);
		
	}
	
	public void addReview(Review review) {
		
		reviewRepository.save(review);
		
	}
	
	public void updateReview(Review review) {
		
		reviewRepository.save(review);
		
	}
	
	public void deleteReview(int id) {
		
		reviewRepository.delete(id);
		
	}

}
