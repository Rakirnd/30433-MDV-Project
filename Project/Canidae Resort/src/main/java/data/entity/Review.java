package data.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int userId;
	private int hotelId;
	
	private LocalDateTime reviewDateTime;
	private String comment;
	private int stars;
	
	public Review() {
		
	}
	
	public Review(int id, int userId, int hotelId, LocalDateTime reviewDateTime, String comment, int stars) {
		super();
		this.id = id;
		this.userId = userId;
		this.hotelId = hotelId;
		this.reviewDateTime = reviewDateTime;
		this.comment = comment;
		this.stars = stars;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public LocalDateTime getReviewDateTime() {
		return reviewDateTime;
	}
	public void setReviewDateTime(LocalDateTime reviewDateTime) {
		this.reviewDateTime = reviewDateTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	
}
