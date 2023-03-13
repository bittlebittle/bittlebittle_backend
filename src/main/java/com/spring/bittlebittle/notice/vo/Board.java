package com.spring.bittlebittle.notice.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
//	`NOTICE_NO`	INT	NOT NULL	AUTO_INCREMENT PRIMARY KEY,
//	`USER_NO_FK`	INT	NOT NULL,
//	`NOTICE_TITLE`	VARCHAR(100)	NOT NULL,
//	`NOTICE_CONTENT`	VARCHAR(3000)	NOT NULL,
//	`CREATE_DATE`	DATETIME	NOT NULL	DEFAULT NOW(),
//	`STATUS`	VARCHAR(1)	DEFAULT 'Y' CHECK(STATUS IN ('Y', 'N')) NULL

	private int noitceNo;
	private int userNo;
	private String noticeTitle;
	private String noticeContent;
	private String createDate;
	private String status;
}
