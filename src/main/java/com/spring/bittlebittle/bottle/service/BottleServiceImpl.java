package com.spring.bittlebittle.bottle.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.bottle.dao.BottleDao;
import com.spring.bittlebittle.bottle.vo.Bottle;

@Service
public class BottleServiceImpl implements BottleService{

	@Autowired
	private BottleDao dao;

	Logger log = LogManager.getLogger("case3");
	
    @Override
    public List<Bottle> getBottles() {
        return dao.selectList();

    }
  
	@Override
	@Transactional
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
	public int addBottle(Bottle newBottle) {
	
		dao.insertOne(newBottle);
		return dao.selectLastBottleNo();
	}
	
	@Override
	@Transactional
	public Bottle editBottle(Bottle updateBottle) {
		
		dao.updateOne(updateBottle);
		Bottle bottle = dao.selectOne(updateBottle.getBottleNo());
		
		return bottle;
	}
	
	@Override
	public List<Bottle> removeBottle(int bottleNo) {
		
		dao.deleteOne(bottleNo);
		
		// 완료되면 리스트불러오는 것 추가
		
		return null;
	}

}
