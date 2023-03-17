package com.spring.bittlebittle.user.service;

import com.spring.bittlebittle.user.dao.UserDao;
import com.spring.bittlebittle.user.dao.UserDaoImpl;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
    private JavaMailSender javaMailSender;

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
		// login 占쎈뻻 占쎄텢占쎌뒠占쎈릭占쎈뮉 id 筌랃옙 揶쏉옙筌욑옙�⑨옙 占쎌뵬占쎈뼊 db �몴占� �겫�뜄�쑎占쎌궔 占쎈츟
		User loginUser = dao.selectLoginUser(user);

		// 筌띾슣鍮� 占쎌�占쏙옙 占쎈툡占쎌뵠占쎈탵揶쏉옙 占쎌뵬燁삼옙 占쎈릭筌욑옙 占쎈륫占쎌몵筌롳옙 db 占쎈퓠 鈺곌퀬�돳揶쏉옙 占쎈툧占쎈쭍 野껉퍔�뵠�⑨옙,
		if (loginUser == null) {
			log.debug("占쎈퉸占쎈뼣 占쎈툡占쎌뵠占쎈탵占쎌벥 占쎌�占쏙옙揶쏉옙 鈺곕똻�삺占쎈릭筌욑옙 占쎈륫占쎈뮸占쎈빍占쎈뼄.");
			return false;
		}

		// 筌띾슣鍮� �뜮袁⑨옙甕곕뜇�깈揶쏉옙 占쎌뵬燁살꼹釉�筌욑옙 占쎈륫占쎈뮉占쎈뼄筌롳옙
		if (!passwordEncoder.matches(user.getUserPwd(), loginUser.getUserPwd())) {
			log.debug(passwordEncoder.encode(user.getUserPwd()));
			log.debug(user.getUserPwd());
			log.debug(loginUser.getUserPwd());
			log.debug("�뜮袁⑨옙甕곕뜇�깈揶쏉옙 占쎌뵬燁살꼹釉�筌욑옙 占쎈툧占쎈뮸占쎈빍占쎈뼄.");
			return false;
		}
		log.debug("嚥≪뮄�젃占쎌뵥占쎈퓠 占쎄쉐�⑤벏六쏙옙�뮸占쎈빍占쎈뼄.");
		return true;
	}

	@Override
	public int registerUser(User user) {
		user.setUserPwd(passwordEncoder.encode(user.getUserPwd()));
		return dao.registerUser(user);

	}

	@Override
	public int updateUser(User user) {
		return dao.updateUser(user);
	}

	@Override
	public int deleteUser(User user) {
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

	@Override
	public User selectUser(User user) {
		return dao.selectUser(user);
	}

//////////////////////
//�븘�옒�뒗 tag 愿��젴

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
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("이메일 인증 코드");
//        message.setText("인증 코드: 123456"); // 실제로는 무작위로 생성한 인증 코드를 사용해야 합니다.
        
        Random random = new Random();
        int authCode = random.nextInt(900000) + 100000; // 100000부터 999999까지의 랜덤한 6자리 수 생성
        message.setText("인증 코드: " + authCode);

        try {
            javaMailSender.send(message);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }
    }
	

	/*
	 * 
	 * @Override public boolean checkPassword(String userName, String userPwd) {
	 * User user = userDao.getUserByUsername(userName); if (user == null) { return
	 * false; } else { return BCrypt.checkpw(userPwd, user.getUserPwd()); }
	 * 
	 * }
	 * 
	 * @Override public boolean checkUserExists(String username) { User user =
	 * userDao.getUserByUsername(username); return user != null; }
	 * 
	 * @Override public User getUserById(String userId) { return
	 * userDao.getUserById(userId); }
	 * 
	 * @Override public User getUserByUsername(String username) { return
	 * userDao.getUserByUsername(username); }
	 * 
	 * @Override public List<User> getAllUsers(User user) { return
	 * userDao.getAllUsers(user); }
	 * 
	 * @Override public void insertUser(User user) { String userPwd =
	 * BCrypt.hashpw(user.getUserPwd(), BCrypt.gensalt()); user.setUserPwd(userPwd);
	 * userDao.insertUser(user); }
	 * 
	 * @Override public void updateUser(User user) { String userPwd =
	 * BCrypt.hashpw(user.getUserPwd(), BCrypt.gensalt()); user.setUserPwd(userPwd);
	 * userDao.updateUser(user); }
	 * 
	 * @Override public void deleteUser(String userId) { userDao.deleteUser(userId);
	 * }
	 * 
	 */
}
