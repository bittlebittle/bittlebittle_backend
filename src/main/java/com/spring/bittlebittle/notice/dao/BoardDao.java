package com.spring.bittlebittle.notice.dao;

import java.util.List;

import com.spring.bittlebittle.notice.vo.Board;

// DAO(Data Access Object)
// 데이터베이스와 관련된 작업을 처리하는 객체. 
// DAO는 데이터베이스의 데이터를 읽고 쓰는 등의 데이터 액세스 작업을 처리
// DAO는 데이터베이스와의 연결을 수행하고, 데이터베이스와의 통신을 담당하는 역할

public interface BoardDao {

    List<Board> getBoardList();
    Board getBoard(int noticeNo);
    void addBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(int noticeNo);
    boolean isAuthor(int noticeNo, int userNo);
}
