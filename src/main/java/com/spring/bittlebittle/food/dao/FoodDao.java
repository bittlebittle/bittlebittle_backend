package com.spring.bittlebittle.food.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.bittlebittle.food.vo.Food;

public interface FoodDao {

	List<Food> selectList(int bottleNo);

	List<Food> selectList();

}
