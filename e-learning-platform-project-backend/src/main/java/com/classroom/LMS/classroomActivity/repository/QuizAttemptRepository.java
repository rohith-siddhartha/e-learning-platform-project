package com.classroom.LMS.classroomActivity.repository;

import com.classroom.LMS.classroomActivity.entity.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt,Long>{
}
