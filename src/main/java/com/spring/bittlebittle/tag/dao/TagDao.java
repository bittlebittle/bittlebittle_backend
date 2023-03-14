package com.spring.bittlebittle.tag.dao;

import com.spring.bittlebittle.tag.vo.BottleTag;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;

import java.util.List;

public interface TagDao {

	List<TagType> selectAllTagType();

	List<Tag> selectAllTag();

	List<Tag> selectTag(int bottleNo);

	void deleteBottleTag(int i);

	void insertBottleTag(List<BottleTag> tagList);

}
