package com.spring.bittlebittle.user.dao;

import com.spring.bittlebittle.tag.vo.BottleTag;
import com.spring.bittlebittle.tag.vo.Tag;
import com.spring.bittlebittle.tag.vo.TagType;
import com.spring.bittlebittle.user.vo.User;

import java.util.List;

public interface UserDao  {

    List<User> selectUsers();

    List<User> selectUsersByKeyword(User user);

    User selectUser(User user);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(User user);

}
