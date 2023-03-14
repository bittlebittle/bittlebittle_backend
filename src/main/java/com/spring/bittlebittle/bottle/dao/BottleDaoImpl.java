package com.spring.bittlebittle.bottle.dao;


import com.spring.bittlebittle.bottle.vo.Bottle;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BottleDaoImpl implements BottleDao {


	@Autowired
	private SqlSession sqlSession;


	@Override
	public List<Bottle> selectAllBottles(Map<String, String> param) {
		return sqlSession.selectList("bottleMapper.selectAll", param);
	}


// 매퍼 작성해야함
//	@Override
//	public List<Bottle> selectMainList() {
//		return sqlSession.selectList();
//	}


	@Override
	public List<Bottle> selectNewList() {

		return sqlSession.selectList("bottleMapper.selectNewBottles");
	}

	@Override
	public List<Bottle> selectBestList() {

		return sqlSession.selectList("bottleMapper.selectBestBottles");
	}

	@Override
	public List<Bottle> selectRelatedFavoriteList() {

		return sqlSession.selectList("bottleMapper.selectRelatedFavorite");
	}



//	@Override
//	public List<Bottle> selectSearchBottlesList(String keyword) {
//		return sqlSession.selectList("bottleMapper.selectAll", keyword);
//	}

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
}
