package com.spring.bittlebittle.board.vo;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class BoardReply {
	
	private int replyNo;
	private int userNo;
	private String nickname;
//	private int reviewNo;
	private String createDate;
	
	
	public BoardReply() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public BoardReply(int replyNo, int userNo, String createDate) {
		super();
		this.replyNo = replyNo;
		this.userNo = userNo;
		this.createDate = createDate;
	}




	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
//	public int getReviewNo() {
//		return reviewNo;
//	}
//	public void setReviewNo(int reviewNo) {
//		this.reviewNo = reviewNo;
//	}
	public String getCreatDate() {
		return createDate;
	}
	public void setCreatDate(String creatDate) {
		this.createDate = creatDate;
	}

	
	
	
	
	
}
