package com.spring.bittlebittle.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	
	private int replyNo;
	private int userNo;
	private int reviewNo;
	private String creatDate;
	
	public Reply() {
		// TODO Auto-generated constructor stub
	}

	public Reply(int replyNo, int userNo, int reviewNo, String creatDate) {
		super();
		this.replyNo = replyNo;
		this.userNo = userNo;
		this.reviewNo = reviewNo;
		this.creatDate = creatDate;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}
	
	
	
	
	
	

}
