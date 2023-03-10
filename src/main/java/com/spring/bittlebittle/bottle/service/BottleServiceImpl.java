package com.spring.bittlebittle.bottle.service;

import com.spring.bittlebittle.bottle.dao.BottleDao;
import com.spring.bittlebittle.bottle.vo.Bottle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BottleServiceImpl implements BottleService{

	@Autowired
	private BottleDao dao;

    @Override
    public List<Bottle> getBottles() {
        return dao.getBottles();

    }
  
	@Override
	public Bottle getBottle(int bottleNo) {
		
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
	public Bottle updateBottle(Bottle updateBottle) {
		
		dao.updateOne(updateBottle);
		Bottle bottle = dao.selectOne(updateBottle.getBottleNo());
		
		return bottle;
	}
}
