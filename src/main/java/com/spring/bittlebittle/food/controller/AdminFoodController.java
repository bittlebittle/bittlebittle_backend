package com.spring.bittlebittle.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.food.service.FoodService;
import com.spring.bittlebittle.food.vo.Food;

@RestController
@RequestMapping(value="/api/admin/foods", produces="application/json; charset=UTF-8")
public class AdminFoodController {

	@Autowired
	private FoodService fservice;
	
	@GetMapping
	public List<Food> getFoods() {
		List<Food> foodList = fservice.getAllFoods();
		
		return foodList;
	}
	
}
