package com.spring.bittlebittle.tag.dao;

import java.util.List;

import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;

public interface TagDao {

	List<TagType> selectAllTagType();

	List<Tag> selectAllTag();

	List<Tag> selectTag(int bottleNo);

	void deleteBottleTag(int i);

	void insertBottleTag(List<Tag> tagList);

}
