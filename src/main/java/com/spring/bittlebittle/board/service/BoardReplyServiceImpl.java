package com.spring.bittlebittle.board.service;

import com.spring.bittlebittle.board.dao.BoardReplyDaoImpl;
import com.spring.bittlebittle.board.vo.BoardReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardReplyServiceImpl implements BoardReplyService{

    @Autowired
    private BoardReplyDaoImpl dao;

    public List<BoardReply> getReplyList(int reviewNo) {
        return dao.getReplyList(reviewNo);
    }

    public void addReply(BoardReply boardReply) {
        dao.addReply(boardReply);
    }

    public void updateReply(BoardReply boardReply) {
        int replyNo = boardReply.getReplyNo();
        int userNo = boardReply.getUserNo();
        if (dao.isAuthor(replyNo, userNo)) {
            dao.updateReply(boardReply);
        }
    }

    public void deleteReply(int replyNo, int userNo){
            if (dao.isAuthor(replyNo, userNo)) {
                dao.deleteReply(replyNo);
            }
        }

}
