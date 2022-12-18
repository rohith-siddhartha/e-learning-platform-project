package com.classroom.LMS.classroomActivity.service;

import com.classroom.LMS.classroomActivity.entity.Comment;
import com.classroom.LMS.classroomActivity.entity.Notice;
import com.classroom.LMS.classroomActivity.repository.CommentRepository;
import com.classroom.LMS.classroomActivity.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    public CommentServiceImpl(CommentRepository commentRepository, NoticeRepository noticeRepository) {
        this.commentRepository = commentRepository;
        this.noticeRepository = noticeRepository;
    }

    @Override
    public Comment create(Comment comment) throws Exception {

        Notice notice = this.noticeRepository.findById(comment.getNotice().getId()).get();

        Comment parent = null;

        if(comment.getParentId() != null){
            parent = this.commentRepository.findById(comment.getParentId()).get();
        }

        comment.setNotice(notice);

        Comment savedComment = this.commentRepository.save(comment);

        notice.getComments().add(savedComment);

        if(comment.getParentId() != null){
            parent.getReplies().add(savedComment);
            this.commentRepository.save(parent);
        }

        this.noticeRepository.save(notice);

        return savedComment;
    }

    @Override
    public Comment delete(Long commentId) throws Exception {
        return null;
    }
}
