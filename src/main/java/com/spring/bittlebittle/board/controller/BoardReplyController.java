package com.spring.bittlebittle.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.bittlebittle.board.service.BoardReplyService;
import com.spring.bittlebittle.board.vo.BoardReply;
import com.spring.bittlebittle.user.vo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reply")
public class BoardReplyController {
    @Autowired
    private BoardReplyService service;

    // 댓글 목록 조회
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BoardReply>> getReplyList(@RequestParam int boardNo) {
    	System.out.println(boardNo);
        List<BoardReply> replyList = service.getReplyList(boardNo);
        System.out.println(replyList.get(0));
        return ResponseEntity.ok(replyList);
    }

//    // 댓글 작성
//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Map<String, Object>> addReply(@RequestBody BoardReply reply,
//                                                         @RequestParam int boardNo,
//                                                         @AuthenticationPrincipal User user) {
//        Map<String, Object> resultMap = new HashMap<>();
//        String nickname = user.getNickname();
//        int userNo = user.getUserNo();
//        service.addReply(reply, userNo, nickname);
//        resultMap.put("status", "success");
//        return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
//    }

    
 // 댓글 작성
    @PostMapping(value = "/api/boards/{boardNo}/addReply", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> addReply(@RequestBody BoardReply reply,
                                                         @PathVariable int boardNo,
                                                         @AuthenticationPrincipal User user) {
        Map<String, Object> resultMap = new HashMap<>();
        String nickname = user.getNickname();
        int userNo = user.getUserNo();
        service.addReply(reply, userNo, nickname);
        resultMap.put("status", "success");
        resultMap.put("nickname", nickname);
        resultMap.put("createDate", reply.getCreatDate());
        		
        return ResponseEntity.status(HttpStatus.CREATED).body(resultMap);
    }
    
    
    // 댓글 수정
    @PostMapping(value = "/{replyNo}/set-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> updateReply(@PathVariable int replyNo,
                                                            @RequestBody BoardReply reply,
                                                            @AuthenticationPrincipal User user) {
        Map<String, Object> resultMap = new HashMap<>();
        if (service.isAuthor(replyNo, user.getUserNo())) {
            service.updateReply(reply);
            resultMap.put("status", "success");
            return ResponseEntity.ok(resultMap);
        } else {
            resultMap.put("status", "fail");
            resultMap.put("message", "본인이 작성한 댓글만 수정할 수 있습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultMap);
        }
    }

    // 댓글 삭제
    @GetMapping(value = "/{replyNo}/delestion", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> deleteReply(@PathVariable int replyNo,
                                                            @AuthenticationPrincipal User user) {
        Map<String, Object> resultMap = new HashMap<>();
        if (service.isAuthor(replyNo, user.getUserNo())) {
            service.deleteReply(replyNo);
            resultMap.put("status", "success");
            return ResponseEntity.ok(resultMap);
        } else {
            resultMap.put("status", "fail");
            resultMap.put("message", "본인이 작성한 댓글만 삭제할 수 있습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resultMap);
        }
    }
}
    
    
    
