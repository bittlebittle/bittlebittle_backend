package com.spring.bittlebittle.tag.service;

import java.util.List;

import com.spring.bittlebittle.tag.vo.BottleTag;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;

public interface TagService {

	List<TagType> getAllTagTypes();

	List<Tag> getAllTags();

	List<Tag> getTags(int bottleNo);

	void editTag(int i, List<BottleTag> taglist);

	void insertBottleTag(List<BottleTag> tagList);

}
