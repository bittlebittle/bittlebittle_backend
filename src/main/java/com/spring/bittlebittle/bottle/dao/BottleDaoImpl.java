package com.spring.bittlebittle.bottle.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.bottle.vo.Bottle;

@Repository
public class BottleDaoImpl implements BottleDao{

	@Override
	public Bottle selectOne(SqlSession session, int bottleNo) {
		
		return session.selectOne("bottleMapper.selectOne", bottleNo);
	}
	
	@Override
	public List<Bottle> selectRelatedBottleList(SqlSession session, int bottleNo) {
		// TODO Auto-generated method stub
		return session.selectList("bottleMappper.selectRelated", bottleNo);
	}
}
