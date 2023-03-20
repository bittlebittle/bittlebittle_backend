package com.spring.bittlebittle.bottle.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bittlebittle.bottle.service.BottleService;
import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.bottle.vo.BottleInfo;
import com.spring.bittlebittle.reply.vo.Reply;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.tag.service.TagService;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.JwtUtil;

@RestController
@RequestMapping(value="/api/admin/bottles", produces="application/json; charset=UTF-8")
public class AdminBottleController {

	@Autowired
	private BottleService bservice;
	@Autowired
	private ReviewService rservice;
	@Autowired
	private TagService tservice;
	@Autowired
	private JwtUtil jwtUtil;
	
	Logger log = LogManager.getLogger("case3");
	
	// 조회 (확인완료)
	@GetMapping(value="/{bottleNo}")
	public Map<String, Object> getBottle(@PathVariable int bottleNo) {
		
		Map<String, Object> map = bservice.getBottleByAdmin(bottleNo);
		
		
		return map;
	}
	
//	// 추가창 들어갈때(확인완료)
//	@GetMapping(value="/addition")
//	public Map<String, Object> getTags() {
//		
//		// 태그 선택할 수 있도록 태그 선택지 불러오기
//		List<TagType> tagTypeList = tservice.getAllTagTypes();
//		List<Tag> tagList = tservice.getAllTags();
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("tagTypeList", tagTypeList);
//		map.put("tagList", tagList);
//		
//		return map;
//	}
	
	// 추가 완료할 때(확인완료)
	@PostMapping
	public List<Bottle> addBottle(@ModelAttribute BottleInfo bottle, HttpServletRequest request) {
		
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {
			
			
			List<Bottle> bottleList = bservice.addBottle(bottle);
			
			return bottleList;
			
		} else {
			
			Map<String, Object> map = bservice.getBottles(null);
			
			List<Bottle> bottleList = (List<Bottle>) map.get("bottle");
			
			return bottleList;
		}
		
		
	}
	

	
//	// 수정창 들어갈때 
//	@GetMapping(value="/{bottleNo}/set-data")
//	public Map<String, Object> getBottleInfo(@PathVariable int bottleNo) {
//		
//		
//		Bottle bottle = bservice.getBottleByAdmin(bottleNo);
//		List<TagType> tagTypeList= tservice.getAllTagTypes();
//		List<Tag> bottleTagList = tservice.getTagsByBottle(bottleNo);
//		
//		// 태그 리스트도 보내야돼~
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("bottle", bottle);
//		map.put("tagTypeList", tagTypeList);
//		map.put("bottleTagList", bottleTagList);
//		
//		return map;
//	}
//	
	// 수정완료 (확인완료)
	@PostMapping(value="/set-data")
	public Map<String, Object> editBottle(@ModelAttribute BottleInfo editBottle, HttpServletRequest request) {
		
		
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {
			
			Map<String, Object> map = bservice.editBottle(editBottle);
			
			// 수정된 bottle정보, tag
			return map;
			
		} else {
			
			Map<String, Object> map = bservice.getBottle(editBottle.getBottleNo());
			
			return map;
		}
		
		
		
	}
	
	// 삭제 (확인완료)
	@GetMapping(value="/{bottleNo}/deletion")
	public List<Bottle> removeBottle(@PathVariable int bottleNo){
		
		
		List<Bottle> bottleList = bservice.removeBottle(bottleNo);
		
		// 원래 없애야 하지만 DB 보존을 위해..
		// tservice.removeBottleTag(bottleNo);
		
		return bottleList;
	}
}
