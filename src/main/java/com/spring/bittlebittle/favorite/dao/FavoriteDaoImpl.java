package com.spring.bittlebittle.favorite.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.favorite.vo.Favorite;

@Repository
public class FavoriteDaoImpl implements FavoriteDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertOne(Favorite favorite) {
		
		return sqlSession.insert("favoriteMapper", favorite);
	}
	
	@Override
	public int selectCnt(int bottleNo) {
		
		return sqlSession.selectOne("favoriteMapper.selectCnt", bottleNo);
	}
	
	@Override
	public int selectOne(Favorite favorite) {
		
		return sqlSession.selectOne("favoriteMapper.selectOne", favorite);
	}
	
	
	@Override
	public int deleteOne(Favorite favorite) {
		
		return sqlSession.delete("favoriteMapper.deleteOne", favorite);
	}
}
