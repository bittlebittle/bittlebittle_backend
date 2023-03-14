package com.spring.bittlebittle.bottle.dao;


import java.util.List;
import java.util.Map;

import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.bottle.vo.BottleInfo;

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

	int updateOne(BottleInfo editBottle);

	int insertOne(BottleInfo bottle);

	void editViewCnt(int bottleNo);
	
	void deleteOne(int bottleNo);

	int selectLastBottleNo();
	


}
