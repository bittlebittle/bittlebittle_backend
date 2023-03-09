package com.spring.bittlebittle.review.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bittlebittle.review.dao.ReviewDao;
import com.spring.bittlebittle.review.vo.Review;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDao dao;
	@Autowired
	private SqlSession session;
	
	@Override
	public List<Review> getReviewList(int bottleNo) {
		
		return dao.selectList(session, bottleNo);
	}
	
	@Override
	public List<Review> addReview(Review review) {
		
		int count=0;
		count = dao.insertOne(session, review);
		List<Review> list = dao.selectList(session, review.getBottleNo());
		
		return list;
	}
	
	@Override
	public List<Review> updateReview(Review review) {
		
		int count=0;
		count = dao.updateOne(session,review);
		List<Review> list = dao.selectList(session, review.getBottleNo());
		
		return list;
	}
	
	@Override
	public List<Review> deleteReview(Review review) {

		int count=0;
		count=dao.deleteOne(session,review);
		List<Review> list = dao.selectList(session, review.getBottleNo());
		
		return null;
	}
}
