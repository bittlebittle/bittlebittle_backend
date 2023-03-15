package com.spring.bittlebittle.bottle.controller;

import com.spring.bittlebittle.bottle.service.BottleService;
import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.bottle.vo.BottleSearch;
import com.spring.bittlebittle.favorite.service.FavoriteService;
import com.spring.bittlebittle.favorite.vo.Favorite;
import com.spring.bittlebittle.food.service.FoodService;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.tag.service.TagService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


	Logger log = LogManager.getLogger("case3");

	// 리스트, 키워드는 확인 완료
	// 태그 선택은 확인 해야함
	@GetMapping(value = "/all") // bittlebittle/api/bottles?keyword={bottleTitle}&sorted={sorted}
	public Map<String, Object> getBottles(@ModelAttribute BottleSearch bottleSearch) {

		Map<String, Object> map = bservice.getBottles(bottleSearch);

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

	// 확인 완료
	@GetMapping
	public Map<String, Object> getMainBottles() {

		Map<String, Object> map = bservice.getMainBottles();

		return map;
	}


	
	// 개별조회 (확인완료)
	@GetMapping(value="/{bottleNo}")
	public Map<String, Object> getBottle(@PathVariable int bottleNo) {

		
		Map<String, Object> map = bservice.getBottle(bottleNo);
		
		
		return map;
	} 
	
	// favorite 클릭했을 때 (확인완료)
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
