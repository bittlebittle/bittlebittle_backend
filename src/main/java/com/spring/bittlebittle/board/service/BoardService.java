package com.spring.bittlebittle.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.board.dao.BoardDao;
import com.spring.bittlebittle.board.vo.Board;
import com.spring.bittlebittle.NotAuthorizedException;


@Service
@Transactional
public class BoardService {
    @Autowired
    private BoardDao boardDao;

    public List<Board> getBoardList() {
        return boardDao.getBoardList();
    }

    public Board getBoard(int boardNo) {
        return boardDao.getBoard(boardNo);
    }

    public void addBoard(Board board) {
        boardDao.addBoard(board);
    }

    public void updateBoard(Board board) throws NotAuthorizedException {
        int boardNo = board.getBoardNo();
        int userNo = board.getUserNo();
        if (boardDao.isAuthor(boardNo, userNo)) {
            boardDao.updateBoard(board);
        } else {
            throw new NotAuthorizedException();
        }
    }

    public void deleteBoard(int boardNo, int userNo) throws NotAuthorizedException {
        if (boardDao.isAuthor(boardNo, userNo)) {
            boardDao.deleteBoard(boardNo);
        } else {
            throw new NotAuthorizedException();
        }
    }
}
