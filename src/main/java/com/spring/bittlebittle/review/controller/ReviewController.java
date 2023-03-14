package com.spring.bittlebittle.review.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.reply.service.ReplyService;
import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.review.vo.Review;

@RestController
@RequestMapping(value="/api/bottles/{bottleNo}/reviews", produces="application/json; charset=UTF-8")
public class ReviewController {

	
	@Autowired
	private ReviewService rservice;
	@Autowired
	private ReplyService rpservice;
	
	Logger log = LogManager.getLogger("case3");
	
	
	// 상품별리뷰리스트조회 (확인완료)
	@GetMapping
	public List<Review> getReviews(@PathVariable int bottleNo){
		
		List<Review> reviewList = rservice.getReviews(bottleNo);
		
		return reviewList;
	}
	
	// 개별리뷰조회 (확인완료)
	@GetMapping(value="/{reviewNo}")
	public Map<String, Object> getReview(@PathVariable int reviewNo) {
		
		
		Map<String, Object> map = rservice.getReview(reviewNo);
		
		return map;
	}
	
	// 리뷰등록 (확인완료)
	@PostMapping
	public List<Review> addReview(@PathVariable int bottleNo, 
			@RequestBody Review review){
		
		int userNo=1;
		
		review.setUserNo(userNo);
		review.setBottleNo(bottleNo);
		
		List<Review> reviewList = rservice.addReview(review);
		
		return reviewList;
	}
	
	// 리뷰수정 (확인완료)
	@PostMapping(value="/set-data")
	public List<Review> editReveiw(@PathVariable int bottleNo, @RequestBody Review review){
																			// reviewNo도 데이터로 보내줘야함
		
		review.setBottleNo(bottleNo);
		
		log.debug(review);
		
		List<Review> reviewList = rservice.editReview(review);
		
		return reviewList;
	}
	
	@GetMapping(value="/deletion")
	public List<Review> deleteReview(Review review){
		
		List<Review> reviewList = rservice.removeReview(review);
		
		return reviewList;
	}
	
	@PostMapping(value="/replies")
	public List<Reply> addReply(Reply reply){

		List<Reply> replyList = rpservice.addReply(reply);
		
		return replyList;
	}
	
	@PostMapping(value="/replies/set-data")
	public List<Reply> updateReply(Reply reply){
		
		List<Reply> replyList = rpservice.editReply(reply);
		
		return replyList;
	}
	
	@GetMapping(value="/replies/deletion")
	public List<Reply> deleteReply(Reply reply){
		List<Reply> replyList = rpservice.removeReply(reply);
		
		return replyList;
	}
	
}
