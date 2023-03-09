package com.spring.bittlebittle.bottle.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.bittlebittle.bottle.vo.Bottle;

public interface BottleDao {

	Bottle selectOne(SqlSession session, int bottleNo);

	List<Bottle> selectRelatedBottleList(SqlSession session, int bottleNo);

}
