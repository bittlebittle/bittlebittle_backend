package com.spring.bittlebittle.user.controller;


import com.google.gson.Gson;
import com.spring.bittlebittle.user.service.UserService;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.JwtUtil;
import com.spring.bittlebittle.utils.OAuth.service.OAuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.spring.bittlebittle.user.service.UserService;
import com.spring.bittlebittle.user.vo.User;


@RestController // @Controller + @ResponseBody
@RequestMapping(value = "api/users", produces = "application/json; charset=utf-8")
public class UserController {

	private Logger log = LogManager.getLogger("case3");
	@Autowired
	private UserService service;
	@Autowired
	private OAuthService oService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private Gson gson;

	// 회원가입
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public void registerUser(@RequestBody User user) {
		service.registerUser(user);
	}

//    @GetMapping()
//    public List<User> getUsers(){
//        log.debug("user �쟾泥� 議고쉶");
//        return service.getUsers();
//    }
	// 회원개인정보조회
//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public User selectUser(User user) {
//        log.debug("user 전체 조회");
//        return service.selectUser(user);
//    }

	// 정보삭제(탈퇴)
	@PostMapping("/{user_no}/deletion, produces = \"application/json; charset=utf-8")
	public ResponseEntity<String> deleteUser(@RequestBody User user) {
		int result = service.deleteUser(user);
		if (result > 0) {
			return ResponseEntity.ok("User has been deleted.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user.");
		}
	}

	// 회원정보수정
	@PostMapping("/set-data, produces = \"application/json; charset=utf-8")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		int result = service.updateUser(user);
		if (result > 0) {
			return ResponseEntity.ok("User information has been updated.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user information.");
		}
	}

