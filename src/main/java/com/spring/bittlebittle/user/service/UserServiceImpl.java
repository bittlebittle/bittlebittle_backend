package com.spring.bittlebittle.user.service;

import com.spring.bittlebittle.user.dao.UserDaoImpl;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private Logger log = LogManager.getLogger("case3");
    @Autowired
    private UserDaoImpl dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${secretKey}")
    private String key;

    @Override
    public List<User> getUsers() {
        return dao.selectUsers();
    }

    @Override
    public User getUser(User user) {
        return dao.selectUser(user);
    }

    @Override
    public Boolean loginUser(User user) {
        // login 시 사용하는 id 만 가지고 일단 db 를 불러온 뒤
        User loginUser = dao.selectLoginUser(user);

        // 만약 유저 아이디가 일치 하지 않으면 db 에 조회가 안될 것이고,
        if(loginUser == null) {
            log.debug("해당 아이디의 유저가 존재하지 않습니다.");
            return false;
        }

        // 만약 비밀번호가 일치하지 않는다면
        if(!passwordEncoder.matches(user.getUserPwd(), loginUser.getUserPwd())) {
            log.debug(passwordEncoder.encode(user.getUserPwd()));
            log.debug(user.getUserPwd());
            log.debug(loginUser.getUserPwd());
            log.debug("비밀번호가 일치하지 안습니다.");
            return false;
        }
        log.debug("로그인에 성공했습니다.");
        return true;
    }

    @Override
    public int registerUser(User user) {
        user.setUserPwd(passwordEncoder.encode(user.getUserPwd()));
        return 0;
    }

    @Override
    public User editUser(User user) {
        return null;
    }

    @Override
    public int removeUser(User user) {
        return 0;
    }


    @Override
    @Transactional
    public UserJwt createUserJwt(UserJwt userJwt) {
        userJwt.setUserJwtIdx(passwordEncoder.encode(userJwt.getSubject()+key));
        dao.insertJwtWithIdx(userJwt);
        return dao.selectUserJwt(userJwt);
    }

    @Override
    public UserJwt getUserJwt(UserJwt userJwt) {
        return dao.selectUserJwt(userJwt);
    }

    @Override
    public UserJwt getUserJwtBySubject(UserJwt userJwt) {
        return dao.selectUserJwtBySubject(userJwt);
    }

    @Override
    @Transactional
    public UserJwt editUserJwt(UserJwt userJwt) {
        dao.updateUserJwt(userJwt);
        return dao.selectUserJwt(userJwt);
    }

    @Override
    public int removeUserJwt(UserJwt userJwt) {
        return dao.deleteUserJwt(userJwt);
    }
}
