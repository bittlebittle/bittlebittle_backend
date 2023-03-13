package com.spring.bittlebittle.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.board.service.BoardReplyService;
import com.spring.bittlebittle.board.vo.BoardReply;

@RestController
@RequestMapping("/reviews/{reviewNo}")
public class BoardReplyController {
    @Autowired
    private BoardReplyService service;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BoardReply> replyList(@PathVariable int reviewNo) {
        return service.getReplyList(reviewNo);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addReply(@PathVariable int reviewNo, @RequestBody BoardReply reply) {
        reply.setReviewNo(reviewNo);
        service.addReply(reply);
    }

    @PutMapping(value = "/{replyNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateReply(@PathVariable int reviewNo, @PathVariable int replyNo, @RequestBody BoardReply boardReply) {
        boardReply.setReviewNo(reviewNo);
        boardReply.setReplyNo(replyNo);
        service.updateReply(boardReply);
    }

    @DeleteMapping(value = "/{replyNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteReply(@PathVariable int reviewNo, @PathVariable int replyNo, @RequestParam int userNo) {
        service.deleteReply(replyNo, userNo);

    }
}