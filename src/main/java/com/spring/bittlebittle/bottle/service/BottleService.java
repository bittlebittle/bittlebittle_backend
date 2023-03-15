package com.spring.bittlebittle.bottle.service;


import java.util.List;
import java.util.Map;

import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.bottle.vo.BottleInfo;
import com.spring.bittlebittle.bottle.vo.BottleSearch;


public interface BottleService {

	// 검색,키워드 조회하는 리스트
	Map<String, Object> getBottles(BottleSearch bottleSearch);

//	List<Bottle> getSearchBottleList(String keyword);
    
	Map<String, Object> getBottle(int bottleNo);

	Map<String, Object> getBottleByAdmin(int bottleNo);

	List<Bottle> getRelatedBottleList(int bottleNo);


	Map<String, Object> editBottle(BottleInfo editBottle);

	List<Bottle> removeBottle(int bottleNo);

	List<Bottle> addBottle(BottleInfo bottle);


	// 로그인하면 나오는 메인 리스트
//	List<Bottle> getMainBottles();

	// New 리스트
	List<Bottle> getNewBottles();

	// Best 리스트
	List<Bottle> getBestBottles();

	// 찜하기 관련 리스트
	List<Bottle> getRelatedFavorite();
}
