package com.spring.bittlebittle.board.controller;

import java.util.List;
import java.util.NoSuchElementException;

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

import com.spring.bittlebittle.board.service.BoardService;
import com.spring.bittlebittle.board.vo.Board;

import com.spring.bittlebittle.NotAuthorizedException;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Board> boardList() {
        return boardService.getBoardList();
    }

    @GetMapping(value = "/{boardNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> boardDetail(@PathVariable int boardNo) {
        Board board = boardService.getBoard(boardNo);
        if (board != null) {
            return new ResponseEntity<Board>(board, HttpStatus.OK);
        } else {
            return new ResponseEntity<Board>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> addBoard(@RequestBody Board board) {
        boardService.addBoard(board);
        return new ResponseEntity<Board>(board, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{boardNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Board> updateBoard(@PathVariable int boardNo, @RequestBody Board board) {
        board.setBoardNo(boardNo);
        try {
            boardService.updateBoard(board);
            return new ResponseEntity<Board>(board, HttpStatus.OK);
        } catch (NotAuthorizedException e) {
            return new ResponseEntity<Board>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(value = "/{boardNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteBoard(@PathVariable int boardNo, @RequestParam int userNo) {
        try {
            boardService.deleteBoard(boardNo, userNo);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (NotAuthorizedException e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        }
    }
}