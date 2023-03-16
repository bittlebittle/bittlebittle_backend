package com.spring.bittlebittle.board.dao;

import com.spring.bittlebittle.board.vo.BoardReply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardReplyDao {

    List<BoardReply> getReplyList(int replyNo) ; // 조회
//    BoardReply getReply(int replyNo);
    void addReply(BoardReply reply, int userNo, String nickname); //생성 => 이때 nickname, userNo 같이 넣어주기
    void updateReply(BoardReply reply); //수정
    void deleteReply(int replyNo); //삭제
    boolean isAuthor(int replyNo, int userNo); // 작성자여부
    

}
