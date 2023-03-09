package com.spring.bittlebittle.favorite.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bittlebittle.favorite.dao.FavoriteDao;
import com.spring.bittlebittle.favorite.vo.Favorite;

@Service
public class FavoriteServiceImpl implements FavoriteService{

	 
	@Autowired
	private FavoriteDao dao;
	@Autowired
	private SqlSession session;
	
	@Override
	public int addFavorite(Favorite favorite) {
		
		int count = 0;
		count = dao.insertOne(session, favorite);
		
		int favoriteCnt = dao.selectCnt(session, favorite.getBottleNo());
		
		return favoriteCnt;
	}
	
	@Override
	public int checkFavorite(Favorite favorite) {
		
		return dao.selectOne(session,favorite);
	}
	
	@Override
	public int deleteFavorite(Favorite favorite) {
		
		return dao.deleteOne(session,favorite);
	}
}
