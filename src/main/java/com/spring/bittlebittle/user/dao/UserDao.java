package com.spring.bittlebittle.user.dao;

import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;

import java.util.List;

public interface UserDao  {

    List<User> selectUsers();

    List<User> selectUsersByKeyword(User user);

    User selectUser(User user);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(User user);

    UserJwt selectUserJwt(UserJwt userJwt);

    int insertJwtWithIdx(UserJwt userJwt);

    int updateUserJwt(UserJwt userJwt);

    UserJwt selectUserJwtBySubject(UserJwt userJwt);

    int deleteUserJwt(UserJwt userJwt);
}
