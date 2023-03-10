package com.spring.bittlebittle.user1.dao;

import com.spring.bittlebittle.user1.vo.User;
import com.spring.bittlebittle.utils1.DaoInterface;
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
    public User getUserById(String userId) {
        return sqlSession.selectOne("getUserById", userId);
    }

    @Override
    public User getUserByUsername(String userName) {
        return sqlSession.selectOne("getUserByUsername", userName);
    }

    @Override   //???????
    public List<User> getAllUsers(User user) {
        return sqlSession.selectList("getAllUsers", user); 
    }

    @Override
    public void insertUser(User user) {
        sqlSession.insert("insertUser", user);
    }

    @Override
    public void updateUser(User user) {
        sqlSession.update("updateUser", user);
    }

    @Override
    public void deleteUser(String userId) {
        sqlSession.delete("deleteUser", userId);
    }

    
	

//    @Override
//    public List<Object> selectList(Object obj) {
//        return null;
//    }
//
//    @Override
//    public Object selectOne(Object obj) {
//        return null;
//    }
//
//    @Override
//    public int insert(Object obj) {
//        return 0;
//    }
//
//    @Override
//    public int update(Object obj) {
//        return 0;
//    }
//
//    @Override
//    public int delete(Object obj) {
//        return 0;
//    }
}
