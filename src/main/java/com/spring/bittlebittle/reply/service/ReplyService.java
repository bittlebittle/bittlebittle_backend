package com.spring.bittlebittle.reply.service;

import java.util.List;

import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.review.vo.Review;

public interface ReplyService {

	List<Reply> getReplyList(int reviewNo);

	List<Reply> addReply(Reply reply);

	List<Reply> updateReply(Reply reply);

	List<Reply> deleteReply(Reply reply);

}
