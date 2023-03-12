package com.spring.bittlebittle.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.board.vo.Reply;

@Repository
public class ReplyDao {
	@Autowired
	private SqlSession sqlSession;

	public List<Reply> getReplyList(int reviewNo) {
		return sqlSession.selectList("ReplyMapper.getReplyList", reviewNo);
	}

	public Reply getReply(int replyNo) {
		return sqlSession.selectOne("ReplyMapper.getReply", replyNo);
	}

	public void addReply(Reply reply) {
		sqlSession.insert("ReplyMapper.addReply", reply);
	}

	public void updateReply(Reply reply) {
		sqlSession.update("ReplyMapper.updateReply", reply);
	}

	public void deleteReply(int replyNo) {
		sqlSession.delete("ReplyMapper.deleteReply", replyNo);
	}

	public boolean isAuthor(int replyNo, int userNo) {
		Map<String, Integer> params = new HashMap<>();
		params.put("replyNo", replyNo);
		params.put("userNo", userNo);
		Integer count = sqlSession.selectOne("ReplyMapper.isAuthor", params);
		return count != null && count > 0;
	}
}