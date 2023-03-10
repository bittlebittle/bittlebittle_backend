package com.spring.bittlebittle.tag.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;

@Repository
public class TagDaoImpl implements TagDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<TagType> selectAllTagType() {
		
		return sqlSession.selectList("tagMapper.selectAllTagType");
	}
	
	@Override
	public List<Tag> selectAllTag() {
		
		return sqlSession.selectList("tagMapper.selectAllTag");
	}
	
	@Override
	public List<Tag> selectTag(int bottleNo) {
		
		return sqlSession.selectList("tagMapper.selectTag", bottleNo);
	}
	
	@Override
	public void deleteBottleTag(int i) {
		
		sqlSession.delete("tagMapper.deleteBottleTag", i);
	}
	
	@Override
	public void insertBottleTag(List<Tag> tagList) {
		
		sqlSession.delete("tagMapper.insertBottleTag", tagList);
		
	}
}