package com.spring.bittlebittle.user.service;

import com.spring.bittlebittle.user.dao.UserDaoImpl;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;
import com.spring.bittlebittle.utils.JwtUtil;
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
    private JwtUtil jwtUtil;

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
    public User loginUser(User user) {
        // login 시 사용하는 id 만 가지고 일단 db 를 불러온 뒤
        User loginUser = dao.selectLoginUser(user);

        // 만약 유저 아이디가 일치 하지 않으면 db 에 조회가 안될 것이고,
        if (loginUser == null) {
            log.debug("해당 아이디의 유저가 존재하지 않습니다.");
            return null;
        }

        // 만약 비밀번호가 일치하지 않는다면
        if (!passwordEncoder.matches(user.getUserPwd(), loginUser.getUserPwd())) {
            log.debug("유저가 로그인 창에 입력한 비밀번호를 인코딩한 값 : " + passwordEncoder.encode(user.getUserPwd()));
            log.debug("유저가 로그인 창에 입력한 비밀번호 원본 값 : " + user.getUserPwd());
            log.debug("실제 db에 암호화되서 저장된 비밀번호 값 : " + loginUser.getUserPwd());
            log.debug("비밀번호가 일치하지 않습니다.");
            return null;
        }
        log.debug("로그인에 성공했습니다.");
        return loginUser;
    }

    @Override
    public int registerUser(User user) {
        user.setUserPwd(passwordEncoder.encode(user.getUserPwd()));
        return dao.insertUser(user);
    }

    @Override
    @Transactional
    public User editUser(User user) {
        user.setUserPwd(passwordEncoder.encode(user.getUserPwd()));
        dao.updateUser(user);
        return dao.selectUser(user);
    }

    @Override
    public int removeUser(User user) {
        return dao.deleteUser(user);
    }


    @Override
    @Transactional
    public UserJwt createUserJwt(UserJwt userJwt) {
        userJwt.setUserJwtIdx(passwordEncoder.encode(userJwt.getSubject() + key));
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

//
//	@Override
//    public boolean checkUserExists(String username) {
//        User user = dao.getUserByUsername(username);
//        return user != null;
//    }
//
//    @Override
//    public User getUserById(String userId) {
//        return dao.getUserById(userId);
//    }
//
//    @Override
//    public User getUserByUsername(String username) {
//        return dao.getUserByUsername(username);
//    }
//
//    @Override
//    public List<User> getAllUsers(User user) {
//        return dao.getAllUsers(user);
//    }

//	@Override
//	public User selectUser(User user) {
//		return dao.selectUser(user);
//	}
//

	@Override
	public void addUserTags(int userNo, List<Integer> tagNoList) throws Exception {
		
		dao.addUserTags(userNo, tagNoList);

	}

	@Override
	public void deleteUserTags(int userNo, List<Integer> tagNoList) throws Exception {
		dao.deleteUserTags(userNo, tagNoList);

	}

	//아이디 중복확인, 이메일인증
	@Override
	public boolean isUsernameDuplicate(String userId) {
		User user = dao.findByUserId(userId);
        return user != null;
	}

    @Override
    public boolean sendEmailAuth(String email) {
        return false;
    }

//	@Override
//	public boolean sendEmailAuth(String email) {
//		SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("이메일 인증 코드");
////        message.setText("인증 코드: 123456"); // 실제로는 무작위로 생성한 인증 코드를 사용해야 합니다.
//
//        Random random = new Random();
//        int authCode = random.nextInt(900000) + 100000; // 100000부터 999999까지의 랜덤한 6자리 수 생성
//        message.setText("인증 코드: " + authCode);
//
//        try {
//            javaMailSender.send(message);
//            return true;
//        } catch (MailException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
	

}
