package com.spring.bittlebittle.user.service;

import com.spring.bittlebittle.user.dao.UserDao;
import com.spring.bittlebittle.utils.DaoInterface;
import com.spring.bittlebittle.utils.ServiceInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements ServiceInterface {

    private Logger log = LogManager.getLogger("case3");
    @Autowired
    @Qualifier(value = "userDao")
    private DaoInterface dao;


    @Override
    public List<Object> selectList(Object obj) {
        return dao.selectList(obj);
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
    @Transactional
    public Object update(Object user) {
        // 수정하고 싶은 vo 값을 넘기고
        int result = dao.update(user);
        dao.selectOne(user);
        return null;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }
}
