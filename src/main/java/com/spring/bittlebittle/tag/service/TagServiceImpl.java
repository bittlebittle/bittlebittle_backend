package com.spring.bittlebittle.tag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.tag.dao.TagDao;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	private TagDao dao;
	
	@Override
	public List<TagType> getAllTagType() {
		
		return dao.selectAllTagType();
	}
	
	@Override
	public List<Tag> getAllTag() {
		
		return dao.selectAllTag();
	}
	
	@Override
	public List<Tag> getTag(int bottleNo) {
		
		return dao.selectTag(bottleNo);
	}
	
	@Override
	@Transactional
	public void updateTag(int i, List<Tag> tagList) {
		
		dao.deleteBottleTag(i);
		dao.insertBottleTag(tagList);
		
	}
}
