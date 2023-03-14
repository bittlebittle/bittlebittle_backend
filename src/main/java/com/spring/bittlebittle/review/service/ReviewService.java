package com.spring.bittlebittle.review.service;

import java.util.List;
import java.util.Map;

import com.spring.bittlebittle.review.vo.Review;

public interface ReviewService {

	List<Review> getReviews(int bottleNo);

	Map<String, Object> getReview(int reviewNo);
	
	List<Review> addReview(Review review);

	List<Review> editReview(Review review);

	List<Review> removeReview(Review review);

}
