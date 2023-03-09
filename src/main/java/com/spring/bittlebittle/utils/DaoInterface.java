package com.spring.bittlebittle.utils;

import java.util.List;

import com.spring.bittlebittle.user.vo.User;

public interface DaoInterface {

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
//    int update(Object obj);
//
//    // delete
//    int delete(Object obj);
	
	
	User getUserById(String userId);
	
	User getUserByPwd(int userPwd);
	
	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);
	
	
}
