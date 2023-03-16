package com.spring.bittlebittle.user.dao;

import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.vo.UserJwt;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return sqlSession.selectOne("userMapper.selectListbyNo", user);
	}

	public User selectLoginUser(User user) {
		return sqlSession.selectOne("userMapper.selectLoginUser", user);
	}

	@Override
	public int registerUser(User user) {
		return sqlSession.insert("userMapper.registerUser", user);

	}

	@Override
	public int updateUser(User user) {
		return sqlSession.update("userMapper.updateUser", user);
	}

	@Override
	public int deleteUser(User user) {
		return sqlSession.update("userMapper.deleteUser", user);
	}

	@Override
	public UserJwt selectUserJwt(UserJwt userJwt) {
		return sqlSession.selectOne("userMapper.selectUserJwt", userJwt);
	}

	@Override
	public UserJwt selectUserJwtBySubject(UserJwt userJwt) {
		return sqlSession.selectOne("userMapper.selectUserJwtBySubject", userJwt);
	}

	public int insertJwtWithIdx(UserJwt userJwt) {
		return sqlSession.insert("userMapper.insertJwtWithIdx", userJwt);
	}

	@Override
	public int updateUserJwt(UserJwt userJwt) {
		return sqlSession.update("userMapper.updateUserJwtWithIdx", userJwt);
	}

	@Override
	public int deleteUserJwt(UserJwt userJwt) {
		return sqlSession.delete("userMapper.deleteUserJwtWithUserJwtIdx", userJwt);
	}

//////////////////////
// 아래는 tag 관련

	@Override
	public void addUserTags(int userNo, List<Integer> tagNoList) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userNo", userNo);
		paramMap.put("tagNoList", tagNoList);
		sqlSession.insert("tagMapper.addUserTags", paramMap);

	}

	@Override
	public void deleteUserTags(int userNo, List<Integer> tagNoList) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("userNo", userNo);
	    paramMap.put("tagNoList", tagNoList);
	    sqlSession.delete("tagMapper.deleteUserTags", paramMap);
	}

//	@Override
//	public String getTagName(int tagNo) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}

	/*
	 * 
	 * @Override public User getUserById(String userId) { return
	 * sqlSession.selectOne("getUserById", userId); }
	 * 
	 * @Override public User getUserByUsername(String userName) { return
	 * sqlSession.selectOne("getUserByUsername", userName); }
	 * 
	 * @Override //??????? public List<User> getAllUsers(User user) { return
	 * sqlSession.selectList("getAllUsers", user); }
	 * 
	 * @Override public void insertUser(User user) { sqlSession.insert("insertUser",
	 * user); }
	 * 
	 * @Override public void updateUser(User user) { sqlSession.update("updateUser",
	 * user); }
	 * 
	 * @Override public void deleteUser(String userId) {
	 * sqlSession.delete("deleteUser", userId); }
	 * 
	 */
}
