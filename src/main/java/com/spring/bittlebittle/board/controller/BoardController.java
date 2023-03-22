package com.spring.bittlebittle.board.controller;

import com.spring.bittlebittle.board.service.BoardService;
import com.spring.bittlebittle.board.vo.Board;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//import com.spring.bittlebittle.NotAuthorizedException;

@RestController
@RequestMapping("api/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired JwtUtil jwtUtil;

    private Logger log = LogManager.getLogger("case3");


    @GetMapping(produces = "application/json; charset=utf-8")
    public List<Board> boardList() {
        log.debug("12321312");
        List<Board> list = boardService.getBoardList();
        log.debug(list);
        return list;
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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> addBoard(@RequestBody Board board, HttpServletRequest request) {

        String token = jwtUtil.resolveAccessToken(request);
        // access token 과 refreshtokenIdx 를 가지고 조건 검사. 리턴 타입은 boolean
        if(jwtUtil.validateToken(token, UserJwt.builder()
                .userJwtIdx(jwtUtil.resolveRefreshToken(request))
                .build())){
            // 토큰이 유효하다면 유저 정보 조회

            boardService.addBoard(board);
            return new ResponseEntity<>(board, HttpStatus.CREATED);
        } else {
            return ResponseEntity.ok().body(null);
        }
    }

    @PostMapping(value = "/{boardNo}/set-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> updateBoard(@PathVariable int boardNo, @RequestBody Board board) {
        board.setBoardNo(boardNo);

        boardService.updateBoard(board);
        return new ResponseEntity<>(board, HttpStatus.OK);

    }

    @GetMapping(value = "/{boardNo}/deletion", produces = MediaType.APPLICATION_JSON_VALUE)
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