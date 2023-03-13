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
		
		return sqlSession.selectList("bottleMapper.selectRelated", bottleNo);
	}
	
	@Override
	public int insertOne(Bottle newBottle) {
		
		return sqlSession.insert("bottleMapper.insertOne", newBottle);
	}
	
	@Override
	public int updateOne(Bottle editBottle) {
		
		return sqlSession.update("bottleMapper.updateOne", editBottle);
	}
	
	@Override
	public void editViewCnt(int bottleNo) {
	
		sqlSession.update("bottleMapper.updateViewCnt", bottleNo);
		
	}
	
	@Override
	public void deleteOne(int bottleNo) {
		
		sqlSession.delete("bottleMapper.deleteOne", bottleNo);
		
	}
	
	@Override
	public int selectLastBottleNo() {
		
		return sqlSession.selectOne("bottleMapper.selectLastBottleNo");
	}
}
