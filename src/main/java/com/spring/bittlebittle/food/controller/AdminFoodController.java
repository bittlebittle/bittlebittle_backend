package com.spring.bittlebittle.food.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.food.service.FoodService;
import com.spring.bittlebittle.food.vo.Food;
import com.spring.bittlebittle.tag.service.TagService;
import com.spring.bittlebittle.tag.vo.FoodTag;
import com.spring.bittlebittle.tag.vo.Tag;

@RestController
@RequestMapping(value="/api/admin/foods", produces="application/json; charset=UTF-8")
public class AdminFoodController {

	@Autowired
	private FoodService fservice;
	private TagService tservice;
	
	// 조회
	@GetMapping
	public List<Food> getAllFoods(){
		
		List<Food> foodList = fservice.getAllFoods();
		
		return foodList;
	}
	
	// 작성
	@PostMapping
	public List<Food> addFood(Food newFood, List<Integer> tagNoList){
		
		int foodNo = fservice.addFood(newFood);
	
		List<FoodTag> foodTagList = new ArrayList();
		
		for(int tagNo : tagNoList) {
			foodTagList.add(new FoodTag(tagNo, foodNo));
		}
		
		tservice.addFoodTag(foodTagList);
		
		List<Food> foodList = fservice.getAllFoods();
		
		return foodList;
	}
	
	// 수정창 들어갔을 때
	@GetMapping(value="/{foodNo}")
	public Map<String, Object> getFood(@PathVariable int foodNo) {
		
		Food oneFood = new Food();
		oneFood.setFoodNo(foodNo);
		
		Food food = fservice.getFood(oneFood);
		List<Tag> foodTagList = tservice.getTagsByFood(food.getFoodNo());
		
		Map<String, Object> map = new HashMap();
		map.put("food", food);
		map.put("foodTagList", foodTagList);
		
		return map;
	}
	
	// 수정 완료할 때 
	@PostMapping(value="/set-data")
	public List<Food> editFood(Food editFood, List<FoodTag> foodTagList){
		
		List<Food> foodList = fservice.editFood(editFood);
		tservice.editFoodTag(editFood.getFoodNo(), foodTagList);
		
		
		return foodList;
	}
	
	// 삭제
	@GetMapping(value="/{foodNo}/deletion")
	public List<Food> removeFood(@PathVariable int foodNo){
		
		List<Food> foodList = fservice.removeFood(foodNo);
	
		tservice.removeFoodTag(foodNo);
		
		return foodList;
	}
}
