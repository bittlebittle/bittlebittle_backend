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
	 private String replyContent;
	 private String status;
	
	
	
	public BoardReply() {
		// TODO Auto-generated constructor stub
	}
	

	

	public BoardReply(int replyNo, int userNo, String nickname, String createDate, String replyContent, String status) {
		super();
		this.replyNo = replyNo;
		this.userNo = userNo;
		this.nickname = nickname;
		this.createDate = createDate;
		this.replyContent = replyContent;
		this.status = status;
	}

	



	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getReplyContent() {
		return replyContent;
	}


	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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
	public Object getCreatDate() {
		return createDate;
	}
	public void setCreatDate(String creatDate) {
		this.createDate = creatDate;
	}

	
	
	
	
	
}
