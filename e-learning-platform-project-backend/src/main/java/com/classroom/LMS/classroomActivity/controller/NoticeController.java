package com.classroom.LMS.classroomActivity.controller;

import com.classroom.LMS.classroomActivity.entity.Comment;
import com.classroom.LMS.classroomActivity.entity.Notice;
import com.classroom.LMS.classroomActivity.service.NoticeService;
import com.classroom.LMS.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PostMapping("create")
    public Notice createNotice(@RequestBody Notice notice) throws Exception{
        return this.noticeService.createNotice(notice);
    };

    @PutMapping("edit")
    public Notice updateNotice(@RequestBody Notice notice) throws Exception{
        return this.noticeService.editNotice(notice);
    }

    @GetMapping("{noticeId}")
    public Optional<Notice> getNoticeById(@PathVariable Long noticeId) throws Exception{
        return this.noticeService.getNoticeById(noticeId);
    }

    @DeleteMapping("delete/{noticeId}")
    public String deleteNoticeById(@PathVariable Long noticeId) throws Exception{
        return this.noticeService.deleteNoticeById(noticeId);
    }

    @GetMapping("{noticeId}/comments")
    public Set<Comment> getCommentsByNoticeId(@PathVariable Long noticeId) throws Exception{
        return this.noticeService.getCommentsByNoticeId(noticeId);
    }
}
