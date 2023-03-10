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
	public List<Food> selectList(int bottleNo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Food> selectList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("foodMapper.selectAllFoods");
	}
}
