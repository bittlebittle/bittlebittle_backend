package com.spring.bittlebittle.board.controller;

import com.spring.bittlebittle.board.service.BoardReplyService;
import com.spring.bittlebittle.board.vo.BoardReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards/{boardNo}/replies")
public class BoardReplyController {
    @Autowired
    private BoardReplyService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BoardReply> replyList(@PathVariable int boardNo) {
        return service.getReplyList(boardNo);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void addReply(@PathVariable int boardNo, @RequestBody BoardReply boardReply, HttpEntity entity) {
        boardReply.setBoardNo(boardNo);
        service.addReply(boardReply);
    }

    @PostMapping(value = "/{replyNo}/set-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateReply(@PathVariable int replyNo, @RequestBody BoardReply boardReply, HttpEntity entity) {
        boardReply.setReplyNo(replyNo);
        service.updateReply(boardReply);
    }

    @GetMapping(value = "/{replyNo}/deletion", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteReply(@PathVariable int replyNo, HttpEntity entity) {
        service.deleteReply(replyNo);

    }
}

