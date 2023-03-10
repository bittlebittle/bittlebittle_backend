package com.spring.bittlebittle.utils;

import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.user.dao.ApiResponse;
import com.spring.bittlebittle.user.vo.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServiceInterface {

//    // selectList
//    List<Object> selectList(Object obj);
//
//    // selectOne
//    Object selectOne(Object obj);
//
//    // insert
//    int insert(Object obj);
//
//    // update
//    Object update(Object obj);
//
//    // delete
//    int delete(Object obj);
	
	String login(User user, boolean rememberMe);
	
	ApiResponse logout(HttpServletRequest request, HttpServletResponse response);
	
	boolean isAutoLogin(HttpServletRequest request);
	
	String getIdFromToken(HttpServletRequest request);

}
