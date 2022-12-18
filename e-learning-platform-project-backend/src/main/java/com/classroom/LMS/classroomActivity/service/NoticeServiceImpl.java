package com.classroom.LMS.classroomActivity.service;

import com.classroom.LMS.Instructor.Instructor;
import com.classroom.LMS.classroom.Classroom;
import com.classroom.LMS.classroom.ClassroomRepository;
import com.classroom.LMS.classroomActivity.entity.Comment;
import com.classroom.LMS.classroomActivity.entity.Notice;
import com.classroom.LMS.classroomActivity.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository, ClassroomRepository classroomRepository) {
        this.noticeRepository = noticeRepository;
        this.classroomRepository = classroomRepository;
    }

    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public NoticeServiceImpl() {
    }

    @Override
    public Notice createNotice(Notice notice) throws Exception {

        if(notice.getTitle()== null || notice.getTitle().trim() == ""){
            throw new Exception("title should not be empty");
        }


        Long classroomId = notice.getClassroom().getId();

        Optional<Classroom> classroom = this.classroomRepository.findById(classroomId);

        notice.setClassroom(classroom.get());

        classroom.get().getNotices().add(notice);

        return this.noticeRepository.save(notice);
    }

    @Override
    public Notice editNotice(Notice notice) throws Exception {
        return this.noticeRepository.save(notice);
    }

    @Override
    public Optional<Notice> getNoticeById(Long noticeId) throws Exception {
        return this.noticeRepository.findById(noticeId);
    }

    @Override
    public String deleteNoticeById(Long noticeId) throws Exception {
        this.noticeRepository.deleteById(noticeId);
        return "deleted";
    }

    @Override
    public Set<Comment> getCommentsByNoticeId(Long noticeId) throws Exception {
        return this.noticeRepository.findById(noticeId).get().getComments();
    }

}
