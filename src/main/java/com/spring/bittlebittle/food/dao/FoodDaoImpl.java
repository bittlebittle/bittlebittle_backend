package com.spring.bittlebittle.food.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.food.vo.Food;

@Repository
public class FoodDaoImpl implements FoodDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Food> selectRelatedFoods(int bottleNo) {
		
		return sqlSession.selectList("foodMapper.selectRelated", bottleNo);
	}
	
	@Override
	public List<Food> selectAllFoods() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("foodMapper.selectAllFoods");
	}
	
	@Override
	public void insertOne(Food newFood) {
		
		sqlSession.insert("foodMapper.insertOne", newFood);
		
	}
	
	@Override
	public Food selectOne(Food food) {
		
		return sqlSession.selectOne("foodMapper.selectOne", food);
	}

	@Override
	public void updateOne(Food editFood) {

		sqlSession.update("foodMapper.updateOne", editFood);
		
	}
	
	@Override
	public void deleteOne(int foodNo) {
		
		sqlSession.delete("foodMapper.deleteOne", foodNo);
	}
	
	@Override
	public int selectLastFoodNo() {
		
		return sqlSession.selectOne("foodMapper.selectLastFoodNo");
	}
}
