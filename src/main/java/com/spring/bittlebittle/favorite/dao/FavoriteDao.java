package com.spring.bittlebittle.favorite.dao;

import org.apache.ibatis.session.SqlSession;

import com.spring.bittlebittle.favorite.vo.Favorite;

public interface FavoriteDao {

	int insertOne(Favorite favorite);

	int selectCnt(int bottleNo);

	int selectOne(Favorite favorite);

	int deleteOne(Favorite favorite);

}
