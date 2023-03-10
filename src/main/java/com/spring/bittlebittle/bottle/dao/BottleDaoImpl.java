package com.spring.bittlebittle.bottle.dao;


import com.spring.bittlebittle.bottle.vo.Bottle;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BottleDaoImpl implements BottleDao {


	@Autowired
	private SqlSession sqlSession;

    @Override
    public List<Bottle> selectList() {

		return sqlSession.selectList("bottleMapper.selectAll");
    }
  
	@Override
	public Bottle selectOne(int bottleNo) {
		
		return sqlSession.selectOne("bottleMapper.selectOne", bottleNo);
	}
	
	@Override
	public List<Bottle> selectRelatedBottleList(int bottleNo) {
		
		return sqlSession.selectList("bottleMappper.selectRelated", bottleNo);
	}
	
	@Override
	public int updateOne(Bottle updateBottle) {
		
		return sqlSession.update("bottleMapper.updateOne", updateBottle);
	}

	@Override
	public Bottle insertOne(Bottle newBottle) {
		return null;
	}
}
