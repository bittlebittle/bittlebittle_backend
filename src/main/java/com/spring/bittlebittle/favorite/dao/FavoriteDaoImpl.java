package com.spring.bittlebittle.favorite.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.favorite.vo.Favorite;

@Repository
public class FavoriteDaoImpl implements FavoriteDao{

	@Override
	public int insertOne(SqlSession session, Favorite favorite) {
		
		return session.insert("favoriteMapper", favorite);
	}
	
	@Override
	public int selectCnt(SqlSession session, int bottleNo) {
		
		return session.selectOne("favoriteMapper.selectCnt", bottleNo);
	}
	
	@Override
	public int selectOne(SqlSession session, Favorite favorite) {
		
		return session.selectOne("favoriteMapper.selectOne", favorite);
	}
	
	
	@Override
	public int deleteOne(SqlSession session, Favorite favorite) {
		
		return session.delete("favoriteMapper.deleteOne", favorite);
	}
}