//    @GetMapping(value = "/accounts/auth/{socialLoginType}")
//    public void socialLoginRedirect(@PathVariable(value = "socialLoginType") String socialLoginPath) throws IOException {
//        SocialLoginType socialLoginType = SocialLoginType.valueOf(socialLoginPath.toUpperCase());
//        oService.request(socialLoginType);
//    }

	@GetMapping(value = "/{userNo}")
	public User getUser(@PathVariable int userNo, HttpEntity entity) {
		log.debug("�쑀�� 議고쉶");

		// access token �쓽 �쑀�슚�꽦 寃��궗
		String token = jwtUtil.resolveAccessToken(entity);

		if (jwtUtil.validateToken(token, UserJwt.builder().userJwtIdx(jwtUtil.resolveRefreshToken(entity)).build())) {
			// �쑀�� �젙蹂� 議고쉶
			User user = service.getUser(User.builder().userNo(userNo).build());
			return user;
		} else {
			return null;
		}
	}

	// 보안 로그인
	@PostMapping(value = "/login")
	public ResponseEntity<Object> loginUserToken(@RequestBody User user) {

		Boolean isLoginValidation = service.loginUser(user);
		Map<String, Object> map = new HashMap<>();
		HttpHeaders headers = new HttpHeaders();
		if (isLoginValidation) {
			String accessToken = jwtUtil.createAccessJwt(user.getUserId());
			String userJwtIdx = jwtUtil.createRefreshJwt(user.getUserId());
			jwtUtil.setHeaderAccessToken(headers, accessToken);
			jwtUtil.setHeaderRefreshToken(headers, userJwtIdx);
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		String json = gson.toJson(map);
		return ResponseEntity.ok().headers(headers).body(json);
	}

	// 보안해제 로그아웃
	@PostMapping(value = "/logout")
	public ResponseEntity<Object> logoutUser(HttpEntity entity) {
		log.debug("濡쒓렇 �븘�썐");

		// access token header �뿉�꽌 異붿텧
		String token = jwtUtil.resolveAccessToken(entity);
		String subject = jwtUtil.getSubject(token);
		String userJwtIdx = jwtUtil.resolveRefreshToken(entity);

		// access token �씠 留뚮즺�릺�뿀�떎硫�?
		// subject 瑜� 媛��졇�삱 �닔 �뾾寃� �맂�떎.
		// 洹몃윭硫� 洹몃깷 idx 瑜� 湲곕컲�쑝濡� 議고쉶�빐�꽌 �궘�젣�빐以��떎.
		UserJwt userJwt = UserJwt.builder().userJwtIdx(userJwtIdx).subject(subject).build();
		Map<String, Object> map = new HashMap<>();
		if (jwtUtil.validateToken(token, userJwt)) {
			if (jwtUtil.removeRefreshJwt(token, userJwt)) {
				map.put("success", true);
			} else {
				map.put("success", false);
			}
		} else {
			map.put("validate", false);
		}
		return ResponseEntity.ok().body(map);
	}

	public Object update(User user) {
		return null;
	}
	
	
//////////////////////
//아래는 tag 관련
	
	@PostMapping(value = "/tagadd", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addUserTags(@RequestParam int userNo, @RequestBody List<Integer> tagNoList) throws Exception {
        service.addUserTags(userNo, tagNoList);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/tagdelete/{userNo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUserTags(@PathVariable int userNo, @RequestBody List<Integer> tagNoList) throws Exception {
        service.deleteUserTags(userNo, tagNoList);
        return ResponseEntity.ok().build();
    }
	
//	 @PostMapping(value="/tagadd", produces = MediaType.APPLICATION_JSON_VALUE)
//	 public void addUserTags(@RequestParam int userNo, @RequestBody List<Integer> tagNoList) throws Exception {
//	          service.addUserTags(userNo, tagNoList);
//	    }
//	 
//	 @DeleteMapping(value = "/tagdelete/{userNo}", produces = MediaType.APPLICATION_JSON_VALUE)
//	    public void deleteUserTags(@PathVariable int userNo, @RequestBody List<Integer> tagNoList) throws Exception {
//	        service.deleteUserTags(userNo, tagNoList);
//	    }
    
    
    //이메일인증, 아이디 중복확인
    
    @PostMapping(value="/check-duplicate" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkDuplicate(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        boolean isDuplicate = service.isUsernameDuplicate(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("isDuplicate", isDuplicate);
        return ResponseEntity.ok(response);
    }
	
    @PostMapping(value="/send-email-auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendEmailAuth(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        boolean success = service.sendEmailAuth(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", success);
        return ResponseEntity.ok(response);
    }
	
	
	
	

	/*
	 * 
	 * @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public Map<String, Object> createUser(@RequestBody User user) {
	 * Map<String, Object> response = new HashMap<>(); if
	 * (userService.checkUserExists(user.getUserName())) { response.put("status",
	 * "error"); response.put("message", "User already exists"); } else {
	 * userService.insertUser(user); response.put("status", "success");
	 * response.put("message", "User created successfully"); } return response; }
	 * 
	 * @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public List<User> getAllUsers(User user) { List<User> users =
	 * userService.getAllUsers(user); return users; }
	 * 
	 * @GetMapping(value = "/users/{id}", produces =
	 * MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public Map<String, Object> getUserById(@PathVariable String
	 * userId) { Map<String, Object> response = new HashMap<>(); User user =
	 * userService.getUserById(userId); if (user == null) { response.put("status",
	 * "error"); response.put("message", "User not found"); } else {
	 * response.put("status", "success"); response.put("user", user); } return
	 * response; }
	 * 
	 * @PutMapping(value = "/users/{id}", produces =
	 * MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public Map<String, Object> updateUser(@PathVariable String
	 * userId, @RequestBody User user) { Map<String, Object> response = new
	 * HashMap<>(); User existingUser = userService.getUserById(userId); if
	 * (existingUser == null) { response.put("status", "error");
	 * response.put("message", "User not found"); } else { user.setUserId(userId);
	 * userService.updateUser(user); response.put("status", "success");
	 * response.put("message", "User updated successfully"); } return response; }
	 * 
	 * @DeleteMapping(value = "/users/{id}", produces =
	 * MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public Map<String, Object> deleteUser(@PathVariable String
	 * userId) { Map<String, Object> response = new HashMap<>(); User existingUser =
	 * userService.getUserById(userId); if (existingUser == null) {
	 * response.put("status", "error"); response.put("message", "User not found"); }
	 * else { userService.deleteUser(userId); response.put("status", "success");
	 * response.put("message", "User deleted successfully"); } return response; }
	 * 
	 */
}
