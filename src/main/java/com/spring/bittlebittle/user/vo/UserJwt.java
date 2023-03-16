package com.spring.bittlebittle.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class UserJwt {

    private String userJwtIdx;
    private String subject;
    private int userNo;
    private String refreshToken;
    
	public UserJwt() {
		super();
	}

	public UserJwt(String userJwtIdx, String subject, int userNo, String refreshToken) {
		super();
		this.userJwtIdx = userJwtIdx;
		this.subject = subject;
		this.userNo = userNo;
		this.refreshToken = refreshToken;
	}

	public String getUserJwtIdx() {
		return userJwtIdx;
	}

	public void setUserJwtIdx(String userJwtIdx) {
		this.userJwtIdx = userJwtIdx;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
    
	
    
    
    

}
