package com.spring.bittlebittle.user.service;

import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;

import java.util.List;


public interface UserService {

    // selectList
    List<User> getUsers();

    // selectOne
    User getUser(User user);

    Boolean loginUser(User user);
    // insert
    int registerUser(User user);

    // update
    User editUser(User user);

    // delete
    int removeUser(User user);

    // Boolean registerJwtWithIdx(UserAuthentication userAuthentication);

    UserJwt getUserJwt(UserJwt userJwt);

    UserJwt createUserJwt(UserJwt userJwt);

    UserJwt editUserJwt(UserJwt build);

    UserJwt getUserJwtBySubject(UserJwt build);

    int removeUserJwt(UserJwt userJwt);
}
