package com.spring.bittlebittle.bottle.dao;


import com.spring.bittlebittle.bottle.vo.Bottle;

import java.util.List;

public interface BottleDao {


	List<Bottle> selectList();
	Bottle selectOne(int bottleNo);

	List<Bottle> selectRelatedBottleList(int bottleNo);

	int updateOne(Bottle updateBottle);

	Bottle insertOne(Bottle newBottle);

}
