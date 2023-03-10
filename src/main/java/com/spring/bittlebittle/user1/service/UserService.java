package com.spring.bittlebittle.user1.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.spring.bittlebittle.user1.dao.UserDao;
import com.spring.bittlebittle.user1.vo.User;
import com.spring.bittlebittle.utils1.ServiceInterface;
import com.spring.bittlebittle.utils1.UserDetails;
import com.spring.bittlebittle.utils1.UserRepository;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class UserService implements ServiceInterface {

    private Logger log = LogManager.getLogger("case3");
    
   
    
    @Autowired
    private UserDao userDao;
    
    

	@Override
	public boolean checkPassword(String userName, String userPwd) {
		User user = userDao.getUserByUsername(userName);
		if (user == null) {
            return false;
        } else {
            return BCrypt.checkpw(userPwd, user.getUserPwd());
        }

	}

	@Override
    public boolean checkUserExists(String username) {
        User user = userDao.getUserByUsername(username);
        return user != null;
    }

    @Override
    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers(User user) {
        return userDao.getAllUsers(user);
    }

    @Override
    public void insertUser(User user) {
        String userPwd = BCrypt.hashpw(user.getUserPwd(), BCrypt.gensalt());
        user.setUserPwd(userPwd);
        userDao.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        String userPwd = BCrypt.hashpw(user.getUserPwd(), BCrypt.gensalt());
        user.setUserPwd(userPwd);
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(String userId) {
        userDao.deleteUser(userId);
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
//    public Object update(Object obj) {
//        return null;
//    }
//
//    @Override
//    public int delete(Object obj) {
//        return 0;
//    }
    
    
}
