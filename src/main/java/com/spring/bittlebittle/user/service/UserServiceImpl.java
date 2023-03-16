package com.spring.bittlebittle.user.service;

import com.spring.bittlebittle.user.dao.UserDao;
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
	private UserDao dao;

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
		// login �떆 �궗�슜�븯�뒗 id 留� 媛�吏�怨� �씪�떒 db 瑜� 遺덈윭�삩 �뮘
		User loginUser = dao.selectLoginUser(user);

		// 留뚯빟 �쑀�� �븘�씠�뵒媛� �씪移� �븯吏� �븡�쑝硫� db �뿉 議고쉶媛� �븞�맆 寃껋씠怨�,
		if (loginUser == null) {
			log.debug("�빐�떦 �븘�씠�뵒�쓽 �쑀��媛� 議댁옱�븯吏� �븡�뒿�땲�떎.");
			return false;
		}

		// 留뚯빟 鍮꾨�踰덊샇媛� �씪移섑븯吏� �븡�뒗�떎硫�
		if (!passwordEncoder.matches(user.getUserPwd(), loginUser.getUserPwd())) {
			log.debug(passwordEncoder.encode(user.getUserPwd()));
			log.debug(user.getUserPwd());
			log.debug(loginUser.getUserPwd());
			log.debug("鍮꾨�踰덊샇媛� �씪移섑븯吏� �븞�뒿�땲�떎.");
			return false;
		}
		log.debug("濡쒓렇�씤�뿉 �꽦怨듯뻽�뒿�땲�떎.");
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
//아래는 tag 관련

	@Override
	public void addUserTags(int userNo, List<Integer> tagNoList) throws Exception {
		
		dao.addUserTags(userNo, tagNoList);

	}

	@Override
	public void deleteUserTags(int userNo, List<Integer> tagNoList) throws Exception {
		dao.deleteUserTags(userNo, tagNoList);
		

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
