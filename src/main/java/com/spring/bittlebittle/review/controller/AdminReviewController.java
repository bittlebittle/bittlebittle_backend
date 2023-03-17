package com.spring.bittlebittle.review.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.reply.service.ReplyService;
import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.review.vo.Review;

@RestController
@RequestMapping(value="/api/admin/bottles/{bottleNo}/reviews", produces="application/json; charset=UTF-8")
public class AdminReviewController {

	@Autowired
	private ReviewService rservice;
	@Autowired
	private ReplyService rpservice;
	
	// 리뷰목록조회(확인완료)
	@GetMapping
	public List<Review> getReviewList(@PathVariable int bottleNo){
		
		List<Review> reviewList = rservice.getReviews(bottleNo);
		
		
		return reviewList;
	}
	
	// 개별리뷰조회(확인완료)
	@GetMapping(value="/{reviewNo}")
	public Map<String, Object> getReview(@PathVariable int reviewNo) {
		
		Map<String, Object> map = rservice.getReview(reviewNo);
		
		return map;
	}
	
	// 리뷰삭제 (확인완료)
	@GetMapping(value="/{reviewNo}/deletion")
	public List<Review> removeReview(@PathVariable int bottleNo, @PathVariable int reviewNo){
		
		List<Review> reviewList = rservice.removeReview(bottleNo, reviewNo);
		
		return reviewList;
	}
	
	// 리뷰댓글조회  (확인완료)
	@GetMapping(value="/{reviewNo}/replies")
	public List<Reply> getReplies(@PathVariable int reviewNo){
			
		List<Reply> replyList = rpservice.getReplies(reviewNo);
			
		return replyList;
	}
	
	// 리뷰댓글삭제 (확인완료)
	@GetMapping(value="/{reviewNo}/replies/{replyNo}/deletion")
	public List<Reply> removeReply(@PathVariable int reviewNo,
			@PathVariable int replyNo){
	
		List<Reply> replyList = rpservice.removeReply(reviewNo, replyNo);
		
		return replyList;
	}
}
