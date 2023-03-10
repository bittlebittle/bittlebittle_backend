package com.spring.bittlebittle.user.dao;

import com.spring.bittlebittle.user.vo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private Logger log = LogManager.getLogger("case3");
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<User> selectUsers() {
        return sqlSession.selectList("userMapper.selectList");
    }

    @Override
    public List<User> selectUsersByKeyword(User user) {
        return null;
    }

    @Override
    public User selectUser(User user) {
        return null;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int deleteUser(User user) {
        return 0;
    }
}
