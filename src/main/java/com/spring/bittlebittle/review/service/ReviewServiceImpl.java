package com.spring.bittlebittle.review.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.review.dao.ReviewDao;
import com.spring.bittlebittle.review.vo.Review;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDao dao;
	
	
	@Override
	public List<Review> getReviews(int bottleNo) {
		
		return dao.selectList(bottleNo);
	}
	
	@Override
	@Transactional
	public List<Review> addReview(Review review) {
		
		dao.insertOne(review);
	
		
		List<Review> list = dao.selectList(review.getBottleNo());
		
		return list;
	}
	
	@Override
	public Review getReview(int reviewNo) {
		
		return dao.selectOne(reviewNo);
	}
	
	@Override
	@Transactional
	public List<Review> editReview(Review review) {
		
		dao.updateOne(review);
		
		List<Review> list = dao.selectList(review.getBottleNo());
		
		return list;
	}
	
	@Override
	@Transactional
	public List<Review> removeReview(Review review) {

		dao.deleteOne(review);
		List<Review> list = dao.selectList(review.getBottleNo());
		
		return list;
	}
}
