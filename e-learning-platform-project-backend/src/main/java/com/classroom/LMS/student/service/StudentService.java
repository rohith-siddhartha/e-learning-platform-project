package com.classroom.LMS.student.service;

import com.classroom.LMS.classroom.Classroom;
import com.classroom.LMS.student.Student;

import java.util.Optional;
import java.util.Set;

public interface StudentService {

    public Student createStudent(Student student) throws Exception;
    public Student updateStudent(Student student) throws Exception;
    public Optional<Student> getStudentById(Long studentId) throws Exception;
    public Student getStudentByUsername(String studentUsername) throws Exception;
    public String deleteStudentById(Long studentId) throws Exception;

    Set<Classroom> getClassroomsByStudentId(Long studentId) throws Exception;
}
