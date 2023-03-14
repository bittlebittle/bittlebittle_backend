package com.spring.bittlebittle.bottle.controller;

import com.spring.bittlebittle.bottle.service.BottleService;
import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.favorite.service.FavoriteService;
import com.spring.bittlebittle.favorite.vo.Favorite;
import com.spring.bittlebittle.food.service.FoodService;
import com.spring.bittlebittle.food.vo.Food;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.tag.service.TagService;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	
//	@GetMapping
//	public List<Bottle> getBottles() {
//		List<Bottle> selectList = bservice.getBottles();
//		return selectList;
//	}

	@GetMapping
	public Map<String, Object> getBottles(String keyword) {

		int userNo = 1;
		Favorite favorite = new Favorite();
		favorite.setUserNo(userNo);

		List<Favorite> favoriteList = fvservice.isFavorite(favorite);
		List<Bottle> selectList = bservice.getBottles(keyword);
		List<TagType> tagTypeList = tservice.getAllTagTypes();
		List<Tag> tagList = tservice.getAllTags();

		Map<String, Object> map = new HashMap<>();
		map.put("bottle", selectList);
		map.put("TagType", tagTypeList);
		map.put("Tag", tagList);
		map.put("Favorite", favoriteList);

		return map;
	}

	@GetMapping
	public List<Bottle> getNewBottles(){

		List<Bottle> bottlenewList = bservice.getNewBottles();

		return  bottlenewList;
	}

	@GetMapping
	public List<Bottle> getBestBottles(){

		List<Bottle> bottlebestList = bservice.getBestBottles();

		return  bottlebestList;
	}

	@GetMapping
	public List<Bottle> getRelatedFavoriteBottles(){

		List<Bottle> bottleFavoriteList = bservice.getBestBottles();

		return  bottleFavoriteList;
	}



	@GetMapping
	public Map<String, Object> getMainBottles() {

		int userNo = 1;
		Favorite favorite = new Favorite();
		favorite.setUserNo(userNo);

		List<Favorite> favoriteList = fvservice.isFavorite(favorite);
		List<Bottle> bottleNewList = bservice.getNewBottles();


		Map<String, Object> map = new HashMap<>();
		map.put("bottle", bottleNewList);
		map.put("Favorite", favoriteList);

		return map;
	}



	@GetMapping(value="/{bottleNo}")
	public Map<String, Object> getBottle(@PathVariable int bottleNo) {
		
		Bottle bottle = bservice.getBottle(bottleNo);
		
		List<Bottle> relatedBottleList = bservice.getRelatedBottleList(bottleNo);
		List<Review> reviewList = rservice.getReviews(bottleNo);
		List<Food> foodList = fservice.getFoods(bottleNo);
		
		// userNo -> session 등록되면 session에서 빼오는 걸로 할것
		
		int userNo = 1;
		
		Favorite favorite = new Favorite(userNo, bottleNo);

		List<Favorite> favoriteList = fvservice.isFavorite(favorite);
		// 1이면 찜이 되어있는 것, 0이면 찜이 안 되어있는 것.
	
		Map<String, Object> map = new HashMap<>();
		map.put("bottle", bottle);
		map.put("relatedBottleList", relatedBottleList);
		map.put("reviewList", reviewList);
		map.put("foodList",foodList);
		map.put("isFavorite", favoriteList);
		
		return map;
	}
	
	@GetMapping(value="/{bottleNo}/favorite")
	public List<Favorite> isFavorite(Favorite favorite) {
		
		List<Favorite> favoriteList = fvservice.isFavorite(favorite);

		return favoriteList;
	}



}
