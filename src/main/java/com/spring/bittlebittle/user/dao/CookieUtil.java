package com.spring.bittlebittle.user.dao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	public static final String AUTO_LOGIN_COOKIE_NAME = "autologin";
	private static final int COOKIE_MAX_AGE = 30*24*60*60; // 30days
	
	public static String getValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(cookieName)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	public static void setAutoLoginCookie(HttpServletResponse response, String token, boolean rememberMe) {
		if(rememberMe) {
			Cookie cookie = new Cookie(AUTO_LOGIN_COOKIE_NAME,"");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}

//	public static Cookie createCookie(String cookieName, String token, int i) {
//		return null;
//		
//		// TODO Auto-generated method stub ??????
//		
//	}
	
	public static Cookie createCookie(String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		return cookie;
	}
	
	
}
