package com.spring.bittlebittle.user.service;

import com.spring.bittlebittle.user.dao.UserDaoImpl;
import com.spring.bittlebittle.user.vo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private Logger log = LogManager.getLogger("case3");
    @Autowired
    private UserDaoImpl dao;

    @Override
    public List<User> selectList() {
        return dao.selectUsers();
    }

    @Override
    public User selectOne(User user) {
        return null;
    }

    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public Object update(User user) {
        return null;
    }

    @Override
    public int delete(User user) {
        return 0;
    }
}
