package com.classroom.LMS.classroomActivity.repository;

import com.classroom.LMS.classroomActivity.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
}
