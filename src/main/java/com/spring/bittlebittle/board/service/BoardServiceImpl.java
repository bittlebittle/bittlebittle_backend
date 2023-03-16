package com.spring.bittlebittle.board.service;

import java.util.List;

import com.spring.bittlebittle.board.dao.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.board.dao.BoardDaoImpl;
import com.spring.bittlebittle.board.vo.Board;


@Service
@Transactional
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardDao dao;

    @Override
    public List<Board> getBoardList() {
        return dao.getBoardList();
    }

    @Override
    public Board getBoard(int boardNo) {
        return dao.getBoard(boardNo);
    }

    @Override
    public int addBoard(Board board) {
        return dao.addBoard(board);
    }

    @Override
    public void updateBoard(Board board) {
        int boardNo = board.getBoardNo();
        int userNo = board.getUserNo();
        if (dao.isAuthor(boardNo, userNo)) {
            dao.updateBoard(board);

        }
    }

    @Override
    public void deleteBoard(int boardNo, int userNo) {
        if (dao.isAuthor(boardNo, userNo)) {
            dao.deleteBoard(boardNo);
        }
    }
}
