package com.classroom.LMS.classroomActivity.service;

import com.classroom.LMS.classroomActivity.entity.Comment;
import com.classroom.LMS.classroomActivity.entity.Notice;
import com.classroom.LMS.student.Student;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public interface NoticeService {

    public Notice createNotice(Notice notice) throws Exception;
    public Notice editNotice(Notice notice) throws Exception;
    public Optional<Notice> getNoticeById(Long noticeId) throws Exception;
    public String deleteNoticeById(Long noticeId) throws Exception;

    Set<Comment> getCommentsByNoticeId(Long noticeId) throws Exception;
}
