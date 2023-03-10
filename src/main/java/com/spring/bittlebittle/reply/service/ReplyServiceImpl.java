package com.spring.bittlebittle.reply.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.reply.dao.ReplyDao;
import com.spring.bittlebittle.reply.vo.Reply;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyDao dao;
	
	@Override
	public List<Reply> getReplyList(int reviewNo) {
		
		return dao.selectList(reviewNo);
	}
	
	@Override
	@Transactional
	public List<Reply> addReply(Reply reply) {
		
		dao.insertOne(reply);
		
		List<Reply> list=dao.selectList(reply.getReviewNo());
		
		return list;
	}
	
	@Override
	@Transactional
	public List<Reply> updateReply(Reply reply) {
		
		dao.updateOne(reply);
		
		List<Reply> list=dao.selectList(reply.getReviewNo());
		
		return list;
	}
	
	@Override
	@Transactional
	public List<Reply> deleteReply(Reply reply) {
		
		dao.deleteOne(reply);
		
		List<Reply> list = dao.selectList(reply.getReviewNo());
		
		return list;
	}
}
