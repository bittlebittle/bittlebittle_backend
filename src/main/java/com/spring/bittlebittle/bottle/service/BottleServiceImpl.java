package com.spring.bittlebittle.bottle.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bittlebittle.bottle.dao.BottleDao;
import com.spring.bittlebittle.bottle.vo.Bottle;

@Service
public class BottleServiceImpl implements BottleService{
		
	@Autowired
	private BottleDao dao;
	@Autowired
	private SqlSession session;
	
	@Override
	public Bottle getBottle(int bottleNo) {
		
		return dao.selectOne(session, bottleNo);
	}
	
	@Override
	public List<Bottle> getRelatedBottleList(int bottleNo) {
		// TODO Auto-generated method stub
		return dao.selectRelatedBottleList(session, bottleNo);
	}

}
