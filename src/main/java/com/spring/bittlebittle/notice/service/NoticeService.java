package com.spring.bittlebittle.notice.service;

import java.util.List;

import com.spring.bittlebittle.notice.vo.Notice;

public interface NoticeService {
    List<Notice> getNoticeList();
    Notice getNotice(int noticeNo);
}
// Create
// Read
// Update
// Delete