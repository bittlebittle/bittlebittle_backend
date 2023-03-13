package com.spring.bittlebittle.bottle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.bottle.service.BottleService;
import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.favorite.service.FavoriteService;
import com.spring.bittlebittle.favorite.vo.Favorite;
import com.spring.bittlebittle.food.service.FoodService;
import com.spring.bittlebittle.food.vo.Food;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.tag.service.TagService;

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
	
	Logger log = LogManager.getLogger("case3");
	
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
		
		List<Bottle> relatedBottleList = bservice.getRelatedBottleList(bottleNo);
		List<Review> reviewList = rservice.getReviews(bottleNo);
		List<Food> foodList = fservice.getRelatedFoods(bottleNo);
		
		// userNo -> session 등록되면 session에서 빼오는 걸로 할것
		
		int userNo = 1;
		
		Favorite favorite = new Favorite(userNo, bottleNo);
		
		List<Favorite> favoriteList = fvservice.isFavorite(favorite);
		// not null 이면 찜되어있는 것.
	
		Map<String, Object> map = new HashMap<>();
		map.put("bottle", bottle);
		map.put("relatedBottleList", relatedBottleList);
		map.put("reviewList", reviewList);
		map.put("foodList",foodList);
		map.put("isFavorite", favoriteList);
		
		return map;
	}
	
	// favorite 클릭했을 때
	@GetMapping(value="/{bottleNo}/favorite")
	public List<Favorite> isFavorite(@PathVariable int bottleNo) {
		
		
		// userNo -> session 등록되면 session에서 빼오는 것으로 할 것 
		int userNo = 1;
		

		Favorite favorite = new Favorite(userNo, bottleNo);
		
		List<Favorite> favoriteList = fvservice.isFavorite(favorite);
		
		
		if(favoriteList.isEmpty()) {
			fvservice.addFavorite(favorite);
		} else {
			fvservice.removeFavorite(favorite);
		}
		
		
		List<Favorite> newFavoriteList = fvservice.isFavorite(favorite);
		
		return newFavoriteList;
	}

}
