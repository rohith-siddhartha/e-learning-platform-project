package com.classroom.LMS.Instructor;

import com.classroom.LMS.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {

    public Instructor findByUsername(String username);

}
