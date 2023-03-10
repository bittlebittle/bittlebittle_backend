package com.spring.bittlebittle.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.bittlebittle.reply.vo.Reply;

public class ReplyDaoImpl implements ReplyDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Reply> selectList(int reviewNo) {
		
		return sqlSession.selectList("replyMapper.selectList", reviewNo);
	}
	
	@Override
	public int insertOne(Reply reply) {
		
		return sqlSession.insert("replyMapper.insertOne", reply);
	}
	
	@Override
	public int updateOne(Reply reply) {
		
		return sqlSession.update("replyMapper.updateOne", reply);
	}
	
	@Override
	public int deleteOne(Reply reply) {
		
		return sqlSession.delete("replyMapper.deleteOne", reply);
	}
}