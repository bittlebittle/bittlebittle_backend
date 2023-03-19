package com.spring.bittlebittle.utils1;

import java.util.List;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;


public class CheckAuthor {
	
	
	public boolean checkAuthorization(String token, String url) {
		  try {
		    // JWT 토큰을 검증한다.
		    Claims claims = Jwts.parser().setSigningKey("mySecretKey").parseClaimsJws(token).getBody();
		    // 권한 정보를 조회한다.
		    List<String> authorities = claims.get("authorities", List.class);
		    // 요청 URL에 대한 권한이 있는지 확인한다.
		    return authorities.contains(url);
		  } catch (SignatureException e) {
		    // 검증이 실패하면 권한이 없는 것으로 처리한다.
		    return false;
		  }
		}

	

}

/*
위 코드에서는 검증된 JWT 토큰에서 권한 정보를 조회하고, 요청 URL에 대한 권한이 있는지 확인합니다. 
이를 이용하여, 인증과 권한부여를 수행할 수 있습니다.
*/