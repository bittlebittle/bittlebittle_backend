package com.spring.bittlebittle.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.review.vo.Review;

@RestController
@RequestMapping("/api/bottles/{bottleNo}/reivews/{reviewNo}")
public class ReviewController {

	
	@Autowired
	private ReviewService rservice;
	
	@GetMapping(produces="application/json; charset=UTF-8")
	public List<Review> getReviewList(int bottleNo){
		
		List<Review> reviewList = rservice.getReviewList(bottleNo);
		
		return reviewList;
	}
	
	@PostMapping(produces="application/json; charset=UTF-8")
	public List<Review> addReview(Review review){
		
		List<Review> reviewList = rservice.addReview(review);
		
		return reviewList;
	}
	
	@PostMapping(value="/set-data", produces="application/json; charset=UTF-8")
	public List<Review> updateReveiw(Review review){
		
		List<Review> reviewList = rservice.updateReview(review);
		
		return reviewList;
	}
	
	@GetMapping(value="/deletion", produces="application/json; charset=UTF-8")
	public List<Review> deleteReview(Review review){
		
		List<Review> reviewList = rservice.deleteReview(review);
		
		return reviewList;
	}
	
}
