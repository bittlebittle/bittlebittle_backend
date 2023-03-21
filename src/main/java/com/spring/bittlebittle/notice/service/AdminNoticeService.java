package com.spring.bittlebittle.notice.service;

import java.util.List;

import com.spring.bittlebittle.notice.vo.Notice;

public interface AdminNoticeService {
	List<Notice> getNoticeList();

	Notice getNotice(int noticeNo);

	void addNotice(Notice notice);

	void updateNotice(Notice notice);

	void deleteNotice(int noticeNo, int userNo);
}
