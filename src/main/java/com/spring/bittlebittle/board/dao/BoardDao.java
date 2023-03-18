package com.spring.bittlebittle.board.dao;

import com.spring.bittlebittle.board.vo.Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BoardDao {


    List<Board> getBoardList();
    Board getBoard(int boardNo);
    int addBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(int boardNo);
    boolean isAuthor(int boardNo, int userNo);
}
