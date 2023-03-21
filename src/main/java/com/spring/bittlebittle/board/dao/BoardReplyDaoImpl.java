package com.spring.bittlebittle.board.dao;

import com.spring.bittlebittle.board.vo.BoardReply;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardReplyDaoImpl implements BoardReplyDao{
    @Autowired
    private SqlSession sqlSession;

    public List<BoardReply> getReplyList(int boardNo) {
        return sqlSession.selectList("ReplyMapper.getReplyList", boardNo);
    }

    public BoardReply getReply(int replyNo) {
        return sqlSession.selectOne("boardReplyMapper.getReply", replyNo);
    }

    public void addReply(BoardReply reply) {
        sqlSession.insert("boardReplyMapper.addReply", reply);
    }

    public void updateReply(BoardReply reply) {
        sqlSession.update("boardReplyMapper.updateReply", reply);
    }

    public void deleteReply(int replyNo) {
        sqlSession.update("boardReplyMapper.deleteReply", replyNo);
    }

}
