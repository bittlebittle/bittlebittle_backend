package com.spring.bittlebittle.board.service;

import com.spring.bittlebittle.board.dao.BoardReplyDaoImpl;
import com.spring.bittlebittle.board.vo.BoardReply;
import com.spring.bittlebittle.user.dao.UserDao;
import com.spring.bittlebittle.user.vo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardReplyServiceImpl implements BoardReplyService{

    @Autowired
    private BoardReplyDaoImpl dao;
    
    @Autowired
    private UserDao userDao;

    @Override
    public List<BoardReply> getReplyList(int replyNo) {
        return dao.getReplyList(replyNo);
    }

    @Override
    public void addReply(BoardReply reply, int userNo, String nickname) {
        User user = userDao.selectUsers().get(0);
        reply.setUserNo(user.getUserNo());
        dao.addReply(reply, userNo, nickname);
       
    }

    @Override
    public void updateReply(BoardReply reply) {
    	dao.updateReply(reply);
    }

    @Override
    public void deleteReply(int replyNo) {
    	dao.deleteReply(replyNo);
    }
    
    @Override
    public boolean isAuthor(int replyNo, int userNo) {
        // 특정 댓글이 작성자인지 확인하는 로직
        BoardReply reply = new BoardReply();
        		
        if (reply != null && reply.getUserNo() == userNo) {
            return true;
        } else {
            return false;
        }
    }

//    @Override
//    public boolean isAuthor(int replyNo, int userNo) {
//        // 특정 댓글이 작성자인지 확인하는 로직
//        BoardReply reply = dao.getReplyList();
//        if (reply != null && reply.getUserNo() == userNo) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    


//    public List<BoardReply> getReplyList(int replyNo) {
//        return dao.getReplyList(replyNo);
//    }
//
//    public void addReply(BoardReply reply, int userNo, String nickname) {
//        dao.addReply(reply, userNo, nickname);
//        
//    }
//
//    public void updateReply(BoardReply reply) {
//        int replyNo = reply.getReplyNo();
//        		
//        int userNo = reply.getUserNo();
//        	
//        if (dao.isAuthor(replyNo, userNo)) {
//            dao.updateReply(reply);
//        }
//    }
//
//    public void deleteReply(int replyNo, int userNo){
//            if (dao.isAuthor(replyNo, userNo)) {
//                dao.deleteReply(replyNo);
//            }
//        }
//
//	@Override
//	public void deleteReply(int replyNo) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public boolean isAuthor(int replyNo, int userNo) {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
