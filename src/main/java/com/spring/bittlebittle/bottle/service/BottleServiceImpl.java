package com.spring.bittlebittle.bottle.service;


import com.spring.bittlebittle.bottle.dao.BottleDao;
import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.bottle.vo.BottleInfo;
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
import com.spring.bittlebittle.tag.vo.TagType;
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

	// 전체 리스트
    @Override
    public Map<String, Object> getBottles(Map<String, String> param) {


		String sorted = param.get("sorted");
		String orderby = "";
		switch (sorted) {
			case "new" : orderby="b.create_date desc, b.bottle_no_pk desc"; break;
			case "best" : orderby= "order by grade desc, b.create_date desc;"; break;
			case "relatedFavorite" : orderby="ORDER BY b.bottle_no_pk desc;"; break;
			default: new Exception("잘못된 입력값");
		}

		param.put("orderby", orderby);

		int userNo = 1;
		Favorite favorite = new Favorite();
		favorite.setUserNo(userNo);

		List<Favorite> favoriteList = fvdao.selectList(favorite);
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
		
		Map<String, Object> map = new HashMap<>();
		map.put("bottle", bottle);
		map.put("tagListByBottle", tagListByBottle);
		
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
		
		return null;

	}
	
	@Override
	@Transactional
	public Map<String, Object> editBottle(BottleInfo editBottle) {
		
		int bottleNo = editBottle.getBottleNo();
		
		bdao.updateOne(editBottle);
		
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
		return null;
	}

}
