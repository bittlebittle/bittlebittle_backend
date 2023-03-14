package com.spring.bittlebittle.tag.controller;

import com.spring.bittlebittle.tag.service.TagService;
import com.spring.bittlebittle.tag.vo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/admin/tags", produces="application/json; charset=UTF-8")
public class AdminTagController {

	@Autowired
	private TagService tservice;
	
	// 태그 창 들어갔을 때
	@GetMapping
	public List<Tag>  getTag(){

		List<Tag> tagList = tservice.getAllTags();
		
		return tagList;
	}
}
