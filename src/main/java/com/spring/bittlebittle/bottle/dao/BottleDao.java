package com.spring.bittlebittle.bottle.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.bittlebittle.bottle.vo.Bottle;

public interface BottleDao {

	Bottle selectOne(int bottleNo);

	List<Bottle> selectRelatedBottleList(int bottleNo);

	int updateOne(Bottle updateBottle);

	Bottle insertOne(Bottle newBottle);

}
