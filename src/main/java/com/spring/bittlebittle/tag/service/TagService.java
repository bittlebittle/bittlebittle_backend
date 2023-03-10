package com.spring.bittlebittle.tag.service;

import java.util.List;

import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;

public interface TagService {

	List<TagType> getAllTagType();

	List<Tag> getAllTag();

	List<Tag> getTag(int bottleNo);

	void updateTag(int i, List<Tag> taglist);

}
