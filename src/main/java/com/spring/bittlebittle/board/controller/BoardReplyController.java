package com.spring.bittlebittle.board.controller;

import com.spring.bittlebittle.board.service.BoardReplyService;
import com.spring.bittlebittle.board.vo.BoardReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards/:{boardNo}/replies")
public class BoardReplyController {
    @Autowired
    private BoardReplyService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BoardReply> replyList(@PathVariable int boardNo) {
        return service.getReplyList(boardNo);
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