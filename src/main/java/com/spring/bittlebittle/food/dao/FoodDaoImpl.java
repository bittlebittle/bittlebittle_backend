package com.spring.bittlebittle.food.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.food.vo.Food;

@Repository
public class FoodDaoImpl implements FoodDao{

	@Override
	public List<Food> selectList(SqlSession session, int bottleNo) {
		// TODO Auto-generated method stub
		return null;
	}
}
