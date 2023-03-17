package com.spring.bittlebittle.user.service;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bittlebittle.user.dao.UserDao;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.utils.ServiceInterface;

import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;

import java.util.List;

public interface UserService {

	// selectList
	List<User> getUsers();

	// selectOne
	User getUser(User user);

	User selectUser(User user);

	Boolean loginUser(User user);

	// insert
	int registerUser(User user);

	// update
	int updateUser(User user);

	// delete
	int deleteUser(User user);

	// Boolean registerJwtWithIdx(UserAuthentication userAuthentication);

	UserJwt getUserJwt(UserJwt userJwt);

	UserJwt createUserJwt(UserJwt userJwt);

	UserJwt editUserJwt(UserJwt build);

	UserJwt getUserJwtBySubject(UserJwt build);

	int removeUserJwt(UserJwt userJwt);

//////////////////////
// 아래는 tag 관련

	void addUserTags(int userNo, List<Integer> tagNoList) throws Exception;

	void deleteUserTags(int userNo, List<Integer> tagNoList) throws Exception;

	//이메일인증, 아이디중복확인
	
	boolean isUsernameDuplicate(String userId);

	boolean sendEmailAuth(String email);

}
