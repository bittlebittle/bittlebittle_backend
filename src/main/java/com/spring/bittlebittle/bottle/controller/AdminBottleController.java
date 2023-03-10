package com.spring.bittlebittle.bottle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.bottle.service.BottleService;
import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.tag.service.TagService;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;

@RestController
@RequestMapping(value="/api/admin/bottles")
public class AdminBottleController {

	@Autowired
	private BottleService bservice;
	@Autowired
	private ReviewService rservice;
	@Autowired
	private TagService tservice;
	
	@GetMapping(value="/{bottleNo}", produces="application/json; charset=UTF-8")
	public Bottle getBottle(int bottleNo) {
		
		Bottle bottle = bservice.getBottle(bottleNo);
		List<Review> reviewList = rservice.getReviewList(bottleNo);
		
		// 우선은 이렇게.... 수정해야됨!
		return bottle;
	}
	
	// 추가창 들어갈때
	@GetMapping(value="/addition", produces="application/json; charset=UTF-8")
	public Bottle getTag() {
		
		// 태그 선택할 수 있도록 태그 선택지 불러오기
		List<TagType> tagTypeList = tservice.getAllTagType();
		List<Tag> tagList = tservice.getAllTag();
		
		return null;
	}
	
	// 추가 완료할 때
	@PostMapping(value="/addition", produces="application/json; charset=UTF-8")
	public Bottle addBottle(Bottle newBottle) {
		
		Bottle bottle= bservice.addBottle(newBottle);
		
		return bottle;
	}
	
	// 수정창 들어갈때 
	@GetMapping(value="/set-data", produces="application/json; charset=UTF-8")
	public Bottle getBottleInfo(int bottleNo) {
		
		Bottle bottle = bservice.getBottle(bottleNo);
		List<TagType> tagTypeList= tservice.getAllTagType();
		List<Tag> tagList = tservice.getTag(bottleNo);
		
		// 태그 리스트도 보내야돼~
		return bottle;
	}
	
	// 수정완료할때
	@PostMapping(value="/set-data", produces="application/json; charset=UTF-8")
	public Bottle updateBottle(Bottle updateBottle, List<Tag> tagList) {
		
		Bottle bottle = bservice.updateBottle(updateBottle);
		tservice.updateTag(updateBottle.getBottleNo(), tagList);
		
		return bottle;
		
	}
}
