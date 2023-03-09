package com.spring.bittlebittle.food.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bittlebittle.food.dao.FoodDao;
import com.spring.bittlebittle.food.vo.Food;

@Service
public class FoodServiceImpl implements FoodService{
 
	@Autowired
	private FoodDao dao;
	@Autowired
	private SqlSession session;
	
	@Override
	public List<Food> getFoodList(int bottleNo) {
		// TODO Auto-generated method stub
		return dao.selectList(session, bottleNo);
	}
}
