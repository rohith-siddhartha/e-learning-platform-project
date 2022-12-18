package com.classroom.LMS.classroomActivity.service;

import com.classroom.LMS.classroomActivity.entity.Enrollment;
import org.springframework.stereotype.Service;

@Service
public interface EnrollmentService {

    public Enrollment create(Enrollment enrollment) throws Exception;
    public Enrollment edit(Enrollment enrollment) throws Exception;

    Enrollment discardenrollment(Enrollment enrollment) throws Exception;
}
