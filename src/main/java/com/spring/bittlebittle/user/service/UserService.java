package com.spring.bittlebittle.user.service;

import com.spring.bittlebittle.user.dao.JwtTokenUtil;
import com.spring.bittlebittle.user.dao.UserDao;
import com.spring.bittlebittle.user.vo.User;
import com.spring.bittlebittle.user.dao.ApiResponse;
import com.spring.bittlebittle.user.dao.CookieUtil;
import com.spring.bittlebittle.utils.ServiceInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class UserService implements ServiceInterface {

    private Logger log = LogManager.getLogger("case3");
    
    @Autowired
    private UserDao dao;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
	@Override
	public String login(User user, boolean rememberMe) {
		// TODO Auto-generated method stub
		User user1 = dao.getUserById(user.getUserId());
		
		if(user1 == null) {
			return null;
		}
		
		if(!BCrypt.checkpw(user.getUserPwd(),user1.getUserPwd())) {
			return null;
		}
		
		String token = jwtTokenUtil.generateToken(user1.getUserId());
		
		if(rememberMe) {
			jwtTokenUtil.setAutoLoginCookie(token);
		}
		
		return token;
	}
	
	@Override
	public ApiResponse logout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		jwtTokenUtil.removeAutoLoginCookie(response);
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		return ApiResponse.OK;
	}


	@Override
	public String getIdFromToken(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = CookieUtil.getValue(request, JwtTokenUtil.COOKIE_NAME);
		
		if(token == null) {
			return null;
		}
		return jwtTokenUtil.getuserNameFromToken(token);
				
	}


	@Override
	public boolean isAutoLogin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String token = CookieUtil.getValue(request, JwtTokenUtil.COOKIE_NAME);
		
		if(token==null) {
			return false;
		}
		
		return jwtTokenUtil.validateToken(token);
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
