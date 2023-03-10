package com.spring.bittlebittle.review.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.reply.service.ReplyService;
import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.review.vo.Review;

@RestController
@RequestMapping("/api/bottles/{bottleNo}/reivews")
public class ReviewController {

	
	@Autowired
	private ReviewService rservice;
	@Autowired
	private ReplyService rpservice;
	
	@GetMapping(produces="application/json; charset=UTF-8")
	public List<Review> getReviews(int bottleNo){
		
		List<Review> reviewList = rservice.getReviews(bottleNo);
		
		return reviewList;
	}
	
	@GetMapping(value="/{reviewNo}", produces="application/json; charset=UTF-8")
	public Map<String, Object> getReview(@PathVariable int reviewNo) {
		
		Review review = rservice.getReview(reviewNo);
		List<Reply> replyList= rpservice.getReplies(reviewNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("review", review);
		map.put("replyList", replyList);
		
		return map;
	}
	
	
	@PostMapping(produces="application/json; charset=UTF-8")
	public List<Review> addReview(Review review){
		
		List<Review> reviewList = rservice.addReview(review);
		
		return reviewList;
	}
	
	@PostMapping(value="/set-data", produces="application/json; charset=UTF-8")
	public List<Review> editReveiw(Review review){
		
		List<Review> reviewList = rservice.editReview(review);
		
		return reviewList;
	}
	
	@GetMapping(value="/deletion", produces="application/json; charset=UTF-8")
	public List<Review> deleteReview(Review review){
		
		List<Review> reviewList = rservice.removeReview(review);
		
		return reviewList;
	}
	
	@PostMapping(value="/replies", produces="application/json; charset=UTF-8")
	public List<Reply> addReply(Reply reply){

		List<Reply> replyList = rpservice.addReply(reply);
		
		return replyList;
	}
	
	@PostMapping(value="/replies/set-data", produces="application/json; charset=UTF-8")
	public List<Reply> updateReply(Reply reply){
		
		List<Reply> replyList = rpservice.editReply(reply);
		
		return replyList;
	}
	
	@GetMapping(value="/replies/deletion", produces="application/json; charset=UTF-8")
	public List<Reply> deleteReply(Reply reply){
		List<Reply> replyList = rpservice.removeReply(reply);
		
		return replyList;
	}
	
}
