package com.spring.bittlebittle.tag.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.tag.service.TagService;
import com.spring.bittlebittle.tag.vo.TagType;

@RestController
@RequestMapping(value="/api/admin/tagtypes", produces="application/json; charset=UTF-8")
public class AdminTagTypeController {

	@Autowired
	private TagService tservice;
	
	Logger log = LogManager.getLogger("case3");
	
	// 조회 (확인완료)
	@GetMapping
	public List<TagType> getTagTypes(){
		
		List<TagType> tagTypeList = tservice.getAllTagTypes();
		
		return tagTypeList;
	}
	
	// 작성 (확인완료)
	@PostMapping
	public List<TagType> addTagType(@RequestBody String tagTypeName){
												// 재료 이렇게만 써야됨	
		
		log.debug(tagTypeName);
		
		List<TagType> tagTypeList = tservice.addTagType(tagTypeName);
		
		return tagTypeList;
	}
	
	// 수정 (확인완료)
	@PostMapping(value="/set-data")
	public List<TagType> editTagType(@RequestBody TagType tagType){
										//tagTypeNo, tagTypeName
		
		List<TagType> tagTypeList = tservice.editTagType(tagType);
		
		return tagTypeList;
	}
	
	// 삭제 (확인완료)
	@GetMapping(value="/{tagTypeNo}/deletion")
	public List<TagType> removeTagType(@PathVariable int tagTypeNo){
		
		List<TagType> tagTypeList = tservice.removeTagType(tagTypeNo);
		
		return tagTypeList;
		
	}
	
}
