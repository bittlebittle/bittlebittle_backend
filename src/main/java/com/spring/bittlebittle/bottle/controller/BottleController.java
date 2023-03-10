package com.spring.bittlebittle.bottle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@RestController
@RequestMapping(value="/api/bottles/{bottleNo}")
public class BottleController {

	
	@Autowired
	private BottleService bservice;
	@Autowired
	private ReviewService rservice;
	@Autowired
	private FoodService fservice;
	@Autowired
	private FavoriteService fvservice;
	
  @GetMapping(value="/bottles")

    public String selectAll() {

        List<Bottle> selectAll = bservice.getAllBottles();

        return "allBottle";
    }
  
	@GetMapping(produces="application/json; charset=UTF-8")
	public Map<String, Object> getBottle(@PathVariable int bottleNo) {
		
		Bottle bottle = bservice.getBottle(bottleNo);
		List<Bottle> relatedBottleList = bservice.getRelatedBottleList(bottleNo);
		List<Review> reviewList = rservice.getReviewList(bottleNo);
		List<Food> foodList = fservice.getFoodList(bottleNo);
		
		// userNo -> session 등록되면 session에서 빼오는 걸로 할것
		
		int userNo = 1;
		
		Favorite favorite = new Favorite(userNo, bottleNo);
		
		int checkFavorite = fvservice.checkFavorite(favorite);
		// 1이면 찜이 되어있는 것, 0이면 찜이 안 되어있는 것.
	
		Map<String, Object> map = new HashMap<>();
		map.put("bottle", bottle);
		map.put("relatedBottleList", relatedBottleList);
		map.put("reviewList", reviewList);
		map.put("foodList",foodList);
		map.put("checkFavorite", checkFavorite);
		
		return map;
	}
	
	@GetMapping(value="/favorite")
	public int clickFavorite(Favorite favorite) {
		
		int checkFavorite = fvservice.checkFavorite(favorite);
		
		int favoriteCnt=0;
		
		if(checkFavorite == 0) {
			favoriteCnt = fvservice.addFavorite(favorite);
		} else {
			favoriteCnt = fvservice.deleteFavorite(favorite);
		}
		
		// 아니면 checkFavorite 넘겨서 색 지울지 말지 결정하면 될듯
		return favoriteCnt;
	}

}
