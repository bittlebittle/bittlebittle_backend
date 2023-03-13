package com.spring.bittlebittle.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardReply {
	
	private int replyNo;
	private int userNo;
	private int reviewNo;
	private String creatDate;

}
