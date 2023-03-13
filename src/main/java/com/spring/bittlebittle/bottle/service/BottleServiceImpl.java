package com.spring.bittlebittle.bottle.service;

import com.spring.bittlebittle.bottle.dao.BottleDao;
import com.spring.bittlebittle.bottle.vo.Bottle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BottleServiceImpl implements BottleService {

	@Autowired
	private BottleDao dao;

	// 전체 리스트
    @Override
    public List<Bottle> getBottles(String keyword) {
        return dao.selectList(keyword);

    }
	// 메인 리스트
	@Override
	public List<Bottle> getMainBottles( ) {
		return dao.selectMainList();

	}
	// New 리스트
	@Override
	public List<Bottle> getNewBottles() {
		return dao.selectNewList();
	}

	// Best 리스트
	@Override
	public List<Bottle> getBestBottles() {
		return dao.selectBestList();
	}

	// 찜하기 관련 리스트
	@Override
	public List<Bottle> getRelatedFavoriteList() {
		return dao.selectRelatedFavoriteList();
	}


	// 키워드 검색
//	@Override
//	public List<Bottle> getSearchBottleList(String keyword) {
//		return dao.selectSearchBottlesList(keyword);
//	}

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
