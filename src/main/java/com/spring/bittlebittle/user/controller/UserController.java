package com.spring.bittlebittle.user.controller;

import com.spring.bittlebittle.user.dao.ApiResponse;
import com.spring.bittlebittle.user.service.UserService;
import com.spring.bittlebittle.user.vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private Logger log = LogManager.getLogger("case3");

    @Autowired
    private UserService service;

//    @PostMapping(value = "/login")
//    public void post(@ModelAttribute User user) {
//        service.insert(user);
//    }
//
//    @GetMapping(value = "api/users")
//    public void get2(){
//        System.out.println(112111);
//        log.debug("dddd");
//    }
//
//    @RequestMapping(value = "start")
//    public String get4() {
//        System.out.println(222);
//        return "start";
//    }
    
    @PostMapping("/login")
    public ApiResponse login(@RequestBody User user, @RequestParam(value="rememberMe", required=false) boolean rememberMe, HttpServletRequest request, HttpServletResponse response) {
    	String token = service.login(user, rememberMe);
    	
    	if(token == null) {
    		return ApiResponse.ERROR_LOGIN;
    	}
    	return ApiResponse.OK.put("token",token);
    }
    
    @GetMapping("/me")
    public ApiResponse me(HttpServletRequest request) {
    	String id = service.getIdFromToken(request);
    	
    	if(id == null) {
    		return ApiResponse.ERROR_NOT_AUTHENTICATED;
    	}
    	return ApiResponse.OK.put("id", id);
    }
    
    @PostMapping("/logout")
    public ApiResponse logout(HttpServletRequest request, HttpServletResponse response) {
    	return service.logout(request, response);
    }
    
}
