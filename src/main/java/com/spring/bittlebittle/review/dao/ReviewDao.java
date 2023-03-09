package com.spring.bittlebittle.review.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.bittlebittle.review.vo.Review;

public interface ReviewDao {

	List<Review> selectList(SqlSession session, int bottleNo);

	int insertOne(SqlSession session, Review review);

	int updateOne(SqlSession session, Review review);

	int deleteOne(SqlSession session, Review review);

}
