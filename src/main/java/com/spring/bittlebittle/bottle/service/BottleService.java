package com.spring.bittlebittle.bottle.service;


import com.spring.bittlebittle.bottle.vo.Bottle;

import java.util.List;
import java.util.Map;

public interface BottleService {

	// 검색,키워드 조회하는 리스트
	Map<String, Object> getBottles(Map<String, String> param);

//	List<Bottle> getSearchBottleList(String keyword);
    
	Bottle getBottle(int bottleNo);

	List<Bottle> getRelatedBottleList(int bottleNo);

	int addBottle(Bottle newBottle);

	Bottle editBottle(Bottle updateBottle);

	// 로그인하면 나오는 메인 리스트
//	List<Bottle> getMainBottles();

	// New 리스트
	List<Bottle> getNewBottles();

	// Best 리스트
	List<Bottle> getBestBottles();

	// 찜하기 관련 리스트
	List<Bottle> getRelatedFavorite();
}
