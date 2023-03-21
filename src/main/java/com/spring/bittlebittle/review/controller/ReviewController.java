package com.spring.bittlebittle.review.controller;


import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.bittlebittle.reply.service.ReplyService;
import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.reply.vo.ReplyNickname;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.review.vo.ReviewNickname;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.ImageUploadUtil;
import com.spring.bittlebittle.utils.JwtUtil;

@RestController
@RequestMapping(value="/api/bottles/{bottleNo}/reviews", produces="application/json; charset=UTF-8")
public class ReviewController {


	@Autowired
	private ReviewService rservice;
	@Autowired
	private ReplyService rpservice;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private ImageUploadUtil imageUploadUtil;

	Logger log = LogManager.getLogger("case3");


	// 상품별리뷰리스트조회 (확인완료)
	@GetMapping
	public List<ReviewNickname> getReviews(@PathVariable int bottleNo){

		List<ReviewNickname> reviewList = rservice.getReviews(bottleNo);

		return reviewList;
	}

	// 개별리뷰조회 (확인완료)
	@GetMapping(value="/{reviewNo}")
	public Map<String, Object> getReview(@PathVariable int reviewNo) {


		Map<String, Object> map = rservice.getReview(reviewNo);

		return map;
	}
	

	// 리뷰등록 (확인완료)
	@PostMapping()
	public List<ReviewNickname> addReview(@PathVariable int bottleNo,
								  @ModelAttribute Review review,
								  @RequestParam(value="imgUrlOrigin", required=false) MultipartFile upfile, 
								  HttpServletRequest request) throws MalformedURLException{
		// access token header 에서 뽑기
		String token = jwtUtil.resolveAccessToken(request);
		// refresh tokenIdx header 에서 뽑기
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		// 뽑아온 token 을 가지고 유효성 검사. validateToen 의 리턴값은 boolean( true/false)
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {
			
			if(upfile != null && !upfile.getOriginalFilename().equals("")) {
				String changeName = imageUploadUtil.saveFile(upfile, request, "review");
				review.setImgUrl(upfile.getOriginalFilename());                                                                                                                                                                                                                                                                                                                                                                                                                 
				review.setImgCusUrl(changeName);
			}
			
			List<ReviewNickname> reviewList = rservice.addReview(review);

			return reviewList;
		} else {
			
			List<ReviewNickname> reviewList = rservice.getReviews(bottleNo);
			
			return reviewList;
		}
	}

	// 리뷰수정 (확인완료)
	@PostMapping(value="/set-data")
	public List<ReviewNickname> editReveiw(@PathVariable int bottleNo, @ModelAttribute Review review
									, HttpServletRequest request){
																			// reviewNo도 데이터로 보내줘야함
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {

			List<ReviewNickname> reviewList = rservice.editReview(review);

			return reviewList;
			
		} else {
			
			List<ReviewNickname> reviewList = rservice.getReviews(bottleNo);
			
			return reviewList;
		}
		

		
	}

	// 리뷰삭제 (확인완료)
	@GetMapping(value="/{reviewNo}/deletion")
	public List<ReviewNickname> deleteReview(@PathVariable int bottleNo, @PathVariable int reviewNo, HttpServletRequest request){

		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {

			List<ReviewNickname> reviewList = rservice.removeReview(bottleNo, reviewNo);

			return reviewList;
			
		} else {
			
			List<ReviewNickname> reviewList = rservice.getReviews(bottleNo);
			
			return reviewList;
		}
	}

	// 리뷰댓글조회 (확인완료)
	@GetMapping(value="/{reviewNo}/replies")
	public List<ReplyNickname> getReplies(@PathVariable int reviewNo){

		List<ReplyNickname> replyList = rpservice.getReplies(reviewNo);

		return replyList;
	}

	// 리뷰댓글작성 (확인완료)
	@PostMapping(value="/{reviewNo}/replies")
	public List<ReplyNickname> addReply(@PathVariable int reviewNo, @ModelAttribute Reply reply, HttpServletRequest request){
																		// replyContent
		
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {

			log.debug(reply);

			List<ReplyNickname> replyList = rpservice.addReply(reply);

			return replyList;
			
		} else {
			
			List<ReplyNickname> replyList = rpservice.getReplies(reviewNo);

			return replyList;
		}
		
		

	}

	// 리뷰댓글수정 (확인완료)
	@PostMapping(value="/{reviewNo}/replies/set-data")
	public List<ReplyNickname> updateReply(@PathVariable int reviewNo, @ModelAttribute Reply reply, HttpServletRequest request){
																// replyNo, replyContent

		
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {

			List<ReplyNickname> replyList = rpservice.editReply(reply);

			return replyList;
			
		} else {
			
			List<ReplyNickname> replyList = rpservice.getReplies(reviewNo);

			return replyList;
		}
		

		
	}

	// 리뷰댓글삭제 (확인완료)
	@GetMapping(value="/{reviewNo}/replies/{replyNo}/deletion")
	public List<ReplyNickname> deleteReply(@PathVariable int reviewNo,
			@PathVariable int replyNo, HttpServletRequest request){
		
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {


			List<ReplyNickname> replyList = rpservice.removeReply(reviewNo, replyNo);

			return replyList;
			
		} else {
			
			List<ReplyNickname> replyList = rpservice.getReplies(reviewNo);

			return replyList;
		}
		
	}

}
