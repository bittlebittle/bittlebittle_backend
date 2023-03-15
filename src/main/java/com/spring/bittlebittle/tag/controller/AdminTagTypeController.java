package com.spring.bittlebittle.tag.controller;

import com.spring.bittlebittle.tag.service.TagService;
import com.spring.bittlebittle.tag.vo.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/admin/tagtypes", produces="application/json; charset=UTF-8")
public class AdminTagTypeController {

	@Autowired
	private TagService tservice;
	
	// 조회
	@GetMapping
	public List<TagType> getTagTypes(){
		
		List<TagType> tagTypeList = tservice.getAllTagTypes();
		
		return tagTypeList;
	}
	
	// 작성
	@PostMapping
	public List<TagType> addTagType(String tagTypeName){
		
		List<TagType> tagTypeList = tservice.addTagType(tagTypeName);
		
		return tagTypeList;
	}
	
	// 수정
	@PostMapping(value="/set-data")
	public List<TagType> editTagType(TagType tagType){
		
		List<TagType> tagTypeList = tservice.editTagType(tagType);
		
		return tagTypeList;
	}
	
	// 삭제
	@GetMapping(value="/{tagTypeNo}/deletion")
	public List<TagType> removeTagType(@PathVariable int tagTypeNo){
		
		List<TagType> tagTypeList = tservice.removeTagType(tagTypeNo);
		
		return tagTypeList;
		
	}
	
}
