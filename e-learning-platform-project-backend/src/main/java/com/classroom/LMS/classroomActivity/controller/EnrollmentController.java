package com.classroom.LMS.classroomActivity.controller;

import com.classroom.LMS.classroomActivity.entity.Enrollment;
import com.classroom.LMS.classroomActivity.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollment")
@CrossOrigin("*")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("create")
    public Enrollment create(@RequestBody Enrollment enrollment) throws Exception {
        return this.enrollmentService.create(enrollment);
    }

    @PostMapping("discardenrollment")
    public Enrollment discardenrollment(@RequestBody Enrollment enrollment) throws Exception{
        return this.enrollmentService.discardenrollment(enrollment);
    }

}
