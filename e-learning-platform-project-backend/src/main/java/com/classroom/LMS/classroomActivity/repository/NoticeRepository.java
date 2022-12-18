package com.classroom.LMS.classroomActivity.repository;

import com.classroom.LMS.classroomActivity.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
