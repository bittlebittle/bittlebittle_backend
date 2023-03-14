package com.spring.bittlebittle.bottle.dao;


import java.util.List;

import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.bottle.vo.BottleInfo;

public interface BottleDao {


	List<Bottle> selectList();
	
	Bottle selectOne(int bottleNo);

	List<Bottle> selectRelatedBottleList(int bottleNo);

	int updateOne(BottleInfo editBottle);

	int insertOne(BottleInfo bottle);

	void editViewCnt(int bottleNo);
	
	void deleteOne(int bottleNo);

	int selectLastBottleNo();
	

}
