package com.spring.bittlebittle.user.service;

import com.spring.bittlebittle.user.dao.UserDao;
import com.spring.bittlebittle.utils.ServiceInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements ServiceInterface {

    private Logger log = LogManager.getLogger("case3");
    @Autowired
    private UserDao dao;

    @Override
    public List<Object> selectList(Object obj) {
        return null;
    }

    @Override
    public Object selectOne(Object obj) {
        return null;
    }

    @Override
    public int insert(Object obj) {
        return 0;
    }

    @Override
    public Object update(Object obj) {
        return null;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }
}
