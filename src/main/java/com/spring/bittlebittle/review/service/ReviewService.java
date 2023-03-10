package com.spring.bittlebittle.review.service;

import java.util.List;

import com.spring.bittlebittle.review.vo.Review;

public interface ReviewService {

	List<Review> getReviewList(int bottleNo);

	Review getReview(int reviewNo);
	
	List<Review> addReview(Review review);

	List<Review> updateReview(Review review);

	List<Review> deleteReview(Review review);

}
