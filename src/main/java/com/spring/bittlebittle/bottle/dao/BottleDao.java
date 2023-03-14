package com.spring.bittlebittle.bottle.dao;


import com.spring.bittlebittle.bottle.vo.Bottle;

import java.util.List;
import java.util.Map;

public interface BottleDao {


//	List<Bottle> selectAllBottles(String keyword, String sorted);
	List<Bottle> selectAllBottles(Map<String, String> param);

//	List<Bottle> selectMainList();

	List<Bottle> selectNewList();

	List<Bottle> selectBestList();

	List<Bottle> selectRelatedFavoriteList();

//	List<Bottle> selectSearchBottlesList(String keyword);

	Bottle selectOne(int bottleNo);

	List<Bottle> selectRelatedBottleList(int bottleNo);

	int updateOne(Bottle updateBottle);

	int insertOne(Bottle newBottle);

	void editViewCnt(int bottleNo);


}
