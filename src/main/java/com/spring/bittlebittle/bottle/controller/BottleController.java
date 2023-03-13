package com.spring.bittlebittle.bottle.controller;

import com.spring.bittlebittle.bottle.service.BottleService;
import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.favorite.service.FavoriteService;
import com.spring.bittlebittle.favorite.vo.Favorite;
import com.spring.bittlebittle.food.service.FoodService;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api/bottles", produces="application/json; charset=UTF-8")
public class BottleController {

	
	@Autowired
	private BottleService bservice;
	@Autowired
	private ReviewService rservice;
	@Autowired
	private FoodService fservice;
	@Autowired
	private FavoriteService fvservice;

	@Autowired
	private TagService tservice;
	
	@GetMapping
	public List<Bottle> getBottles() {
		List<Bottle> selectList = bservice.getBottles();
		return selectList;
	}

//	@GetMapping
//	public List<Bottle> getBottles() {
//		// 도수, 맛 , 종류, 탄산, 나라, 상황
//
//		List<Bottle> selectList = bservice.getBottles();
//		List<String> abvList = tservice;
//
//		List<String> tasteList = new ArrayList<>();
//
//
//
//		return selectList;
//	}


	@GetMapping(value="/{bottleNo}")
	public Map<String, Object> getBottle(@PathVariable int bottleNo) {
		
		Bottle bottle = bservice.getBottle(bottleNo);
		
//		List<Bottle> relatedBottleList = bservice.getRelatedBottleList(bottleNo);
//		List<Review> reviewList = rservice.getReviews(bottleNo);
//		List<Food> foodList = fservice.getFoods(bottleNo);
//		
//		// userNo -> session 등록되면 session에서 빼오는 걸로 할것
//		
//		int userNo = 1;
//		
//		Favorite favorite = new Favorite(userNo, bottleNo);
//		
//		int isFavorite = fvservice.isFavorite(favorite);
//		// 1이면 찜이 되어있는 것, 0이면 찜이 안 되어있는 것.
//	
		Map<String, Object> map = new HashMap<>();
		map.put("bottle", bottle);
//		map.put("relatedBottleList", relatedBottleList);
//		map.put("reviewList", reviewList);
//		map.put("foodList",foodList);
//		map.put("isFavorite", isFavorite);
		
		return map;
	}
	
	@GetMapping(value="/{bottleNo}/favorite")
	public int isFavorite(Favorite favorite) {
		
		int isFavorite = fvservice.isFavorite(favorite);
		
		
		return isFavorite;
	}

}
