package com.classroom.LMS.classroomActivity.repository;

import com.classroom.LMS.classroomActivity.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
