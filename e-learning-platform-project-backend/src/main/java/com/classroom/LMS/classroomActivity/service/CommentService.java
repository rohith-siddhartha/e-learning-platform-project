package com.classroom.LMS.classroomActivity.service;

import com.classroom.LMS.classroomActivity.entity.Comment;
import com.classroom.LMS.classroomActivity.entity.Notice;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    public Comment create(Comment comment) throws Exception;
    public Comment delete(Long commentId) throws Exception;

}
