package com.spring.bittlebittle.bottle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.spring.bittlebittle.tag.vo.BottleTag;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;

@RestController
@RequestMapping(value="/api/admin/bottles", produces="application/json; charset=UTF-8")
public class AdminBottleController {

	@Autowired
	private BottleService bservice;
	@Autowired
	private ReviewService rservice;
	@Autowired
	private TagService tservice;
	
	@GetMapping(value="/{bottleNo}")
	public Map<String, Object> getBottle(int bottleNo) {
		
		Bottle bottle = bservice.getBottle(bottleNo);
		List<Review> reviewList = rservice.getReviews(bottleNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("bottle", bottle);
		map.put("reviewList", reviewList);
		
		return map;
	}
	
	// 추가창 들어갈때
	@GetMapping(value="/addition")
	public Map<String, Object> getTags() {
		
		// 태그 선택할 수 있도록 태그 선택지 불러오기
		List<TagType> tagTypeList = tservice.getAllTagTypes();
		List<Tag> tagList = tservice.getAllTags();
		
		Map<String, Object> map = new HashMap<>();
		map.put("tagTypeList", tagTypeList);
		map.put("tagList", tagList);
		
		return map;
	}
	
	// 추가 완료할 때
	@PostMapping(value="/addition")
	public Bottle addBottle(Bottle newBottle, List<BottleTag> tagList) {
		
		// 전체검색해오는 것 합친 뒤에 태그리스트를 받아오는걸로 수정하겠음
		int count = bservice.addBottle(newBottle);
		tservice.insertBottleTag(tagList);
		
		// 전체검색해오는 것 합친 뒤에 태그리스트를 받아오는걸로 수정하겠음
		return null;
	}
	
	// 수정창 들어갈때 
	@GetMapping(value="/{bottleNo}/set-data")
	public Map<String, Object> getBottleInfo(int bottleNo) {
		
		Bottle bottle = bservice.getBottle(bottleNo);
		List<TagType> tagTypeList= tservice.getAllTagTypes();
		List<Tag> bottleTagList = tservice.getTags(bottleNo);
		
		// 태그 리스트도 보내야돼~
		
		Map<String, Object> map = new HashMap<>();
		map.put("bottle", bottle);
		map.put("tagTypeList", tagTypeList);
		map.put("bottleTagList", bottleTagList);
		
		return map;
	}
	
	// 수정완료할때
	@PostMapping(value="/{bottleNo}/set-data", produces="application/json; charset=UTF-8")
	public Bottle editBottle(Bottle editBottle, List<BottleTag> tagList) {
		
		Bottle bottle = bservice.editBottle(editBottle);
		tservice.editTag(editBottle.getBottleNo(), tagList);
		
		return bottle;
		
	}
}
