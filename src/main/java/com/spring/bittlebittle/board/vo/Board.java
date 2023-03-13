package com.spring.bittlebittle.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

	private int boardNo;
	private int userNo;
	private String boardTitle;
	private String createDate;
	private String boardContent;
	private String status;
	
	
}
