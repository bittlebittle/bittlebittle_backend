package com.spring.bittlebittle.utils1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.spring.bittlebittle.user1.vo.User;

@Mapper
@Repository
public interface DaoInterface {

	User getUserById(String userId);
    User getUserByUsername(String userName);
    List<User> getAllUsers(User user);
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(String userId);

	
	
}
