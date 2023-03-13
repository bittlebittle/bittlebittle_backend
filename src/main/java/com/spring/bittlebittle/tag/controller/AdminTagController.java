package com.spring.bittlebittle.tag.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.tag.service.TagService;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;

@RestController
@RequestMapping(value="/api/admin/tags", produces="application/json; charset=UTF-8")
public class AdminTagController {

	@Autowired
	private TagService tservice;
	
	// 태그 창 들어갔을 때
	@GetMapping
	public Map<String, Object> getTag(){
		
		List<TagType> tagTypeList = tservice.getAllTagTypes();
		List<Tag> tagList = tservice.getAllTags();
		
		Map<String, Object> map = new HashMap<>();
		map.put("tagTypeList", tagTypeList);
		map.put("tagList", tagList);
		
		return map;
	}
}
