package com.bittlebittle.user.dao;

import com.bittlebittle.user.vo.User;
import com.bittlebittle.utils.DaoInterface;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao implements DaoInterface {

    private Logger log = LogManager.getLogger("case3");
    @Autowired
    private SqlSession sqlSession;

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
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }
}
