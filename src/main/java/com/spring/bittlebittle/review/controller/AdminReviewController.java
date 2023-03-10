package com.spring.bittlebittle.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.reply.service.ReplyService;
import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.review.vo.Review;

@RestController
@RequestMapping(value="/api/admin/reviews")
public class AdminReviewController {

	@Autowired
	private ReviewService rservice;
	@Autowired
	private ReplyService rpservice;
	
	
	@GetMapping(produces="application/json; charset=UTF-8")
	public List<Review> getReviewList(int bottleNo){
		
		List<Review> reviewList = rservice.getReviewList(bottleNo);
		
		
		return reviewList;
	}
	
	@GetMapping(value="/deletion", produces="application/json; charset=UTF-8")
	public List<Review> deleteReview(Review review){
		
		List<Review> reviewList = rservice.deleteReview(review);
		
		return reviewList;
	}
	
	@GetMapping(value="/replies/deletion", produces="application/json; charset=UTF-8")
	public List<Reply> deleteReply(Reply reply){
	
		List<Reply> replyList = rpservice.deleteReply(reply);
		
		return replyList;
	}
}