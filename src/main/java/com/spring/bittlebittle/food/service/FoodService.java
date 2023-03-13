package com.spring.bittlebittle.food.service;

import java.util.List;

import com.spring.bittlebittle.food.vo.Food;

public interface FoodService {

	List<Food> getRelatedFoods(int bottleNo);

	List<Food> getAllFoods();

	int addFood(Food newFood);

	Food getFood(Food food);

	List<Food> editFood(Food editFood);

	List<Food> removeFood(int foodNo);

}
