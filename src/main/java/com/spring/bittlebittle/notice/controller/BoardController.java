package com.spring.bittlebittle.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.notice.service.BoardServiceImpl;
import com.spring.bittlebittle.notice.vo.Board;

//해당 클래스가 RESTFul API를 처리하는 컨트롤러임을 선언
@RestController

//해당 클래스의 요청 URL이 /notice임을 지정
@RequestMapping("/notice")
public class BoardController {
    @Autowired
//	// Spring의 DI 기능을 활용하여 BoardServiceImpl 클래스를 주입
    private BoardServiceImpl boardService;

// HTTP GET 요청에 대한 /notice/ 경로에 대한 처리를 담당하는 메소드
// produce 속성을 사용하여 해당 method가 생성하는 응답 데이터의 'Content-Type'을 설정
// MediaType.APPLICATION_JSON_VALUE 값으로 설정하여 JSON 형태의 응답 데이터를 생성
// boardService를 통해 게시판 목록 데이터를 조회한 후, 조회된 데이터를 List<Board> 형태로 반환
// 반환된 데이터는 스프링이 자동으로 JSON 형태로 변환하여 클라이언트에 응답
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Board> boardList() {
        return boardService.getBoardList();
    }

    @GetMapping(value = "/{boardNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> boardDetail(@PathVariable int boardNo) {
        Board board = boardService.getBoard(boardNo);
        if (board != null) {
            return new ResponseEntity<>(board, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> addBoard(@RequestBody Board board) {
        boardService.addBoard(board);
        return new ResponseEntity<>(board, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{boardNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> updateBoard(@PathVariable int boardNo, @RequestBody Board board) {
        board.setNoticeNo(noitceNo);

        boardService.updateBoard(board);
        return new ResponseEntity<>(board, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{boardNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteBoard(@PathVariable int boardNo, @RequestParam int userNo) {
//        try {
            boardService.deleteBoard(boardNo, userNo);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (NotAuthorizedException e) {
//            return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
//        }
    }
}
