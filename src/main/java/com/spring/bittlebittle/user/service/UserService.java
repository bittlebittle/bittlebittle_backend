package com.spring.bittlebittle.user.service;

import com.spring.bittlebittle.user.vo.User;

import java.util.List;


public interface UserService {

    // selectList
    List<User> selectList();

    // selectOne
    User selectOne(User user);

    // insert
    int insert(User user);

    // update
    Object update(User user);

    // delete
    int delete(User user);

}
