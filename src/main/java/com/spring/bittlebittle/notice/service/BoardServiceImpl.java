package com.spring.bittlebittle.notice.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.notice.dao.BoardDaoImpl;
import com.spring.bittlebittle.notice.vo.Board;

// 해당 클래스가 비즈니스 로직을 처리하는 클래스임을 표시
// Controller와 Dao 사이에서 중개자 역할
// @Autowired 어노테이션을 사용해 해당 클래스의 인스턴스를 주입 받아 사용 가능
@Service

// 메서드 또는 클래스에 적용하여 트랜잭션 관리를 수행하는 데 사용
// 트랜잭션이란 여러 개의 데이터베이스 조작 쿼리를 논리적인 작업 단위로 묶어서 일괄 처리하는 것을 의미
// @Transactional 어노테이션을 적용하면, 트랜잭션을 시작하고 종료하는 것은 개발자가 직접 처리할 필요가 없으며, 스프링이 자동으로 처리해줌
// 또한, 트랜잭션 처리 중 예외가 발생하면 롤백 (Rollback) 처리되어 이전 상태로 복구
@Transactional

public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDaoImpl dao;

	@Override
	public List<Board> getBoardList() {
		return dao.getBoardList();
	}

	@Override
	public Board getBoard(int boardNo) {
		return dao.getBoard(boardNo);
	}

	@Override
	public void addBoard(Board board) {
		dao.addBoard(board);
	}

	@Override
	public void updateBoard(Board board) {
		int boardNo = notice.getNoticeNo();
		int userNo = notice.getUserNo();
		if (dao.isAuthor(noticeNo, userNo)) {
			dao.updateBoard(board);

		}
	}

	@Override
	public void deleteBoard(int noticeNo, int userNo) {
		if (dao.isAuthor(noticeNo, userNo)) {
			dao.deleteBoard(noticeNo);
		}
	}
}
