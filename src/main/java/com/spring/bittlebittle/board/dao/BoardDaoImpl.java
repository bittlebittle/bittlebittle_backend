package com.spring.bittlebittle.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.board.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao{

	@Autowired
    private SqlSession sqlSession;

    private Logger log = LogManager.getLogger("case3");


    public List<Board> getBoardList() {
        log.debug("Dada");
        return sqlSession.selectList("boardMapper.getBoards");
    }

    public Board getBoard(int boardNo) {
        return sqlSession.selectOne("boardMapper.getBoard", boardNo);
    }

    public int addBoard(Board board) {
        return sqlSession.insert("boardMapper.addBoard", board);
    }

    public void updateBoard(Board board) {
        sqlSession.update("boardMapper.updateBoard", board);
    }

    public void deleteBoard(int boardNo) {
        sqlSession.update("boardMapper.deleteBoard", boardNo);
    }

    public boolean isAuthor(int boardNo, int userNo) {
        Map<String, Integer> params = new HashMap<>();
        params.put("boardNo", boardNo);
        params.put("userNo", userNo);
        Integer count = sqlSession.selectOne("BoardMapper.isAuthor", params);
        return count != null && count > 0;
    }
}
