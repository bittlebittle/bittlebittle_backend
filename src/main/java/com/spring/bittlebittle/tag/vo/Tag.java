package com.spring.bittlebittle.tag.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Tag {

	private int tagNo;
	private int keyTypeNo;
	private String tagName; 
	
	public Tag() {
		// TODO Auto-generated constructor stub
	}

	public Tag(int tagNo, int keyTypeNo, String tagName) {
		super();
		this.tagNo = tagNo;
		this.keyTypeNo = keyTypeNo;
		this.tagName = tagName;
	}

	public int getTagNo() {
		return tagNo;
	}

	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
	}

	public int getKeyTypeNo() {
		return keyTypeNo;
	}

	public void setKeyTypeNo(int keyTypeNo) {
		this.keyTypeNo = keyTypeNo;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	
	
}
