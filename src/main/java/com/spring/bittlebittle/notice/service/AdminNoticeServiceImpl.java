package com.spring.bittlebittle.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bittlebittle.notice.dao.AdminNoticeDaoImpl;
import com.spring.bittlebittle.notice.vo.Notice;

@Service
@Transactional
public class AdminNoticeServiceImpl implements AdminNoticeService {

	@Autowired
	private AdminNoticeDaoImpl adminNoticeDaoImpl;

	@Override
	public List<Notice> getNoticeList() {
		return adminNoticeDaoImpl.getNoticeList();
	}

	@Override
	public Notice getNotice(int noticeNo) {
		return adminNoticeDaoImpl.getNotice(noticeNo);
	}

	@Override
	public void addNotice(Notice notice) {
		adminNoticeDaoImpl.addNotice(notice);
	}

	@Override
	public void updateNotice(Notice notice) {
		int noticeNo = notice.getNoticeNo();
		int userNo = notice.getUserNo();
	}

	@Override
	public void deleteNotice(int noticeNo, int userNo) {
		adminNoticeDaoImpl.deleteNotice(noticeNo);
	}
}
