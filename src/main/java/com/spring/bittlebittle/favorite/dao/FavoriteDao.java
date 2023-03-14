package com.spring.bittlebittle.favorite.dao;


import org.apache.ibatis.session.SqlSession;

import com.spring.bittlebittle.favorite.vo.Favorite;

import java.util.List;

public interface FavoriteDao {

	int insertOne(Favorite favorite);

	List<Favorite> selectOne(Favorite favorite);

	int deleteOne(Favorite favorite);

}
