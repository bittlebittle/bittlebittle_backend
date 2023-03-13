package com.spring.bittlebittle.favorite.dao;

import com.spring.bittlebittle.favorite.vo.Favorite;

import java.util.List;

public interface FavoriteDao {

	int insertOne(Favorite favorite);

	int selectCnt(int bottleNo);

	List<Favorite> selectOne(Favorite favorite);

	int deleteOne(Favorite favorite);

}
