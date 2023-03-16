package com.spring.bittlebittle.board.service;

import com.spring.bittlebittle.board.vo.BoardReply;

import java.util.List;

public interface BoardReplyService {


	 List<BoardReply> getReplyList(int replyNo);

	    void addReply(BoardReply reply, int userNo, String nickname);

	    void updateReply(BoardReply reply);

	    void deleteReply(int replyNo);

	    boolean isAuthor(int replyNo, int userNo);

}
