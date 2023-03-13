package com.spring.bittlebittle.notice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.notice.vo.Board;

// BoardDaoImpl은 실제로 DB에 접근하여 CRUD 작업을 수행함
// BoardDao에서 선언된 메서드들을 실제로 구현하는 클래스
@Repository
public class BoardDaoImpl implements BoardDao{

	
	@Autowired
    private SqlSession sqlSession;

    public List<Board> getBoardList() {
        return sqlSession.selectList("BoardMapper.getBoardList");
    }

    public Board getBoard(int noticeNo) {
        return sqlSession.selectOne("BoardMapper.getBoard", noticeNo);
    }

    public void addBoard(Board board) {
        sqlSession.insert("BoardMapper.addBoard", board);
    }

    public void updateBoard(Board board) {
        sqlSession.update("BoardMapper.updateBoard", board);
    }

    public void deleteBoard(int noticeNo) {
        sqlSession.delete("BoardMapper.deleteBoard", noticeNo);
    }

    public boolean isAuthor(int noticeNo, int userNo) {
        Map<String, Integer> params = new HashMap<>();
        params.put("noticeNo", noticeNo);
        params.put("userNo", userNo);
        Integer count = sqlSession.selectOne("BoardMapper.isAuthor", params);
        return count != null && count > 0;
    }
}
