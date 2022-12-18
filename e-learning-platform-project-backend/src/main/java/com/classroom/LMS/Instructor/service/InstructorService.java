package com.classroom.LMS.Instructor.service;

import com.classroom.LMS.Instructor.Instructor;
import com.classroom.LMS.classroom.Classroom;

import java.util.Optional;
import java.util.Set;

public interface InstructorService {

    public Instructor create(Instructor instructor) throws Exception;
    public Instructor update(Instructor instructor) throws Exception;
    public Optional<Instructor> findById(Long instructorId) throws Exception;
    public Instructor findByUsername(String username) throws Exception;
    public String deleteById(Long instructorId) throws Exception;
    public Set<Classroom> getClassroomsById(Long id);
}
