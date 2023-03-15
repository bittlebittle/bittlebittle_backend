package com.spring.bittlebittle.tag.controller;

import com.spring.bittlebittle.tag.service.TagService;
import com.spring.bittlebittle.tag.vo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	// 작성
	@PostMapping
	public List<Tag> addTagType(Tag tag){
		
		List<Tag> tagList = tservice.addTag(tag);
		
		return tagList;
	}
	
	// 수정
	@PostMapping(value="/set-data")
	public List<Tag> editTag(Tag tag){
		
		List<Tag> tagList = tservice.editTag(tag);
		
		return tagList;
	}
	
	// 삭제
	@GetMapping(value="/{tagNo}/deletion")
	public List<Tag> removeTagType(@PathVariable int tagNo){
		
		List<Tag> tagList = tservice.removeTag(tagNo);
		
		return tagList;
		
	}
	
}
