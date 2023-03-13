package com.spring.bittlebittle.tag.service;

import com.spring.bittlebittle.tag.dao.TagDao;
import com.spring.bittlebittle.tag.vo.BottleTag;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	private TagDao dao;
	
	@Override
	public List<TagType> getAllTagTypes() {
		
		return dao.selectAllTagType();
	}
	
	@Override
	public List<Tag> getAllTags() {
		
		return dao.selectAllTag();
	}
	
	@Override
	public List<Tag> getTags(int bottleNo) {
		
		return dao.selectTag(bottleNo);
	}
	
	@Override
	@Transactional
	public void editTag(int i, List<BottleTag> tagList) {
		
		dao.deleteBottleTag(i);
		dao.insertBottleTag(tagList);
		
	}
	@Override
	public void insertBottleTag(List<BottleTag> tagList) {
		dao.insertBottleTag(tagList);
		
	}

	@Override
	public List<Tag> getAbvs() {

		dao.setAbvTag();
	}


}
