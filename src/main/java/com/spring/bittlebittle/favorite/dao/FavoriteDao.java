package com.spring.bittlebittle.favorite.dao;

import org.apache.ibatis.session.SqlSession;

import com.spring.bittlebittle.favorite.vo.Favorite;

public interface FavoriteDao {

	int insertOne(SqlSession session, Favorite favorite);

	int selectCnt(SqlSession session, int bottleNo);

	int selectOne(SqlSession session, Favorite favorite);

	int deleteOne(SqlSession session, Favorite favorite);

}
