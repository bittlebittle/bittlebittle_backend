package com.spring.bittlebittle.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.NotAuthorizedException;
import com.spring.bittlebittle.board.dao.ReplyDao;
import com.spring.bittlebittle.board.vo.Reply;

@Service
@Transactional
public class ReplyService {
    @Autowired
    private ReplyDao replyDao;

    public List<Reply> getReplyList(int reviewNo) {
        return replyDao.getReplyList(reviewNo);
    }

    public void addReply(Reply reply) {
        replyDao.addReply(reply);
    }

    public void updateReply(Reply reply) throws NotAuthorizedException {
        int replyNo = reply.getReplyNo();
        int userNo = reply.getUserNo();
        if (replyDao.isAuthor(replyNo, userNo)) {
            replyDao.updateReply(reply);
        } else {
            throw new NotAuthorizedException();
        }
    }

    public void deleteReply(int replyNo, int userNo) throws NotAuthorizedException {
        if (replyDao.isAuthor(replyNo, userNo)) {
            replyDao.deleteReply(replyNo);
        } else {
            throw new NotAuthorizedException();
        }
    }
}