package com.spring.bittlebittle.review.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.bittlebittle.review.vo.Review;

public interface ReviewDao {

	List<Review> selectList(int bottleNo);

	Review selectOne(int reviewNo);

	int insertOne(Review review);

	int updateOne(Review review);

	int deleteOne(int reviewNo);


}
