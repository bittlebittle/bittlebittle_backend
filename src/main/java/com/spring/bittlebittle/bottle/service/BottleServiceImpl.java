package com.spring.bittlebittle.bottle.service;

import com.spring.bittlebittle.bottle.dao.BottleDao;
import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.bottle.vo.BottleInfo;
import com.spring.bittlebittle.bottle.vo.BottleSearch;
import com.spring.bittlebittle.favorite.dao.FavoriteDao;
import com.spring.bittlebittle.favorite.vo.Favorite;
import com.spring.bittlebittle.food.dao.FoodDao;
import com.spring.bittlebittle.food.vo.Food;
import com.spring.bittlebittle.reply.dao.ReplyDao;
import com.spring.bittlebittle.review.dao.ReviewDao;
import com.spring.bittlebittle.review.vo.Review;
import com.spring.bittlebittle.tag.dao.TagDao;
import com.spring.bittlebittle.tag.vo.BottleTag;
import com.spring.bittlebittle.tag.vo.Tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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


	Logger log = LogManager.getLogger("case3");
	
	// 전체 리스트
    @Override
    public Map<String, Object> getBottles(BottleSearch bottleSearch) {

		int userNo = 1;
		Favorite favorite = new Favorite();
		favorite.setUserNo(userNo);

		List<Favorite> favoriteList = fvdao.selectList(favorite);
		List<Bottle> allBottles = bdao.selectAllBottles(bottleSearch);

		Map<String, Object> map = new HashMap<>();
		map.put("bottle", allBottles);
		map.put("Favorite", favoriteList);

		return map;
    }
	// 메인 리스트
	@Override
	public Map<String, Object> getMainBottles( ) {

		int userNo = 1;
		Favorite favorite = new Favorite();
		favorite.setUserNo(userNo);

		List<Favorite> favoriteList = fvdao.selectList(favorite);
		List<Bottle> bottleNewList = bdao.selectNewList();
		List<Bottle> bottleBestList = bdao.selectBestList();
		List<Bottle> bottleRelatedeFavoriteList = bdao.selectRelatedFavoriteList();

		Map<String, Object> map = new HashMap<>();
		map.put("newBottle", bottleNewList);
		map.put("bestBottle", bottleBestList);
		map.put("relatedFavorite", bottleRelatedeFavoriteList);
		map.put("favorite", favoriteList);

		return map;
	}

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

	@Override
	@Transactional
	public Map<String, Object> getBottle(int bottleNo) {


		// session 등록되면 넣는걸로
		int userNo = 1;
		
		Favorite favorite = new Favorite(userNo, bottleNo);
		
		Bottle bottle = bdao.selectOne(bottleNo);
		bdao.editViewCnt(bottleNo);
		List<Tag> tagListByBottle = tdao.selectTagByBottle(bottleNo); 
		List<Bottle> relatedBottleList = bdao.selectRelatedBottleList(bottleNo);
		List<Review> reviewList = rdao.selectList(bottleNo);
		List<Food> foodList = fdao.selectRelatedFoods(bottleNo);
		List<Favorite> favoriteList = fvdao.selectList(favorite);
	
		Map<String, Object> map = new HashMap<>();
		map.put("bottle", bottle);
		map.put("tagListByBottle", tagListByBottle);
		map.put("relatedBottleList", relatedBottleList);
		map.put("reviewList", reviewList);
		map.put("foodList",foodList);
		map.put("isFavorite", favoriteList);
		
		return map;	
	}
	
	@Override
	public Map<String, Object> getBottleByAdmin(int bottleNo) {
		
		Bottle bottle = bdao.selectOne(bottleNo);
		List<Tag> tagListByBottle = tdao.selectTagByBottle(bottleNo);
		List<Review> reviewList = rdao.selectList(bottleNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("bottle", bottle);
		map.put("tagListByBottle", tagListByBottle);
		map.put("reviewList", reviewList);
		
		return map;

	}
	
	@Override
	public List<Bottle> getRelatedBottleList(int bottleNo) {
		
		return bdao.selectRelatedBottleList(bottleNo);
	}


	@Override
	@Transactional
	public List<Bottle> addBottle(BottleInfo bottle) {
		
		bdao.insertOne(bottle);
		int bottleNo = bdao.selectLastBottleNo();

		
		List<BottleTag> bottleTagList = new ArrayList();
		for(int tagNo : bottle.getTagNoList()) {
			bottleTagList.add(new BottleTag(tagNo, bottleNo));
		}
		
		tdao.insertBottleTag(bottleTagList);
		
		List<Bottle> bottleList = bdao.selectAllBottles(null);
		
		return bottleList;

	}
	
	@Override
	@Transactional
	public Map<String, Object> editBottle(BottleInfo editBottle) {
		
		int bottleNo = editBottle.getBottleNo();
		
		bdao.updateOne(editBottle);
		log.debug(bottleNo);

		tdao.deleteBottleTag(bottleNo);
	
		List<BottleTag> bottleTagList = new ArrayList();
		for(int tagNo : editBottle.getTagNoList()) {
			bottleTagList.add(new BottleTag(tagNo, bottleNo));
		}
		
		tdao.insertBottleTag(bottleTagList);
		
		Bottle bottle = bdao.selectOne(bottleNo);
		List<Tag> tagListByBottle = tdao.selectTagByBottle(bottleNo);
		
		Map<String, Object> map = new HashMap<>();
		map.put("bottle", bottle);
		map.put("tagListByBottle", tagListByBottle);
		
		return map;
	}
	
	@Override
	public List<Bottle> removeBottle(int bottleNo) {
		
		bdao.deleteOne(bottleNo);
		
		// 완료되면 리스트불러오는 것 추가
		List<Bottle> bottleList = bdao.selectAllBottles(null);
		
		return bottleList;
	}

}
