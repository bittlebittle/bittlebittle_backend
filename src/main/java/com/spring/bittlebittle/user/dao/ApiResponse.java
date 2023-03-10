package com.spring.bittlebittle.user.dao;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

	public static final ApiResponse OK = new ApiResponse("OK", "success");
	public static final ApiResponse ERROR_NOT_AUTHENTICATED = new ApiResponse("ERROR_NOT_AUTHENTICATED", "not authenticated");
	public static final ApiResponse ERROR_LOGIN = new ApiResponse("ERROR_LOGIN","incorrect id or password");
	
	private String code;
	private String message;
	private Map<String, Object> data = new HashMap<String, Object>();
	
	public ApiResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public ApiResponse put(String key, Object value) {
		data.put(key,value);
		return this;
	}
	
	
	
}
