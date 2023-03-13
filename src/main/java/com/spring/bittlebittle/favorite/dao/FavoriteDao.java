package com.spring.bittlebittle.favorite.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.bittlebittle.favorite.vo.Favorite;

public interface FavoriteDao {

	int insertOne(Favorite favorite);

	List<Favorite> selectOne(Favorite favorite);

	int deleteOne(Favorite favorite);

}
