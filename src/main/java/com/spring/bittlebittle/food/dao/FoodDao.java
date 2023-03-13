package com.spring.bittlebittle.food.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.bittlebittle.food.vo.Food;

public interface FoodDao {

	List<Food> selectRelatedFoods(int bottleNo);

	List<Food> selectAllFoods();

	void insertOne(Food newFood);

	Food selectOne(Food newFood);

	void updateOne(Food editFood);

	void deleteOne(int foodNo);

	int selectLastFoodNo();

}
