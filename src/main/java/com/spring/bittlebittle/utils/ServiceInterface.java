package com.spring.bittlebittle.utils;

import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.user.vo.User;

import java.util.List;



public interface ServiceInterface {

	boolean checkPassword(String userId, String userPwd);
    boolean checkUserExists(String userId);
    User getUserById(String userId);
    User getUserByUsername(String userName);
    List<User> getAllUsers(User user);
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(String userId);


	
	
	
	
	
	
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
	
	
}
