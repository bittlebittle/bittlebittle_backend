package com.spring.bittlebittle.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.spring.bittlebittle.user.service.AdminUserService;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.JwtUtil;
import com.spring.bittlebittle.utils.OAuth.service.OAuthService;

@RestController// @Controller + @ResponseBody
@RequestMapping(value = "api/admin", produces = "application/json; charset=utf-8")
public class AdminUserController {
	
	private Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private AdminUserService adminService;
	
	@Autowired
    private OAuthService oService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private Gson gson;
	
	 // 사용자 정보 수정 API
    @PostMapping(value="/set-data", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        int result = adminService.updateUser(user);
        if (result > 0) {
            return ResponseEntity.ok("User information has been updated.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user information.");
        }
    }

    // 사용자 정보 삭제 API
    @PostMapping(value="/deletion/{user_no}", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        int result = adminService.deleteUser(user);
        if (result > 0) {
            return ResponseEntity.ok("User has been deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user.");
        }
    }
    
    //전체조회
    
 // 모든 사용자 정보 조회 API
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = adminService.selectUsers();
        		 return ResponseEntity.ok(users);
    }
    
    //선택조회
    
 // 특정 사용자 정보 조회 API
//    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> getUser(@PathVariable String userId) {
//        User user = new User();
//        user.setUserId(userId);
//        List<User> userList = adminService.selectListbyNo();
//        if (userList != null && userList.size() > 0) {
//            return ResponseEntity.ok(userList.get(0));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    
    //관리자 등록
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<Map<String, String>> registerAdminUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        int result = adminService.adminUser(user);
        if (result > 0) {
            response.put("status", "success");
            response.put("message", "Admin user registered successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Failed to register admin user.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    //정보조회
    
    @GetMapping(value = "/{userNo}")
    public User getUser(@PathVariable int userNo, HttpEntity entity){
        log.debug("�쑀�� 議고쉶");

        // access token �쓽 �쑀�슚�꽦 寃��궗
        String token = jwtUtil.resolveAccessToken(entity);

        if(jwtUtil.validateToken(token, UserJwt.builder()
                                        .userJwtIdx(jwtUtil.resolveRefreshToken(entity))
                                        .build()
                )){
            // �쑀�� �젙蹂� 議고쉶
            User user = adminService.selectListbyNo(User.builder().userNo(userNo).build());
            return user;
        }
        else {
            return null;
        }
    }
    
    //보안 로그인
    @PostMapping(value = "/login")
    public ResponseEntity<Object> loginUserToken(@RequestBody User user) {

        Boolean isLoginValidation = adminService.loginUser(user);
        Map<String, Object> map = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        if(isLoginValidation) {
            String accessToken = jwtUtil.createAccessJwt(user.getUserId());
            String userJwtIdx = jwtUtil.createRefreshJwt(user.getUserId());
            jwtUtil.setHeaderAccessToken(headers, accessToken);
            jwtUtil.setHeaderRefreshToken(headers, userJwtIdx);
            map.put("success", true);
        }
        else {
            map.put("success", false);
        }
        String json = gson.toJson(map);
        return ResponseEntity.ok()
                .headers(headers)
                .body(json);
    }
    //보안해제 로그아웃
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
        UserJwt userJwt = UserJwt.builder()
                .userJwtIdx(userJwtIdx)
                .subject(subject)
                .build();
        Map<String, Object> map = new HashMap<>();
        if(jwtUtil.validateToken(token, userJwt)) {
            if(jwtUtil.removeRefreshJwt(token, userJwt)) {
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        } else {
            map.put("validate", false);
        }
        return ResponseEntity.ok().body(map);
    }
    
    
}
