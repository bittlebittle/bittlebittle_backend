package com.spring.bittlebittle.food.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.food.service.FoodService;
import com.spring.bittlebittle.food.vo.Food;
import com.spring.bittlebittle.food.vo.FoodInfo;
import com.spring.bittlebittle.tag.service.TagService;

@RestController
@RequestMapping(value="/api/admin/foods", produces="application/json; charset=UTF-8")
public class AdminFoodController {

	@Autowired
	private FoodService fservice;
	private TagService tservice;
	
	// 조회 (확인완료)
	@GetMapping
	public List<Food> getAllFoods(){
		
		List<Food> foodList = fservice.getAllFoods();
		
		return foodList;
	}
	
	
	// 작성 (확인완료)
	@PostMapping
	public Map<String, Object> addFood(@RequestBody FoodInfo newFood){
		
		Map<String, Object> map = fservice.addFood(newFood);
	
		
		return map;
	}
	
	// 개별창 들어갔을 때 (확인완료)
	@GetMapping(value="/{foodNo}")
	public Map<String, Object> getFood(@PathVariable int foodNo) {
		
		
		Map<String, Object> map = fservice.getFood(foodNo);
		
		return map;
	}
	
	// 수정 완료할 때  (확인완료)
	@PostMapping(value="/set-data")
	public Map<String, Object> editFood(@RequestBody FoodInfo editFood){
		
		Map<String, Object> map= fservice.editFood(editFood);
		
		// 수정한 음식정보, 태그
		return map;
	}
	
	// 삭제 (확인완료)
	@GetMapping(value="/{foodNo}/deletion")
	public List<Food> removeFood(@PathVariable int foodNo){
		
		List<Food> foodList = fservice.removeFood(foodNo);
		
		return foodList;
	}
}
