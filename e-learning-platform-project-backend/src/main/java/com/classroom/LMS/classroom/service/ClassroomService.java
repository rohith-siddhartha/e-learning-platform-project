package com.classroom.LMS.classroom.service;

import com.classroom.LMS.classroom.Classroom;
import com.classroom.LMS.classroomActivity.entity.Enrollment;
import com.classroom.LMS.classroomActivity.entity.Notice;
import com.classroom.LMS.classroomActivity.entity.Quiz;
import com.classroom.LMS.student.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public interface ClassroomService {

    public Classroom create(Classroom classroom) throws Exception;
    public Classroom findById(Long classroomId) throws Exception;
    public String deleteById(Long classroomId) throws Exception;

    Set<Notice> findNoticesByClassroomId(Long classroomId);

    Set<Quiz> findQuizzesByClassroomId(Long classroomId);

    List<Classroom> findAll();

    Set<Enrollment> findEnrollmentRequestsByClassroomId(Long classroomId);

    Enrollment enrolStudent(Enrollment enrollment) throws Exception;

    Set<Student> findStudentsByClassroomId(Long classroomId) throws Exception;

    Classroom removeStudent(Long classroomId, Long studentId) throws Exception;
}
