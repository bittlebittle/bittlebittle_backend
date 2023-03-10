package com.spring.bittlebittle.user.dao;

import com.spring.bittlebittle.user.vo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    private Logger log = LogManager.getLogger("case3");
    @Autowired
    private SqlSession sqlSession;

    public List<User> selectList() {
        return sqlSession.selectList("userMapper.selectList");
    }


}
