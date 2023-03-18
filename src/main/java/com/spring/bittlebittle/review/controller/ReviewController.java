package com.spring.bittlebittle.review.controller;

import com.spring.bittlebittle.reply.service.ReplyService;
import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.review.vo.Review;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
	
	// 리뷰삭제 (확인완료)
	@GetMapping(value="/{reviewNo}/deletion")
	public List<Review> deleteReview(@PathVariable int bottleNo, @PathVariable int reviewNo){
		
		List<Review> reviewList = rservice.removeReview(bottleNo, reviewNo);
		
		return reviewList;
	}
	
	// 리뷰댓글조회 (확인완료)
	@GetMapping(value="/{reviewNo}/replies")
	public List<Reply> getReplies(@PathVariable int reviewNo){
		
		List<Reply> replyList = rpservice.getReplies(reviewNo);
		
		return replyList;
	}
	
	// 리뷰댓글작성 (확인완료)
	@PostMapping(value="/{reviewNo}/replies")
	public List<Reply> addReply(@PathVariable int reviewNo, @RequestBody Reply reply){
																		// replyContent
		int userNo = 1;
		
		reply.setReviewNo(reviewNo);
		reply.setUserNo(userNo);
		
		List<Reply> replyList = rpservice.addReply(reply);
		
		return replyList;
	}
	
	// 리뷰댓글수정 (확인완료)
	@PostMapping(value="/{reviewNo}/replies/set-data")
	public List<Reply> updateReply(@PathVariable int reviewNo, @RequestBody Reply reply){

		// replyNo, replyContent
		reply.setReviewNo(reviewNo);
		
		List<Reply> replyList = rpservice.editReply(reply);
		
		return replyList;
	}
	
	// 리뷰댓글삭제 (확인완료)
	@GetMapping(value="/{reviewNo}/replies/{replyNo}/deletion")
	public List<Reply> deleteReply(@PathVariable int reviewNo,
			@PathVariable int replyNo){
		List<Reply> replyList = rpservice.removeReply(reviewNo, replyNo);
		
		return replyList;
	}
	
}
