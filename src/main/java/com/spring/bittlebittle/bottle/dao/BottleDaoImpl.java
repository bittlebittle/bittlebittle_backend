package com.spring.bittlebittle.bottle.dao;


import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.bottle.vo.BottleInfo;
import com.spring.bittlebittle.bottle.vo.BottleSearch;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class BottleDaoImpl implements BottleDao {


	@Autowired
	private SqlSession sqlSession;


	@Override
	public List<Bottle> selectAllBottles(BottleSearch bottleSearch) {
		return sqlSession.selectList("bottleMapper.selectAll", bottleSearch);
	}


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

	@Override
	public Bottle selectOne(int bottleNo) {
		
		return sqlSession.selectOne("bottleMapper.selectOne", bottleNo);
	}
	
	@Override
	public List<Bottle> selectRelatedBottleList(int bottleNo) {
		
		return sqlSession.selectList("bottleMapper.selectRelated", bottleNo);
	}
	
	@Override
	public int insertOne(BottleInfo bottle) {
		
		return sqlSession.insert("bottleMapper.insertOne", bottle);
	}
	
	@Override
	public int updateOne(BottleInfo editBottle) {
		
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
