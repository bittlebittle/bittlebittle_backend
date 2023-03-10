package com.spring.bittlebittle.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.review.vo.Review;

public interface ReplyDao {

	List<Reply> selectList(int reviewNo);

	int insertOne(Reply reply);

	int updateOne(Reply reply);

	int deleteOne(Reply reply);

}
