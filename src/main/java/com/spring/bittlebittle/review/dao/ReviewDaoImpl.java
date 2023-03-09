package com.spring.bittlebittle.review.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.review.vo.Review;

@Repository
public class ReviewDaoImpl implements ReviewDao{

	
	@Override
	public List<Review> selectList(SqlSession session, int bottleNo) {
		
		return session.selectList("reviewMapper.selectList", bottleNo);
	}
	
	@Override
	public int insertOne(SqlSession session, Review review) {
		
		return session.insert("reviewMapper.insertOne", review);
		
	}
	
	@Override
	public int updateOne(SqlSession session, Review review) {
		
		return session.insert("reviewMapper.updateOne", review);
	}
	
	@Override
	public int deleteOne(SqlSession session, Review review) {
		
		return session.delete("reviewMapper.deleteOne", review);
	}
}
