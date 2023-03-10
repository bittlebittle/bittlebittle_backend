package com.spring.bittlebittle.food.service;

import java.util.List;

import com.spring.bittlebittle.food.vo.Food;

public interface FoodService {

	List<Food> getFoods(int bottleNo);

	List<Food> getAllFoods();

}
