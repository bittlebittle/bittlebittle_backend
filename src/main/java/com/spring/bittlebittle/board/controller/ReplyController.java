package com.spring.bittlebittle.board.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import com.spring.bittlebittle.NotAuthorizedException;
import com.spring.bittlebittle.board.service.ReplyService;
import com.spring.bittlebittle.board.vo.Reply;

@RestController
@RequestMapping("/reviews/{reviewNo}/replies")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reply> replyList(@PathVariable int reviewNo) {
        return replyService.getReplyList(reviewNo);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addReply(@PathVariable int reviewNo, @RequestBody Reply reply) {
        reply.setReviewNo(reviewNo);
        replyService.addReply(reply);
    }

    @PutMapping(value = "/{replyNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateReply(@PathVariable int reviewNo, @PathVariable int replyNo, @RequestBody Reply reply) {
        reply.setReviewNo(reviewNo);
        reply.setReplyNo(replyNo);
        replyService.updateReply(reply);
    }

    @DeleteMapping(value = "/{replyNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteReply(@PathVariable int reviewNo, @PathVariable int replyNo, @RequestParam int userNo) {
        try {
            replyService.deleteReply(replyNo, userNo);
        } catch (NotAuthorizedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not authorized to perform this operation.", e);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reply not found.", e);
        }
    }
}