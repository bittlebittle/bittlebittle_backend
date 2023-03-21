package com.spring.bittlebittle.food.controller;

import com.spring.bittlebittle.food.service.FoodService;
import com.spring.bittlebittle.food.vo.Food;
import com.spring.bittlebittle.food.vo.FoodInfo;
import com.spring.bittlebittle.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/api/admin/foods", produces="application/json; charset=UTF-8")
public class AdminFoodController {

	@Autowired
	private FoodService fservice;
	private TagService tservice;
	
	// 조회 (확인완료)
	@GetMapping
	public List<Food> getAllFoods(){
		
		List<Food> foodList = fservice.getAllFoods();
		
		return foodList;
	}
	
	
	// 작성 (확인완료)
	@PostMapping
	public Map<String, Object> addFood(FoodInfo newFood, MultipartFile upfile, HttpServletRequest request){
		// foodInfo / imgfile 을 받아올 수 있도록
		// axios 에서 데이터를 전송할 떄, content-type="multipart/form-data" 의 형태로 전송해주어야 한다.

		// 전달된 파일이 있을 경우
		// 1. 파일명 수정 => yyyymmddhhmmssxxxxx.확장자
		// 2. 서버로 업로드
		// 3. 원본명, 서버에 업로드된 수정명, 경로를 db 로 insert
		if(!upfile.getOriginalFilename().equals("")) {

			// saveFile 메소드로 위의 코드를 따로 정의함
			// 필요한 인자로는 업로드한 파일과 webapp 경로를 찾기 위한 request, image/food||bottle 을 구분하는 텍스트
			String changeName = saveFile(upfile, request, "food");
			newFood.setImgUrl(upfile.getOriginalFilename());
			newFood.setImgCusUrl("resources/image/food/" + changeName);
		}
		// Service 단으로 b 를 넘겨서 insert 요청
		// 넘어온 첨부파일이 있을 경우 b : 제목, 내용, 작성자, 원본파일명, 수정파일명
		// 넘어온 첨부파일이 없을 경우 b : 제목, 내용, 작성자
		Map<String, Object> map = fservice.addFood(newFood);
		return map;
	}
	
	// 개별창 들어갔을 때 (확인완료)
	@GetMapping(value="/{foodNo}")
	public Map<String, Object> getFood(@PathVariable int foodNo) {
		
		
		Map<String, Object> map = fservice.getFood(foodNo);
		
		return map;
	}
	
	// 수정 완료할 때  (확인완료)
	@PostMapping(value="/set-data")
	public Map<String, Object> editFood(@RequestBody FoodInfo editFood){
		
		Map<String, Object> map= fservice.editFood(editFood);
		
		// 수정한 음식정보, 태그
		return map;
	}
	
	// 삭제 (확인완료)
	@GetMapping(value="/{foodNo}/deletion")
	public List<Food> removeFood(@PathVariable int foodNo){
		
		List<Food> foodList = fservice.removeFood(foodNo);
		
		return foodList;
	}

	// 일반 메소드

	// * 스프링에서 반드시 요청을 처리하는 메소드만 Controller 에 들어가라는 법은 없다!!
	// 현재 넘어온 첨부파일의 이름을 수정 후 서버의 그 폴더에 저장하는 메소드
	// => url 요청을 처리하는 메소드가 아닌 일반 메소드로 만듬
	public String saveFile(MultipartFile upfile, HttpServletRequest request, String path) {

		// 파일명 수정 후 업로드 시키기
		// 예) "flower.png" => "2022061511020112345.png"
		// 1. 원본파일명 가져오기
		String originName = upfile.getOriginalFilename(); // "flower.png"

		// 2. 시간 형식을 문자열로 뽑아내기
		// "20220615110201" (년월일시분초) => SimpleDateFormat
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		// 3. 뒤에 붙을 5자리 랜덤값 뽑기
		// "13253"
		int ranNum = (int)(Math.random() * 90000 + 10000);

		// 4. 원본파일명으로부터 확장자 부분만 추출
		// ".png"
		String ext = originName.substring(originName.lastIndexOf("."));

		// 5. 모두 이어 붙이기
		String changeName = currentTime + ranNum + ext;

		// 6. 업로드 하고자 하는 물리적인 경로 알아내기
		String savePath = request.getServletContext().getRealPath("/resources/image/"+path+"/");

		// 7. 경로와 수정파일명을 합체시킨 후 저장
		try {
			upfile.transferTo(new File(savePath + changeName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		return changeName;
	}
}
