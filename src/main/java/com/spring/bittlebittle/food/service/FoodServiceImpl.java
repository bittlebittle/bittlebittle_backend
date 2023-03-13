package com.spring.bittlebittle.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.food.dao.FoodDao;
import com.spring.bittlebittle.food.vo.Food;

@Service
public class FoodServiceImpl implements FoodService{
 
	@Autowired
	private FoodDao dao;
	
	
	@Override
	public List<Food> getRelatedFoods(int bottleNo) {
		
		return dao.selectRelatedFoods(bottleNo);
	}
	
	@Override
	public List<Food> getAllFoods() {
		
		return dao.selectAllFoods();
	}
	
	@Override
	public Food getFood(Food food) {
		
		return dao.selectOne(food);
	}
	
	@Override
	@Transactional
	public int addFood(Food newFood) {
		
		dao.insertOne(newFood);
		
		return dao.selectLastFoodNo();
	}
	
	@Override
	@Transactional
	public List<Food> editFood(Food editFood) {
		
		dao.updateOne(editFood);
		
		return dao.selectAllFoods();
	}
	
	@Override
	@Transactional
	public List<Food> removeFood(int foodNo) {

		dao.deleteOne(foodNo);
		
		return dao.selectAllFoods();
	}
}
