package com.spring.bittlebittle.bottle.service;

import com.spring.bittlebittle.bottle.dao.BottleDao;
import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.favorite.dao.FavoriteDao;
import com.spring.bittlebittle.favorite.vo.Favorite;
import com.spring.bittlebittle.food.dao.FoodDao;
import com.spring.bittlebittle.reply.dao.ReplyDao;
import com.spring.bittlebittle.review.dao.ReviewDao;
import com.spring.bittlebittle.tag.dao.TagDao;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BottleServiceImpl implements BottleService {

	@Autowired
	private BottleDao bdao;
	@Autowired
	private FoodDao fdao;
	@Autowired
	private ReviewDao rdao;
	@Autowired
	private ReplyDao rpdao;
	@Autowired
	private TagDao tdao;
	@Autowired
	private FavoriteDao fvdao;


	// 전체 리스트
    @Override
    public Map<String, Object> getBottles(Map<String, String> param) {

		int userNo = 1;
		Favorite favorite = new Favorite();
		favorite.setUserNo(userNo);

		List<Favorite> favoriteList = fvdao.selectOne(favorite);
		List<Bottle> allBottles = bdao.selectAllBottles(param);
		List<TagType> tagTypeList = tdao.selectAllTagType();
		List<Tag> tagList = tdao.selectAllTag();

		Map<String, Object> map = new HashMap<>();
		map.put("bottle", allBottles);
		map.put("TagType", tagTypeList);
		map.put("Tag", tagList);
		map.put("Favorite", favoriteList);

		return map;
    }
	// 메인 리스트
//	@Override
//	public List<Bottle> getMainBottles( ) {
//		return bdao.selectMainList();
//
//	}
	// New 리스트
	@Override
	public List<Bottle> getNewBottles() {
		return bdao.selectNewList();
	}

	// Best 리스트
	@Override
	public List<Bottle> getBestBottles() {
		return bdao.selectBestList();
	}

	// 찜하기 관련 리스트
	@Override
	public List<Bottle> getRelatedFavorite() {
		return bdao.selectRelatedFavoriteList();
	}


	// 키워드 검색
//	@Override
//	public List<Bottle> getSearchBottleList(String keyword) {
//		return dao.selectSearchBottlesList(keyword);
//	}


	@Override
	@Transactional
	public Bottle getBottle(int bottleNo) {
    
    bdao.editViewCnt(bottleNo);
		return bdao.selectOne(bottleNo);
	}
	
	@Override
	public List<Bottle> getRelatedBottleList(int bottleNo) {
		
		return bdao.selectRelatedBottleList(bottleNo);
	}

	@Override
	@Transactional
	public int addBottle(Bottle newBottle) {
	
		return bdao.insertOne(newBottle);
	}
	
	@Override
	@Transactional
	public Bottle editBottle(Bottle updateBottle) {
		
		bdao.updateOne(updateBottle);
		Bottle bottle = bdao.selectOne(updateBottle.getBottleNo());
		
		return bottle;
	}

}
