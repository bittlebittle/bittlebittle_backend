package com.spring.bittlebittle.notice.service;

import java.util.List;

import com.spring.bittlebittle.notice.vo.Board;

// Service: 비즈니스 로직을 처리하는 객체입니다. 
// Service는 데이터 액세스 작업 이외의 로직, 
// 예를 들어 비즈니스 로직, 트랜잭션 관리, 보안 등의 작업을 처리합니다. 
// Service는 DAO와 컨트롤러 사이에서 중간 계층으로 위치하여, 
// 컨트롤러로부터 요청을 받아 DAO를 통해 데이터를 읽고 쓰는 등의 작업을 수행합니다.

public interface BoardService {
    List<Board> getBoardList();
    Board getBoard(int noticeNo);
    void addBoard(Board board);
    void updateBoard(Board board);
    void deleteBoard(int noticeNo, int userNo);

}
