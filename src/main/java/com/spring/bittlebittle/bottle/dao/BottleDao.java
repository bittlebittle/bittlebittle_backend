package com.spring.bittlebittle.bottle.dao;


import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.bottle.vo.BottleInfo;
import com.spring.bittlebittle.bottle.vo.BottleSearch;

import java.util.List;

public interface BottleDao {


	List<Bottle> selectAllBottles(BottleSearch bottleSearch);

//	List<Bottle> selectMainList();

	List<Bottle> selectNewList();

	List<Bottle> selectBestList();

	List<Bottle> selectRelatedFavoriteList();

	Bottle selectOne(int bottleNo);

	List<Bottle> selectRelatedBottleList(int bottleNo);

	int updateOne(BottleInfo editBottle);

	int insertOne(BottleInfo bottle);

	void editViewCnt(int bottleNo);
	
	void deleteOne(int bottleNo);

	int selectLastBottleNo();
	


}
