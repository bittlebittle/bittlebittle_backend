package com.spring.bittlebittle.bottle.dao;


import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.spring.bittlebittle.bottle.vo.Bottle;

@Repository
public class BottleDaoImpl implements BottleDao {

	@Autowired
	private SqlSession sqlSession;
	
  @Override
    public List<Bottle> selectAll() {
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
	public Bottle insertOne(Bottle newBottle) {
		// TODO Auto-generated method stub
		return null;
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
