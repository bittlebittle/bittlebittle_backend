package com.spring.bittlebittle.user.dao;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	
	public static final String COOKIE_NAME = "autologin";
	private static final String SECRET_KEY = "jwt_secret_key";
	private static final long EXPIRATION_TIME = 86400000;
	
	public String generateToken(String subject) {
		return Jwts.builder()
				.setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
		
		
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public String getuserNameFromToken(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
				.getBody().getSubject();
		
	}
	
//	public static Cookie createCookie(String name, String value, int maxAge) {
//		Cookie cookie = new Cookie(name, value);
//		cookie.setMaxAge(maxAge);
//		cookie.setPath("/");
//		cookie.setHttpOnly(true);
//		return cookie;
//	}
	
	
	
	public static void setAutoLoginCookie(String token) {
		CookieUtil.createCookie(COOKIE_NAME, token, (int)EXPIRATION_TIME/1000);
	}
	
	public static void removeAutoLoginCookie(HttpServletResponse response) {
	CookieUtil.createCookie(COOKIE_NAME,"",0);
	response.addCookie(CookieUtil.createCookie(COOKIE_NAME,"",0)); 
	}
	
}
