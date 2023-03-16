package com.spring.bittlebittle.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.board.dao.BoardDao;
import com.spring.bittlebittle.board.vo.Board;
import com.spring.bittlebittle.NotAuthorizedException;

import com.spring.bittlebittle.board.vo.Board;

import java.util.List;

public interface BoardService {

    List<Board> getBoardList();
    Board getBoard(int boardNo);
    void addBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(int boardNo, int userNo);

}
