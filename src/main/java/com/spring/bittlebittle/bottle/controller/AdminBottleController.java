package com.spring.bittlebittle.bottle.controller;

import com.spring.bittlebittle.bottle.service.BottleService;
import com.spring.bittlebittle.bottle.vo.Bottle;
import com.spring.bittlebittle.bottle.vo.BottleInfo;
import com.spring.bittlebittle.review.service.ReviewService;
import com.spring.bittlebittle.tag.service.TagService;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.ImageUploadUtil;
import com.spring.bittlebittle.utils.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private ImageUploadUtil imageUploadUtil;

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
	public List<Bottle> addBottle(@ModelAttribute BottleInfo bottle,
								  @RequestParam("imgUrlOrigin") MultipartFile upfile, HttpServletRequest request) throws MalformedURLException {

		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {

			// 전달된 파일이 있을 경우
			// 1. 파일명 수정 => yyyymmddhhmmssxxxxx.확장자
			// 2. 서버로 업로드
			// 3. 원본명, 서버에 업로드된 수정명, 경로를 db 로 insert
			if (!upfile.getOriginalFilename().equals("")) {

				// saveFile 메소드로 위의 코드를 따로 정의함
				// 필요한 인자로는 업로드한 파일과 webapp 경로를 찾기 위한 request, image/food||bottle 을 구분하는 텍스트
				String changeName = imageUploadUtil.saveFile(upfile, request, "bottle");
				bottle.setImgUrl(upfile.getOriginalFilename());
				bottle.setImgCusUrl(changeName);
			}
			// Service 단으로 b 를 넘겨서 insert 요청
			// 넘어온 첨부파일이 있을 경우 b : 제목, 내용, 작성자, 원본파일명, 수정파일명
			// 넘어온 첨부파일이 없을 경우 b : 제목, 내용, 작성자
			List<Bottle> bottleList = bservice.addBottle(bottle);

			// 리스트 or 새로운 bottle
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
	public Map<String, Object> editBottle(@ModelAttribute BottleInfo editBottle,
										  MultipartFile reupfile,
										  HttpServletRequest request) throws MalformedURLException {
		
		
		String token = jwtUtil.resolveAccessToken(request);
		String refreshTokenIdx = jwtUtil.resolveRefreshToken(request);
		log.debug(token);
		log.debug(refreshTokenIdx);
		if (jwtUtil.validateToken(token, UserJwt.builder()
				.userJwtIdx(refreshTokenIdx)
				.build())) {

			// 만약 업로드된 이미지파일이 존재한다면
			if (!reupfile.getOriginalFilename().equals("")) {

				// 넘어온 기존의 bottleInfo 에서 bottleNo 를 가지고 와서, db의 bottle img 를 가지고 온다.
//				Map<String, Object> originBottleInfo = bservice.getBottle(editBottle.getBottleNo());


				// 만약 들어온 reupfile 과 기존의 db 파일이름이 동일하지 않으면! 저장
//				if (!((Bottle) originBottleInfo.get("bottle")).getImgCusUrl().equals(reupfile.getOriginalFilename())) {
				if (!reupfile.getOriginalFilename().equals(editBottle.getImgCusUrl()) ) {


					// 기존 파일 삭제
					// String filePath = request.getServletContext().getRealPath("/resources/static/image/" + "bottle" + File.separator + ((Bottle) originBottleInfo.get("bottle")).getImgCusUrl());
					String filePath = request.getServletContext().getRealPath("/resources/static/image/" + "bottle" + File.separator + editBottle.getImgCusUrl());

					log.debug(filePath);
					File file = new File(filePath);
					if (file.exists() && file.isFile()) {
						file.delete();
					}

					// 새로운 파일을 저장
					String changeName = imageUploadUtil.saveFile(reupfile, request, "bottle");
					editBottle.setImgUrl(reupfile.getOriginalFilename());
					editBottle.setImgCusUrl(changeName);

					Map<String, Object> map = bservice.editBottle(editBottle);
					return map;
				} else {
					Map<String, Object> map = bservice.editBottle(editBottle);
					return map;
				}

			} else {
				// 만약 reupfile 이 없다면 기존파일만 삭제
				String filePath = request.getServletContext().getRealPath("/resources/static/image/" + "bottle" + File.separator + editBottle.getImgCusUrl());

				log.debug(filePath);
				File file = new File(filePath);
				if (file.exists() && file.isFile()) {
					file.delete();
				}

				editBottle.setImgUrl("");
				editBottle.setImgCusUrl("");

				Map<String, Object> map = bservice.editBottle(editBottle);
				return map;
			}
		} else {
			// 토큰이 유효하지 않다면,
			Map<String, Object> map = new HashMap<>();
			map.put("msg", "error");
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
