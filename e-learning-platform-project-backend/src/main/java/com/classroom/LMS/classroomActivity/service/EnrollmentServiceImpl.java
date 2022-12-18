package com.classroom.LMS.classroomActivity.service;

import com.classroom.LMS.Instructor.InstructorRepository;
import com.classroom.LMS.classroom.Classroom;
import com.classroom.LMS.classroom.ClassroomRepository;
import com.classroom.LMS.classroomActivity.entity.Enrollment;
import com.classroom.LMS.classroomActivity.repository.EnrollmentRepository;
import com.classroom.LMS.student.Student;
import com.classroom.LMS.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public Enrollment create(Enrollment enrollment) throws Exception {

        Student student = this.studentRepository.findById(enrollment.getStudent().getId()).get();

        Classroom classroom = this.classroomRepository.findById((enrollment.getClassroom().getId())).get();

        enrollment.setStudent(student);
        enrollment.setClassroom(classroom);
        enrollment.setActive(true);

        student.getEnrollmentRequests().add(enrollment);
        classroom.getEnrollmentRequests().add(enrollment);

        return this.enrollmentRepository.save(enrollment);


    }

    @Override
    public Enrollment discardenrollment(Enrollment enrollment) throws Exception{

        enrollment.setStatus(false);
        enrollment.setActive(false);
        return this.enrollmentRepository.save(enrollment);

    };

    @Override
    public Enrollment edit(Enrollment enrollment) throws Exception {
        return null;
    }
}
