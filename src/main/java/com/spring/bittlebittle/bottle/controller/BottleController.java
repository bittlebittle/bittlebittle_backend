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
	

//	@GetMapping
//	public List<Bottle> getBottles() {
//		List<Bottle> selectList = bservice.getBottles();
//		return selectList;
//	}

	@GetMapping(value = "/all") // bittlebittle/api/bottles?keyword={bottleTitle}&sorted={sorted}
	public Map<String, Object> getBottles(String keyword, String sorted) {

		Logger log = LogManager.getLogger("case3");

		Map<String, String> param = new HashMap<>();
		param.put("keyword", keyword);
		param.put("sorted", sorted);

		log.debug("keyword" + keyword);
		log.debug("sorted" + sorted);
//		String keyword = param.get("keyword");
//		String sorted = param.get("sorted");

//		param.put("keyword", keyword);
//		param.put("sorted", sorted);

//		String keyword = param.put("keyword");
//		String sorted = param.get("sorted");
		Map<String, Object> map = bservice.getBottles(param);

		return map;
	}

	@GetMapping(params = "sorted = new")
	public List<Bottle> getNewBottles(){

		List<Bottle> bottlenewList = bservice.getNewBottles();

		return  bottlenewList;
	}

	@GetMapping(params = "sorted = best")
	public List<Bottle> getBestBottles(){

		List<Bottle> bottlebestList = bservice.getBestBottles();

		return  bottlebestList;
	}

	@GetMapping(params = "sorted = relatedFavorite")
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
		List<Bottle> bottleBestList = bservice.getBestBottles();
		List<Bottle> bottleRelatedeFavoriteList = bservice.getRelatedFavorite();


		Map<String, Object> map = new HashMap<>();
		map.put("newBottle", bottleNewList);
		map.put("bestBottle", bottleBestList);
		map.put("relatedFavorite", bottleRelatedeFavoriteList);
		map.put("favorite", favoriteList);

		return map;
	}



	@GetMapping(value="/{bottleNo}")
	public Map<String, Object> getBottle(@PathVariable int bottleNo) {

		
		Map<String, Object> map = bservice.getBottle(bottleNo);
		
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
