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
        return sqlSession.selectOne("userMapper.selectOne", user);
    }

    public User selectLoginUser(User user) {
        return sqlSession.selectOne("userMapper.selectLoginUser", user);
    }

    @Override
    public int insertUser(User user) {
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

	@Override
	public User findByUserId(String userId) {
		return sqlSession.selectOne("userMapper.findByUserId", userId);
	}


}
