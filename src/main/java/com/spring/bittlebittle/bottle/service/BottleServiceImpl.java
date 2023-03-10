package com.spring.bittlebittle.bottle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.bottle.dao.BottleDao;
import com.spring.bittlebittle.bottle.vo.Bottle;

@Service
public class BottleServiceImpl implements BottleService{
		
	@Autowired
	private BottleDao dao;
	
	@Override
	public Bottle getBottle(int bottleNo) {
		
		dao.editViewCnt(bottleNo);
		
		return dao.selectOne(bottleNo);	
	}
	
	@Override
	public List<Bottle> getRelatedBottleList(int bottleNo) {
		
		return dao.selectRelatedBottleList(bottleNo);
	}

	@Override
	@Transactional
	public Bottle addBottle(Bottle newBottle) {
	
		return dao.insertOne(newBottle);
	}
	
	@Override
	@Transactional
	public Bottle editBottle(Bottle updateBottle) {
		
		dao.updateOne(updateBottle);
		Bottle bottle = dao.selectOne(updateBottle.getBottleNo());
		
		return bottle;
	}

}
