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

import com.spring.bittlebittle.notice.service.NoticeService;
import com.spring.bittlebittle.notice.vo.Notice;

@RestController
@RequestMapping("api/notices")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Notice> noticeList() {
		return noticeService.getNoticeList();
	}

	@GetMapping(value = "/{noticeNo}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Notice> noticeDetail(@PathVariable int noticeNo) {
		Notice notice = noticeService.getNotice(noticeNo);
		if (notice != null) {
			return new ResponseEntity<>(notice, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}